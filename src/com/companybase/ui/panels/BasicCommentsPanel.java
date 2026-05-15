package com.companybase.ui.panels;

import java.awt.*;
import javax.swing.*;

import com.companybase.model.UserComments;
import com.companybase.ui.windows.MainWindow;

public class BasicCommentsPanel extends JPanel {

	private MainWindow mWindow;
		
	public JTextArea txtComments;
	private JScrollPane jspComments;
	
	public BasicCommentsPanel(MainWindow mWindow) {
		
		this.mWindow = mWindow;
		
		this.initComponents();
		this.setupJPanel();
		this.createLayout();
		
	}	
	
	private void initComponents() {
		
		this.txtComments = new JTextArea();
		this.jspComments = new JScrollPane(txtComments);
		
	}
	
	private void setupJPanel() {
		
		this.configJTextArea();		
		this.setBorder(null);
		this.setLayout(new GridBagLayout());
		
	}
	
	private void configJTextArea() {
		
		this.txtComments.setDisabledTextColor(SystemColor.textText);
		this.txtComments.setEnabled(false);
		this.txtComments.setCaretColor(Color.white);
		this.txtComments.setCaretPosition(0);
		this.txtComments.setLineWrap(true);
		this.txtComments.setTabSize(1);
		this.txtComments.setWrapStyleWord(true);
		this.txtComments.setMargin(new Insets(6,6,6,6));
		this.txtComments.setFont(new Font("Arial",Font.PLAIN,16));
		
	}
	
	private void createLayout() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = GridBagConstraints.BOTH;
		gbc.weighty = GridBagConstraints.BOTH;
		this.add(jspComments, gbc);		
		
	}
	
	public UserComments getComments() {
		
		String text = txtComments.getText();
		UserComments comments = new UserComments();
		comments.setComments(text);
		
		return comments;
		
	}
}
