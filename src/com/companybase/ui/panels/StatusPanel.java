package com.companybase.ui.panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.companybase.model.Company;
import com.companybase.ui.windows.MainWindow;

public class StatusPanel extends JPanel {

	private MainWindow mWindow;
	private Border emptyBorder;
	private Border softBorder;
	private Border compBorder;
	private Box horizontalBox;
	private JLabel lblCompaniesInfo;
	private JLabel lblCompaniesIndex;
	private JLabel lblLastInserted;
	private JLabel lblCompanyName;
	
	public StatusPanel(MainWindow mWindow) {
		
		this.mWindow = mWindow;
		
		this.initComponents();
		this.setupJPanel();
		this.createLayout();
		
	}	
	
	private void initComponents() {
		
		this.emptyBorder = new EmptyBorder(0,3,0,3);
		this.softBorder = new SoftBevelBorder(BevelBorder.LOWERED);
		this.compBorder = new CompoundBorder(softBorder,emptyBorder);
		this.horizontalBox = Box.createHorizontalBox();
		this.lblCompaniesInfo = new JLabel("Number of companies:");
		this.lblCompaniesIndex = new JLabel();
		this.lblLastInserted = new JLabel();
		this.lblCompanyName = new JLabel();
		
	}
	
	private void setupJPanel() {
		
		this.setBorder(compBorder);
		this.setLayout(new GridBagLayout());
		
	}
	
	private void createLayout() {
		
		this.setupJLabel();
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,6);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = GridBagConstraints.NONE;
		this.add(lblCompaniesInfo, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,6);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = GridBagConstraints.NONE;
		this.add(lblCompaniesIndex, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,6);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = GridBagConstraints.BOTH;
		this.add(horizontalBox, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,6);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = GridBagConstraints.NONE;
		this.add(lblLastInserted, gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,6);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = GridBagConstraints.NONE;
		this.add(lblCompanyName, gbc);
		
	}
	
	private void setupJLabel() {
		
		java.util.List<Company> list = mWindow.service.companyDao.findAll();
		String numValue = (list.size() == 0) ? "0" : Integer.toString(list.size());
				
		this.lblCompaniesIndex.setText(numValue);		
		this.fetchLastInsertedCompany();
		
	}
	
	private void fetchLastInsertedCompany() {
		
		java.util.List<Company> list = mWindow.service.companyDao.findAll();
		
		if (list.size() != 0) {

			this.lblLastInserted.setText("Last inserted company");
			
			Company company = mWindow.service.companyDao.getLastInsertedCompany();
			if (company == null) return;
		
			String name = company.getName();
			this.lblCompanyName.setText(name);
		
		}
	}
	
	@Override
	public Dimension getMinimumSize() {
		
		return new Dimension(0,30);
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		
		return new Dimension(0,30);
		
	}
	
	@Override
	public Dimension getMaximumSize() {
		
		return new Dimension(0,30);
		
	}
}
