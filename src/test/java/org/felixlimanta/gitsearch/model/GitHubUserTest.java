package org.felixlimanta.gitsearch.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;
import org.felixlimanta.gitsearch.controller.GitHubUserRepositoryGetter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for <code>GitHubUser</code> model class
 *
 * <p>All tests are done with random values generated before each test.</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-06-05
 * @see GitHubUser
 */
class GitHubUserTest {

  /**
   * GitHubUser object for testing
   */
  private static GitHubUser user;

  /**
   * SecureRandom object for random string generation.
   */
  private static SecureRandom random;

  /**
   * Random integer for testing.
   */
  private int randomInt;

  /**
   * Random string for testing.
   */
  private String randomString;

  /**
   * Sets up a new <code>GitHubUser</code> object for testing.
   */
  @BeforeAll
  static void setUp() {
    user = new GitHubUser();
    random = new SecureRandom();
  }

  /**
   * Generates random values for testing.
   */
  @BeforeEach
  void setUpTest() {
    randomInt = ThreadLocalRandom.current().nextInt();
    randomString = new BigInteger(130, random).toString(32);
  }

  /**
   * Tests user ID setter and getter.
   */
  @Test
  void setId() {
    user.setId(randomInt);
    assertEquals(randomInt, user.getId(), "User ID mismatch");
  }

  /**
   * Tests username setter and getter.
   */
  @Test
  void setUsername() {
    user.setUsername(randomString);
    assertEquals(randomString, user.getUsername(), "Username mismatch");
  }

  /**
   * Tests API URL setter and getter.
   *
   * <p>URL validity is ignored; only setter correctness is tested</p>
   */
  @Test
  void setUrl() {
    user.setUrl(randomString);
    assertEquals(randomString, user.getUrl(), "API URL mismatch");
  }

  /**
   * Tests HTML URL setter and getter.
   *
   * <p>URL validity is ignored; only setter correctness is tested</p>
   */
  @Test
  void setHtmlUrl() {
    user.setHtmlUrl(randomString);
    assertEquals(randomString, user.getHtmlUrl(), "HTML URL mismatch");
  }

  /**
   * Tests repositories URL setter and getter.
   *
   * <p>URL validity is ignored; only setter correctness is tested</p>
   */
  @Test
  void setReposUrl() {
    user.setReposUrl(randomString);
    assertEquals(randomString, user.getReposUrl(), "Repositories URL mismatch");
  }

  /**
   * Tests user account type setter and getter.
   */
  @Test
  void setType() {
    user.setType(randomString);
    assertEquals(randomString, user.getType(), "User account type mismatch");
  }

  /**
   * Tests user repositories setter and getter
   */
  @Test
  void setRepos() {
    final String url = "https://api.github.com/users/octocat/repos";
    GitHubUserRepositoryGetter g = new GitHubUserRepositoryGetter(url);
    g.retrieveDataFromGitHub();

    user.setRepos(g.getResponse());
    assertEquals(g.getResponse(), user.getRepos(), "Repositories mismatch");
  }
}