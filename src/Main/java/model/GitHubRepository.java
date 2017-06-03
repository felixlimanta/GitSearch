package org.felixlimanta.gitsearch.model;

/**
 * Represents a GitHub repository.
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-05-29
 */
public class GitHubRepository {

  /**
   * Repository ID
   */
  private int id;

  /**
   * ID of repository owner
   */
  private int ownerId;

  /**
   * Repository name
   */
  private String name;

  /**
   * Repository description
   */
  private String description;

  /**
   * Repository access level
   */
  private boolean isPrivate;

  /**
   * Repository URL for API access
   */
  private String url;

  /**
   * Repository URL for browser access
   */
  private String htmlUrl;

  /**
   * Repository ID getter.
   *
   * @return Repository ID.
   */
  public int getId() {
    return id;
  }

  /**
   * Repository ID setter.
   *
   * @param id Repository ID.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Owner ID setter.
   *
   * @param ownerId ID of repository owner.
   */
  public void setOwnerId(int ownerId) {
    this.ownerId = ownerId;
  }

  /**
   * Repository name getter.
   *
   * @return Repository name.
   */
  public String getName() {
    return name;
  }

  /**
   * Repository name setter.
   *
   * @param name Repository name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Repository description getter.
   *
   * @return Repository description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Repository description setter.
   *
   * @param description Repository description.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Repository access level getter.
   *
   * @return Whether this repository is private or not.
   */
  public boolean isPrivate() {
    return isPrivate;
  }

  /**
   * Repository access level setter.
   *
   * @param aPrivate Whether this repository is private or not.
   */
  public void setPrivate(boolean aPrivate) {
    isPrivate = aPrivate;
  }

  /**
   * Repository API URL getter
   *
   * @return Repository URL for API access
   */
  public String getUrl() {
    return url;
  }

  /**
   * Repository API URL setter
   *
   * @param url Repository URL for API access
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * Repository HTML URL getter
   *
   * @return Repository URL for browser access
   */
  public String getHtmlUrl() {
    return htmlUrl;
  }

  /**
   * Repository HTML URL setter
   *
   * @param htmlUrl Repository URL for browser access
   */
  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }
}
