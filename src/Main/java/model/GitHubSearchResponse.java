package org.felixlimanta.gitsearch.model;

import java.util.ArrayList;

/**
 * Represents a GitHub API search response.
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-05-29
 * @param   <T> Class of search response
 */
public class GitHubSearchResponse<T> {

  /**
   * Number of results
   */
  private int totalCount;

  /**
   * Whether there are incomplete results or not
   */
  private boolean incompleteResults;

  /**
   * Search results
   */
  private ArrayList<T> items;

  /**
   * Total count getter
   *
   * @return  Number of results
   */
  public int getTotalCount() {
    return totalCount;
  }

  /**
   * Completeness of results getter.
   *
   * @return  Whether there are incomplete results or not
   */
  public boolean isIncompleteResults() {
    return incompleteResults;
  }

  /**
   * Search results getter.
   *
   * @return  Search results
   */
  public ArrayList<T> getItems() {
    return items;
  }

  /**
   * Search results setter.
   *
   * @param items Search results
   */
  public void setItems(ArrayList<T> items) {
    this.items = items;
  }
}
