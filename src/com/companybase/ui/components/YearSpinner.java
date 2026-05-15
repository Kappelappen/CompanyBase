package com.companybase.ui.components;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.time.Year;

public class YearSpinner extends JSpinner {

    private static final int MIN_YEAR = 1000;

    private int width;
    
    public YearSpinner(int width) {

        super(new SpinnerNumberModel(
                Year.now().getValue(),
                MIN_YEAR,
                Year.now().getValue(),
                1));
        
        this.width = width;

        // Get the default number editor
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) getEditor();
        JFormattedTextField textField = editor.getTextField();
        
        textField.setHorizontalAlignment(JFormattedTextField.LEFT);
        textField.setFont(new Font("Arial",Font.PLAIN,16));
        textField.setBorder(BorderFactory.createEmptyBorder(0,3,0,3));

        // Disable grouping separators so 2026 is shown instead of "2 026"
        DecimalFormat format = editor.getFormat();
        format.setGroupingUsed(false);

        // Update the formatter with the modified format
        NumberFormatter formatter = (NumberFormatter) textField.getFormatter();
        formatter.setFormat(format);

        // Force the current value to be reformatted immediately
        textField.setValue(getValue());

        // Only allow digits and control keys (e.g. backspace)
        textField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                char chr = e.getKeyChar();

                if (!Character.isDigit(chr) &&
                    !Character.isISOControl(chr)) {
                    e.consume();
                }
            }
        });
    }

    public int getSelectedYear() {
        return (Integer) getValue();
    }

    public void setSelectedYear(int year) {

        int currentYear = Year.now().getValue();

        if (year < MIN_YEAR || year > currentYear) {
            throw new IllegalArgumentException(
                    "Year must be between " +
                    MIN_YEAR +
                    " and " +
                    currentYear
            );
        }

        setValue(year);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(width, 28);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, 27);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(width, 27);
    }
}