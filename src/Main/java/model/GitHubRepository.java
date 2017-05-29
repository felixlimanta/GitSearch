package org.felixlimanta.gitsearch.model;

/**
 * Created by ASUS on 29/05/17.
 */
public class GitHubRepository {
  private int id;
  private int ownerId;
  private String name;
  private String description;
  private boolean isPrivate;
  private String url;
  private String htmlUrl;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setOwnerId(int ownerId) {
    this.ownerId = ownerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public void setPrivate(boolean aPrivate) {
    isPrivate = aPrivate;
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
}
