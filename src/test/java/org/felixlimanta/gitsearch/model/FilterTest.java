package org.felixlimanta.gitsearch.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for <code>Filter</code> model class
 *
 * <p>All tests are done with random values generated before each test.</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-06-05
 * @see Filter
 */
class FilterTest {

  /**
   * Filter object for testing.
   */
  private static Filter filter;

  /**
   * SecureRandom object for random string generation.
   */
  private static SecureRandom random;

  /**
   * Random Boolean value for testing.
   */
  private boolean randomBoolean;

  /**
   * Random string for testing.
   */
  private String randomString;


  /**
   * Sets up a new <code>Filter</code> object for testing
   */
  @BeforeAll
  static void setUp() {
    filter = new Filter();
    random = new SecureRandom();
  }

  /**
   * Generates random values for testing.
   */
  @BeforeEach
  void setUpTest() {
    randomBoolean = ThreadLocalRandom.current().nextBoolean();
    randomString = new BigInteger(130, random).toString(32);
  }

  /**
   * Tests filter components setter and getter.
   */
  @Test
  void setFilterComponents() {
    filter.setFilter(randomBoolean, randomString);
    assertAll(
        () -> assertEquals(randomBoolean, filter.getUsed(), "Used value mismatch"),
        () -> assertEquals(randomString, filter.getLimit(), "Limit mismatch")
    );
  }

  /**
   * Tests constructor with parameters and copy setter and getter.
   */
  @Test
  void setFilterCopy() {
    filter.setFilter(new Filter(randomBoolean, randomString));
    assertAll(
        () -> assertEquals(randomBoolean, filter.getUsed(), "Used value mismatch"),
        () -> assertEquals(randomString, filter.getLimit(), "Limit mismatch")
    );
  }

  /**
   * Tests used value setter and getter.
   */
  @Test
  void setUsed() {
    filter.setUsed(randomBoolean);
    assertEquals(randomBoolean, filter.getUsed(), "Used value mismatch");
  }

  /**
   * Tests limit setter and getter.
   */
  @Test
  void setLimit() {
    filter.setLimit(randomString);
    assertEquals(randomString, filter.getLimit(), "Limit mismatch");
  }
}