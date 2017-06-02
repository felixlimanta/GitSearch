package org.felixlimanta.gitsearch.view;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by ASUS on 02/06/17.
 */
public class SearchResultsPanel {

  private JPanel rootPanel;

  private JButton getRepositoriesButton;
  private JScrollPane resultsContainer;
  private JList resultsList;

  public SearchResultsPanel() {

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
}
