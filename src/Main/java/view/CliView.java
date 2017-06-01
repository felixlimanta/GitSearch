package org.felixlimanta.gitsearch.view;

import java.util.ArrayList;
import java.util.Scanner;
import org.felixlimanta.gitsearch.controller.GitHubUserRepositoryGetter;
import org.felixlimanta.gitsearch.controller.GitHubUserSearcher;
import org.felixlimanta.gitsearch.model.GitHubRepository;
import org.felixlimanta.gitsearch.model.GitHubSearchUserUrlGenerator;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Created by ASUS on 01/06/17.
 */
public class CliView {
  private Scanner scan;
  private String url;
  private ArrayList<GitHubUser> users;

  public CliView() {
    scan = new Scanner(System.in);
    getUserInput();
    searchUsers();
    getRepo();
  }

  public void getUserInput() {
    System.out.print("Query: ");
    String queryString = scan.nextLine();
    GitHubSearchUserUrlGenerator query = new GitHubSearchUserUrlGenerator(queryString);
    System.out.println();

    System.out.println("0. Search all");
    System.out.println("1. Search username");
    System.out.println("2. Search email");
    System.out.println("3. Search full name");
    System.out.print(": ");
    int searchIn = scan.nextInt();
    query.setSearchIn(searchIn);
    System.out.println();

    System.out.print("Limit repositories? [y/N] ");
    char repo = scan.next().charAt(0);
    if (repo == 'Y' || repo == 'y') {
      System.out.printf("\t\ta: exactly a repositories\n");
      System.out.printf("\t\ta..b: between a and b repositories\n");
      System.out.printf("\t\t>a: more than a repositories. Operator: > / < / >= / >=\n");
      System.out.print("Limit: ");
      String limit = scan.next();
      query.setRepoFilter(true, limit);
    } else {
      query.setRepoUsed(false);
    }
    System.out.println();

    System.out.print("Limit followers? [y/N] ");
    char follower = scan.next().charAt(0);
    if (follower == 'Y' || follower == 'y') {
      System.out.printf("\t\ta: exactly a followers\n");
      System.out.printf("\t\ta..b: between a and b followers\n");
      System.out.printf("\t\t>a: more than a followers. Operator: > / < / >= / >=\n");
      System.out.print("Limit: ");
      String limit = scan.next();
      query.setFollowerFilter(true, limit);
    } else {
      query.setFollowerUsed(false);
    }
    System.out.println();

    url = query.generateUrl();
    System.out.println(url);
  }

  public void searchUsers() {
    GitHubUserSearcher g = new GitHubUserSearcher(url);
    g.retrieveDataFromGitHub();

    users = g.getResponse().getItems();
    for (int i = 0; i < users.size(); ++i) {
      System.out.printf("%d. %s | %s\n", i, users.get(i).getUsername(), users.get(i).getHtmlUrl());
    }
    System.out.println();
  }

  public void getRepo() {
    System.out.print("User to get repos: ");
    int i = scan.nextInt();

    GitHubUserRepositoryGetter r = new GitHubUserRepositoryGetter(users.get(i));
    r.retrieveDataFromGitHub();
    users.get(i).setRepos(r.getResponse());

    System.out.printf("%d: %s | %s\n", users.get(i).getId(), users.get(i).getUsername(), users.get(i).getHtmlUrl());
    for (GitHubRepository rep: r.getResponse()) {
      System.out.printf("\t%d: %s | %s | %s\n", rep.getId(), rep.getName(), rep.getDescription(), rep.getHtmlUrl());
    }
    System.out.printf("%d repos\n\n", users.get(i).getRepos().size());
  }
}
