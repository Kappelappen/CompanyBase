package com.companybase.ui.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;
import javax.swing.*;

import com.companybase.ui.windows.MainWindow;

public class UrlAddressPanel extends JPanel {
	
	private MainWindow mWindow;
	
	public JTextField txtAddressField;
	public JButton btnUrlScout;
	
	public UrlAddressPanel(MainWindow mWindow) {
		
		this.mWindow = mWindow;
				
		this.setupJPanel();
		this.initComponents();
		this.buildLayout();
		
	}
	
	private void setupJPanel() {
				
		this.setBorder(null);
		this.setOpaque(false);
		this.setFocusable(false);
		this.setLayout(new GridBagLayout());
		
	}
	
	private void initComponents() {
		
		this.txtAddressField = new JTextField();
		this.btnUrlScout = createJButton();
		
	}
	
	private void buildLayout() {
				
		Dimension dim = new Dimension(0,30);
		
		this.txtAddressField.setMinimumSize(dim);
		this.txtAddressField.setPreferredSize(dim);
		this.txtAddressField.setMaximumSize(dim);
		this.txtAddressField.setMargin(new Insets(0,3,0,3));
		this.txtAddressField.setFont(new Font("Arial",Font.PLAIN,16));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,3);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = GridBagConstraints.BOTH;
		this.add(txtAddressField, gbc);
		
		gbc.gridx = 1; 
		gbc.gridy = 0; 
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = GridBagConstraints.NONE;
		this.add(btnUrlScout, gbc);
		
	}
	
	private void openUrl() {
        
    	String urlText = txtAddressField.getText().trim();
        
        if (urlText == null || urlText.isBlank()) {
        
        	this.btnUrlScout.setEnabled(false);
        	
        	JOptionPane.showMessageDialog(new JFrame(), 
            "The URL-address was not found", 
            "Error message", 
            JOptionPane.WARNING_MESSAGE);
        	
        	this.txtAddressField.setText(null);
            this.txtAddressField.requestFocus();
        	
            this.btnUrlScout.setEnabled(true);
            
            return;
        
        }

        try {
        
        URI uri = new URI(urlText);
        Desktop.getDesktop().browse(uri);
        
        } catch (Exception ex) {
        	
        	this.txtAddressField.requestFocus();
            this.txtAddressField.setText(null);
        	
            JOptionPane.showMessageDialog(new JFrame(), 
            "Could not open the requested URL: " + ex.getMessage(),
            "There was an error", JOptionPane.ERROR_MESSAGE);
                    
        }
    }
	
	private JButton createJButton() {
		
		URL url = super.getClass().getResource("/images/right_arrow.png");
		ImageIcon icon = new ImageIcon(url);
		
		Dimension dim = new Dimension(26,26);
		JButton button = new JButton();
		
		button.setFocusable(false);
		button.setMinimumSize(dim);
		button.setPreferredSize(dim);
		button.setMaximumSize(dim);
		button.setOpaque(false);
		button.setIcon(icon);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				openUrl();
				
			}			
		});
		
		return button;
		
		
	}
}
