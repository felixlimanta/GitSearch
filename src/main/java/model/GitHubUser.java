package model;

/**
 * The GitHubUser class contains fields for a GitHub User
 * deserialized from a GitHub User's JSON object.
 *
 * @author  Felix Limanta
 * @since   2017-05-29
 */
public class GitHubUser {
  private String login;
  private int id;
  private String avatarUrl;
  private String gravatarId;
  private String url;
  private String htmlUrl;
  private String followersUrl;
  private String subscriptionsUrl;
  private String organizationsUrl;
  private String reposUrl;
  private String receivedEventsUrl;
  private String type;
  private float score;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
