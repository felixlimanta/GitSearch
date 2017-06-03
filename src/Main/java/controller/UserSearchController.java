package org.felixlimanta.gitsearch.controller;

import org.felixlimanta.gitsearch.model.GitHubSearchUserUrlGenerator;
import org.felixlimanta.gitsearch.view.ResultsPanel;
import org.felixlimanta.gitsearch.view.SearchPanel;
import org.felixlimanta.gitsearch.view.UserRepoPanel;

/**
 * Created by ASUS on 03/06/17.
 */
public class UserSearchController {
  private SearchPanel searchPanel;
  private ResultsPanel resultsPanel;
  private UserRepoPanel userRepoPanel;
  private GitHubUserSearcher userSearcher;

  public UserSearchController(SearchPanel searchPanel) {
    this.searchPanel = searchPanel;
  }

  public void setSearchPanel(SearchPanel searchPanel) {
    this.searchPanel = searchPanel;
  }

  public void setResultsPanel(ResultsPanel resultsPanel) {
    this.resultsPanel = resultsPanel;
  }

  public void setUserRepoPanel(UserRepoPanel userRepoPanel) {
    this.userRepoPanel = userRepoPanel;
  }

  public void searchUsers() {
    String searchUrl = getSearchUrlFromView();
    retrieveUserSearchDataFromGitHub(searchUrl);
    displayResultsToView();
  }

  public void resetView() {
    searchPanel.resetPanel();
  }

  private String getSearchUrlFromView() {
    GitHubSearchUserUrlGenerator query =
        new GitHubSearchUserUrlGenerator(searchPanel.getQuery());
    query.setSearchIn(searchPanel.getSearchIn());
    query.setRepoFilter(searchPanel.getRepoFilter());
    query.setFollowerFilter(searchPanel.getFollowerFilter());
    return query.generateUrl();
  }

  private void retrieveUserSearchDataFromGitHub(String searchUrl) {
    userSearcher = new GitHubUserSearcher(searchUrl);
    userSearcher.retrieveDataFromGitHub();
  }

  private void displayResultsToView() {
    resultsPanel.setResultsList(userSearcher.getResponse().getItems());
  }
}
