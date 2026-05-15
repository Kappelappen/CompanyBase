package com.companybase.ui.panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import com.companybase.model.ApplicationStatus;
import com.companybase.model.Company;
import com.companybase.model.Country;
import com.companybase.ui.components.ComboBoxCalendar;
import com.companybase.ui.components.NumberSpinner;
import com.companybase.ui.components.NumberTextField;
import com.companybase.ui.components.YearSpinner;
import com.companybase.ui.windows.MainWindow;
import com.companybase.ui.windows.NewCompanyDialog;
import com.companybase.utils.ComponentUtils;

public class NewCompanyPanel extends JPanel {

	private MainWindow mWindow;
	
	private JLabel lblCompanyName;
	private JLabel lblIndustry;
	private JLabel lblCity;
	private JLabel lblStreetAddress;
	private JLabel lblCountry;
	private JLabel lblWebsite;
	private JLabel lblEmail;
	private JLabel lblPhone;
	private JLabel lblStatus;
	private JLabel lblApplicationDate;
	
	public JTextField txtCompanyName;
	private JTextField txtIndustry;	
	private JTextField txtCity;
	private JTextField txtStreetAddress;
	private JTextField txtEmail;
	private JTextField txtPhone;
	
	private JComboBox<Country> jcbCountry;
	private JComboBox<ApplicationStatus> jcbStatus;
	
	private JComboBox<String> jcbApplicationDate;
	
	private JSpinner jspEmployeeCount;
	private JSpinner jspYearFounded;
	
	private UrlAddressPanel jplWebsite;
	
	private Box horizontalBox;
	
	public NewCompanyPanel(MainWindow mWindow) {
		
		this.mWindow = mWindow;
		
		this.initComponents();
		this.fetchApplicationStatus();
		this.fetchCountries();
		this.setupJPanel();
		this.createLayout();
		
	}	
	
	private void initComponents() {
		
		this.lblCompanyName = new JLabel("Companyname");
		this.lblIndustry = new JLabel("Industry");
		this.lblCity = new JLabel("CIty");
		this.lblStreetAddress = new JLabel("Streetaddress");
		this.lblCountry = new JLabel("Country");		
		this.lblWebsite = new JLabel("Website");
		this.lblEmail = new JLabel("E-mail");
		this.lblPhone = new JLabel("Phone");
		this.lblApplicationDate = new JLabel("Application date");
		this.lblStatus = new JLabel("Application status");
		
		this.txtCompanyName = new JTextField();
		this.txtIndustry = new JTextField();
		this.txtCity = new JTextField();
		this.txtStreetAddress = new JTextField();
		this.txtEmail = new JTextField();
		this.txtPhone = new NumberTextField();
		
		this.jplWebsite = new UrlAddressPanel(mWindow);
		
		this.jcbCountry = new JComboBox<Country>();
		this.jcbStatus = new JComboBox<ApplicationStatus>();
		this.jcbApplicationDate = new ComboBoxCalendar();		
		
		this.jspEmployeeCount = 
			new NumberSpinner(230,0,Integer.MAX_VALUE);
		
		this.jspYearFounded = new YearSpinner(230);
		
		this.horizontalBox = Box.createHorizontalBox();
		
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
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(12,10,6,0);
		this.add(lblCompanyName, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 0; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(12,10,6,6);
		this.add(txtCompanyName, gbc);
				
		gbc.gridx = 0; 
		gbc.gridy = 1; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,0);
		this.add(lblIndustry, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 1; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,6,6);
		this.add(txtIndustry, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 2; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,0);
		this.add(lblStreetAddress, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 2; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,6,6);
		this.add(txtStreetAddress, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 3; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,0);
		this.add(lblCity, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 3; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,6,6);
		this.add(txtCity, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 4; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,0);
		this.add(lblCountry, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 4; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,6,6);
		this.add(jcbCountry, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 5; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,0);
		this.add(lblWebsite, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 5; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,6,6);
		this.add(jplWebsite, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 6; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,0);
		this.add(lblEmail, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 6; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,6,6);
		this.add(txtEmail, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 7; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,0);
		this.add(lblPhone, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 7; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,6,6);
		this.add(txtPhone, gbc);
						
		gbc.gridx = 0; 
		gbc.gridy = 8; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,0);
		this.add(lblApplicationDate, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 8; 
		gbc.gridwidth = 15;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,6,6);
		this.add(jcbApplicationDate, gbc);		
		
		gbc.gridx = 0; 
		gbc.gridy = 9; 
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.insets = new Insets(0,10,6,0);
		this.add(lblStatus, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 9; 
		gbc.gridwidth = 15;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,6,6);
		this.add(jcbStatus, gbc);		
		
		gbc.gridx = 0; 
		gbc.gridy = 50; 
		gbc.gridwidth = 50;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,10,6,6);
		this.add(horizontalBox, gbc);

		ComponentUtils.customize(((JPanel) this));
		
	}
	
	private void fetchApplicationStatus() {
		
		this.jcbStatus.addItem(null);
		
		java.util.List<ApplicationStatus> list = mWindow.service.statusDao.findAll();
				
		for (int i = 0; i < list.size(); i++) {
			
			ApplicationStatus status = list.get(i);
			this.jcbStatus.addItem(status);
			
		}		
	}
	
	private void fetchCountries() {
		
		this.jcbCountry.addItem(null);
		
		java.util.List<Country> list = mWindow.service.countryDao.findAll();
		
		for (int i = 0; i < list.size(); i++) {
			
			Country country = list.get(i);
			this.jcbCountry.addItem(country);
			
		}		
	}
	
	private void selectCountryItem(String countryName) {

	    for (int i = 1; i < jcbCountry.getItemCount(); i++) {

	        Country country = jcbCountry.getItemAt(i);

	        if (country.getName().equalsIgnoreCase(countryName)) {
	            
	        	jcbCountry.setSelectedIndex(i);
	            break;
	            
	        }
	    }
	}
	
	private void selectApplicationStatus(String statusName) {

	    for (int i = 1; i < jcbStatus.getItemCount(); i++) {

	        ApplicationStatus status = jcbStatus.getItemAt(i);

	        if (status.getName().equalsIgnoreCase(statusName)) {
	            
	        	jcbStatus.setSelectedIndex(i);
	            break;
	            
	        }
	    }
	}
	
	public void loadCompany(long id) {
		
		Company company = mWindow.service.companyDao.findById(id);
		if (company == null) return;
		
		String name = company.getName();
		String industry = company.getIndustry();
		String address = company.getStreetAddress();
		String city = company.getCity();
		String website = company.getWebsite();
		String email = company.getEmail();
		String phone = company.getPhone();
		String appDate = company.getApplicationDate();
				
		this.txtCompanyName.setText(name);
		this.txtIndustry.setText(industry);
		this.txtStreetAddress.setText(address);
		this.txtCity.setText(city);
		this.jplWebsite.txtAddressField.setText(website);
		this.txtEmail.setText(email);
		this.txtPhone.setText(phone);
		this.jcbApplicationDate.addItem(appDate);
		
		this.selectCountryItem(company.getCountry());
		this.selectApplicationStatus(company.getApplicationStatus());
		
	}
	
	public Company getCompany() {
		
		String companyName = txtCompanyName.getText().trim();
		String industry = txtIndustry.getText().trim();
		String streetAddress = txtStreetAddress.getText().trim();
		String city = txtCity.getText().trim();
				
		Country country =  (Country) jcbCountry.getSelectedItem();
		String countryName = (country == null) ? null : country.getName();
		
		String website = jplWebsite.txtAddressField.getText().trim();
		String email = txtEmail.getText().trim();
		String phone = txtPhone.getText().trim();
		
		int dateIndex = jcbApplicationDate.getSelectedIndex();
		String applicationDate = jcbApplicationDate.getItemAt(dateIndex);
		
		int statusIndex = jcbStatus.getSelectedIndex();
		ApplicationStatus appStatus = jcbStatus.getItemAt(statusIndex);
		String statusName = appStatus.getName();
		
		Company company = new Company();
		
		company.setCompanyName(companyName);
		company.setIndustry(industry);
		company.setStreetAddress(streetAddress);
		company.setCity(city);
		company.setCountry(countryName);
		company.setWebsite(website);
		company.setEmail(email);
		company.setPhone(phone);
		company.setApplicationDate(applicationDate);
		company.setApplicationStatus(statusName);
		
		return company;
		
	}
	
}
