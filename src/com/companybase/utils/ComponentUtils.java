package com.companybase.utils;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public abstract class ComponentUtils {

	public static void customize(JComponent comp) {
		
		Dimension txtDim = new Dimension(0,30);
		Dimension comboDim = new Dimension(0,30);
		
		int index = comp.getComponentCount();
		
		for (int i = 0; i < index; i++) {
			
			Component formComp = comp.getComponent(i);
			
			if (formComp instanceof JLabel) {
				
				JLabel label = (JLabel) formComp;
				label.setFont(new Font("Arial",Font.PLAIN,16));
				
			}
			
			if (formComp instanceof JTextComponent) {
				
				JTextComponent txtComp = (JTextComponent) formComp;
				
				txtComp.setMinimumSize(txtDim);
				txtComp.setPreferredSize(txtDim);
				txtComp.setMaximumSize(txtDim);
				txtComp.setMargin(new Insets(0,3,0,3));
				txtComp.setFont(new Font("Arial",Font.PLAIN,16));
				
			}			
			
			if (formComp instanceof JComboBox<?>) {
				
				JComboBox<?> comboBox = (JComboBox<?>) formComp;	
				comboBox.setMinimumSize(comboDim);
				comboBox.setPreferredSize(comboDim);
				comboBox.setMaximumSize(comboDim);
				
			}			
		}		
	}	
	
	public static JMenuItem createJMenuItem(int width, Action action) {
		
		Dimension dim = new Dimension(width,30);
		JMenuItem jmiComponent = new JMenuItem();
		
		jmiComponent.setFocusable(false);
		jmiComponent.setMinimumSize(dim);
		jmiComponent.setPreferredSize(dim);
		jmiComponent.setMaximumSize(dim);
		jmiComponent.setAction(action);
		
		return jmiComponent;
		
	}
}
