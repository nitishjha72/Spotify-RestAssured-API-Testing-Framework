package com.nitish.qa.tests.playlists;

import com.nitish.qa.api.PlaylistApi;
import com.nitish.qa.builder.PlaylistTestDataBuilder;
import com.nitish.qa.config.TestDataFactory;
import com.nitish.qa.constants.ErrorMessages;
import com.nitish.qa.constants.StatusCode;
import com.nitish.qa.pojo.ErrorRoot;
import com.nitish.qa.pojo.Playlist;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.nitish.qa.assertions.PlaylistAssertions.assertError;
import static com.nitish.qa.assertions.PlaylistAssertions.assertPlaylistEqual;
import static com.nitish.qa.utility.FakerUtils.generateDescription;
import static com.nitish.qa.utility.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify API")
@Feature("Playlist")
public class PlaylistTests {

    private final PlaylistApi playlistApi = new PlaylistApi();

    // -------------------- TESTS --------------------

    @Link(name = "Sample Link", type = "issue")
    @Issue("9876")
    @TmsLink("123567")
    @Description("Should be able to create a playlist")
    @Test
    public void shouldBeAbleToCreateAPlaylist() {

        Playlist reqPlaylist =
                PlaylistTestDataBuilder.create(
                        generateName(),
                        generateDescription(),
                        false
                );

        Response response = playlistApi.create(reqPlaylist);

        assertThat(response.statusCode(), equalTo(StatusCode.CREATED.code));

        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(reqPlaylist, responsePlaylist);
    }

    @Test
    public void shouldBeAbleToGetAPlaylist() {

        Playlist expectedPlaylist =
                PlaylistTestDataBuilder.create(
                        "RestAssuredTestNitish_2",
                        "Testing Nitish Rest Assured",
                        true
                );

        String playlistId = TestDataFactory.getPlaylistId();
        Response response = playlistApi.get(playlistId);

        assertThat(response.statusCode(), equalTo(StatusCode.OK.code));
        assertPlaylistEqual(expectedPlaylist, response.as(Playlist.class));

        // Optional schema validation
        // ApiAssertions.validateSchema(response, "schemas/playlist_schema.json");
    }

    @Test
    public void shouldBeAbleToUpdateAPlaylist_parallelSafe() {

        // 1. Create playlist
        Playlist createRequest = PlaylistTestDataBuilder.create(generateName(), generateDescription(), false);

        Response createResponse = playlistApi.create(createRequest);
        assertThat(createResponse.statusCode(), equalTo(StatusCode.CREATED.code));

        String playlistId = createResponse.as(Playlist.class).getId();

        // 2. Update playlist
        Playlist updateRequest =
                PlaylistTestDataBuilder.create(
                        generateName(),
                        generateDescription(),
                        true
                );

        Response updateResponse =
                playlistApi.update(playlistId, updateRequest);

        assertThat(updateResponse.statusCode(), equalTo(StatusCode.OK.code));

        // 3. Get & verify update
        Response getResponse = playlistApi.get(playlistId);
        assertThat(getResponse.statusCode(), equalTo(StatusCode.OK.code));

        Playlist updatedPlaylist = getResponse.as(Playlist.class);
        assertPlaylistEqual(updateRequest, updatedPlaylist);
    }


    @Test
    public void shouldNotBeAbleToCreatePlaylistWithoutName() {

        Playlist reqPlaylist =
                PlaylistTestDataBuilder.create(
                        "",
                        generateDescription(),
                        false
                );

        Response response = playlistApi.create(reqPlaylist);

        assertThat(response.statusCode(), equalTo(StatusCode.BAD_REQUEST.code));

        ErrorRoot error = response.as(ErrorRoot.class);
        assertError(
                error,
                StatusCode.BAD_REQUEST.code,
                ErrorMessages.MISSING_NAME
        );
    }

    @Test
    public void shouldNotBeAbleToAccessWithInvalidToken() {

        Playlist reqPlaylist =
                PlaylistTestDataBuilder.create(
                        generateName(),
                        generateDescription(),
                        false
                );

        Response response =
                playlistApi.createWithToken("invalid_token", reqPlaylist);

        assertThat(response.statusCode(), equalTo(StatusCode.UNAUTHORIZED.code));

        ErrorRoot error = response.as(ErrorRoot.class);
        assertError(
                error,
                StatusCode.UNAUTHORIZED.code,
                ErrorMessages.INVALID_TOKEN
        );
    }
}