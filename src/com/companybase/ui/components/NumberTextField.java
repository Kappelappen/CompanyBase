package com.companybase.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumberTextField extends JTextField {

	private boolean showValidationTooltip = false;
	
    public NumberTextField() {
            	
        this.setupJTextField();
        this.registerEvents();
        
    }

    private void setupJTextField() {

        Border emptyBorder = BorderFactory.createEmptyBorder(0, 3, 0, 3);
        Border lineBorder = BorderFactory.createLineBorder(SystemColor.controlDkShadow);
        Border compBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
             
        this.setBorder(compBorder);
        this.setDisabledTextColor(SystemColor.textText);
        this.setForeground(Color.BLACK); 

    }
    
    private void registerEvents() {
    	
    	this.addKeyListener(new KeyAdapter() {
    		
    		@Override
    		public void keyTyped(KeyEvent e) {
    			
    			Character chr = e.getKeyChar();
    			
    			if (!chr.isDigit(chr)) {
    				
    				e.consume();
    				
    			}   			
    		}    		
    	});
    }
    
    @Override
	public Dimension getMinimumSize() {
		
		Dimension dim = new Dimension(0, 27);
		return dim;
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		
		Dimension dim = new Dimension(0, 27);
		return dim;
		
	}
	
	@Override
	public Dimension getMaximumSize() {
		
		Dimension dim = new Dimension(0,27);
		return dim;
		
	}
}
