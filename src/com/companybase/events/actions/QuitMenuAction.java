package com.companybase.events.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

import com.companybase.ui.windows.MainWindow;
import com.companybase.ui.windows.NewCompanyDialog;

public class QuitMenuAction extends AbstractAction {

	private MainWindow mWindow;
	
	public QuitMenuAction(MainWindow mWindow) {
		
		this.mWindow = mWindow;
		
		this.configAction();
		
	}
	
	private void configAction() {
		
		this.putValue(Action.SHORT_DESCRIPTION, "Quit");
		this.putValue(Action.NAME, "Quit");
		
		this.putValue(Action.ACCELERATOR_KEY,
	    KeyStroke.getKeyStroke(KeyEvent.VK_Q,
	    java.awt.Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		this.mWindow.dispose();
		System.exit(0);
		
	}
}
