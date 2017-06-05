package org.felixlimanta.gitsearch.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * Gets JSON from the Internet using HTML <code>GET</code> operation through REST API access.
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-05-30
 */
public class JsonGetter {
  private static final int timeout = 10000;
  private static final String userAgent = "git-search";
  private static final String token =
      "2guftCp8ETpJ0X7p7Wfy93+qOz98EpcGnRE8udK5TL+uMHI4a5x+SaxRosL/BZ3JMQOCTM5WoFQ=";

  /**
   * Source URL for HTML <code>GET</code>
   */
  private final String url;

  /**
   * Status code from HTML <code>GET</code>
   */
  private int status;

  /**
   * Status message from HTML <code>GET</code>
   */
  private String message;

  /**
   * Response from HTML <code>GET</code>
   */
  private final String rawJson;

  /**
   * Constructor
   *
   * @param url Source URL for HTML <code>GET</code>
   */
  public JsonGetter(String url) {
    this.url = url;
    rawJson = getJson(timeout);
  }

  /**
   * URL getter
   *
   * @return Source URL for HTML <code>GET</code>
   */
  public String getUrl() {
    return url;
  }

  /**
   * Raw JSON getter
   *
   * @return Raw JSON from HTML <code>GET</code>
   */
  public String getRawJson() {
    return rawJson;
  }

  /**
   * Formatted JSON getter
   *
   * @return Formatted JSON from HTML <code>GET</code>
   * @see    #formatJson(String)
   */
  public String getFormattedJson() {
    return formatJson(rawJson);
  }

  /**
   * Status code getter
   *
   * @return Status code from HTML <code>GET</code>
   */
  public int getStatus() {
    return status;
  }

  /**
   * Status message getter
   *
   * @return Status message from HTML <code>GET</code>
   */
  public String getMessage() {
    return message;
  }

  /**
   * <code>GET</code>s JSON fron the Internet
   *
   * @param timeout Operation timeout in milliseconds
   * @return Raw JSON string
   */
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
      ex.printStackTrace();
    } finally {
      if (c != null) {
        try {
          c.disconnect();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }
    return null;
  }

  /**
   * Formats JSON to a human-readable format
   *
   * @param jsonString Raw JSON string
   * @return Formatted JSON string
   */
  public static String formatJson(String jsonString) {
    JsonParser parser = new JsonParser();
    JsonObject json = parser.parse(jsonString).getAsJsonObject();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    return gson.toJson(json);
  }

  private String getDecryptedToken() {
    StandardPBEStringEncryptor e = new StandardPBEStringEncryptor();
    e.setPassword("GitSearch");
    e.setAlgorithm("PBEWithMD5AndDES");
    return e.decrypt(token);
  }
}
