package com.companybase.ui.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.companybase.model.ContactPerson;
import com.companybase.ui.windows.MainWindow;
import com.companybase.ui.windows.NewCompanyDialog;
import com.companybase.ui.windows.NewContactDialog;

public class BasicContactPanel extends JPanel {
	
	private MainWindow mWindow;
	
	public DefaultTableModel personModel;
	public JTable jtbContacts;
	private JScrollPane jspContacts;
		
	public BasicContactPanel(MainWindow mWindow) {
		
		this.mWindow = mWindow;
		
		this.initComponents();
		this.setupJPanel();
		this.createLayout();
		
	}
	
	private void initComponents() {
		
		this.personModel = new DefaultTableModel();
		this.jtbContacts = new JTable(personModel);
		this.jspContacts = new JScrollPane(jtbContacts);
		
	}
	
	private void setupJPanel() {
		
		this.setBorder(null);
		this.setLayout(new GridBagLayout());
		
	}
	
	private void createLayout() {
		
		this.setupJTbContacts();
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.insets = new Insets(6,6,6,6);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.BOTH;
		this.add(jspContacts, gbc);
		
		
	}
	
	private void setupJTbContacts() {
		
		this.jspContacts.setBorder(null);
		this.jspContacts.setViewportBorder(BorderFactory.createEtchedBorder());
		
		JTableHeader header = jtbContacts.getTableHeader();
		header.setReorderingAllowed(false);
		
		this.personModel.addColumn("ID");
		this.personModel.addColumn("Name");
		this.personModel.addColumn("Jobtitle");
		this.personModel.addColumn("Phone");
		
		TableColumn col = jtbContacts.getColumnModel().getColumn(0);
		col.setMinWidth(0);
		col.setPreferredWidth(0);
		col.setMaxWidth(0);
		col.setResizable(false);
				
	}	
}
