package org.felixlimanta.gitsearch.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.felixlimanta.gitsearch.controller.GitSearchController;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Created by ASUS on 02/06/17.
 */
public class ResultsPanel {

  private JPanel rootPanel;

  private JButton getRepositoriesButton;
  private JScrollPane resultsContainer;
  private DefaultListModel<String> listModel;
  private JList<String> resultsList;

  private GitSearchController control;

  public ResultsPanel() {
    setUpListListener();
    setUpGetRepoButtonListener();
  }

  public JPanel getRootPanel() {
    return rootPanel;
  }

  public int getSelectedUserIndex() {
    return resultsList.getSelectedIndex();
  }

  public void setControl(GitSearchController control) {
    this.control = control;
  }

  public void populateResultsList(ArrayList<GitHubUser> result) {
    listModel.clear();
    for (GitHubUser u: result) {
      listModel.addElement(u.getUsername());
    }
  }

  public void resetPanel() {
    listModel.clear();
    getRepositoriesButton.setEnabled(false);
  }

  private void createUIComponents() {
    listModel = new DefaultListModel<>();
    resultsList = new JList<>(listModel);
  }

  private void setUpListListener() {
    resultsList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
          getRepositoriesButton.setEnabled(resultsList.getSelectedIndex() != -1);
        }
      }
    });
  }

  private void setUpGetRepoButtonListener() {
    getRepositoriesButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (control != null) {
          control.getRepos();
        }
      }
    });
  }
}
