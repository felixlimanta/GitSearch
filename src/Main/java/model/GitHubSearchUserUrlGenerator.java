package org.felixlimanta.gitsearch.model;

/**
 * Generates a URL for searching users through REST API access.
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-05-29
 */
@SuppressWarnings("ALL")
public class GitHubSearchUserUrlGenerator {
  private final String baseUrl = "https://api.github.com/search/users?q=";

  /**
   * Search query
   */
  private String query;

  /**
   * Which fields are searched.
   *
   * <p>Possible values:
   * <ul>
   *   <li>0: search all</li>
   *   <li>1: search usernames only</li>
   *   <li>2: search email addresses only</li>
   *   <li>3: search full names only</li>
   * </ul></p>
   */
  private int searchIn;

  /**
   * Filter for number of repositories.
   */
  private Filter repo;

  /**
   * Filter for number of followers.
   */
  private Filter follower;

  /**
   * Constructor.
   *
   * @param query Search query
   */
  public GitHubSearchUserUrlGenerator(String query) {
    this.query = query;
    searchIn = 0;
    repo = new Filter();
    follower = new Filter();
  }

  /**
   * Query getter.
   *
   * @return  Search query
   */
  public String getQuery() {
    return query;
  }

  /**
   * Query setter.
   *
   * @param query Search query
   */
  public void setQuery(String query) {
    this.query = query;
  }

  /**
   * Search in which fields getter.
   * 
   * @return  Which fields are searched
   * @see     #searchIn
   */
  public int getSearchIn() {
    return searchIn;
  }

  /**
   * Search in which fields getter.
   * 
   * @param searchIn  Which fields are searched
   * @see   #searchIn
   */
  public void setSearchIn(int searchIn) {
    if (searchIn >= 0 && searchIn <= 3) {
      this.searchIn = searchIn;
    } else {
      this.searchIn = 0;
    }
  }

  /**
   * Repository filter getter.
   *
   * @return Repository filter
   */
  public Filter getRepoFilter() {
    return repo;
  }

  /**
   * Repository usage filter setter.
   * 
   * @param used Whether or not repositories are filtered
   */
  public void setRepoUsed(boolean used) {
    repo.setUsed(used);
  }

  /**
   * Repository filter setter.
   * 
   * @param used  Whether or not repositories are filtered
   * @param limit Actual value of filter
   */
  public void setRepoFilter(boolean used, String limit) {
    repo.setFilter(used, limit);
  }

  /**
   * Repository filter copy setter.
   * 
   * @param filter  Original filter to be copied
   */
  public void setRepoFilter(Filter filter) {
    repo.setFilter(filter);
  }

  /**
   * Follower filter getter.
   *
   * @return Follower filter
   */
  public Filter getFollowerFilter() {
    return follower;
  }

  /**
   * Follower usage filter setter.
   *
   * @param used Whether or not followers are filtered
   */
  public void setFollowerUsed(boolean used) {
    follower.setUsed(used);
  }

  /**
   * Follower filter setter.
   *
   * @param used  Whether or not followers are filtered
   * @param limit Actual value of filter
   */
  public void setFollowerFilter(boolean used, String limit) {
    follower.setFilter(used, limit);
  }

  /**
   * Follower filter copy setter.
   *
   * @param filter  Original filter to be copied
   */
  public void setFollowerFilter(Filter filter) {
    follower.setFilter(filter);
  }

  /**
   * Generates URL based on inputted parameters.
   *
   * @return  Generated URL
   */
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
