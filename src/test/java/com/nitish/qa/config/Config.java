package com.nitish.qa.config;

import com.nitish.qa.constants.ConfigKeys;
import com.nitish.qa.constants.ErrorMessages;

import java.util.Properties;

public final class Config {

    private final String baseUrl;
    private final String accountUrl;
    private final String userId;
    private final String grantType;

    // Secrets (from ENV)
    private final String clientId;
    private final String clientSecret;
    private final String refreshToken;

    public Config(Properties props) {

        this.baseUrl = props.getProperty(ConfigKeys.BASE_URL);
        this.accountUrl = props.getProperty(ConfigKeys.ACCOUNT_URL);
        this.userId = props.getProperty(ConfigKeys.USER_ID);
        this.grantType = props.getProperty(ConfigKeys.GRANT_TYPE);

        //  ENV VARS
        this.clientId = getEnvOrFail("SPOTIFY_CLIENT_ID");
        this.clientSecret = getEnvOrFail("SPOTIFY_CLIENT_SECRET");
        this.refreshToken = getEnvOrFail("SPOTIFY_REFRESH_TOKEN");
    }

    private String getEnvOrFail(String key) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            throw new RuntimeException(ErrorMessages.MISSING_ENV_VARIABLE + key);
        }
        return value;
    }


    // getters
    public String getBaseUrl() { return baseUrl; }
    public String getAccountUrl() { return accountUrl; }
    public String getUserId() { return userId; }
    public String getGrantType() { return grantType; }
    public String getClientId() { return clientId; }
    public String getClientSecret() { return clientSecret; }
    public String getRefreshToken() { return refreshToken; }
}
