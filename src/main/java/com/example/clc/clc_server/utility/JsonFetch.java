package com.example.clc.clc_server.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;

public class JsonFetch {

    public static List<YtsMovie> parseJsonFromYts() throws IOException, JSONException {
        // 택1
        // return parseJsonFromURL();
        return parseJsonFromFile();
    }


    private static List<YtsMovie> parseJsonFromFile() throws IOException, JSONException{
        ClassPathResource resource = new ClassPathResource("yts.json");
        Path path = Paths.get(resource.getURI());

        byte[] bytes = Files.readAllBytes(path);
        JSONObject ret = new JSONObject(new String(bytes, StandardCharsets.UTF_8));

        return jsonToYtsMovieList(ret);
    }

    private static List<YtsMovie> parseJsonFromURL() throws IOException, JSONException {
        JSONObject result = readJsonFromUrl("https://yts.mx/api/v2/list_movies.json");
        return jsonToYtsMovieList(result);
    }

    private static List<YtsMovie> jsonToYtsMovieList(JSONObject json) throws IOException, JSONException {
        JSONObject data = json.getJSONObject("data");
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
