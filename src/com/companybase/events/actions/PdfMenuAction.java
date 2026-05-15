package com.companybase.events.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.companybase.threads.PdfExportWorker;
import com.companybase.ui.windows.MainWindow;
import com.companybase.ui.windows.NewCompanyDialog;

public class PdfMenuAction extends AbstractAction {

	private MainWindow mWindow;
	
	public PdfMenuAction(MainWindow mWindow) {
		
		this.mWindow = mWindow;		
		this.configAction();
		
	}
	
	private void configAction() {
		
		this.putValue(Action.SHORT_DESCRIPTION, "Export to PDF");
		this.putValue(Action.NAME, "Export to PDF");
		
		this.putValue(Action.ACCELERATOR_KEY,
	    KeyStroke.getKeyStroke(KeyEvent.VK_P,
	    java.awt.Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		
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
}
