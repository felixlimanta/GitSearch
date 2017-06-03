package org.felixlimanta.gitsearch.controller;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.felixlimanta.gitsearch.model.GitHubRepository;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Gets JSON data for user repositories
 *
 * <p>Implements GitHubApiGetter</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-05-29
 * @see     GitHubApiGetter
 * @see     GitHubRepository
 */
public class GitHubUserRepositoryGetter extends GitHubApiGetter {

  /**
   * Response containing GitHubRepository
   */
  private ArrayList<GitHubRepository> response = null;

  /**
   * Constructor with GitHubUser parameter
   *
   * @param user The user whose repositories are to be retrieved
   */
  public GitHubUserRepositoryGetter(GitHubUser user) {
    super(user.getReposUrl());
  }

  /**
   * Constructor with URL parameter
   *
   * @param reposUrl API URL of user repositories
   */
  public GitHubUserRepositoryGetter(String reposUrl) {
    super(reposUrl);
  }

  /**
   * Deserializes JSON with Google Gson
   *
   * @see <a href="https://github.com/google/gson/blob/master/UserGuide.md">Google Gson</a>
   */
  @Override
  public void deserializeJson() {
    Type type = new TypeToken<ArrayList<GitHubRepository>>() {}.getType();
    response = gson.fromJson(jsonString, type);
  }

  /**
   * User repositories getter
   *
   * @return User repositories
   */
  @Override
  public ArrayList<GitHubRepository> getResponse() {
    return response;
  }
}
