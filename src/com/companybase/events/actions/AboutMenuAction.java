package com.companybase.events.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

import com.companybase.ui.windows.AboutAppDialog;
import com.companybase.ui.windows.MainWindow;
import com.companybase.ui.windows.NewCompanyDialog;

public class AboutMenuAction extends AbstractAction {

	private MainWindow mWindow;
	
	public AboutMenuAction(MainWindow mWindow) {
		
		this.mWindow = mWindow;		
		this.configAction();
		
	}
	
	private void configAction() {
		
		this.putValue(Action.SHORT_DESCRIPTION, "About CompanyBase");
		this.putValue(Action.NAME, "About CompanyBase");
		
		this.putValue(Action.ACCELERATOR_KEY,
	    KeyStroke.getKeyStroke(KeyEvent.VK_A,
	    java.awt.Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		super.setEnabled(false);
		
		AboutAppDialog dialog = new AboutAppDialog(mWindow);
		dialog.setVisible(true);
		
		super.setEnabled(true);
		
	}
}
