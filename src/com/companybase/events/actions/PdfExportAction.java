package com.companybase.events.actions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.companybase.threads.PdfExportWorker;
import com.companybase.ui.windows.MainWindow;

public class PdfExportAction extends AbstractAction {

	private boolean isButtonAction;
	private MainWindow mWindow;
	
	public PdfExportAction(boolean isButtonAction, 
		MainWindow mWindow) {
		
		this.isButtonAction = isButtonAction;
		this.mWindow = mWindow;
		
		this.setupAction();
				
	}
	
	private void setupAction() {
		
		super.putValue(Action.SHORT_DESCRIPTION, "PDF");
		
		if (isButtonAction) {
			
			super.putValue(Action.SMALL_ICON, loadIcon("pdf.png"));
			
		}
		
		if (!isButtonAction) {
						
			super.putValue(Action.NAME, "Export to pdf");
			
		}		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		super.setEnabled(false);
		
        JFileChooser fileChooser = new JFileChooser();
        
        FileNameExtensionFilter filter =
        	new FileNameExtensionFilter("PDF files (*.pdf)", "pdf");

        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Save PDF file");
        
        int result = fileChooser.showSaveDialog(mWindow);

        if (result == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();

            // Ensure .pdf extension
            if (!file.getName().toLowerCase().endsWith(".pdf")) {
            
            	file = new File(file.getAbsolutePath() + ".pdf");
            
            }
            
            if (file.getName().endsWith(".pdf")) {
            	
            	final PdfExportWorker worker = 
            		new PdfExportWorker(mWindow.jtbCompanyInfo, file);
            	
            	worker.start();
            	super.setEnabled(true);
            	
            }
        }
        
        if (result == JFileChooser.CANCEL_OPTION) {
        	
        	super.setEnabled(true);
        	
        }        
	}
	
	public ImageIcon loadIcon(String name) {
		
		URL url = super.getClass().getResource("/images/" + name);
		ImageIcon icon = new ImageIcon(url);
		
		return icon;
		
		
	}
	
	
}
