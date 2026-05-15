package com.companybase.ui.panels;

import java.awt.*;
import javax.swing.*;

import com.companybase.model.AboutText;
import com.companybase.ui.windows.MainWindow;
import com.companybase.ui.windows.NewCompanyDialog;

public class NewAboutPanel extends JPanel {

	private MainWindow mWindow;
	
	public JTextArea txtAboutUs;
	private JScrollPane jspAboutUs;
	
	public NewAboutPanel(MainWindow mWindow) {
		
		this.mWindow = mWindow;
		
		this.initComponents();
		this.setupJPanel();
		this.createLayout();
		
	}	
	
	private void initComponents() {
		
		this.txtAboutUs = new JTextArea();
		this.jspAboutUs = new JScrollPane(txtAboutUs);
		
	}
	
	private void setupJPanel() {
		
		this.configJTextArea();
		
		this.setBorder(null);
		this.setLayout(new GridBagLayout());
		
	}
	
	private void configJTextArea() {
		
		this.txtAboutUs.setLineWrap(true);
		this.txtAboutUs.setTabSize(1);
		this.txtAboutUs.setWrapStyleWord(true);
		this.txtAboutUs.setMargin(new Insets(6,6,6,6));
		this.txtAboutUs.setFont(new Font("Arial",Font.PLAIN,16));
		
	}
	
	private void createLayout() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.BOTH;
		this.add(jspAboutUs, gbc);		
		
	}
	
	public void loadAboutText(long id) {
		
		AboutText text = mWindow.service.aboutDao.findById(id);
		String content = text.getAboutText();
		this.txtAboutUs.setText(content);
		
	}
	
	public AboutText getAboutText() {
		
		String text = txtAboutUs.getText();
		
		AboutText about = new AboutText();
		about.setAboutText(text);
		
		return about;
		
	}
	
	
}
