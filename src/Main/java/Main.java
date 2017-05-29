import controller.GitHubUserRepositoryGetter;
import controller.GitHubUserSearcher;
import java.util.ArrayList;
import model.GitHubRepository;
import model.GitHubSearchUserQuery;
import model.GitHubUser;

/**
 * Created by ASUS on 28/05/17.
 */
public class Main {
  public static void main(String args[]) throws Exception {
    GitHubSearchUserQuery query = new GitHubSearchUserQuery("holy");
    query.setSearchIn(1);
    query.setRepoFilter(true, ">=", 5);
    query.setFollowerFilter(true, "<=", 10);
    String url = query.generateUrl();

    GitHubUserSearcher g = new GitHubUserSearcher(url);
    g.getJson();
    g.deserializeJson();

    ArrayList<GitHubUser> users = g.getResponse().getItems();
    for (GitHubUser u: users) {
      System.out.printf("%d: %s | %s\n", u.getId(), u.getUsername(), u.getHtmlUrl());
    }
    System.out.println();

    for (GitHubUser u: users) {
      GitHubUserRepositoryGetter r = new GitHubUserRepositoryGetter(u);
      r.getJson();
      r.deserializeJson();
      u.setRepos(r.getRepos());

      System.out.printf("%d: %s | %s\n", u.getId(), u.getUsername(), u.getHtmlUrl());
      for (GitHubRepository rep: r.getRepos()) {
        System.out.printf("\t%d: %s | %s | %s\n", rep.getId(), rep.getName(), rep.getDescription(), rep.getHtmlUrl());
      }
      System.out.printf("%d repos\n\n", u.getRepos().size());
    }
  }
}
