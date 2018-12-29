//@formatter:off
/**
 *  $$Id$$
 *       . * .
 *     * RRRR  *    Copyright (c) 2017 EUIPO: European Union Intellectual
 *   .   RR  R   .  Property Office (trade marks and designs)
 *   *   RRR     *
 *    .  RR RR  .   ALL RIGHTS RESERVED
 *     * . _ . *
 */
//@formatter:on
package com.jskno.controller;

import com.jskno.domain.LoginRequest;
import com.jskno.domain.LoginResponse;
import com.jskno.jwt.JwtTokenProvider;
import com.jskno.jwt.JwtUser;
import com.jskno.service.CustomUserDetailsService;
import com.jskno.utils.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jose on 18/11/17.
 */
@RestController
@RequestMapping(value = "${jwt.route.authentication.path}")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse createAuthenticationToken(@RequestBody LoginRequest loginRequest)
            throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String token = SecurityConstants.TOKEN_PREFIX + tokenProvider.generateToken(userDetails);

        // Return the token
        return new LoginResponse(true, token);
    }

    @GetMapping("${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {

        String token = request.getHeader(tokenHeader);
        String username = tokenProvider.getUsernameFromToken(token);
        JwtUser user = (JwtUser) customUserDetailsService.loadUserByUsername(username);

        if(tokenProvider.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokenProvider.refreshToken(token);
            return ResponseEntity.ok(new LoginResponse(true, refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
