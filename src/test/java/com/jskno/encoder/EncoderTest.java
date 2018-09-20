//@formatter:off
/**
 *  $$Id$$
 *       . * .
 *     * RRRR  *    Copyright (c) 2018 EUIPO: European Union Intellectual
 *   .   RR  R   .  Property Office (trade marks and designs)
 *   *   RRR     *
 *    .  RR RR  .   ALL RIGHTS RESERVED
 *     * . _ . *
 */
//@formatter:on
package com.jskno.encoder;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Jose on 22/01/18.
 */
public class EncoderTest {


    @Test
    public void encoderTest() {
        String encodedPassword = "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("Jkn-62015"));
    }

    @Test
    public void decoderTest() {
        String password = "Jkn-62015";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode(password));
    }
}
