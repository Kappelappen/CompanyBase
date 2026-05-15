package com.companybase.ui.windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import com.companybase.dao.ApplicationStatusDao;
import com.companybase.dao.ApplicationStatusDaoImpl;
import com.companybase.dao.ContactDao;
import com.companybase.dao.ContactDaoImpl;
import com.companybase.dao.CountryDao;
import com.companybase.dao.CountryDaoImpl;
import com.companybase.database.Connector;
import com.companybase.model.AboutText;
import com.companybase.model.ApplicationStatus;
import com.companybase.model.Company;
import com.companybase.model.ContactPerson;
import com.companybase.model.Country;
import com.companybase.model.Details;
import com.companybase.model.UserComments;
import com.companybase.service.AboutService;
import com.companybase.service.AppService;
import com.companybase.service.ApplicationService;
import com.companybase.service.CommentsService;
import com.companybase.service.CompanyService;
import com.companybase.service.ContactService;
import com.companybase.service.CountryService;
import com.companybase.service.DetailsService;
import com.companybase.ui.components.ComboBoxCalendar;
import com.companybase.ui.components.NumberSpinner;
import com.companybase.ui.components.YearSpinner;
import com.companybase.ui.panels.NewAboutPanel;
import com.companybase.ui.panels.NewCommentsPanel;
import com.companybase.ui.panels.NewCompanyPanel;
import com.companybase.ui.panels.NewContactPanel;
import com.companybase.ui.panels.NewDetailsPanel;
import com.companybase.ui.panels.UrlAddressPanel;

public class NewCompanyDialog extends JDialog {
	
	public MainWindow mWindow;
	public Connection conn;
	
	private NewCompanyPanel jplNewCompany;
	private NewDetailsPanel jplNewDetails;
	private NewAboutPanel jplAboutText;
	private NewCommentsPanel jplComments;
	private NewContactPanel jplContacts;
	
	private JScrollPane jspNewCompany;
	private JScrollPane jspNewDetails;
	private JScrollPane jspAboutText;
	private JScrollPane jspComments;
	private JScrollPane jspContact;
	
	private JTabbedPane jtbCompanyInfo;
		
	private JButton btnCancel;
	private JButton btnOk;
			
	private JPanel jplCommand;
	private JPanel jplContainer;
		
	public AppService appService;
	
	public NewCompanyDialog(MainWindow mWindow) {
		
		super(mWindow);
		
		this.mWindow = mWindow;
		
		this.initDao();
		this.initComponents();
		
		this.setupJDialog();
		this.createLayout();
		this.registerEvents();
		
	}
	
	private void initDao() {
		
		try {
			
		this.conn = Connector.getConnection();
		this.appService = new AppService(conn);		
		
		} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
	}
	
	private void initComponents() {
					
		this.jplNewCompany = new NewCompanyPanel(mWindow);
		this.jplNewDetails = new NewDetailsPanel(mWindow);
		this.jplAboutText = new NewAboutPanel(mWindow);
		this.jplComments = new NewCommentsPanel(mWindow);
		this.jplContacts = new NewContactPanel(mWindow);
				
		this.jspNewCompany = new JScrollPane(jplNewCompany);
		this.jspNewDetails = new JScrollPane(jplNewDetails);
		this.jspAboutText = new JScrollPane(jplAboutText);;
		this.jspComments = new JScrollPane(jplComments);
		this.jspContact = new JScrollPane(jplContacts);
		
		this.jtbCompanyInfo = new JTabbedPane(JTabbedPane.TOP);
		
		this.btnCancel = createJButton("Cancel");
		this.btnOk = createJButton("Ok");
		
		this.jplCommand = new JPanel(new GridBagLayout());		
		this.jplContainer = new JPanel(new GridBagLayout());
		
	}
	
	private void setupJDialog() {
		
		this.setSize(730,565);
		this.setModal(true);
		this.setTitle("New company");
		this.setContentPane(jplContainer);
		this.setLocationRelativeTo(null);
				
	}
	
	private void createLayout() {
					
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.jtbCompanyInfo.addTab("Basic", jspNewCompany);
		this.jtbCompanyInfo.addTab("Details", jspNewDetails);
		this.jtbCompanyInfo.addTab("About", jspAboutText);
		this.jtbCompanyInfo.addTab("Comments", jspComments);
		this.jtbCompanyInfo.addTab("Contacts", jspContact);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(3,3,3,3);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.BOTH;
		gbc.fill = GridBagConstraints.BOTH;
		this.jplContainer.add(jtbCompanyInfo, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,6,6,3);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.jplContainer.add(jplCommand, gbc);
		
		this.setupJPlCommand();
		
	}
	
	private void setupJPlCommand() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(3,3,3,3);
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.EAST;
		this.jplCommand.add(btnCancel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(3,3,3,3);
		gbc.weightx = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		this.jplCommand.add(btnOk, gbc);
		
	}
	
	private void btnCancelPressed() {
		
		super.dispose();
		
	}
	
	private void btnOkPressed() {

	    try {

	        String tempName = jplNewCompany.txtCompanyName.getText().trim();
	    		        
	        if (tempName == null || 
	        	tempName.isBlank() || tempName.isEmpty()) {
	        	
	        	btnOk.setEnabled(false);
	        	
	        	String nameMsg = "<html><center>The value of " + 
	        	"Companyname<br /> must not be empty</center></html>";
	        	JOptionPane.showMessageDialog(mWindow, nameMsg);
	        	
	        	btnOk.setEnabled(true);	        	
	        	return;
	        	
	        }	        
	        
	        Company company = jplNewCompany.getCompany();
	        Details details = jplNewDetails.getDetails();
	        AboutText aboutText = jplAboutText.getAboutText();
	        UserComments comments = jplComments.getComments();

	        long companyId = appService.saveCompany(company);
	        	        
	        details.setId(companyId);
	        this.appService.saveDetails(details);
	        
	        aboutText.setId(companyId);
	        this.appService.saveAboutText(aboutText);
	        
	        comments.setId(companyId);
	        this.appService.saveComments(comments);
	        
	        java.util.List<ContactPerson> contacts =
	        jplContacts.getContactPersons(companyId);

	        this.appService.saveContacts(companyId, contacts);
	        
	        JOptionPane.showMessageDialog(
	        mWindow,"Company saved successfully!",
	        "Success", JOptionPane.INFORMATION_MESSAGE);

	    dispose();

	    } catch (Exception ex) {

	        ex.printStackTrace();

	        JOptionPane.showMessageDialog(
	                this, "Error saving company:\n" + ex.getMessage(),
	                "Error",JOptionPane.ERROR_MESSAGE
	        );
	    }
	    
	    this.mWindow.refreshTable();
	    
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
		
		try {
			
		if (!conn.isClosed()) {
			
			this.conn.close();			
			
		}
			
		} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
		
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
