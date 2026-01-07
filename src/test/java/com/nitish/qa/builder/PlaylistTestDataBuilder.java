package com.nitish.qa.builder;

import com.nitish.qa.pojo.Playlist;

public final class PlaylistTestDataBuilder {

    private PlaylistTestDataBuilder() {}

    public static Playlist create(String name, String description, boolean isPublic) {

        return Playlist.builder()
                .name(name)
                .description(description)
                ._public(isPublic)
                .build();
    }

    public static Playlist defaultPlaylist() {
        return create(
                "Default Playlist",
                "Default Description",
                false
        );
    }

}
