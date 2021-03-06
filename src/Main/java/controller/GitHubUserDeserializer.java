package org.felixlimanta.gitsearch.controller;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Deserializer for GitHubUser for use with Google Gson
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-05-29
 * @see     GitHubUser
 * @see     <a href="https://github.com/google/gson/blob/master/UserGuide.md#TOC-Writing-a-Deserializer">Gson deserializer documentation</a>
 */
class GitHubUserDeserializer implements JsonDeserializer<GitHubUser> {
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
