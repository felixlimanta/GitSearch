package org.felixlimanta.gitsearch.controller;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import org.felixlimanta.gitsearch.model.GitHubSearchResponse;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Gets JSON data for user search results
 *
 * <p>Implements GitHubApiGetter</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-05-29
 * @see     GitHubApiGetter
 * @see     GitHubUser
 */
public class GitHubUserSearcher extends GitHubApiGetter {
  /**
   * Response containing GitHubUser
   *
   * @see GitHubSearchResponse
   */
  private GitHubSearchResponse<GitHubUser> response = null;

  /**
   * Constructor
   *
   * @param url API URL to search users
   */
  public GitHubUserSearcher(String url) {
    super(url);
  }

  /**
   * Deserializes JSON with Google Gson
   *
   * @see <a href="https://github.com/google/gson/blob/master/UserGuide.md">Google Gson</a>
   */
  @Override
  public void deserializeJson() {
    Type type = new TypeToken<GitHubSearchResponse<GitHubUser>>() {}.getType();
    response = gson.fromJson(jsonString, type);
  }

  /**
   * Search results getter
   *
   * @return Search results
   */
  @Override
  public GitHubSearchResponse<GitHubUser> getResponse() {
    return response;
  }
}
