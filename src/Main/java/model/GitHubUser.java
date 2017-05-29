package model;

import java.util.ArrayList;

/**
 * The GitHubUser class contains fields for a GitHub User
 * deserialized from a GitHub User's JSON object.
 *
 * @author  Felix Limanta
 * @since   2017-05-29
 */
public class GitHubUser {
  private int id;
  private String username;
  private String url;
  private String htmlUrl;
  private String reposUrl;
  private String type;
  private ArrayList<GitHubRepository> repos = null;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public String getReposUrl() {
    return reposUrl;
  }

  public void setReposUrl(String reposUrl) {
    this.reposUrl = reposUrl;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ArrayList<GitHubRepository> getRepos() {
    return repos;
  }

  public void setRepos(ArrayList<GitHubRepository> repos) {
    this.repos = repos;
  }
}
