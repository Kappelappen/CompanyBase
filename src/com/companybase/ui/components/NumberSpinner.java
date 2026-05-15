package com.companybase.ui.components;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NumberSpinner extends JSpinner {

	private int width;
	private int initialValue;
	private int MAX_VALUE;
	private Border disabledBorder;
	private Border enabledBorder;
	
    public NumberSpinner(int width, 
    	int initialValue, int MAX_VALUE) {
    	    	
    	super(new SpinnerNumberModel(
    			initialValue,   // initialt värde
                0,   // minvärde
                MAX_VALUE,   // maxvärde
                1           // steg
        ));
    	
    	this.width = width;
    	this.initialValue = initialValue;
    	this.MAX_VALUE = MAX_VALUE;
    	
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(this, "#");
        JTextField textField = editor.getTextField();
        textField.setFont(new Font("Arial",Font.PLAIN,16));
        textField.setBackground(Color.white);
                
        textField.setBorder(BorderFactory.createEmptyBorder(0,3,0,3));
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setDisabledTextColor(SystemColor.textText);
                
        textField.addKeyListener(new KeyAdapter() {
        	
        	@Override
        	public void keyTyped(KeyEvent e) {
        		
        		char chr = e.getKeyChar();
        		
        		if (Character.isLetter(chr)) {
        			
        			e.consume();
        			
        		}        		
        	}        	
        });
        
        this.init();
        this.setEditor(editor);
            
    }
    
    private void init() {

    	enabledBorder = getBorder();
        disabledBorder = BorderFactory.createLineBorder(SystemColor.controlDkShadow);

        // Använder explicit listener istället för lambda
        this.addPropertyChangeListener("enabled", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateBorder();
            }
        });

        updateBorder();
        
    }
    
    private void updateBorder() {
        
    	if (isEnabled()) {
            setBorder(enabledBorder);
        } else {
            setBorder(disabledBorder);
        }
    }
    
    @Override
    public Dimension getMinimumSize() {
    	
    	return new Dimension(width,28);
    	
    }
    
    @Override
    public Dimension getPreferredSize() {
    	
    	return new Dimension(width,27);
    	
    }
    
    @Override
    public Dimension getMaximumSize() {
    	
    	return new Dimension(width,27);
    	
    }
}
