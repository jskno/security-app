INSERT INTO users (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE) VALUES (1, 'jskno', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, STR_TO_DATE('01-01-2016', '%d-%c-%Y'));
INSERT INTO users (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, STR_TO_DATE('01-01-2016','%d-%c-%Y'));
INSERT INTO users (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, STR_TO_DATE('01-01-2016','%d-%c-%Y'));
INSERT INTO `security`.users (id, email, enabled, firstname, lastname, last_password_reset_date, password, username) VALUES(4, 'admin@admin.com', 1, 'admin', 'admin', '2016-01-01', '$2a$10$A53IxghJOsk3/GkNBQIrA.vvWRaKQDG0ksYe0seWEwQMxpl.3V.7O', 'canogjo');

INSERT INTO authorities (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO authorities (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (3, 1);