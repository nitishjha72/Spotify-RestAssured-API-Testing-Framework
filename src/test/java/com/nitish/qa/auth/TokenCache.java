package com.nitish.qa.auth;

import com.nitish.qa.pojo.Token;

public final class TokenCache {

    private static String token;
    private static long expiryTime;

    private TokenCache() {}

    public static synchronized String getOrFetch(TokenSupplier supplier) {
        if (token == null || System.currentTimeMillis() >= expiryTime) {
            Token tokenObj = supplier.fetch();
            token = tokenObj.getAccessToken();
            expiryTime = System.currentTimeMillis() + tokenObj.getExpiresIn() * 1000;
        }
        return token;
    }
}
