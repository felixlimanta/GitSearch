package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ASUS on 29/05/17.
 */
public class JsonGetter {
  private static final int timeout = 10000;
  private String url;
  private String rawJson;

  public JsonGetter(String url) {
    this.url = url;
    rawJson = getJson(timeout);
  }

  public String getUrl() {
    return url;
  }

  public String getRawJson() {
    return rawJson;
  }

  public String getPrettyJson() {
    return prettifyJson(rawJson);
  }

  public String getJson(int timeout) {
    HttpURLConnection c = null;
    try {
      URL u = new URL(url);
      c = (HttpURLConnection) u.openConnection();
      c.setRequestMethod("GET");
      c.setUseCaches(false);
      c.setAllowUserInteraction(false);
      c.setConnectTimeout(timeout);
      c.setReadTimeout(timeout);
      c.connect();
      int status = c.getResponseCode();

      switch (status) {
        case 200:
        case 201:
          BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
          StringBuilder sb = new StringBuilder();
          String line;
          while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
          }
          br.close();
          return sb.toString();
      }
    } catch (Exception ex) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (c != null) {
        try {
          c.disconnect();
        } catch (Exception ex) {
          Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return null;
  }

  public static String prettifyJson(String jsonString) {
    JsonParser parser = new JsonParser();
    JsonObject json = parser.parse(jsonString).getAsJsonObject();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    return gson.toJson(json);
  }
}
