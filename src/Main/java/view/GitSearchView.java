package org.felixlimanta.gitsearch.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import org.felixlimanta.gitsearch.controller.UserSearchController;

/**
 * Created by ASUS on 02/06/17.
 */
public class GitSearchView extends JFrame {
  private SearchPanel searchPanelObj;
  private ResultsPanel resultsPanelObj;
  private UserRepoPanel userRepoPanelObj;
  private UserSearchController control;

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

  private void createUIComponents() {
    searchPanelObj = new SearchPanel();
    resultsPanelObj = new ResultsPanel();
    userRepoPanelObj = new UserRepoPanel();

    searchPanel = searchPanelObj.getRootPanel();
    resultsPanel = resultsPanelObj.getRootPanel();
    userPanel = userRepoPanelObj.getRootPanel();
  }

  private void setUpController() {
    control = new UserSearchController(searchPanelObj);
    control.setResultsPanel(resultsPanelObj);
    searchPanelObj.setControl(control);
  }
}
