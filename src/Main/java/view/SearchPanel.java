package org.felixlimanta.gitsearch.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Created by ASUS on 02/06/17.
 */
public class SearchPanel  {

  private JPanel rootPanel;
  
  private JTextField queryTextField;
  private JLabel queryLabel;

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

  public SearchPanel() {
    setUpRepoListener();
    setUpFollowersListener();
  }

  public JPanel getRootPanel() {
    return rootPanel;
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
    checkBox.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        boolean checked = e.getStateChange() == ItemEvent.SELECTED;
        String label = comboBox.getItemAt(comboBox.getSelectedIndex()).toString();
        boolean range = label.equals("range");
        comboBox.setEnabled(checked);
        lower.setEnabled(checked);
        to.setEnabled(checked && range);
        upper.setEnabled(checked && range);
      }
    });
    comboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String label = (String) comboBox.getItemAt(
            comboBox.getSelectedIndex());
        boolean range = label.equals("range");
        to.setEnabled(range);
        upper.setEnabled(range);
      }
    });
  }
}
