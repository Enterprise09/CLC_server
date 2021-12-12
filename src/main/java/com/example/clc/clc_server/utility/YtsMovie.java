package com.example.clc.clc_server.utility;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class YtsMovie {
    long id;
    String url;
    String imdbCode;
    String title;
    String titleEnglish;
    String titleLong;
    String slug;

    int year;
    double rating;
    int runtime;
    List<String> genres = new ArrayList<>();

    String summary;
    String descriptionFull;
    String synopsis;
    String ytTrailerCode;
    String language;
    String mpaRating;

    String backgroundImage;
    String backgroundImageOriginal;
    String smallCoverImage;
    String mediumCoverImage;
    String largeCoverImage;

    String state;
    String dateUploaded;
    long dateUploadedUnix;

    @Override
    public String toString() {
        return id + " : " + title + " => " + summary + " \n";
    }

    static YtsMovie createYtsMovie(JSONObject cur) throws JSONException {
        JSONArray genresJSON = cur.getJSONArray("genres");
        List<String> genres = new ArrayList<>();

        if (genresJSON != null) {
            for (int j = 0; j < genresJSON.length(); j++) {
                genres.add(genresJSON.getString(j));
            }
        }

        return new YtsMovie.YtsMovieBuilder().id(cur.getLong("id")).url(cur.getString("url"))
                .imdbCode(cur.getString("imdb_code")).title(cur.getString("title"))
                .titleEnglish(cur.getString("title_english")).titleLong(cur.getString("title_long"))
                .slug(cur.getString("slug")).year(cur.getInt("year")).rating(cur.getDouble("rating"))
                .runtime(cur.getInt("runtime")).genres(genres).summary(cur.getString("summary"))
                .descriptionFull(cur.getString("description_full")).synopsis(cur.getString("synopsis"))
                .ytTrailerCode(cur.getString("yt_trailer_code")).language(cur.getString("language"))
                .mpaRating(cur.getString("mpa_rating")).backgroundImage(cur.getString("background_image"))
                .backgroundImageOriginal(cur.getString("background_image_original"))
                .smallCoverImage(cur.getString("small_cover_image"))
                .mediumCoverImage(cur.getString("medium_cover_image"))
                .largeCoverImage(cur.getString("large_cover_image")).state(cur.getString("state"))
                .dateUploaded(cur.getString("date_uploaded")).dateUploadedUnix(cur.getLong("date_uploaded_unix"))
                .build();
    }
}