package com.nitish.qa.assertions;

import com.nitish.qa.pojo.ErrorRoot;
import com.nitish.qa.pojo.Playlist;

import static com.nitish.qa.utility.TextNormalizer.normalize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public final class PlaylistAssertions {

    private PlaylistAssertions() {}


    public static void assertPlaylistEqual(Playlist expected, Playlist actual) {

        assertThat(normalize(actual.getName()), equalTo(expected.getName()));
        assertThat(normalize(actual.getDescription()), equalTo(expected.getDescription()));
        assertThat(actual.is_public(), equalTo(expected.is_public()));
    }

    public static void assertError(ErrorRoot error, int status, String message) {

        assertThat(error.getErrorDetails().getStatus(), equalTo(status));
        assertThat(error.getErrorDetails().getMessage(), equalTo(message));
    }
}
