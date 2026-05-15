package com.companybase.events.actions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.*;

import com.companybase.ui.windows.EditCompanyDialog;
import com.companybase.ui.windows.MainWindow;

public class DeleteCompanyAction extends AbstractAction {

	private boolean isButtonAction;
	private MainWindow mWindow;
	
	public DeleteCompanyAction(boolean isButtonAction, 
		MainWindow mWindow) {
		
		this.isButtonAction = isButtonAction;
		this.mWindow = mWindow;
		
		this.setupAction();
				
	}
	
	private void setupAction() {
		
		super.putValue(Action.SHORT_DESCRIPTION, "Delete");
		
		if (isButtonAction) {
			
			super.putValue(Action.SMALL_ICON, loadIcon("delete.png"));
			
		}
		
		if (!isButtonAction) {
						
			super.putValue(Action.NAME, "Delete");
			
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int tableRow = mWindow.jtbCompanyInfo.getSelectedRow();
		if (tableRow == -1) return;
		
		int modelRow = mWindow.jtbCompanyInfo.convertRowIndexToModel(tableRow);
		if (modelRow == -1) return;
		
		long tableId = (long) mWindow.jtbCompanyInfo.getValueAt(modelRow, 0);
		if (tableId == -1) return;
		
		super.setEnabled(false);
		
		String msg ="Do you wish to delete the selected company ?";
		int option = JOptionPane.showConfirmDialog(mWindow, msg);
		
		if (option == JOptionPane.OK_OPTION) {
		
			boolean deleteCompany = mWindow.service.companyDao.deleteById(tableId);
			
			if (deleteCompany) {
				
				this.mWindow.dtModel.removeRow(modelRow);
				this.mWindow.dtModel.fireTableDataChanged();
				this.mWindow.jtbCompanyInfo.revalidate();
				
				String result = "Company was deleted";
				JOptionPane.showMessageDialog(mWindow, result);
				
			}			
		}		
		
		super.setEnabled(true);
				
	}
	
	public ImageIcon loadIcon(String name) {
		
		URL url = super.getClass().getResource("/images/" + name);
		ImageIcon icon = new ImageIcon(url);
		
		return icon;
		
		
	}
	
	
}
