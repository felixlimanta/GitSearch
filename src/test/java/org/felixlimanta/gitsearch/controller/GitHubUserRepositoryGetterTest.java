package org.felixlimanta.gitsearch.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;
import java.util.ArrayList;
import org.felixlimanta.gitsearch.model.GitHubRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for <code>GitHubUserRepositoryGetter</code> controller class
 *
 * <p>Testing for classes <code>GitHubApiGetter</code> and
 * <code>GitHubRepositoryDeserializer</code> is also executed here.</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-06-05
 * @see GitHubUserRepositoryGetter
 */
class GitHubUserRepositoryGetterTest {

  /**
   * Test URL as per the example given at GitHub Search API documentation.
   */
  private final static String url =
      "https://api.github.com/users/octocat/repos";

  /**
   * GitHubUserRepositoryGetter object for testing.
   */
  private static GitHubUserRepositoryGetter g;

  /**
   * Sets up objects for testing.
   */
  @BeforeAll
  static void setUpAll() {
    g = new GitHubUserRepositoryGetter(url);
  }

  /**
   * Tests URL setter and getter from parent class.
   */
  @Test
  void setUrl() {
    g.setUrl(url);
    assertEquals(url, g.getUrl(), "URL mismatch");
  }

  /**
   * Tests validity of JSON retrieved from GitHub
   */
  @Test
  void getAndFormatJson() {
    g.getJsonFromGitHub();
    String json = g.getJsonString();

    boolean isValid;
    try {
      new Gson().fromJson(json, Object.class);
      isValid = true;
    } catch(com.google.gson.JsonSyntaxException ex) {
      isValid = false;
    }
    assertTrue(isValid, "Invalid JSON");
  }

  /**
   * Tests data retrieval from GitHub. Also tests JSON deserialization with deserializeJson method
   * and GitHubRepositoryDeserializer class.
   */
  @Test
  void retrieveDataFromGitHub() {
    g.retrieveDataFromGitHub();
    ArrayList<GitHubRepository> r = g.getResponse();
    assertNotNull(r, "Results cannot be null");
  }
}