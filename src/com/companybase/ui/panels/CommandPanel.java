package com.companybase.ui.panels;

import java.awt.*;
import javax.swing.*;

import com.companybase.events.actions.DeleteCompanyAction;
import com.companybase.events.actions.EditCompanyAction;
import com.companybase.events.actions.NewCompanyAction;
import com.companybase.events.actions.PdfExportAction;
import com.companybase.ui.windows.MainWindow;

public class CommandPanel extends JPanel {

	private MainWindow mWindow;
	
	private JButton btnNewCompany;
	private JButton btnEditCompany;
	private JButton btnDeleteCompany;
	private JButton btnPdfExport;
	
	public CommandPanel(MainWindow mWindow) {
		
		this.mWindow = mWindow;
		
		this.initComponents();
		this.setupJPanel();
		this.createLayout();
		
	}	
	
	private void initComponents() {
		
		this.btnNewCompany = createJButton(new NewCompanyAction(true,mWindow));
		this.btnEditCompany = createJButton(new EditCompanyAction(true,mWindow));
		this.btnDeleteCompany = createJButton(new DeleteCompanyAction(true,mWindow));
		this.btnPdfExport = createJButton(new PdfExportAction(true,mWindow));
		
	}
	
	private void setupJPanel() {
		
		this.setBorder(null);
		this.setLayout(new GridBagLayout());
		
	}
	
	private void createLayout() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0; 
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(3,3,3,3);
		gbc.anchor = GridBagConstraints.WEST;
		this.add(btnNewCompany, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(3,0,3,3);
		gbc.anchor = GridBagConstraints.WEST;
		this.add(btnEditCompany, gbc);
		
		gbc.gridx = 2; 
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(3,0,3,3);
		gbc.anchor = GridBagConstraints.WEST;
		this.add(btnDeleteCompany, gbc);	
		
		gbc.gridx = 3; 
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(3,0,3,3);
		gbc.anchor = GridBagConstraints.WEST;
		this.add(btnPdfExport, gbc);	
		
	}
	
	private JButton createJButton(Action action) {
		
		Dimension dim = new Dimension(44,44);
		JButton button = new JButton();
		button.setMinimumSize(dim);
		button.setPreferredSize(dim);
		button.setMaximumSize(dim);
		button.setFocusable(false);
		button.setHorizontalAlignment(JButton.CENTER);
		button.setAction(action);		
		
		return button;
		
	}
}
