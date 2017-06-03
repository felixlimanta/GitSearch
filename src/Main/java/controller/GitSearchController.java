package org.felixlimanta.gitsearch.controller;

import org.felixlimanta.gitsearch.model.GitHubSearchUserUrlGenerator;
import org.felixlimanta.gitsearch.model.GitHubUser;
import org.felixlimanta.gitsearch.view.GitSearchView;
import org.felixlimanta.gitsearch.view.ResultsPanel;
import org.felixlimanta.gitsearch.view.SearchPanel;
import org.felixlimanta.gitsearch.view.UserRepoPanel;

/**
 * Central controller for the whole application
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-06-03
 */
public class GitSearchController {

  /**
   * GitSearch application main frame
   */
  private GitSearchView gitSearchView;

  /**
   * Search panel object
   *
   * @see SearchPanel
   */
  private SearchPanel searchPanel;

  /**
   * Results panel object
   *
   * @see ResultsPanel
   */
  private ResultsPanel resultsPanel;

  /**
   * User repositories panel object
   *
   * @see UserRepoPanel
   */
  private UserRepoPanel userRepoPanel;

  /**
   * GitHub User Searcher object
   *
   * @see GitHubUserSearcher
   */
  private GitHubUserSearcher userSearcher;

  /**
   * GitHub user repositories getter object
   *
   * @see GitHubUserRepositoryGetter
   */
  private GitHubUserRepositoryGetter repoGetter;

  /**
   * GitSearch main frame setter
   *
   * @param gitSearchView GitSearch main frame
   */
  public void setGitSearchView(GitSearchView gitSearchView) {
    this.gitSearchView = gitSearchView;
  }

  /**
   * Search panel setter
   *
   * @param searchPanel Search panel
   */
  public void setSearchPanel(SearchPanel searchPanel) {
    this.searchPanel = searchPanel;
  }

  /**
   * Results panel setter
   *
   * @param resultsPanel Results panel
   */
  public void setResultsPanel(ResultsPanel resultsPanel) {
    this.resultsPanel = resultsPanel;
  }

  /**
   * User repositories panel setter
   *
   * @param userRepoPanel User repositories panel
   */
  public void setUserRepoPanel(UserRepoPanel userRepoPanel) {
    this.userRepoPanel = userRepoPanel;
  }

  /**
   * Controls searching users
   */
  public void searchUsers() {
    String searchUrl = getSearchUrlFromView();
    retrieveUserSearchDataFromGitHub(searchUrl);
    displaySearchResultsToView();
  }

  /**
   * Controls getting user repositories
   */
  public void getRepos() {
    GitHubUser user = getUser();
    retrieveReposFromGitHub(user);
    displayReposToView(user);
  }

  /**
   * Controls resetting application view
   */
  public void resetView() {
    searchPanel.resetPanel();
    resultsPanel.resetPanel();
    userRepoPanel.resetPanel();
    gitSearchView.resetPanel();
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
