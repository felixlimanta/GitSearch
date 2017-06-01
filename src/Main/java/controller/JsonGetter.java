package org.felixlimanta.gitsearch.controller;

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
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * Created by ASUS on 29/05/17.
 */
public class JsonGetter {
  private static final int timeout = 10000;
  private static final String userAgent = "git-search";
  private static final String token = "2guftCp8ETpJ0X7p7Wfy93+qOz98EpcGnRE8udK5TL+uMHI4a5x+SaxRosL/BZ3JMQOCTM5WoFQ=";
  private final String url;
  private int status;
  private String message;
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

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public String getDecryptedToken() {
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setPassword("GitSearch");
    encryptor.setAlgorithm("PBEWithMD5AndDES");
    return encryptor.decrypt(token);
  }

  public String getJson(int timeout) {
    HttpURLConnection c = null;
    try {
      URL u = new URL(url);
      c = (HttpURLConnection) u.openConnection();
      c.setRequestMethod("GET");
      c.setRequestProperty("User-Agent", userAgent);
      c.setRequestProperty("Authorization", "token " + getDecryptedToken());
      c.setUseCaches(false);
      c.setAllowUserInteraction(false);
      c.setConnectTimeout(timeout);
      c.setReadTimeout(timeout);
      c.connect();
      status = c.getResponseCode();
      message = c.getResponseMessage();

      BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = br.readLine()) != null) {
        sb.append(line).append("\n");
      }
      br.close();
      return sb.toString();
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
