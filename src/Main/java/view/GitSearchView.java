package org.felixlimanta.gitsearch.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import org.felixlimanta.gitsearch.controller.GitSearchController;

/**
 * Created by ASUS on 02/06/17.
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

  public GitSearchView() {
    super("GitSearch");
    setUpController();

    setContentPane(rootPanel);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  public void switchTabToSearchResults() {
    resultsTabbedPane.setSelectedIndex(0);
  }

  public void switchTabToUserRepo() {
    resultsTabbedPane.setSelectedIndex(1);
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
