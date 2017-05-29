package controller;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import model.GitHubUser;

/**
 * Created by ASUS on 29/05/17.
 */
public class GitHubUserDeserializer implements JsonDeserializer<GitHubUser> {
  public GitHubUser deserialize(final JsonElement json, final Type typeOfT,
      final JsonDeserializationContext context) throws JsonParseException {
    final JsonObject jsonObject = json.getAsJsonObject();

    int id = jsonObject.get("id").getAsInt();
    String username = jsonObject.get("login").getAsString();
    String url = jsonObject.get("url").getAsString();
    String htmlUrl = jsonObject.get("html_url").getAsString();
    String reposUrl = jsonObject.get("repos_url").getAsString();
    String type = jsonObject.get("type").getAsString();

    GitHubUser usr = new GitHubUser();
    usr.setId(id);
    usr.setUsername(username);
    usr.setUrl(url);
    usr.setHtmlUrl(htmlUrl);
    usr.setReposUrl(reposUrl);
    usr.setType(type);
    return usr;
  }
}
