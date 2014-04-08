package fr.rouen.Cagliostro;

import android.app.Application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class CAGApp extends Application {
    public JSONArray getEpisodes() {
        InputStream is = getResources().openRawResource(R.raw.episodes);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

        }

        String jsonString = writer.toString();

        JSONArray jsonArray = new JSONArray();

        try {
            JSONObject jObject = new JSONObject(jsonString);
            jsonArray = jObject.getJSONArray("episodes");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        return jsonArray;
    }

    public JSONArray getCharacters() {
        InputStream is = getResources().openRawResource(R.raw.characters);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

        }

        String jsonString = writer.toString();

        JSONArray jsonArray = new JSONArray();

        try {
            JSONObject jObject = new JSONObject(jsonString);
            jsonArray = jObject.getJSONArray("characters");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        return jsonArray;
    }

    public JSONArray getPlaces() {
        InputStream is = getResources().openRawResource(R.raw.places);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

        }

        String jsonString = writer.toString();

        JSONArray jsonArray = new JSONArray();

        try {
            JSONObject jObject = new JSONObject(jsonString);
            jsonArray = jObject.getJSONArray("places");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        return jsonArray;
    }


}
