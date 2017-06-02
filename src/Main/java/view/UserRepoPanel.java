package org.felixlimanta.gitsearch.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Created by ASUS on 02/06/17.
 */
public class UserRepoPanel {

  private JPanel rootPanel;
  private JLabel usernameLabel;
  private JTable repoTable;
  private JLabel repoCountLabel;
  private JScrollPane repoContainer;

  public JPanel getRootPanel() {
    return rootPanel;
  }

  private void createUIComponents() {
    String[] colHeadings = {"Name", "Description", "URL"};
    int numRows = 5;
    DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length);
    model.setColumnIdentifiers(colHeadings);
    repoTable = new JTable(model);
  }
}
