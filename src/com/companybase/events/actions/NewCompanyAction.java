package com.companybase.events.actions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.*;

import com.companybase.ui.windows.MainWindow;
import com.companybase.ui.windows.NewCompanyDialog;

public class NewCompanyAction extends AbstractAction {

	private boolean isButtonAction;
	private MainWindow mWindow;
	
	public NewCompanyAction(boolean isButtonAction, 
		MainWindow mWindow) {
		
		this.isButtonAction = isButtonAction;
		this.mWindow = mWindow;
		
		this.setupAction();
				
	}
	
	private void setupAction() {
		
		super.putValue(Action.SHORT_DESCRIPTION, "New company");
		
		if (isButtonAction) {
			
			super.putValue(Action.SMALL_ICON, loadIcon("new.png"));
			
		}
		
		if (!isButtonAction) {
						
			super.putValue(Action.NAME, "New company");
			
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		super.setEnabled(false);
		
		NewCompanyDialog dialog = new NewCompanyDialog(mWindow);
		dialog.setVisible(true);
		
		super.setEnabled(true);
		
	}
	
	public ImageIcon loadIcon(String name) {
		
		URL url = super.getClass().getResource("/images/" + name);
		ImageIcon icon = new ImageIcon(url);
		
		return icon;
		
		
	}
	
	
}
