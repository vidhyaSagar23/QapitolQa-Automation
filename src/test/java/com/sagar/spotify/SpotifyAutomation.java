package com.sagar.spotify;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;


/*      to generate token use this
*
https://accounts.spotify.com/authorize?response_type=token&scope=user-read-private, user-read-email, user-top-read, playlist-modify-public, playlist-modify-private, user-follow-read, user-follow-modify, user-library-modify playlist-modify-public, playlist-modify-private, playlist-read-private, playlist-modify-public, playlist-modify-private, playlist-modify-public, playlist-modify-private, playlist-modify-public, playlist-modify-private, playlist-read-collaborative, ugc-image-upload, user-library-read, user-library-modify, user-read-playback-position, user-read-playback-position, user-library-read, user-library-modify, user-read-playback-state, user-modify-playback-state, user-read-playback-state, user-read-currently-playing, user-modify-playback-state, user-read-recently-played, user-read-playback-position&state=state&redirect_uri=http://localhost:3000&client_id=ef1a8583bec54f40a3b25c406cf101ad
*
*
* */

public class SpotifyAutomation {
   static String token="BQBQCR-purY9TO8QG-0netdb3rrTOZMGbU3Oxxc-N58GuSV9MiIAcyj_OiOCYg9wXKAg0yc7_aXZGyqmkJE1oTXYOxHhRnbIQndwMr_dV2ST6s0F7AJeaznrAyLyfAwFnHDBofDm6dsFxsZ86BlHxyD80GOT4SCUodKeGxCObLpt2albmKZ9x4cDENZgI3xmuAMes3BuxJp2nriAdCxaVk867SzKkXyY5XMFNHy7KOdUHAkrsI3bLyzbrwNgqYAMDBQLis0QMOExboor4vyObprwEpliVmOM-0bUzlCMbZZ1HnItFLQwoKhVo4uQyiBP9JLOMvoppE24&token_type=Bearer&expires_in=3600&state=state";
   static String userId="31dh4ni5trwwufkolmdvdoswfn4e";

   static String albumId="";

    /* User starts */

    @Test(priority = 1)
    public void getCurrentUserProfile(){
        Response response=given()
                .header("Content-Type", "application/josn")
                .header("Authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me");
        response.prettyPrint();
        response.then().statusCode(200);
        userId=response.path("id");
    }

    @Test(priority = 3)
    public void getUsersTopItem(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/top/artists");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 2)
    public void getUsersProfile(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
               // .pathParam("user_id",userId)
                .when()
                .get("https://api.spotify.com/v1/users/"+ userId);
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 4)
    public void followPlaylist(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("playlistid","3cEYpjA9oz9GiPac4AsH4n")
                .body("{\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/{playlistid}/followers");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 6)
    public void unfollowPlaylist(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("playlistid","3cEYpjA9oz9GiPac4AsH4n")
                .body("{\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/playlists/{playlistid}/followers");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 5)
    public void getFollowedArtist(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("type","artist")
                .when()
                .get("https://api.spotify.com/v1/me/following");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 7)
    public void followArtistOrUsers(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("type","artist")
                .queryParam("ids","2CIMQHirSU0MQqyYHq0eOx," +
                        "57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6")
                .when()
                .put("https://api.spotify.com/v1/me/following");
        response.prettyPrint();
        response.then().statusCode(204);
    }

    @Test(priority = 9)
    public void unFollowArtistOrUsers(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("type","artist")
                .queryParam("ids","2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6")
                .when()
                .delete("https://api.spotify.com/v1/me/following/");
        response.prettyPrint();
        response.then().statusCode(204);
    }

    @Test(priority = 8)
    public void checkIfUserFollowsArtistsorUsers(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("type","artist")
                .queryParam("ids","2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6")
                .when()
                .get("https://api.spotify.com/v1/me/following/contains");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 10)
    public void checkifUsersFollowPlaylist(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","jmperezperez,thelinmichael,wizzler")
                .pathParam("playlistid","3cEYpjA9oz9GiPac4AsH4n")
                .when()
                .get("https://api.spotify.com/v1/playlists/{playlistid}/followers/contains");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /* User Ends*/

    /* Album starts */

    @Test(priority = 11)
    public void getAlbum(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","4aawyAB9vmqN3uQ7FjRGTy")
                .when()
                .get("https://api.spotify.com/v1/albums/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
        albumId=response.path("id");
    }
    @Test
    public void getSeveralAlbum(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc")
                .when()
                .get("https://api.spotify.com/v1/albums");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 12)
    public void getAlbumTracks(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id", albumId)
                .when()
                .get("https://api.spotify.com/v1/albums/{id}/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getUsersSavedAlbums(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("offsest",0)
                .queryParam("limit",20)
                .when()
                .get("https://api.spotify.com/v1/me/albums");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public  void saveAlbumsForCurrentUser(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc")
                .when()
                .put("https://api.spotify.com/v1/me/albums");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public  void deleteSavedAlbumsForCurrentUser(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc")
                .when()
                .delete("https://api.spotify.com/v1/me/albums");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void checkUsersSavedAlbums(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc")
                .when()
                .get("https://api.spotify.com/v1/me/albums/contains");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getNewReleases(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/browse/new-releases");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /* Albums Ends */

    /* Artist Starts */

    @Test
    public void getArtist(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","0TnOYISbd1XYRBk9myaseg")
                .when()
                .get("https://api.spotify.com/v1/artists/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getSeveralArtist(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6")
                .when()
                .get("https://api.spotify.com/v1/artists");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getArtistAlbums(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","0TnOYISbd1XYRBk9myaseg")
                .when()
                .get("https://api.spotify.com/v1/artists/{id}/albums");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void GetArtistsTopTracks(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","0TnOYISbd1XYRBk9myaseg")
                .when()
                .get("https://api.spotify.com/v1/artists/{id}/top-tracks");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getArtistsRelatedArtists(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","0TnOYISbd1XYRBk9myaseg")
                .when()
                .get("https://api.spotify.com/v1/artists/{id}/related-artists");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getAnAudioBook(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","7iHfbu1YPACw6oZPAFJtqe")
                .when()
                .get("https://api.spotify.com/v1/audiobooks/{id}");
        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test
    public void getSeveralAudiobooks(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe")
                .when()
                .get("https://api.spotify.com/v1/audiobooks");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getAudiobookChapters(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","7iHfbu1YPACw6oZPAFJtqe")
                .when()
                .get("https://api.spotify.com/v1/audiobooks/{id}/chapters");
        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test
    public void getUsersSavedAudiobooks(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void saveAudiobooksforCurrentUser(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe")
                .when()
                .put("https://api.spotify.com/v1/me/audiobooks");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void removeSavedAudiobooksforCurrentUser(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe")
                .when()
                .delete("https://api.spotify.com/v1/me/audiobooks");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void checkSavedAudiobooksforCurrentUser(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe")
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks/contains");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /* AudioBooks Ends */

    /* Category starts*/

    @Test
    public void getSeveralBrowseCategories(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getSingleBrowseCategory(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("category_id","dinner")
                .when()
                .get("https://api.spotify.com/v1/browse/categories/{category_id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /* Category ends */

    /* Chapter starts */

    @Test
    public void getAChapter(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","0D5wENdkdwbqlrHoaJ9g29")
                .when()
                .get("https://api.spotify.com/v1/chapters/{id}");
        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test
    public void getSeveralChapters(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","0IsXVP0JmcB2adSE338GkK,3ZXb8FKZGU0EHALYX6uCzU,0D5wENdkdwbqlrHoaJ9g29")
                .when()
                .get("https://api.spotify.com/v1/chapters");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /* Chapter Ends*/

    /* Episode Starts*/

    @Test
    public void getEpisode(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","512ojhOuo1ktJprKbVcKyQ")
                .when()
                .get("https://api.spotify.com/v1/episodes/{id}");
        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test
    public void getSeveralEpisode(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf")
                .when()
                .get("https://api.spotify.com/v1/episodes");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getUserSavedEpisodes(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/episodes");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void saveEpisodesforCurrentUser(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf")
                .when()
                .put("https://api.spotify.com/v1/me/episodes");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void removeSavedEpisodesForCurrentUser(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf")
                .when()
                .delete("https://api.spotify.com/v1/me/episodes");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void checkUsersSavedEpisodes(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf")
                .when()
                .get("https://api.spotify.com/v1/me/episodes/contains");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /* Episode Ends */

    /* Genre Starts */

    @Test
    public void getAvailableGenreSeeds(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
        response.prettyPrint();
        response.then().statusCode(200);
    }
    /* Genre Ends */

    /* Markets Starts */

    @Test
    public void getAvailableMarkets(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/markets");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /* Market Ends */

    /* Player Starts */

    @Test
    public void getPlaybackState(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/player");
        response.prettyPrint();
        response.then().statusCode(204);
    }

    @Test
    public void transferPlayback(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .body("{\n" +
                        "    \"device_ids\": [\n" +
                        "        \"74ASZWbe4lXaubB36ztrGX\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/player");
        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void getAvailableDevices(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/player/devices");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getCurrentlyPlayingTrack(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/player/currently-playing");
        response.prettyPrint();
        response.then().statusCode(204);
    }

    @Test
    public void startResumePlayback(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .body("{\n" +
                        "    \"context_uri\": \"spotify:album:5ht7ItJgpBH7W6vJ5BqpPr\",\n" +
                        "    \"offset\": {\n" +
                        "        \"position\": 5\n" +
                        "    },\n" +
                        "    \"position_ms\": 0\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/player/play");
        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void pausePlayback(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .put("https://api.spotify.com/v1/me/player/pause");
        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void skipToNext(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .post("https://api.spotify.com/v1/me/player/next");
        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void skipToPrevious(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .post("https://api.spotify.com/v1/me/player/previous");
        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void seekToPosition(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("position_ms",25000)
                .when()
                .put("https://api.spotify.com/v1/me/player/seek");
        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void setRepeatMode(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("state","context")
                .when()
                .put("https://api.spotify.com/v1/me/player/repeat");
        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void setPlaybackVolume(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("volume_percent",50)
                .when()
                .put("https://api.spotify.com/v1/me/player/volume");
        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void togglePlaybackShuffle(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("state","true")
                .when()
                .put("https://api.spotify.com/v1/me/player/shuffle");
        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void getRecentlyPlayedTracks(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/player/recently-played");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getTheUsersQueue(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/player/queue");
        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void addItemtoPlaybackQueue(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("uri","spotify:track:4iV5W9uYEdYUVa79Axb7Rh")
                .when()
                .post("https://api.spotify.com/v1/me/player/queue");
        response.prettyPrint();
        response.then().statusCode(403);
    }
    /* Player Ends */

    /* Playlist Start */

    @Test
    public void getPlaylist(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("playlist_id","3cEYpjA9oz9GiPac4AsH4n")
                .when()
                .get("https://api.spotify.com/v1/playlists/{playlist_id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void changePlayListDetails(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("playlist_id","3cEYpjA9oz9GiPac4AsH4n")
                .body("{\n" +
                        "    \"name\": \"ilayaraja vibes\",\n" +
                        "    \"description\": \"Updated playlist description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/{playlist_id}");
        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void getPlaylistItems(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("playlist_id","3cEYpjA9oz9GiPac4AsH4n")
                .when()
                .get("https://api.spotify.com/v1/playlists/{playlist_id}/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void updatePlaylistItems(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("playlist_id","3cEYpjA9oz9GiPac4AsH4n")
                .body("{\n" +
                        "    \"range_start\": 1,\n" +
                        "    \"insert_before\": 3,\n" +
                        "    \"range_length\": 2\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/{playlist_id}/tracks");
        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void addItemstoPlaylist(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("playlist_id","3cEYpjA9oz9GiPac4AsH4n")
                .body("{\n" +
                        "    \"uris\": [\n" +
                        "        \"\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\",\"spotify:track:1301WleyT98MSxVHPZCA6M\"\"\n" +
                        "    ],\n" +
                        "    \"position\": 5\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/playlists/{playlist_id}/tracks");
        response.prettyPrint();
        response.then().statusCode(403);
    }

    @Test
    public void removePlayListItems(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("playlist_id","3cEYpjA9oz9GiPac4AsH4n")
                .body("{\n" +
                        "    \"tracks\": [\n" +
                        "        {\n" +
                        "            \"uri\": \"string\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"snapshot_id\": \"abc\"\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/playlists/{playlist_id}/tracks");
        response.prettyPrint();
        response.then().statusCode(400);
    }

    @Test
    public void getCurrentUsersPlaylists(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getUsersPlaylists(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("user_id",userId)
                .when()
                .get("https://api.spotify.com/v1/users/{user_id}/playlists");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void createPlaylist(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("user_id",userId)
                .body("{\n" +
                        "    \"name\": \"New Playlist\",\n" +
                        "    \"description\": \"New playlist description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/users/{user_id}/playlists");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void getFeaturedPlaylists(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/browse/featured-playlists");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getCategorysPlaylists(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("category_id","dinner")
                .when()
                .get("https://api.spotify.com/v1/browse/categories/{category_id}/playlists");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getPlaylistCoverImage(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("playlist_id","3cEYpjA9oz9GiPac4AsH4n")
                .when()
                .get("https://api.spotify.com/v1/playlists/{playlist_id}/images");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void addCustomPlaylistCoverImage(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("Content-Type","multipart/form-data")
                .header("authorization","Bearer "+token)
                .pathParam("playlist_id","3cEYpjA9oz9GiPac4AsH4n")
                .multiPart("image",new File("C:\\Users\\sagar\\Desktop\\SELENIUM TESTING\\Rest Assured\\Rest Assured\\src\\test\\java\\com\\sagar\\spotify\\dog.webp"))
                .when()
                .put("https://api.spotify.com/v1/playlists/{playlist_id}/images");
        response.prettyPrint();
        response.then().statusCode(401);
    }

    /* Playlist ends */

    /* Search Starts */

    @Test
    public void searchForItem(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("q","remaster%20track:Doxy%20artist:Miles%20Davis")
                .queryParam("type","album")
                .when()
                .get("https://api.spotify.com/v1/search");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    /* Search Ends */

    /* Shows Starts */

    @Test
    public void getShow(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","38bS44xjbVVZ3No3ByF1dJ")
                .when()
                .get("https://api.spotify.com/v1/shows/{id}");
        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test
    public void getSeveralShows(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ")
                .when()
                .get("https://api.spotify.com/v1/shows");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getShowEpisodes(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","38bS44xjbVVZ3No3ByF1dJ")
                .when()
                .get("https://api.spotify.com/v1/shows/{id}/episodes");
        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test
    public void getUsersSavedShows(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me/shows");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void saveShowsforCurrentUser(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ")
                .when()
                .put("https://api.spotify.com/v1/me/shows");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void removeSavedShowsforCurrentUser(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ")
                .when()
                .delete("https://api.spotify.com/v1/me/shows");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void checkUsersSavedShows(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ")
                .when()
                .get("https://api.spotify.com/v1/me/shows/contains");
        response.prettyPrint();
        response.then().statusCode(200);
    }
    /* Shows Ends */

    /* Track Starts*/

    @Test
    public void getTrack(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","11dFghVXANMlKmJXsNCbNl")
                .when()
                .get("https://api.spotify.com/v1/tracks/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getSeveralTracks(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B")
                .when()
                .get("https://api.spotify.com/v1/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void saveTracksForCurrentUser(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B")
                .when()
                .put("https://api.spotify.com/v1/me/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void removeUsersSavedTracks(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B")
                .when()
                .delete("https://api.spotify.com/v1/me/tracks");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void checkUsersSavedTracks(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B")
                .when()
                .get("https://api.spotify.com/v1/me/tracks/contains");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getSeveralTracksAudioFeatures(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("ids","7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B")
                .when()
                .get("https://api.spotify.com/v1/audio-features");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getTracksAudioFeatures(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","11dFghVXANMlKmJXsNCbNl")
                .when()
                .get("https://api.spotify.com/v1/audio-features/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getTracksAudioAnalysis(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .pathParam("id","11dFghVXANMlKmJXsNCbNl")
                .when()
                .get("https://api.spotify.com/v1/audio-analysis/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getRecommendations(){
        Response response=given()
                .header("Content-Type","application/json")
                .header("authorization","Bearer "+token)
                .queryParam("seed_artists","4NHQUGzhtTLFvgF5SZesLK")
                .queryParam("seed_genres","classical,country")
                .queryParam("seed_tracks","0c6xIDDpzE81m2q797ordA")
                .when()
                .get("https://api.spotify.com/v1/recommendations");
        response.prettyPrint();
        response.then().statusCode(200);
    }
    /* Track Ends */
}
