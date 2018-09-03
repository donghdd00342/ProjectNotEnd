package com.donghd.notend.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_LANGUAGE = "en";

    // Friend status
    public static final Integer PENDING = 0;
    public static final Integer ACCEPT = 1;
    public static final Integer BLOCK = -1;
    
    private Constants() {
    }
}
