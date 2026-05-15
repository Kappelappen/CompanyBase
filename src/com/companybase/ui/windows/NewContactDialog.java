package com.companybase.ui.windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.companybase.ui.components.NumberTextField;

public class NewContactDialog extends JDialog {
	
	private JTable jtbPersonInfo;
	private MainWindow mWindow;
	
	private JLabel lblPersonName;
	private JLabel lblJobTitle;
	private JLabel lblPhoneNumber;
	
	private JTextField txtPersonName;
	private JTextField txtJobTitle;
	private JTextField txtPhoneNumber;
	
	private JButton btnCancel;
	private JButton btnOk;
	
	private JPanel jplCommand;
	
	private JPanel jplContainer;
	
	public NewContactDialog(JTable jtbPersonInfo, 
		MainWindow mWindow) {
		
		super(mWindow);
		
		this.jtbPersonInfo = jtbPersonInfo;
		this.mWindow = mWindow;
		
		this.initComponents();
		this.setupJDialog();
		this.createLayout();
		this.registerEvents();
		
	}
	
	private void initComponents() {
		
		this.lblPersonName = new JLabel("Name");
		this.lblJobTitle = new JLabel("Job title");
		this.lblPhoneNumber = new JLabel("Phone");
		
		this.txtPersonName = new JTextField();
		this.txtJobTitle = new JTextField();
		this.txtPhoneNumber = new NumberTextField();
				
		this.btnCancel = createJButton("Cancel");
		this.btnOk = createJButton("Ok");
		
		this.jplCommand = new JPanel(new GridBagLayout());		
		this.jplContainer = new JPanel(new GridBagLayout());
		
	}
	
	private void setupJDialog() {
		
		this.setSize(600,300);
		this.setModal(true);
		this.setModal(true);
		this.setTitle("New contact");
		this.setContentPane(jplContainer);
		this.setLocationRelativeTo(null);
				
	}
	
	private void createLayout() {
		
		this.setupJPlCommand();
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(6,6,6,0);
		this.jplContainer.add(lblPersonName, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 0; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(6,10,6,6);
		this.jplContainer.add(txtPersonName, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 1; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,6,6,0);
		this.jplContainer.add(lblJobTitle, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 1; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,6,6);
		this.jplContainer.add(txtJobTitle, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 2; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,6,6,0);
		this.jplContainer.add(lblPhoneNumber, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 2; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,6,6);
		this.jplContainer.add(txtPhoneNumber, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 3; 
		gbc.gridwidth = 10;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		this.jplContainer.add(jplCommand, gbc);
		
		this.setupJTextField();
		
	}
	
	private void setupJTextField() {
		
		Dimension dim = new Dimension(0,30);
		
		this.txtPersonName.setMinimumSize(dim);
		this.txtPersonName.setPreferredSize(dim);
		this.txtPersonName.setMaximumSize(dim);
		this.txtPersonName.setMargin(new Insets(0,3,0,3));
		this.txtPersonName.setFont(new Font("Arial",Font.PLAIN,16));
		
		this.txtJobTitle.setMinimumSize(dim);
		this.txtJobTitle.setPreferredSize(dim);
		this.txtJobTitle.setMaximumSize(dim);
		this.txtJobTitle.setMargin(new Insets(0,3,0,3));
		this.txtJobTitle.setFont(new Font("Arial",Font.PLAIN,16));
		
		this.txtPhoneNumber.setMinimumSize(dim);
		this.txtPhoneNumber.setPreferredSize(dim);
		this.txtPhoneNumber.setMaximumSize(dim);
		this.txtPhoneNumber.setMargin(new Insets(0,3,0,3));
		this.txtPhoneNumber.setFont(new Font("Arial",Font.PLAIN,16));
				
	}
	
	private void setupJPlCommand() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,6,0);
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.EAST;
		this.jplCommand.add(btnCancel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,3,6,6);
		gbc.weightx = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		this.jplCommand.add(btnOk, gbc);
		
	}
	
	private void btnCancelPressed() {
		
		super.dispose();
		
	}
	
	private void btnOkPressed() {
				
		String name = txtPersonName.getText().trim();
		String jobTitle = txtJobTitle.getText().trim();
		String phone = txtPhoneNumber.getText().trim();
		
		if (name == null || name.isEmpty()) {
			
			String emptyName = "The value Name must not be empty";
			JOptionPane.showMessageDialog(mWindow, emptyName);
			return;
			
		}
		
		if (jobTitle == null || jobTitle.isEmpty()) {
			
			String emptyTitle = "The value Job title must not be empty";
			JOptionPane.showMessageDialog(mWindow, emptyTitle);
			return;
			
		}
		
		Object[] row = new Object[] { 0, name, jobTitle, phone };
		DefaultTableModel model = (DefaultTableModel) jtbPersonInfo.getModel();
		
		model.addRow(row);
		model.fireTableDataChanged();
				
		this.btnCancelPressed();
		
	}
	
	private void registerEvents() {
		
		this.btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				btnCancelPressed();
				
			}			
		});
		
		this.btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				btnOkPressed();
				
			}			
		});
		
		super.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				jDialogClosing();
				
			}			
		});		
	}
	
	private void jDialogClosing() {
		
		super.dispose();
		
	}
	
	private JButton createJButton(String text) {
		
		Dimension dim = new Dimension(103,30);
		JButton button = new JButton();
		
		button.setMinimumSize(dim);
		button.setPreferredSize(dim);
		button.setMaximumSize(dim);
		button.setFocusable(false);
		button.setText(text);
		
		return button;
		
	}
}
