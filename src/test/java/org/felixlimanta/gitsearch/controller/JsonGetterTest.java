package org.felixlimanta.gitsearch.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for <code>JsonGetter</code> controller class
 *
 * <p>All tests are done with data from JSONPlaceholder REST API</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-06-05
 * @see JsonGetter
 * @see <a href="https://jsonplaceholder.typicode.com/">JSON placeholder generator</a>
 */
class JsonGetterTest {

  /**
   * URL for <code>GET</code> operation
   */
  private static final String url =
      "https://jsonplaceholder.typicode.com/posts/1";

  /**
   * Expected formatted JSON value
   */
  private static final String expectedJson =
      "{\n"
      + "  \"userId\": 1,\n"
      + "  \"id\": 1,\n"
      + "  \"title\": \"sunt aut facere repellat provident occaecati "
      + "excepturi optio reprehenderit\",\n"
      + "  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et "
      + "cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est "
      + "autem sunt rem eveniet architecto\"\n"
      + "}";

  /**
   * JsonGetter object for testing.
   */
  private static JsonGetter g;

  /**
   * Sets up a new <code>JsonGetter</code> object for testing.
   *
   * <p>Actual <code>GET</code> operation also performed here.</p>
   */
  @BeforeAll
  static void setUp() {
    g = new JsonGetter(url);
  }

  /**
   * Tests URL for <code>GET</code>.
   */
  @Test
  void getUrl() {
    assertEquals(url, g.getUrl(), "URL mismatch");
  }

  /**
   * Tests getJson method and formatJson method.
   */
  @Test
  void getFormattedJson() {
    assertEquals(expectedJson, g.getFormattedJson(), "JSON mismatch");
  }

  /**
   * Tests HTTP status code for a successful operation.
   */
  @Test
  void getStatus() {
    assertEquals(200, g.getStatus());
  }

  /**
   * Tests HTTP status message for a successful operation.
   */
  @Test
  void getMessage() {
    assertEquals("OK", g.getMessage());
  }
}