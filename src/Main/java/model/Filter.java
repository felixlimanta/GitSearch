package org.felixlimanta.gitsearch.model;

/**
 * Created by ASUS on 02/06/17.
 */
public class Filter {
  private boolean used;
  private String limit;

  public Filter() {
    this.used = false;
    this.limit = "";
  }

  public Filter(boolean used, String limit) {
    this.used = used;
    this.limit = limit;
  }

  public Filter(Filter filter) {
    this.used = filter.used;
    this.limit = filter.limit;
  }

  public void setFilter(boolean used, String limit) {
    this.used = used;
    this.limit = limit;
  }

  public void setFilter(Filter filter) {
    this.used = filter.used;
    this.limit = filter.limit;
  }

  public void setUsed(boolean used) {
    this.used = used;
  }

  public void setLimit(String limit) { this.limit = limit; }

  public boolean getUsed() {
    return used;
  }

  public String getLimit() {
    return limit;
  }
}
