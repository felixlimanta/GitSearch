package org.felixlimanta.gitsearch.model;

/**
 * Created by ASUS on 29/05/17.
 */
public class GitHubSearchUserUrlGenerator {
  private final String baseUrl = "https://api.github.com/search/users?q=";
  private String query;
  private int searchIn;

  private Filter repo;
  private Filter follower;

  public GitHubSearchUserUrlGenerator(String query) {
    this.query = query;
    searchIn = 0;
    repo = new Filter();
    follower = new Filter();
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public int getSearchIn() {
    return searchIn;
  }

  public void setSearchIn(int searchIn) {
    if (searchIn >= 0 && searchIn <= 3) {
      this.searchIn = searchIn;
    } else {
      this.searchIn = 0;
    }
  }

  public void setRepoUsed(boolean used) {
    repo.setUsed(used);
  }

  public void setRepoFilter(boolean used, String limit) {
    repo.setFilter(used, limit);
  }

  public void setRepoFilter(Filter filter) {
    repo.setFilter(filter);
  }

  public void setFollowerUsed(boolean used) {
    follower.setUsed(used);
  }

  public void setFollowerFilter(boolean used, String limit) {
    follower.setFilter(used, limit);
  }

  public void setFollowerFilter(Filter filter) {
    follower.setFilter(filter);
  }

  public String generateUrl() {
    StringBuilder url = new StringBuilder(baseUrl + query);
    switch (searchIn) {
      case 1:
        url.append("+in:login");
        break;
      case 2:
        url.append("+in:email");
        break;
      case 3:
        url.append("+in:fullname");
        break;
      default:
        break;
    }
    if (repo.getUsed()) {
      url.append("+repos:");
      url.append(repo.getLimit());
    }
    if (follower.getUsed()) {
      url.append("+followers:");
      url.append(follower.getLimit());
    }
    return url.toString();
  }
}
