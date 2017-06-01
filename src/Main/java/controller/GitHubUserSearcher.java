package org.felixlimanta.gitsearch.controller;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import org.felixlimanta.gitsearch.model.GitHubSearchResponse;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Created by ASUS on 29/05/17.
 */
public class GitHubUserSearcher extends GitHubApiGetter {
  private GitHubSearchResponse<GitHubUser> response = null;

  public GitHubUserSearcher(String url) {
    super(url);
  }

  @Override
  public void deserializeJson() {
    Type type = new TypeToken<GitHubSearchResponse<GitHubUser>>() {}.getType();
    response = gson.fromJson(jsonString, type);
  }

  @Override
  public GitHubSearchResponse<GitHubUser> getResponse() {
    return response;
  }
}
