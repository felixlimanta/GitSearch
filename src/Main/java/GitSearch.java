package org.felixlimanta.gitsearch;

import java.util.ArrayList;
import org.felixlimanta.gitsearch.controller.GitHubUserRepositoryGetter;
import org.felixlimanta.gitsearch.controller.GitHubUserSearcher;
import org.felixlimanta.gitsearch.model.GitHubRepository;
import org.felixlimanta.gitsearch.model.GitHubSearchUserUrlGenerator;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Created by ASUS on 28/05/17.
 */
public class GitSearch {
  public static void main(String args[]) throws Exception {
    GitHubSearchUserUrlGenerator query = new GitHubSearchUserUrlGenerator("holy");
    query.setSearchIn(1);
    query.setRepoFilter(true, ">=", 5);
    query.setFollowerFilter(true, "<=", 10);
    String url = query.generateUrl();

    GitHubUserSearcher g = new GitHubUserSearcher(url);
    g.retrieveDataFromGitHub();

    ArrayList<GitHubUser> users = g.getResponse().getItems();
    for (GitHubUser u: users) {
      System.out.printf("%d: %s | %s\n", u.getId(), u.getUsername(), u.getHtmlUrl());
    }
    System.out.println();

    for (GitHubUser u: users) {
      GitHubUserRepositoryGetter r = new GitHubUserRepositoryGetter(u);
      r.retrieveDataFromGitHub();
      u.setRepos(r.getResponse());

      System.out.printf("%d: %s | %s\n", u.getId(), u.getUsername(), u.getHtmlUrl());
      for (GitHubRepository rep: r.getResponse()) {
        System.out.printf("\t%d: %s | %s | %s\n", rep.getId(), rep.getName(), rep.getDescription(), rep.getHtmlUrl());
      }
      System.out.printf("%d repos\n\n", u.getRepos().size());
    }
  }
}
