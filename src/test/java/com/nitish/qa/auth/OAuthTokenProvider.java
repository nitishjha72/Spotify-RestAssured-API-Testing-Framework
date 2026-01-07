package com.nitish.qa.auth;

import com.nitish.qa.config.ConfigFactory;
import com.nitish.qa.http.RestClient;
import java.util.Map;

public final class OAuthTokenProvider {

    private OAuthTokenProvider() {}

    public static String getToken() {

        return TokenCache.getOrFetch(() -> {

            Map<String, String> formParams = Map.of(
                    "grant_type", ConfigFactory.get().getGrantType(),
                    "client_id", ConfigFactory.get().getClientId(),
                    "client_secret", ConfigFactory.get().getClientSecret(),
                    "refresh_token", ConfigFactory.get().getRefreshToken()
            );

            return RestClient.fetchToken(formParams);
        });
    }
}

