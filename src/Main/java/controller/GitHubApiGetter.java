package org.felixlimanta.gitsearch.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.felixlimanta.gitsearch.model.GitHubRepository;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Created by ASUS on 30/05/17.
 */
public abstract class GitHubApiGetter {
  protected static final Gson gson;

  protected String url;
  protected String jsonString;

  static {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(GitHubUser.class, new GitHubUserDeserializer());
    gsonBuilder.registerTypeAdapter(GitHubRepository.class, new GitHubRepositioryDeserializer());
    gson = gsonBuilder.create();
  }

  public GitHubApiGetter(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void getJsonFromGitHub() {
    JsonGetter g = new JsonGetter(url);
    jsonString = g.getRawJson();
  }

  public String getJsonString() {
    return jsonString;
  }

  public String getPrettyJsonString() {
    return JsonGetter.prettifyJson(jsonString);
  }

  public abstract void deserializeJson();

  public abstract Object getResponse();

  public void retrieveDataFromGitHub() {
    getJsonFromGitHub();
    deserializeJson();
  }
}
