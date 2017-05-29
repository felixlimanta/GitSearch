package org.felixlimanta.gitsearch.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.felixlimanta.gitsearch.model.GitHubRepository;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Created by ASUS on 29/05/17.
 */
public class GitHubUserRepositoryGetter {
  private static final Gson gson;

  private String reposUrl;
  private String jsonString;
  private ArrayList<GitHubRepository> repos = null;

  static {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(GitHubUser.class, new GitHubUserDeserializer());
    gsonBuilder.registerTypeAdapter(GitHubRepository.class, new GitHubRepositioryDeserializer());
    gson = gsonBuilder.create();
  }

  public GitHubUserRepositoryGetter(GitHubUser user) {
    reposUrl = user.getReposUrl();
  }

  public GitHubUserRepositoryGetter(String reposUrl) {
    this.reposUrl = reposUrl;
  }

  public void getJson() {
    JsonGetter g = new JsonGetter(reposUrl);
    System.out.printf("%d %s\n", g.getStatus(), g.getMessage());
    jsonString = g.getRawJson();
  }

  public String getJsonString() {
    return jsonString;
  }

  public String getPrettyJsonString() {
    return JsonGetter.prettifyJson(jsonString);
  }

  public void deserializeJson() {
    Type type = new TypeToken<ArrayList<GitHubRepository>>() {}.getType();
    repos = gson.fromJson(jsonString, type);
  }

  public ArrayList<GitHubRepository> getRepos() {
    return repos;
  }
}
