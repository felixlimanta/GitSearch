package org.felixlimanta.gitsearch.view;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

  public ResultsPanel() {

    resultsList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
          getRepositoriesButton.setEnabled(resultsList.getSelectedIndex() != -1);
        }
      }
    });
  }

  public JPanel getRootPanel() {
    return rootPanel;
  }

  public void setResultsList(ArrayList<GitHubUser> result) {
    listModel.clear();
    for (GitHubUser u: result) {
      listModel.addElement(u.getUsername());
    }
  }

  private void createUIComponents() {
    listModel = new DefaultListModel<>();
    resultsList = new JList<>(listModel);
  }
}
