package org.felixlimanta.gitsearch.view;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.felixlimanta.gitsearch.model.GitHubRepository;
import org.felixlimanta.gitsearch.model.GitHubUser;

/**
 * Created by ASUS on 02/06/17.
 */
public class UserRepoPanel {
  private static final int minRows = 5;

  private JPanel rootPanel;
  private JLabel usernameLabel;
  private JLabel repoCountLabel;

  private JScrollPane repoContainer;
  private DefaultTableModel tableModel;
  private JTable repoTable;

  public UserRepoPanel() {
    setUpClickableTableListener();
  }

  public JPanel getRootPanel() {
    return rootPanel;
  }

  public void populateRepoPanel(GitHubUser user) {
    usernameLabel.setEnabled(true);
    usernameLabel.setText(user.getUsername());
    repoCountLabel.setEnabled(true);
    repoCountLabel.setText(user.getRepos().size() + " repositories");

    repoContainer.setEnabled(true);
    repoTable.setEnabled(true);
    tableModel.setRowCount(0);
    for (GitHubRepository r: user.getRepos()) {
      Object[] row = {
          r.getName(), r.getDescription(), r.getHtmlUrl()
      };
      tableModel.addRow(row);
    }
  }

  public void resetPanel() {
    usernameLabel.setEnabled(false);
    usernameLabel.setText("username");
    repoCountLabel.setEnabled(false);
    repoCountLabel.setText("0 repositories");

    repoContainer.setEnabled(false);
    repoTable.setEnabled(false);
    tableModel.setRowCount(0);
    tableModel.setRowCount(minRows);
  }

  private void createUIComponents() {
    String[] colHeadings = {"Name", "Description", "URL"};
    tableModel = new DefaultTableModel(minRows, colHeadings.length);
    tableModel.setColumnIdentifiers(colHeadings);
    repoTable = new JTable(tableModel) {
      public boolean isCellEditable(int row, int column) {
        return false;
      }

      public String getToolTipText(MouseEvent e) {
        int row = rowAtPoint(e.getPoint());
        int column = columnAtPoint(e.getPoint());
        Object value = getValueAt(row, column);
        return value == null ? null : value.toString();
      }
    };
  }

  private void setUpClickableTableListener() {
    repoTable.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent me) {
        super.mouseClicked(me);
        JTable table =(JTable) me.getSource();
        int col = table.columnAtPoint(me.getPoint());
        if (me.getClickCount() == 2 && col == 2) {
          int row = table.rowAtPoint(me.getPoint());
          openUrlAtCell(row, col);
        }
      }
    });
  }

  private void openUrlAtCell(int row, int col) {
    try {
      final URI uri = new URI(repoTable.getValueAt(row, col).toString());
      if (Desktop.isDesktopSupported()) {
        try {
          Desktop.getDesktop().browse(uri);
        } catch (Exception ex) { }
      }
    } catch (Exception ex) { }
  }
}
