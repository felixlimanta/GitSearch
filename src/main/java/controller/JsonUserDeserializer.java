package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import model.GitHubSearchResponse;
import model.GitHubUser;

/**
 * Created by ASUS on 29/05/17.
 */
public class JsonUserDeserializer {

  private class GitHubSearchRawResponse {
    int totalCount;
    boolean incompleteResults;
    String items;
  }

  private String jsonString;
  private GitHubSearchResponse<GitHubUser> response;

  public JsonUserDeserializer(String jsonString) {
    this.jsonString = jsonString;
  }

  public String getJsonString() {
    return jsonString;
  }

  public void setJsonString(String jsonString) {
    this.jsonString = jsonString;
  }

  public void deserializeJson() {
    Gson gson = new Gson();
    Type type = new TypeToken<GitHubSearchResponse<GitHubUser>>() {}.getType();
    response = gson.fromJson(jsonString, type);
  }

  public GitHubSearchResponse<GitHubUser> getResponse() {
    return response;
  }
}