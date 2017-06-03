package org.felixlimanta.gitsearch.controller;

import org.felixlimanta.gitsearch.model.GitHubSearchUserUrlGenerator;
import org.felixlimanta.gitsearch.model.GitHubUser;
import org.felixlimanta.gitsearch.view.GitSearchView;
import org.felixlimanta.gitsearch.view.ResultsPanel;
import org.felixlimanta.gitsearch.view.SearchPanel;
import org.felixlimanta.gitsearch.view.UserRepoPanel;

/**
 * Created by ASUS on 03/06/17.
 */
public class GitSearchController {
  private GitSearchView gitSearchView;
  private SearchPanel searchPanel;
  private ResultsPanel resultsPanel;
  private UserRepoPanel userRepoPanel;

  private GitHubUserSearcher userSearcher;
  private GitHubUserRepositoryGetter repoGetter;

  public void setGitSearchView(GitSearchView gitSearchView) {
    this.gitSearchView = gitSearchView;
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
    displaySearchResultsToView();
  }

  public void getRepos() {
    GitHubUser user = getUser();
    retrieveReposFromGitHub(user);
    displayReposToView(user);
  }

  public void resetView() {
    searchPanel.resetPanel();
    resultsPanel.resetPanel();
    userRepoPanel.resetPanel();
    gitSearchView.switchTabToSearchResults();
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

  private void displaySearchResultsToView() {
    resultsPanel.populateResultsList(userSearcher.getResponse().getItems());
  }

  private GitHubUser getUser() {
    return userSearcher.getResponse().getItems().get(
        resultsPanel.getSelectedUserIndex()
    );
  }

  private void retrieveReposFromGitHub(GitHubUser user) {
    repoGetter = new GitHubUserRepositoryGetter(user);
    repoGetter.retrieveDataFromGitHub();
    user.setRepos(repoGetter.getResponse());
  }

  private void displayReposToView(GitHubUser user) {
    userRepoPanel.populateRepoPanel(user);
    gitSearchView.switchTabToUserRepo();
  }
}
