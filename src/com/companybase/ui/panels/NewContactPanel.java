package com.companybase.ui.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.companybase.model.ContactPerson;
import com.companybase.ui.windows.MainWindow;
import com.companybase.ui.windows.NewContactDialog;

public class NewContactPanel extends JPanel {
	
	private MainWindow mWindow;
	
	public DefaultTableModel personModel;
	public JTable jtbContacts;
	private JScrollPane jspContacts;
	
	private JButton btnAddPerson;
	private JButton btnEditPerson;
	private JButton btnDeletePerson;
	
	public NewContactPanel(MainWindow mWindow) {
		
		this.mWindow = mWindow;
		
		this.initComponents();
		this.setupJPanel();
		this.createLayout();
		
	}
	
	private void initComponents() {
		
		this.personModel = new DefaultTableModel();
		this.jtbContacts = new JTable(personModel);
		this.jspContacts = new JScrollPane(jtbContacts);
		
		this.btnAddPerson = createJButton("plus.gif");
		this.btnDeletePerson = createJButton("minus.gif");
		
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
		gbc.gridwidth = 1;
		gbc.gridheight = 3;
		gbc.insets = new Insets(6,6,6,6);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.BOTH;
		this.add(jspContacts, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(6,0,3,6);
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.weighty = GridBagConstraints.NONE;
		this.add(btnAddPerson, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0,0,0,6);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = GridBagConstraints.NONE;
		gbc.weighty = GridBagConstraints.BOTH;
		this.add(btnDeletePerson, gbc);
		
	}
	
	private void setupJTbContacts() {
		
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
	
	private void btnAddPressed(JButton button) {
		
		button.setEnabled(false);
		
		NewContactDialog dialog = 
			new NewContactDialog(jtbContacts, mWindow);
		
		dialog.setVisible(true);		
		button.setEnabled(true);
		
	}
	
	private void btnDeletePressed() {
		
		int tableRow = jtbContacts.getSelectedRow();
		if (tableRow == -1) return;
		
		int modelRow = jtbContacts.convertRowIndexToModel(tableRow);
		this.personModel.removeRow(modelRow);
		this.personModel.fireTableDataChanged();
		
	}
	
	public void loadContacts(long id) {
		
		java.util.List<ContactPerson> list = mWindow.service.contactDao.findById(id);
		
		for (int i = 0; i < list.size(); i++) {
			
			ContactPerson person = list.get(i);
			
			String name = person.getName();
			String jobTitle = person.getJobTitle();
			String phone = person.getPhone();
			
			Object[] data = new Object[] { id, 
			name,jobTitle,phone };
			
			this.personModel.addRow(data);
			
		}
		
		this.personModel.fireTableDataChanged();
		
	}	
		
	public java.util.List<ContactPerson> getContactPersons(long companyId) {

	    java.util.List<ContactPerson> persons = new ArrayList<>();

	    for (int row = 0; row < personModel.getRowCount(); row++) {

	        ContactPerson person = new ContactPerson();
	        
	        person.setId(companyId);
	        person.setName((String) personModel.getValueAt(row, 1));
	        person.setJobTitle((String) personModel.getValueAt(row, 2));
	        person.setPhone((String) personModel.getValueAt(row, 3));
	        persons.add(person);
	    
	    }

	    return persons;
	
	}
	
	private JButton createJButton(String fileName) {
		
		URL url = super.getClass().getResource("/images/" + fileName);
		ImageIcon icon = new ImageIcon(url);
		
		Dimension dim = new Dimension(33,33);
		JButton button = new JButton();
		
		button.setMinimumSize(dim);
		button.setPreferredSize(dim);
		button.setMaximumSize(dim);
		button.setFocusable(false);
		button.setIcon(icon);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if ("plus.gif".equals(fileName)) {
				
					btnAddPressed(button);
					
				}
				
				if ("minus.gif".equals(fileName)) {
					
					btnDeletePressed();
					
				}				
			}			
		});
		
		return button;
		
	}
	
}
