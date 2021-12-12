package com.example.clc.clc_server.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonFetch {

    public static List<YtsMovie> parseJsonFromYts() throws IOException, JSONException {
        JSONObject result = readJsonFromUrl("https://yts.mx/api/v2/list_movies.json");
        JSONObject data = result.getJSONObject("data");
        int limit = data.getInt("limit");

        JSONArray movies = data.getJSONArray("movies");
        List<YtsMovie> parsedMovie = new ArrayList<>();

        for(int i=0;i<limit;i++){
            JSONObject cur = movies.getJSONObject(i);
            YtsMovie ym = YtsMovie.createYtsMovie(cur);
            parsedMovie.add(ym);
        }

        return parsedMovie;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        }
    }
}
