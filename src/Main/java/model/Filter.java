package org.felixlimanta.gitsearch.model;

/**
 * Created by ASUS on 02/06/17.
 */
public class Filter {
  public boolean used = false;
  public String limit = "";

  public void setUsed(boolean used) {
    this.used = used;
  }

  public void setLimit(String limit) { this.limit = limit; }

  public void setFilter(boolean used, String limit) {
    setUsed(used);
    setLimit(limit);
  }
}
