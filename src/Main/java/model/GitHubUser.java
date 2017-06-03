package org.felixlimanta.gitsearch.model;

import java.util.ArrayList;

/**
 * Represents a GitHub User.
 *
 * @author  Felix Limanta
 * @since   2017-05-29
 */
public class GitHubUser {

  /**
   * GitHub User ID
   */
  private int id;

  /**
   * GitHub Username
   */
  private String username;

  /**
   * URL to user's page for API access
   */
  private String url;

  /**
   * URL to user's page for browser access
   */
  private String htmlUrl;

  /**
   * URL to user's repositories page for API access
   */
  private String reposUrl;

  /**
   * User account type
   */
  private String type;

  /**
   * User repositories
   * @see GitHubRepository
   */
  private ArrayList<GitHubRepository> repos = null;

  /**
   * User ID getter
   *
   * @return  User ID
   */
  public int getId() {
    return id;
  }

  /**
   * User ID setter
   *
   * @param id  User ID
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Username getter
   *
   * @return Username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Username setter
   *
   * @param username  Username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * API URL getter
   *
   * @return  API URL
   * @see     #url
   */
  public String getUrl() {
    return url;
  }

  /**
   * API URL setter
   *
   * @param url API URL
   * @see   #url
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * HTML URL getter
   *
   * @return  HTML URL
   * @see     #htmlUrl
   */
  public String getHtmlUrl() {
    return htmlUrl;
  }

  /**
   * HTML URL setter
   *
   * @param htmlUrl HTML URL
   * @see   #htmlUrl
   */
  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  /**
   * Repositories URL getter
   *
   * @return  Repositories URL
   * @see     #reposUrl
   */
  public String getReposUrl() {
    return reposUrl;
  }

  /**
   * Repositories URL setter
   *
   * @param reposUrl  Repositories URL
   * @see   #reposUrl
   */
  public void setReposUrl(String reposUrl) {
    this.reposUrl = reposUrl;
  }

  /**
   * Account type getter
   *
   * @return  Account type
   */
  public String getType() {
    return type;
  }

  /**
   * Account type setter
   *
   * @param type  Account type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Repositories getter.
   *
   * @return  User repositories
   */
  public ArrayList<GitHubRepository> getRepos() {
    return repos;
  }

  /**
   * Repositories setter
   * @param repos User repositories
   */
  public void setRepos(ArrayList<GitHubRepository> repos) { this.repos = repos; }
}
