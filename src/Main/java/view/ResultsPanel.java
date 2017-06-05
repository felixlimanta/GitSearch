package org.felixlimanta.gitsearch.view;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.felixlimanta.gitsearch.controller.GitSearchController;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Results panel.
 *
 * <p>Displays search results and receives which user to get repositories</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-06-02
 */
public class ResultsPanel {

  private JPanel rootPanel;

  private JButton getRepositoriesButton;
  private JScrollPane resultsContainer;
  private DefaultListModel<String> listModel;
  private JList<String> resultsList;

  private GitSearchController control;

  /**
   * Constructor
   *
   * <p>Sets up listeners for Swing components</p>
   */
  public ResultsPanel() {
    setUpListListener();
    setUpGetRepoButtonListener();
  }

  /**
   * Root panel getter for nested panel purposes
   *
   * @return Root JPanel for SearchPanel
   */
  public JPanel getRootPanel() {
    return rootPanel;
  }

  /**
   * Username index getter
   *
   * @return Index of selected username whose repositories are to be fetched
   */
  public int getSelectedUserIndex() {
    return resultsList.getSelectedIndex();
  }

  /**
   * Control setter
   *
   * @param control Top-level controller object
   */
  public void setControl(GitSearchController control) {
    this.control = control;
  }

  /**
   * Populates results list with usernames of search results
   *
   * @param result List of users from search results
   */
  public void populateResultsList(ArrayList<GitHubUser> result) {
    listModel.clear();
    for (GitHubUser u: result) {
      listModel.addElement(u.getUsername());
    }
  }

  /**
   * Resets panel
   *
   * <p>Clears results list, disables button</p>
   */
  public void resetPanel() {
    listModel.clear();
    getRepositoriesButton.setEnabled(false);
  }

  private void createUIComponents() {
    listModel = new DefaultListModel<>();
    resultsList = new JList<>(listModel);
  }

  private void setUpListListener() {
    resultsList.addListSelectionListener(e -> {
      if (!e.getValueIsAdjusting()) {
        getRepositoriesButton.setEnabled(resultsList.getSelectedIndex() != -1);
      }
    });
  }

  private void setUpGetRepoButtonListener() {
    getRepositoriesButton.addActionListener(e -> {
      if (control != null) {
        control.getRepos();
      }
    });
  }
}
