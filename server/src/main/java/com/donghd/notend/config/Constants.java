package com.donghd.notend.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_LANGUAGE = "en";

    // gender
    public static final Integer MALE_INTEGER = 1;
    public static final Integer FEMALE_INTEGER = 2;
    public static final Integer UNKNOW_INTEGER = 3;

    // married status
    public static final Integer SINGLE = 1;
    public static final Integer MARRIED = 2;
    public static final Integer DIVORCED = 3;
    public static final Integer WIDOWED = 4;

    // Friend status
    public static final Integer PENDING = 0;
    public static final Integer ACCEPT = 1;
    public static final Integer BLOCK = -1;

    // Message status
    public static final Integer SENT = 0;
    public static final Integer RECEIVED = 1;

    // Util resource
    public static final List<String> LIST_OF_COUNTRIES = createCountries();
    public static final List<String> LIST_OF_LANGUAGES = createLanguage();

    private static List<String> createCountries() {
        List<String> list = new ArrayList();

        list.add("United States");
        list.add("United Kingdom");
        list.add("Finland");
        list.add("France");
        list.add("Germany");
        list.add("Hong Kong");
        list.add("Hungary");
        list.add("India");
        list.add("Indonesia");
        list.add("Italy");
        list.add("Japan");
        list.add("Mexico");
        list.add("New Zealand");
        list.add("Thailand");
        list.add("Turkey");
        list.add("Turkey");
        list.add("Viet Nam");
        list.add("Korea");

        return list;
    }

    private static List<String> createLanguage() {
        List<String> list = new ArrayList();

        list.add("Chinese");
        list.add("English");
        list.add("French");
        list.add("German");
        list.add("Indonesian");
        list.add("Italian");
        list.add("Japanese");
        list.add("Russian");
        list.add("Vietnamese");

        return list;
    }

    // avatar
    public static final List<String> BOY_AVATAR_LIST = createAvatarBoy();
    public static final List<String> GIRL_AVATAR_LIST = createAvatarGirl();

    private static List<String> createAvatarBoy() {
        List<String> list = new ArrayList();

        list.add("avatar/boy/1.jpg");
        list.add("avatar/boy/2.jpg");
        list.add("avatar/boy/3.jpg");
        list.add("avatar/boy/4.jpg");
        list.add("avatar/boy/5.jpg");
        list.add("avatar/boy/6.jpg");
        list.add("avatar/boy/7.jpg");
        list.add("avatar/boy/8.jpg");
        list.add("avatar/boy/9.jpg");
        list.add("avatar/boy/10.jpg");

        return list;
    }

    private static List<String> createAvatarGirl() {
        List<String> list = new ArrayList();

        list.add("avatar/girl/1.jpg");
        list.add("avatar/girl/2.jpg");
        list.add("avatar/girl/3.jpg");
        list.add("avatar/girl/4.jpg");
        list.add("avatar/girl/5.jpg");
        list.add("avatar/girl/6.jpg");
        list.add("avatar/girl/7.jpg");
        list.add("avatar/girl/8.jpg");
        list.add("avatar/girl/9.jpg");
        list.add("avatar/girl/10.jpg");

        return list;
    }
    
    private Constants() {
    }
}
