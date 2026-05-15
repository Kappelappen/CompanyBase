package com.companybase.events.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

import com.companybase.ui.windows.MainWindow;
import com.companybase.ui.windows.NewCompanyDialog;

public class NewMenuAction extends AbstractAction {

	private MainWindow mWindow;
	
	public NewMenuAction(MainWindow mWindow) {
		
		this.mWindow = mWindow;
		
		this.configAction();
		
	}
	
	private void configAction() {
		
		this.putValue(Action.SHORT_DESCRIPTION, "New company");
		this.putValue(Action.NAME, "New company");
		
		this.putValue(Action.ACCELERATOR_KEY,
	    KeyStroke.getKeyStroke(KeyEvent.VK_N,
	    java.awt.Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		super.setEnabled(false);
		
		NewCompanyDialog dialog = new NewCompanyDialog(mWindow);
		dialog.setVisible(true);
		
		
		super.setEnabled(true);
		
	}
}
