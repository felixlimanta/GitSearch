package org.felixlimanta.gitsearch.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

/**
 * Created by ASUS on 02/06/17.
 */
public class GitSearchView extends JFrame {

  private JPanel searchPanel;
  private JPanel rootPanel;
  private JTabbedPane resultsTabbedPane;
  private JPanel searchResultsPanel;
  private JPanel userPanel;

  public GitSearchView() {
    super("GitSearch");
    setContentPane(rootPanel);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  private void createUIComponents() {
    searchPanel = (new SearchPanel()).getRootPanel();
    searchResultsPanel = (new SearchResultsPanel()).getRootPanel();
    userPanel = (new UserRepoPanel()).getRootPanel();
  }
}
