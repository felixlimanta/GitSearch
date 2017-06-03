package org.felixlimanta.gitsearch.controller;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import org.felixlimanta.gitsearch.model.GitHubRepository;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Deserializer for GitHubRepository for use with Google Gson
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-05-29
 * @see     GitHubRepository
 * @see     <a href="https://github.com/google/gson/blob/master/UserGuide.md#TOC-Writing-a-Deserializer">Gson deserializer documentation</a>
 */
public class GitHubRepositioryDeserializer implements JsonDeserializer<GitHubRepository> {
  public GitHubRepository deserialize(final JsonElement json, final Type typeOfT,
      final JsonDeserializationContext context) throws JsonParseException {
    final JsonObject jsonObject = json.getAsJsonObject();

    int id = jsonObject.get("id").getAsInt();
    GitHubUser owner = context.deserialize(jsonObject.get("owner"), GitHubUser.class);
    String name = jsonObject.get("name").getAsString();
    String description;
    try {
      description = jsonObject.get("description").getAsString();
    } catch (Exception ex) {
      description = "";
    }
    boolean isPrivate = jsonObject.get("private").getAsBoolean();
    String url = jsonObject.get("url").getAsString();
    String htmlUrl = jsonObject.get("html_url").getAsString();

    GitHubRepository rep = new GitHubRepository();
    rep.setId(id);
    rep.setOwnerId(owner.getId());
    rep.setName(name);
    rep.setDescription(description);
    rep.setPrivate(isPrivate);
    rep.setHtmlUrl(url);
    rep.setHtmlUrl(htmlUrl);
    return rep;
  }
}
