package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import model.GitHubSearchResponse;
import model.GitHubUser;

/**
 * Created by ASUS on 29/05/17.
 */
public class GitHubUserSearcher {
  private static final int timeout = 100000;
  private static GsonBuilder gsonBuilder;
  private static Gson gson;

  private GitHubSearchResponse<GitHubUser> response;
  private String url;
  private String jsonString;

  static {
    gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(GitHubUser.class, new GitHubUserDeserializer());
    gson = gsonBuilder.create();
  }

  public GitHubUserSearcher(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void getJson() {
    JsonGetter g = new JsonGetter(url);
    jsonString = g.getRawJson();
  }

  public String getJsonString() {
    return jsonString;
  }

  public String getPrettyJsonString() {
    return JsonGetter.prettifyJson(jsonString);
  }

  public void deserializeJson() {
    Type type = new TypeToken<GitHubSearchResponse<GitHubUser>>() {}.getType();
    response = gson.fromJson(jsonString, type);
  }

  public GitHubSearchResponse<GitHubUser> getResponse() {
    return response;
  }
}
