package org.felixlimanta.gitsearch.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import org.felixlimanta.gitsearch.controller.GitSearchController;

/**
 * GitSearch application main frame
 *
 * <p>Top-level container for all view panels</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-06-02
 */
public class GitSearchView extends JFrame {
  private SearchPanel searchPanelObj;
  private ResultsPanel resultsPanelObj;
  private UserRepoPanel userRepoPanelObj;
  private GitSearchController control;

  private JPanel searchPanel;
  private JPanel rootPanel;
  private JTabbedPane resultsTabbedPane;
  private JPanel resultsPanel;
  private JPanel userPanel;

  /**
   * Constructor
   *
   * <p>Sets up view components and launch application.</p>
   *
   * <p>Create a new GitSearchView object to launch application from main method.</p>
   */
  public GitSearchView() {
    super("GitSearch");
    resultsTabbedPane.setEnabledAt(1, false);
    setUpController();

    setContentPane(rootPanel);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  /**
   * Switches current tab to search results tab
   */
  public void switchTabToSearchResults() {
    resultsTabbedPane.setSelectedIndex(0);
  }

  /**
   * Switches current tab to user repositories tab and enables it
   */
  public void switchTabToUserRepo() {
    resultsTabbedPane.setEnabledAt(1, true);
    resultsTabbedPane.setSelectedIndex(1);
  }

  /**
   * Resets tab focus and disables user repositories tab
   */
  public void resetPanel() {
    switchTabToSearchResults();
    resultsTabbedPane.setEnabledAt(1, false);
  }

  private void createUIComponents() {
    searchPanelObj = new SearchPanel();
    resultsPanelObj = new ResultsPanel();
    userRepoPanelObj = new UserRepoPanel();

    searchPanel = searchPanelObj.getRootPanel();
    resultsPanel = resultsPanelObj.getRootPanel();
    userPanel = userRepoPanelObj.getRootPanel();
  }

  private void setUpController() {
    control = new GitSearchController();
    control.setGitSearchView(this);
    control.setSearchPanel(searchPanelObj);
    control.setResultsPanel(resultsPanelObj);
    control.setUserRepoPanel(userRepoPanelObj);
    searchPanelObj.setControl(control);
    resultsPanelObj.setControl(control);
  }
}
