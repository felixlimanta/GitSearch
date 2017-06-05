package org.felixlimanta.gitsearch.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for <code>GitHubRepository</code> model class
 *
 * <p>All tests are done with random values generated before each test.</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-06-05
 * @see GitHubRepository
 */
class GitHubRepositoryTest {

  /**
   * GitHubRepository object for testing.
   */
  private static GitHubRepository repo;

  /**
   * SecureRandom object for random string generation.
   */
  private static SecureRandom random;

  /**
   * Random Boolean value for testing.
   */
  private boolean randomBoolean;

  /**
   * Random integer for testing.
   */
  private int randomInt;

  /**
   * Random string for testing.
   */
  private String randomString;

  /**
   * Sets up a new <code>GitHubRepository</code> object for testing.
   */
  @BeforeAll
  static void setUp() {
    repo = new GitHubRepository();
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
   * Tests repository ID setter and getter.
   */
  @Test
  void setId() {
    repo.setId(randomInt);
    assertEquals(randomInt, repo.getId(), "Repository ID mismatch");
  }

  /**
   * Tests owner ID setter and getter.
   */
  @Test
  void setOwnerId() {
    repo.setOwnerId(randomInt);
    assertEquals(randomInt, repo.getOwnerId(), "Owner ID mismatch");
  }

  /**
   * Tests repository name setter and getter.
   */
  @Test
  void setName() {
    repo.setName(randomString);
    assertEquals(randomString, repo.getName(), "Repository name mismatch");
  }

  /**
   * Tests repository description setter and getter.
   */
  @Test
  void setDescription() {
    repo.setDescription(randomString);
    assertEquals(randomString, repo.getDescription(), "Description mismatch");
  }

  /**
   * Tests repository access level setter and getter.
   */
  @Test
  void setPrivate() {
    repo.setPrivate(randomBoolean);
    assertEquals(randomBoolean, repo.isPrivate(), "Access level mismatch");
  }

  /**
   * Tests repository API URL setter and getter.
   *
   * <p>URL validity is ignored; only setter correctness is tested</p>
   */
  @Test
  void setUrl() {
    repo.setUrl(randomString);
    assertEquals(randomString, repo.getUrl(), "API URL mismatch");
  }

  /**
   * Tests repository HTML URL setter and getter.
   *
   * <p>URL validity is ignored; only setter correctness is tested</p>
   */
  @Test
  void setHtmlUrl() {
    repo.setHtmlUrl(randomString);
    assertEquals(randomString, repo.getHtmlUrl(), "HTML URL mismatch");
  }

}