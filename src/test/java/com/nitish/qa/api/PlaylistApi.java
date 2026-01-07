package com.nitish.qa.api;

import com.nitish.qa.config.ConfigFactory;
import com.nitish.qa.constants.Endpoints;
import com.nitish.qa.http.RestClient;
import com.nitish.qa.pojo.Playlist;
import io.restassured.response.Response;

public class PlaylistApi {

    public Response create(Playlist playlist) {
        return RestClient.post(
                Endpoints.USERS + "/" + ConfigFactory.get().getUserId() + Endpoints.PLAYLISTS,
                playlist
        );
    }

    public Response get(String playlistId) {
        return RestClient.get(
                Endpoints.PLAYLISTS + "/" + playlistId
        );
    }

    public Response update(String playlistId, Playlist playlist) {
        return RestClient.put(
                Endpoints.PLAYLISTS + "/" + playlistId, playlist
        );
    }

    public Response createWithToken(String token, Playlist playlist) {
        return RestClient.postWithToken(
                Endpoints.USERS + "/" + ConfigFactory.get().getUserId() + Endpoints.PLAYLISTS,
                token,
                playlist
        );
    }


}
