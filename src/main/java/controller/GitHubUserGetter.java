package controller;

import model.GitHubSearchResponse;
import model.GitHubUser;

/**
 * Created by ASUS on 29/05/17.
 */
public class GitHubUserGetter {
  private static final int timeout = 10000;
  private GitHubSearchResponse<GitHubUser> response;
  private String url;
  private String rawJson;

  public GitHubUserGetter(String url) {
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
    rawJson = g.getRawJson();
  }

  public String getRawJson() {
    return rawJson;
  }

  public String getPrettyJson() {
    return JsonGetter.prettifyJson(rawJson);
  }

  public void setRawJson(String rawJson) {
    this.rawJson = rawJson;
  }

  public void deserializeJson() {
    JsonUserDeserializer u = new JsonUserDeserializer(rawJson);
    u.deserializeJson();
    response = u.getResponse();
  }

  public GitHubSearchResponse<GitHubUser> getResponse() {
    return response;
  }
}
