package com.companybase.events.actions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.*;

import com.companybase.ui.windows.EditCompanyDialog;
import com.companybase.ui.windows.MainWindow;

public class EditCompanyAction extends AbstractAction {

	private boolean isButtonAction;
	private MainWindow mWindow;
	
	public EditCompanyAction(boolean isButtonAction, 
		MainWindow mWindow) {
		
		this.isButtonAction = isButtonAction;
		this.mWindow = mWindow;
		
		this.setupAction();
				
	}
	
	private void setupAction() {
		
		super.putValue(Action.SHORT_DESCRIPTION, "Edit");
		
		if (isButtonAction) {
			
			super.putValue(Action.SMALL_ICON, loadIcon("edit.png"));
			
		}
		
		if (!isButtonAction) {
						
			super.putValue(Action.NAME, "Edit");
			
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
				
		int tableRow = mWindow.jtbCompanyInfo.getSelectedRow();
		if (tableRow == -1) return;
		
		if (tableRow != -1) {
			
			super.setEnabled(false);
			
		}
		
		int modelRow = mWindow.jtbCompanyInfo.convertRowIndexToModel(tableRow);
		if (modelRow == -1) return;
		
		long tableId = (long) mWindow.jtbCompanyInfo.getValueAt(modelRow, 0);
		if (tableId == -1) return;
		
		EditCompanyDialog dialog = 
			new EditCompanyDialog(tableId, mWindow);
				
		dialog.setVisible(true);
		super.setEnabled(true);		
		
	}
	
	public ImageIcon loadIcon(String name) {
		
		URL url = super.getClass().getResource("/images/" + name);
		ImageIcon icon = new ImageIcon(url);
		
		return icon;
		
		
	}
	
	
}
