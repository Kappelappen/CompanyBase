package com.companybase.ui.panels;
import com.companybase.model.Company;
import com.companybase.ui.windows.MainWindow;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchPanel extends JPanel {

	private MainWindow mWindow;
	
	private JLabel lblSearch;
	public JTextField txtSearch;
	
	public SearchPanel(MainWindow mWindow) {
		
		this.mWindow = mWindow;
		
		this.initComponents();
		this.setupJPanel();
		this.registerEvents();
		this.createLayout();
		
	}	
	
	private void initComponents() {
		
		this.lblSearch = new JLabel("Search");
		this.txtSearch = new JTextField();
		
	}
	
	private void setupJPanel() {
		
		this.setupJTextField();
		
		this.setFocusable(false);
		this.setBorder(null);
		this.setLayout(new GridBagLayout());
		
	}
	
	private void registerEvents() {
		
		this.txtSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				performSearch();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				performSearch();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
				performSearch();
				
			}			
		});		
	}	
		
	private void setupJTextField() {
	
		this.txtSearch.setMargin(new Insets(0,3,0,3));
		this.txtSearch.setFont(new Font("Arial",Font.PLAIN,16));
		this.txtSearch.setMinimumSize(new Dimension(0,30));
		this.txtSearch.setPreferredSize(new Dimension(0,30));
		this.txtSearch.setMaximumSize(new Dimension(0,30));
		
	}
	
	private void createLayout() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(6,3,6,6);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = GridBagConstraints.NONE;
		this.add(lblSearch, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(6,0,6,0);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = GridBagConstraints.BOTH;
		this.add(txtSearch, gbc);
		
	}
	
	private void performSearch() {

	    String text = txtSearch.getText().trim();
	    
	    java.util.List<Company> companies = (text.isEmpty()) ? 
	    	mWindow.service.companyDao.findAll() : 
	    	mWindow.service.companyDao.search(text);

	    mWindow.dtModel.setRowCount(0);

	    for (int i = 0; i < companies.size(); i++) {
	    	
	    	Company company = companies.get(i);
	    	long id = company.getId();
	    	String name = company.getName();
	    	String industry = company.getIndustry();
	    	String email = company.getEmail();
	    	String phone = company.getPhone();
	    	
	    	Object[] row = new Object[] { id,
	    	name,industry,email,phone };
	    	
	    	this.mWindow.dtModel.addRow(row);
	    	
	    }
	    
	    this.mWindow.dtModel.fireTableDataChanged();
	    
	}
	
	public String getSearchText() {
        return txtSearch.getText().trim();
    }
	
	 public void clear() {
	 
		 txtSearch.setText("");
	 
	 }
}
