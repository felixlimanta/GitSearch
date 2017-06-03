package org.felixlimanta.gitsearch.model;

/**
 * Represents a filter to be added as a URL parameter.
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-06-02
 */
public class Filter {

  /**
   * Whether or not this filter will used in URL generation.
   */
  private boolean used;

  /**
   * Actual value of filter.
   */
  private String limit;

  /**
   * Default constructor.
   *
   * <p>Initializes <code>used</code> and <code>limit</code> with <code>false</code> and
   * <code>""</code>.</p>
   */
  public Filter() {
    this.used = false;
    this.limit = "";
  }

  /**
   * Constructor with value parameters.
   *
   * @param used  Whether or not this filter will used in URL generation.
   * @param limit Actual value of filter.
   */
  public Filter(boolean used, String limit) {
    this.used = used;
    this.limit = limit;
  }

  /**
   * Copy constructor.
   *
   * @param filter Original filter to be copied.
   */
  public Filter(Filter filter) {
    this.used = filter.used;
    this.limit = filter.limit;
  }

  /**
   * Filter setter.
   *
   * @param used  Whether or not this filter will used in URL generation.
   * @param limit Actual value of filter.
   */
  public void setFilter(boolean used, String limit) {
    this.used = used;
    this.limit = limit;
  }

  /**
   * Filter copy setter.
   *
   * @param filter Original filter object to be copied.
   */
  public void setFilter(Filter filter) {
    this.used = filter.used;
    this.limit = filter.limit;
  }

  /**
   * <code>used</code> value setter
   *
   * @param used Whether or not this filter will used in URL generation.
   */
  public void setUsed(boolean used) {
    this.used = used;
  }

  /**
   * <code>limit</code> value setter.
   *
   * @param limit Actual value of filter.
   */
  public void setLimit(String limit) { this.limit = limit; }

  /**
   * <code>used</code> getter.
   *
   * @return Whether or not this filter will used in URL generation.
   */
  public boolean getUsed() {
    return used;
  }

  /**
   * <code>limit</code> setter.
   *
   * @return Actual value of filter.
   */
  public String getLimit() {
    return limit;
  }
}
