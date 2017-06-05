package org.felixlimanta.gitsearch.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;
import org.felixlimanta.gitsearch.model.GitHubSearchResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for <code>GitHubUserSearcher</code> controller class
 *
 * <p>Testing for classes <code>GitHubApiGetter</code> and <code>GitHubUserDeserializer</code>
 * is also executed here.</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-06-05
 * @see GitHubUserSearcher
 */
class GitHubUserSearcherTest {

  /**
   * Test URL as per the example given at GitHub Search API documentation.
   */
  private final static String url =
      "https://api.github.com/search/users?q=tom+repos:%3E42+followers:%3E1000";

  /**
   * GitHubUserSearcher object for testing.
   */
  private static GitHubUserSearcher g;

  /**
   * Sets up objects for testing.
   */
  @BeforeAll
  static void setUpAll() {
    g = new GitHubUserSearcher(url);
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
   * and GitHubUserDeserializer class.
   */
  @Test
  void retrieveDataFromGitHub() {
    g.retrieveDataFromGitHub();
    GitHubSearchResponse r = g.getResponse();
    assertNotNull(r.getItems(), "Results cannot be null");
    assertEquals(r.getTotalCount(), r.getItems().size(),
        "Unequal total results count and actual number of results");
  }
}