import controller.GitHubUserGetter;
import java.util.ArrayList;
import model.GitHubUser;

/**
 * Created by ASUS on 28/05/17.
 */
public class Main {
  private static final String USER_AGENT = "Mozilla/5.0";

  public static void main(String args[]) throws Exception {
    String url = "https://api.github.com/search/users?q=tom+repos:%3E%3D42+followers:%3E1000";
    GitHubUserGetter g = new GitHubUserGetter(url);
    g.getJson();
    g.deserializeJson();

    ArrayList<GitHubUser> users = g.getResponse().getItems();
    for (GitHubUser u: users) {
      System.out.printf("%d: %s\n", u.getId(), u.getLogin());
    }
  }
}
