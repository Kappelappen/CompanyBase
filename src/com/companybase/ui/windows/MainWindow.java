package com.companybase.ui.windows;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.companybase.database.Connector;
import com.companybase.events.actions.NewMenuAction;
import com.companybase.events.actions.PdfMenuAction;
import com.companybase.events.actions.QuitMenuAction;
import com.companybase.events.actions.AboutMenuAction;
import com.companybase.model.AboutText;
import com.companybase.model.Company;
import com.companybase.model.ContactPerson;
import com.companybase.model.Details;
import com.companybase.model.UserComments;
import com.companybase.service.AppService;
import com.companybase.ui.panels.BasicAboutPanel;
import com.companybase.ui.panels.BasicCommentsPanel;
import com.companybase.ui.panels.BasicCompanyPanel;
import com.companybase.ui.panels.BasicContactPanel;
import com.companybase.ui.panels.BasicDetailsPanel;
import com.companybase.ui.panels.CommandPanel;
import com.companybase.ui.panels.SearchPanel;
import com.companybase.ui.panels.StatusPanel;
import com.companybase.utils.ComponentUtils;

public class MainWindow extends JFrame {

	private String title;
	
	private Connection conn;
	public AppService service;
	
	public DefaultTableModel dtModel;
	public JTable jtbCompanyInfo;
	private JScrollPane jspCompanyInfo;
	
	private JPanel jplCenterView;
	
	public BasicCompanyPanel jplCompany;
	public BasicDetailsPanel jplDetails;
	public BasicAboutPanel jplAbout;
	public BasicCommentsPanel jplComments;
	public BasicContactPanel jplContact;
	
	private JScrollPane jspCompany;
	private JScrollPane jspDetails;
	
	private JTabbedPane jtpBaseView;
	
	private JMenuItem jmiNewAction;
	private JMenuItem jmiPdfAction;
	private JMenuItem jmiQuitAction;
	private JMenuItem jmiAboutAction;
	private JMenu jmFile;
	private JMenu jmHelp;
	private JMenuBar jmbTopView;
	
	private CommandPanel jplCommand;
	private SearchPanel jplSearch;
	private StatusPanel jplStatus;
	
	private JSplitPane jspMainView;
	
	private JPanel jplContainer;
	
	public MainWindow(String title) {
		
		this.title = title;
		
		this.initComponents();
		this.setupJFrame();
		this.createLayout();
		this.registerEvents();
		
	}
	
	private void initComponents() {
		
		try {
						
		this.conn = Connector.getConnection();
		this.service = new AppService(conn);
			
		} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
		
		this.dtModel = new DefaultTableModel();
		this.jtbCompanyInfo = new JTable(dtModel);
		this.jspCompanyInfo = new JScrollPane(jtbCompanyInfo);
		
		this.jplCenterView = new JPanel(new GridBagLayout());
		
		this.jplCompany = new BasicCompanyPanel(this);
		this.jplDetails = new BasicDetailsPanel(this);
		this.jplAbout = new BasicAboutPanel(this);
		this.jplComments = new BasicCommentsPanel(this);
		this.jplContact = new BasicContactPanel(this);
		
		this.jspCompany = new JScrollPane(jplCompany);
		this.jspDetails = new JScrollPane(jplDetails);
		
		this.jtpBaseView = new JTabbedPane(JTabbedPane.TOP);
		
		this.jmiNewAction = ComponentUtils.createJMenuItem(145, new NewMenuAction(this));
		this.jmiPdfAction = ComponentUtils.createJMenuItem(145, new PdfMenuAction(this));
		this.jmiQuitAction = ComponentUtils.createJMenuItem(145, new QuitMenuAction(this));
		this.jmiAboutAction = ComponentUtils.createJMenuItem(180, new AboutMenuAction(this));
		
		this.jmFile = new JMenu("File");
		this.jmHelp = new JMenu("Help");
		this.jmbTopView = new JMenuBar();
		
		this.jplCommand = new CommandPanel(this);
		this.jplSearch = new SearchPanel(this);
		this.jplStatus = new StatusPanel(this);
		
		this.jspMainView = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		this.jplContainer = new JPanel(new GridBagLayout());
		
	}
	
	private void setupJFrame() {
		
		this.setTitle(title);
		this.setJMenuBar(jmbTopView);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setContentPane(jplContainer);
				
	}
	
	private void createLayout() {
	
		this.setupJMenuBar();
		this.setupJTabbedPane();
		this.setupJTbCompanyInfo();
		this.setupJPlCenterView();
		this.setupJSplitPane();
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(3,3,3,3);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		this.jplContainer.add(jplCommand, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,3,3,3);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		this.jplContainer.add(jplSearch, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,3,3,3);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		this.jplContainer.add(jspMainView, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,3,3,3);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		this.jplContainer.add(jplStatus, gbc);
		
	}
	
	private void setupJTabbedPane() {
		
		this.jspCompany.setBorder(null);
		this.jspCompany.setViewportBorder(BorderFactory.createEtchedBorder());
		
		this.jspDetails.setBorder(null);
		this.jspDetails.setViewportBorder(BorderFactory.createEtchedBorder());
		
		this.jtpBaseView.addTab("Company", jspCompany);
		this.jtpBaseView.addTab("Details", jspDetails);
		this.jtpBaseView.addTab("About", jplAbout);
		this.jtpBaseView.addTab("Comments", jplComments);
		this.jtpBaseView.addTab("Contacts", jplContact);
		
	}
	
	private void setupJTbCompanyInfo() {
		
		int mode = ListSelectionModel.SINGLE_SELECTION;		
		this.jtbCompanyInfo.getSelectionModel().setSelectionMode(mode);
		
		JTableHeader header = jtbCompanyInfo.getTableHeader();
		header.setReorderingAllowed(false);
		
		this.dtModel.addColumn("ID");
		this.dtModel.addColumn("Company");
		this.dtModel.addColumn("Business");
		this.dtModel.addColumn("E-mail");
		this.dtModel.addColumn("Phone");
		
		TableColumn column = jtbCompanyInfo.getColumnModel().getColumn(0);
		column.setMinWidth(0);
		column.setPreferredWidth(0);
		column.setMaxWidth(0);
		column.setResizable(false);
		
		java.util.List<Company> list = service.companyDao.findAll();
		
		for (int i = 0; i < list.size(); i++) {
			
			Company company = list.get(i);
			
			long id = company.getId();
			String name = company.getName();
			String industry = company.getIndustry();
			String email = company.getEmail();
			String phone = company.getPhone();
			
			Object[] row = new Object[] { id,
			name,industry,email,phone };
			
			this.dtModel.addRow(row);
			
		}		
		
		this.dtModel.fireTableDataChanged();
		
	}
	
	public void refreshTable() {
		
		this.dtModel.setRowCount(0);
		
		java.util.List<Company> list = service.companyDao.findAll();
		
		for (int i = 0; i < list.size(); i++) {
			
			Company company = list.get(i);
			
			long id = company.getId();
			String name = company.getName();
			String industry = company.getIndustry();
			String email = company.getEmail();
			String phone = company.getPhone();
			
			Object[] row = new Object[] { id,
			name,industry,email,phone };
			
			this.dtModel.addRow(row);
			
		}
		
		this.dtModel.fireTableDataChanged();
		
	}
	
	private void setupJMenuBar() {
		
		this.jmFile.setFocusable(false);
		this.jmbTopView.add(jmFile);
		
		this.jmFile.add(jmiNewAction);
		this.jmFile.add(jmiPdfAction);
		this.jmFile.add(new JSeparator(JSeparator.HORIZONTAL));
		this.jmFile.add(jmiQuitAction);
		
		this.jmbTopView.add(jmHelp);
		this.jmHelp.add(jmiAboutAction);
		
	}
	
	private void fillBasicDetailsForm(Details details) {
		
		String orgNumber = details.getOrgNumber();
		String employeeCount = details.getEmployeeCount();
		String foundedYear = details.getFoundedYear();
		String linkedin = details.getLinkedinAddress();
		String facebook = details.getFacebookAddress();
	
		this.jplDetails.txtOrgNumber.setText(orgNumber);
		this.jplDetails.txtEmployeeCount.setText(employeeCount);
		this.jplDetails.txtFoundedYear.setText(foundedYear);
		this.jplDetails.jplLinkedin.txtAddressField.setText(linkedin);
		this.jplDetails.jplFacebook.txtAddressField.setText(facebook);
	}
	
	private void setupJPlCenterView() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(6,6,6,6);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.BOTH;
		this.jplCenterView.add(jtpBaseView, gbc);
		
	}
	
	private void setupJSplitPane() {
		
		this.jspMainView.setBorder(null);
		this.jspMainView.setDividerLocation(200);
		this.jspMainView.setDividerSize(10);
		this.jspMainView.setContinuousLayout(true);
		this.jspMainView.setOneTouchExpandable(true);
		this.jspMainView.setTopComponent(jspCompanyInfo);
		this.jspMainView.setBottomComponent(jplCenterView);
		
		
	}
	
	private void registerEvents() {
		
		this.jtbCompanyInfo.getSelectionModel().addListSelectionListener(
		new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
			
				if (!e.getValueIsAdjusting()) return;
				
				int tableRow = jtbCompanyInfo.getSelectedRow();
				if (tableRow == -1) return;
				
				long tableId = (long) jtbCompanyInfo.getValueAt(tableRow, 0);	
				if (tableId == -1) return;
				
				Company company = service.companyDao.findById(tableId);
				if (company == null) return;				
				
				jplCompany.fetchCompanyInfo(company);
				
				Details details = service.detailsDao.findById(tableId);
				details.setId(tableId);
				fillBasicDetailsForm(details);
				
				AboutText about = service.aboutDao.findById(tableId);
				String aboutText = about.getAboutText();
				jplAbout.txtAboutUs.setText(aboutText);				
				
				UserComments comments = service.commentsDao.findById(tableId);
				String commentsText = comments.getComments();
				jplComments.txtComments.setText(commentsText);
				
				jplContact.personModel.setRowCount(0);
				
				java.util.List<ContactPerson> persons = service.contactDao.findById(tableId);
				
				for (int i = 0; i < persons.size(); i++) {
					
					ContactPerson contact = persons.get(i);
					
					long id = contact.getId();
					String name = contact.getName();
					String jobTitle = contact.getJobTitle();
					String phone = contact.getPhone();
										
					Object[] rowData = new Object[] { id, name,jobTitle,phone };
					jplContact.personModel.addRow(rowData);
					
				}
				
				jplContact.personModel.fireTableDataChanged();
							
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				jFrameClosing();
				
			}			
		});
	}
	
	private void jFrameClosing() {
		
		try {			
		this.conn.close();			
		} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
		
		this.dispose();
		System.exit(0);
		
	}
}
