package model;

import java.util.ArrayList;

/**
 * Created by ASUS on 29/05/17.
 */
public class GitHubSearchResponse<T> {
  private int totalCount;
  private boolean incompleteResults;
  private ArrayList<T> items;

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public boolean isIncompleteResults() {
    return incompleteResults;
  }

  public void setIncompleteResults(boolean incompleteResults) {
    this.incompleteResults = incompleteResults;
  }

  public ArrayList<T> getItems() {
    return items;
  }

  public void setItems(ArrayList<T> items) {
    this.items = items;
  }
}
