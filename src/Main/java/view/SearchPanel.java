package org.felixlimanta.gitsearch.view;

import java.awt.event.ItemEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import org.felixlimanta.gitsearch.controller.GitSearchController;
import org.felixlimanta.gitsearch.model.Filter;

/**
 * Search panel.
 *
 * <p>Receives query input</p>
 *
 * @author  Felix Limanta
 * @version 1.0
 * @since   2017-06-02
 */
public class SearchPanel  {

  private JPanel rootPanel;

  private JLabel queryLabel;
  private JTextField queryTextField;

  private JLabel searchInLabel;
  private JRadioButton searchInAllRadioButton;
  private JRadioButton searchInUsernamesRadioButton;
  private JRadioButton searchInEmailRadioButton;
  private JRadioButton searchInFullNamesRadioButton;
  
  private JCheckBox limitRepoCheckBox;
  private JComboBox limitRepoOprComboBox;
  private JLabel limitRepoToLabel;
  private JSpinner limitRepoLowerLimitSpinner;
  private JSpinner limitRepoUpperLimitSpinner;
  
  private JCheckBox limitFollowersCheckBox;
  private JComboBox limitFollowersOprComboBox;
  private JLabel limitFollowersToLabel;
  private JSpinner limitFollowersLowerLimitSpinner;
  private JSpinner limitFollowersUpperLimitSpinner;
  
  private JButton searchButton;
  private JButton resetButton;

  private GitSearchController control;

  /**
   * Constructor
   *
   * <p>Sets up listeners for Swing components</p>
   */
  public SearchPanel() {
    setUpRepoListener();
    setUpFollowersListener();
    setUpButtonsListener();
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
   * Query getter
   *
   * @return Text in query text field
   */
  public String getQuery() {
    return queryTextField.getText();
  }

  /**
   * searchIn getter
   *
   * @return searchIn value based on radio button selection
   * @see org.felixlimanta.gitsearch.model.GitHubSearchUserUrlGenerator#searchIn
   */
  public int getSearchIn() {
    if (searchInUsernamesRadioButton.isSelected()) {
      return 1;
    } else if (searchInEmailRadioButton.isSelected()) {
      return 2;
    } else if (searchInFullNamesRadioButton.isSelected()) {
      return 3;
    } else {
      return 0;
    }
  }

  /**
   * Repository filter getter
   *
   * @return Filter object for repository count
   */
  public Filter getRepoFilter() {
    return getFilter(limitRepoCheckBox, limitRepoOprComboBox,
        limitRepoLowerLimitSpinner, limitRepoUpperLimitSpinner);
  }

  /**
   * Follower filter getter
   *
   * @return Filter object for follower filter
   */
  public Filter getFollowerFilter() {
    return getFilter(limitFollowersCheckBox, limitFollowersOprComboBox,
        limitFollowersLowerLimitSpinner, limitFollowersUpperLimitSpinner);
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
   * Resets panel
   *
   * <p>Clears text fields, resets radio button and check box selection, resets value and
   * enabledness of components</p>
   */
  public void resetPanel() {
    queryTextField.setText("");
    searchInAllRadioButton.setSelected(true);
    
    limitRepoCheckBox.setSelected(false);
    limitRepoOprComboBox.setSelectedIndex(0);
    limitRepoLowerLimitSpinner.setValue(0);
    limitRepoUpperLimitSpinner.setValue(10000);

    limitFollowersCheckBox.setSelected(false);
    limitFollowersOprComboBox.setSelectedIndex(0);
    limitFollowersLowerLimitSpinner.setValue(0);
    limitFollowersUpperLimitSpinner.setValue(10000);
  }

  private void createUIComponents() {
    SpinnerNumberModel lowerLimit = new SpinnerNumberModel(0, 0, 1000000, 1);
    limitRepoLowerLimitSpinner = new JSpinner(lowerLimit);
    SpinnerNumberModel upperLimit = new SpinnerNumberModel(10000, 0, 1000000, 1);
    limitRepoUpperLimitSpinner = new JSpinner(upperLimit);

    lowerLimit = new SpinnerNumberModel(0, 0, 1000000, 1);
    limitFollowersLowerLimitSpinner = new JSpinner(lowerLimit);
    upperLimit = new SpinnerNumberModel(10000, 0, 1000000, 1);
    limitFollowersUpperLimitSpinner = new JSpinner(upperLimit);
  }
  
  private void setUpRepoListener() {
    setUpFilterListener(limitRepoCheckBox, limitRepoOprComboBox, limitRepoToLabel,
        limitRepoLowerLimitSpinner, limitRepoUpperLimitSpinner);
  }
  
  private void setUpFollowersListener() {
    setUpFilterListener(limitFollowersCheckBox, limitFollowersOprComboBox, limitFollowersToLabel,
        limitFollowersLowerLimitSpinner, limitFollowersUpperLimitSpinner);
  }
  
  private void setUpFilterListener(JCheckBox checkBox, JComboBox comboBox, JLabel to,
      JSpinner lower, JSpinner upper) {
    checkBox.addItemListener(e -> {
      boolean checked = e.getStateChange() == ItemEvent.SELECTED;
      String label = comboBox.getItemAt(comboBox.getSelectedIndex()).toString();
      boolean range = label.equals("range");
      comboBox.setEnabled(checked);
      lower.setEnabled(checked);
      to.setEnabled(checked && range);
      upper.setEnabled(checked && range);
    });
    comboBox.addActionListener(e -> {
      String label = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
      boolean range = label.equals("range");
      to.setEnabled(range);
      upper.setEnabled(range);
    });
  }

  private void setUpButtonsListener() {
    searchButton.addActionListener(e -> {
      if (control != null) {
        control.searchUsers();
      }
    });
    resetButton.addActionListener(e -> {
      if (control != null) {
        control.resetView();
      }
    });
  }

  private Filter getFilter(JCheckBox checkBox, JComboBox comboBox, JSpinner lower, JSpinner upper) {
    boolean used = checkBox.isSelected();
    String limit = "";
    if (used) {
      String label = comboBox.getItemAt(comboBox.getSelectedIndex()).toString();
      switch (label) {
        case "=":
          limit = lower.getValue().toString();
          break;
        case "range":
          int min = (int) lower.getValue();
          int max = (int) upper.getValue();
          limit = min + ".." + max;
          break;
        default:
          limit = label + lower.getValue().toString();
          break;
      }
    }
    return new Filter(used, limit);
  }
}
