package org.felixlimanta.gitsearch.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.felixlimanta.gitsearch.model.GitHubRepository;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Abstract class for <code>GET</code>ting JSON data from GitHub REST API
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-05-30
 */
public abstract class GitHubApiGetter {

  /**
   * Gson object for JSON deserialization
   */
  protected static final Gson gson;

  /**
   * Source URL for GET operation
   */
  private String url;

  /**
   * JSON string from GET operation
   */
  protected String jsonString;

  static {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(GitHubUser.class, new GitHubUserDeserializer());
    gsonBuilder.registerTypeAdapter(GitHubRepository.class, new GitHubRepositioryDeserializer());
    gson = gsonBuilder.create();
  }

  /**
   * Constructor
   *
   * @param url Source URL for GET operation
   */
  public GitHubApiGetter(String url) {
    this.url = url;
  }

  /**
   * URL getter
   *
   * @return Source URL for GET operation
   */
  public String getUrl() {
    return url;
  }

  /**
   * URL setter
   *
   * @param url Source URL for GET operation
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * Retrieves JSON from GitHub and stores it in <code>jsonString</code>
   *
   * @see JsonGetter
   */
  public void getJsonFromGitHub() {
    JsonGetter g = new JsonGetter(url);
    jsonString = g.getRawJson();
  }

  /**
   * JSON string getter.
   *
   * @return Raw JSON string.
   */
  public String getJsonString() {
    return jsonString;
  }

  /**
   * Formatted JSON string getter.
   *
   * @return Formatted JSON string.
   * @see    JsonGetter#formatJson(String)
   */
  public String getFormattedJsonString() {
    return JsonGetter.formatJson(jsonString);
  }

  /**
   * Deserializes JSON with Google Gson
   *
   * @see <a href="https://github.com/google/gson/blob/master/UserGuide.md">Google Gson</a>
   */
  public abstract void deserializeJson();

  /**
   * GET operation response getter
   *
   * <p>Actual <code>response</code> field must be defined in concrete classes</p>
   *
   * @return GET operation response
   */
  public abstract Object getResponse();

  /**
   * Retrieves data from GitHub
   *
   * @see #getJsonFromGitHub()
   * @see #deserializeJson()
   */
  public void retrieveDataFromGitHub() {
    getJsonFromGitHub();
    deserializeJson();
  }
}
