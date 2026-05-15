package com.companybase.ui.windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.companybase.ui.components.NumberTextField;

public class AboutAppDialog extends JDialog {
	
	private MainWindow mWindow;
		
	private JTextArea txtAboutApp;
	private JScrollPane jspAboutApp;
	
	private JButton btnCancel;
	private JButton btnOk;
	
	private JPanel jplCommand;
	
	private JPanel jplContainer;
	
	public AboutAppDialog(MainWindow mWindow) {
		
		super(mWindow);
		
		this.mWindow = mWindow;
		
		this.initComponents();
		this.setupJDialog();
		this.createLayout();
		this.registerEvents();
		
	}
	
	private void initComponents() {
				
		this.txtAboutApp = new JTextArea();
		this.jspAboutApp = new JScrollPane(txtAboutApp);
		
		this.btnCancel = createJButton("Cancel");
		this.btnOk = createJButton("Ok");
		
		this.jplCommand = new JPanel(new GridBagLayout());		
		this.jplContainer = new JPanel(new GridBagLayout());
		
	}
	
	private void setupJDialog() {
		
		this.setSize(600,300);
		this.setModal(true);
		this.setModal(true);
		this.setTitle("About CompanyBase");
		this.setContentPane(jplContainer);
		this.setLocationRelativeTo(null);
				
	}
	
	private void createLayout() {
		
		this.setupJPlCommand();
		
		GridBagConstraints gbc = new GridBagConstraints();
				
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		this.jplContainer.add(jspAboutApp, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 1; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,0,0,0);
		this.jplContainer.add(jplCommand, gbc);
		
		this.setupJTextArea();
		
	}
	
	private void setupJTextArea() {
		
		this.txtAboutApp.setMargin(new Insets(6,6,6,6));
		this.txtAboutApp.setEnabled(false);
		this.txtAboutApp.setFont(new Font("Arial",Font.PLAIN,16));
		this.txtAboutApp.setLineWrap(true);
		this.txtAboutApp.setTabSize(1);
		this.txtAboutApp.setCaretColor(Color.white);
		this.txtAboutApp.setCaretPosition(0);
		this.txtAboutApp.setWrapStyleWord(true);
		this.txtAboutApp.setDisabledTextColor(SystemColor.textText);
		
		this.txtAboutApp.setText("CompanyBase is a small desktop application designed to " + 
		"help students manage their internship applications and communication with companies. " + 
		"It allows users to organize company information, track application statuses, store contact persons," + 
		"and keep notes throughout the process. The purpose of CompanyBase is to support " + 
		"applicants in maintaining a structured and efficient overview of potential " + 
		"internship opportunities.");
		
	}	
	
	private void setupJPlCommand() {
		
		GridBagConstraints gbc = new GridBagConstraints();
			
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,3,3,3);
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.EAST;
		this.jplCommand.add(btnOk, gbc);
		
	}
	
	private void btnCancelPressed() {
		
		super.dispose();
		
	}
	
	private void btnOkPressed() {
					
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
