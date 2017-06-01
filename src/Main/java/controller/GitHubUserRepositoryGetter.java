package org.felixlimanta.gitsearch.controller;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.felixlimanta.gitsearch.model.GitHubRepository;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Created by ASUS on 29/05/17.
 */
public class GitHubUserRepositoryGetter extends GitHubApiGetter {
  private ArrayList<GitHubRepository> response = null;

  public GitHubUserRepositoryGetter(GitHubUser user) {
    super(user.getReposUrl());
  }

  public GitHubUserRepositoryGetter(String reposUrl) {
    super(reposUrl);
  }

  @Override
  public void deserializeJson() {
    Type type = new TypeToken<ArrayList<GitHubRepository>>() {}.getType();
    response = gson.fromJson(jsonString, type);
  }

  @Override
  public ArrayList<GitHubRepository> getResponse() {
    return response;
  }
}
