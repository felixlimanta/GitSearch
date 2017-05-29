package model;

/**
 * Created by ASUS on 29/05/17.
 */
public class GitHubSearchUserQuery {
  private class Filter {
    boolean used = false;
    String compOperator;
    int compLimit;

    public void setFilter(boolean used) {
      this.used = used;
    }

    public void setFilter(boolean used, String operator, int limit) {
      this.used = used;
      if (used) {
        switch (operator) {
          case "<":
            compOperator = "%3c";
            break;
          case "<=":
            compOperator = "%3c%3d";
            break;
          case ">":
            compOperator = "%3e";
            break;
          case ">=":
            compOperator = "%3e%3d";
            break;
          default:
            compOperator = "%3d";
            break;
        }
        compLimit = limit;
      }
    }
  }

  private final String baseUrl = "https://api.github.com/search/users?q=";
  private String query;
  private int searchIn;

  private Filter repo;
  private Filter follower;

  public GitHubSearchUserQuery(String query) {
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
    repo.setFilter(used);
  }

  public void setRepoFilter(boolean used, String operator, int limit) {
    repo.setFilter(used, operator, limit);
  }

  public void setFollowerUsed(boolean used) {
    repo.setFilter(used);
  }

  public void setFollowerFilter(boolean used, String operator, int limit) {
    follower.setFilter(used, operator, limit);
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
    if (repo.used) {
      url.append("+repos:");
      url.append(repo.compOperator);
      url.append(repo.compLimit);
    }
    if (follower.used) {
      url.append("+followers:");
      url.append(follower.compOperator);
      url.append(follower.compLimit);
    }
    return url.toString();
  }
}
