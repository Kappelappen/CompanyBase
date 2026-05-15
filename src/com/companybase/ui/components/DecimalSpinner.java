package com.companybase.ui.components;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DecimalSpinner extends JSpinner {

	private int width;
	private double maxValue;
	
    public DecimalSpinner(int width,  
    	double maxValue) {
    	
    	this.width = width;
    	this.maxValue = maxValue;
    	
        setModel(new SpinnerNumberModel(0.0, 0.0, maxValue, 0.1));

        // Editor
        JSpinner.NumberEditor editor =
                new JSpinner.NumberEditor(this, "0.0");
        setEditor(editor);

        JTextField textField = editor.getTextField();
        textField.setPreferredSize(new Dimension(110,26));        
        textField.setBorder(BorderFactory.createEmptyBorder(0,3,0,3));
        textField.setFont(new Font("Arial",Font.PLAIN,16));
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setEditable(false);
        textField.setCaretColor(Color.white);
        textField.setCaretPosition(0);
        textField.setBackground(Color.white);
                
        // Blockera bokstäver
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                // Tillåt siffror, decimalpunkt och kontrolltecken
                if (!Character.isDigit(c) &&
                    c != '.' &&
                    c != KeyEvent.VK_BACK_SPACE &&
                    c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }
        });
    }
    
    @Override
	public Dimension getMinimumSize() {
		
		Dimension dim = new Dimension(120, 26);
		return dim;
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		
		Dimension dim = new Dimension(120, 26);
		return dim;
		
	}
	
	@Override
	public Dimension getMaximumSize() {
		
		Dimension dim = new Dimension(120, 26);
		return dim;
		
	}
}
