package org.felixlimanta.gitsearch.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;
import org.felixlimanta.gitsearch.controller.JsonGetter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for <code>GitHubSearchUserUrlGenerator</code> model class
 *
 * <p>All tests are done with random values generated before each test.</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-06-05
 * @see GitHubSearchUserUrlGenerator
 */
class GitHubSearchUserUrlGeneratorTest {

  /**
   * GitHubSearchUserUrlGenerator object for testing.
   */
  private static GitHubSearchUserUrlGenerator g;
  
  /**
   * SecureRandom object for random string generation.
   */
  private static SecureRandom random;

  /**
   * Random integer for testing.
   */
  private boolean randomBoolean;

  /**
   * Random Boolean value for testing.
   */
  private Integer randomInt;

  /**
   * Random string for testing.
   */
  private String randomString;
  
  /**
   * Sets up objects for testing.
   */
  @BeforeAll
  static void setUpAll() {
    g = new GitHubSearchUserUrlGenerator("");
    random = new SecureRandom();
  }

  /**
   * Generates random values for testing.
   */
  @BeforeEach
  void setUpTest() {
    randomBoolean = ThreadLocalRandom.current().nextBoolean();
    randomInt = ThreadLocalRandom.current().nextInt();
    randomString = new BigInteger(130, random).toString(32);
  }

  /**
   * Tests query setter and getter.
   */
  @Test
  void setQuery() {
    g.setQuery(randomString);
    assertEquals(randomString, g.getQuery(), "Query mismatch");
  }

  /**
   * Tests searchIn value setter and getter.
   */
  @Test
  void setSearchIn() {
    int x = Math.abs(randomInt % 4);
    g.setSearchIn(x);
    assertEquals(x, g.getSearchIn(), "searchIn value mismatch");
  }

  /**
   * Tests repository used value setter and repository filter getter.
   */
  @Test
  void setRepoUsed() {
    g.setRepoUsed(randomBoolean);
    assertEquals(randomBoolean, g.getRepoFilter().getUsed(),
        "Repository used value mismatch");
  }

  /**
   * Tests repository filter setter and getter.
   */
  @Test
  void setRepoFilterComponents() {
    g.setRepoFilter(randomBoolean, randomInt.toString());
    assertAll(
        () -> assertEquals(randomBoolean, g.getRepoFilter().getUsed(),
            "Repository used value mismatch"),
        () -> assertEquals(randomInt.toString(), g.getRepoFilter().getLimit(),
            "Repository limit mismatch")
    );
  }

  /**
   * Tests repository filter copy setter and getter.
   */
  @Test
  void setRepoFilterCopy() {
    g.setRepoFilter(new Filter(randomBoolean, randomInt.toString()));
    assertAll(
        () -> assertEquals(randomBoolean, g.getRepoFilter().getUsed(),
            "Repository used value mismatch"),
        () -> assertEquals(randomInt.toString(), g.getRepoFilter().getLimit(),
            "Repository limit mismatch")
    );
  }

  /**
   * Tests follower used value setter and follower filter getter.
   */
  @Test
  void setFollowerUsed() {
    g.setFollowerUsed(randomBoolean);
    assertEquals(randomBoolean, g.getFollowerFilter().getUsed(),
        "Follower used value mismatch");
  }

  /**
   * Tests follower filter setter and getter.
   */
  @Test
  void setFollowerFilterComponents() {
    g.setFollowerFilter(randomBoolean, randomInt.toString());
    assertAll(
        () -> assertEquals(randomBoolean, g.getFollowerFilter().getUsed(),
            "Follower used value mismatch"),
        () -> assertEquals(randomInt.toString(), g.getFollowerFilter().getLimit(),
            "Follower limit mismatch")
    );
  }

  /**
   * Tests follower filter copy setter and getter.
   */
  @Test
  void setFollowerFilterCopy() {
    g.setFollowerFilter(new Filter(randomBoolean, randomInt.toString()));
    assertAll(
        () -> assertEquals(randomBoolean, g.getFollowerFilter().getUsed(),
            "Follower used value mismatch"),
        () -> assertEquals(randomInt.toString(), g.getFollowerFilter().getLimit(),
            "Follower limit mismatch")
    );
  }

  /**
   * Tests validity of generated URL. If the URL is valid, a GET operation will return
   * a 200 OK response.
   */
  @Test
  void generateUrl() {
    g.setQuery(randomString);
    g.setSearchIn(Math.abs(randomInt % 4));
    setUpTest();
    g.setRepoFilter(randomBoolean, randomInt.toString());
    setUpTest();
    g.setFollowerFilter(randomBoolean, randomInt.toString());
    
    String url = g.generateUrl();
    JsonGetter getter = new JsonGetter(url);
    assertEquals(200, getter.getStatus(), "Generated URL is invalid");
  }
}