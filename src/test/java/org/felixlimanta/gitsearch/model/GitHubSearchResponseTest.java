package org.felixlimanta.gitsearch.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for <code>GitHubSearchResponse</code> model class
 *
 * <p>All tests are done with random values generated before each test. Testing for the generic
 * class <code>GitHubSearchResponse&3CT&3E</code> is represented by the class
 * <code>GitHubSearchResponse&3CInteger&3E</code>.</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-06-05
 * @see GitHubSearchResponse
 */
class GitHubSearchResponseTest {

  /**
   * GitHubSearchResponse object for testing.
   */
  private static GitHubSearchResponse<Integer> r;

  /**
   * Random Boolean value for testing.
   */
  private boolean randomBoolean;

  /**
   * Random integer for testing.
   */
  private int randomInt;

  /**
   * Sets up objects for testing.
   */
  @BeforeAll
  static void setUp() {
    r = new GitHubSearchResponse<>();
  }

  /**
   * Generates random values for testing.
   */
  @BeforeEach
  void setUpTest() {
    randomBoolean = ThreadLocalRandom.current().nextBoolean();
    randomInt = ThreadLocalRandom.current().nextInt();
  }

  /**
   * Tests total count setter and getter.
   */
  @Test
  void setTotalCount() {
    r.setTotalCount(randomInt);
    assertEquals(randomInt, r.getTotalCount(), "Total count mismatch");
  }

  /**
   * Tests completeness of results setter and getter.
   */
  @Test
  void isIncompleteResults() {
    r.setIncompleteResults(randomBoolean);
    assertEquals(randomBoolean, r.isIncompleteResults(), "Completeness of results mismatch");
  }

  /**
   * Tests items setter and getter with an array of randomly generated Integers.
   */
  @Test
  void setItems() {
    ArrayList<Integer> items = new ArrayList<>();
    int size = Math.abs(ThreadLocalRandom.current().nextInt()) % 100 + 1;
    for (int i = 0; i < size; ++i) {
      items.add(ThreadLocalRandom.current().nextInt());
    }

    r.setItems(items);
    assertEquals(items, r.getItems(), "Items mismatch");
  }
}