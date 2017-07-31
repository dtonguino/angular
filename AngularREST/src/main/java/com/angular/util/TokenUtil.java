package com.angular.util;

import java.util.UUID;

public class TokenUtil {

    public static String generate() {
        String token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        return token.length() > 50 ? token.substring(0, 50) : token;
    }
}
