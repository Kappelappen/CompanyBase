package com.companybase.ui.panels;
import com.companybase.model.Details;
import com.companybase.ui.components.NumberSpinner;
import com.companybase.ui.components.NumberTextField;
import com.companybase.ui.windows.MainWindow;
import com.companybase.ui.windows.NewCompanyDialog;
import com.companybase.utils.ComponentUtils;

import java.awt.*;
import javax.swing.*;

public class NewDetailsPanel extends JPanel {
	
	private MainWindow mWindow;
	
	private JLabel lblOrgNumber;
	private JLabel lblEmployeeCount;
	private JLabel lblFoundedYear;
	private JLabel lblLinkedinAddress;
	private JLabel lblFacebookAddress;
	
	public JTextField txtOrgNumber;
	public JTextField txtFoundedYear;
	
	public JSpinner jspEmployeeCount;
	
	public UrlAddressPanel jplLinkedin;
	public UrlAddressPanel jplFacebook;
	
	private Box horizontalBox;
	
	public NewDetailsPanel(MainWindow mWindow) {
		
		this.mWindow = mWindow;
		
		this.initComponents();
		this.setupJPanel();
		this.createLayout();
		ComponentUtils.customize((JPanel) this);
		
	}
	
	private void initComponents() {
		
		this.horizontalBox = Box.createHorizontalBox();
		
		this.lblOrgNumber = new JLabel("Organizationnumber");
		this.lblEmployeeCount = new JLabel("Employee count");
		this.lblFoundedYear = new JLabel("Founded year");
		this.lblLinkedinAddress = new JLabel("Linkedin");
		this.lblFacebookAddress = new JLabel("Facebook");
		
		this.txtOrgNumber = new NumberTextField();
		this.txtFoundedYear = new NumberTextField();
		
		this.jspEmployeeCount = new NumberSpinner(200,0,Integer.MAX_VALUE);			
		
		this.jplLinkedin = new UrlAddressPanel(mWindow);
		this.jplFacebook = new UrlAddressPanel(mWindow);
		
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
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.insets = new Insets(6,10,6,6);
		this.add(lblOrgNumber, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 0; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.insets = new Insets(6,10,6,6);
		this.add(txtOrgNumber, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 1; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,6);
		this.add(lblEmployeeCount, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 1; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,6);
		this.add(jspEmployeeCount, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 3; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,6);
		this.add(lblFoundedYear, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 3; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,6);
		this.add(txtFoundedYear, gbc);
	
		gbc.gridx = 0; 
		gbc.gridy = 4; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,6);
		this.add(lblLinkedinAddress, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 4; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,6);
		this.add(jplLinkedin, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 5; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,6);
		this.add(lblFacebookAddress, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 5; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,6);
		this.add(jplFacebook, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 50; 
		gbc.gridwidth = 50;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,6,6);
		this.add(horizontalBox, gbc);
		
	}
	
	public Details getDetails() {
		
		String orgNumber = txtOrgNumber.getText().trim();
		String employeeCount = jspEmployeeCount.getValue().toString();
		String foundedYear = txtFoundedYear.getText().trim();
		String linkedin = jplLinkedin.txtAddressField.getText().trim();
		String facebook = jplFacebook.txtAddressField.getText().trim();
		
		Details details = new Details();
		
		details.setOrgNumber(orgNumber);
		details.setEmployeeCount(employeeCount);
		details.setFoundedYear(foundedYear);
		details.setLinkedinAddress(linkedin);
		details.setFacebookAddress(facebook);
		
		return details;
		
	}
	
	public Details loadDetails(long id) {
		
		Details details = mWindow.service.detailsDao.findById(id);
		String orgNumber = details.getOrgNumber();
		String employeeCount = details.getEmployeeCount();
		String foundedYear = details.getFoundedYear();
		String linkedin = details.getLinkedinAddress();
		String facebook = details.getFacebookAddress();
		
		this.txtOrgNumber.setText(orgNumber);
		this.jspEmployeeCount.setValue(Integer.parseInt(employeeCount));
		this.txtFoundedYear.setText(foundedYear);
		this.jplLinkedin.txtAddressField.setText(linkedin);
		this.jplLinkedin.txtAddressField.setText(facebook);
		
		return details;
		
	}
}
