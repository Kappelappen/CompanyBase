package com.companybase.threads;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PdfExportWorker extends Thread {

    private final JTable table;
    private final File outputFile;

    public PdfExportWorker(JTable table, File outputFile) {
        this.table = table;
        this.outputFile = outputFile;
    }

    @Override
    public void run() {

        TableModel model = table.getModel();

        try (PDDocument document = new PDDocument()) {

            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream content =
                    new PDPageContentStream(document, page);

            content.setFont(PDType1Font.HELVETICA, 9);

            int startX = 50;
            int startY = 750;
            int y = startY;

            int rowHeight = 18;

            // -----------------------------
            // STEP 1: CALCULATE COLUMN WIDTHS
            // -----------------------------
            int colCount = model.getColumnCount();

            int[] colWidths = new int[colCount];

            // minimum width so it doesn't collapse
            for (int i = 1; i < colCount; i++) {
                colWidths[i] = 80;
            }

            for (int row = 0; row < model.getRowCount(); row++) {

                for (int col = 1; col < colCount; col++) {

                    Object value = model.getValueAt(row, col);
                    String text = value != null ? value.toString() : "";

                    int textWidth = text.length() * 6; // rough pixel estimate

                    colWidths[col] = Math.max(colWidths[col], textWidth + 20);
                }
            }

            // -----------------------------
            // HEADER
            // -----------------------------
            int x = startX;

            for (int col = 1; col < colCount; col++) {

                content.addRect(x, y - 5, colWidths[col], rowHeight);
                content.stroke();

                content.beginText();
                content.newLineAtOffset(x + 5, y);
                content.showText(model.getColumnName(col));
                content.endText();

                x += colWidths[col];
            }

            y -= rowHeight;

            // -----------------------------
            // ROWS
            // -----------------------------
            for (int row = 0; row < model.getRowCount(); row++) {

                x = startX;

                int maxLines = 1;

                List<List<String>> wrappedRow = new ArrayList<>();

                for (int col = 1; col < colCount; col++) {

                    Object value = model.getValueAt(row, col);
                    String text = value != null ? value.toString() : "";

                    List<String> wrapped = wrapText(text, 20);
                    wrappedRow.add(wrapped);

                    maxLines = Math.max(maxLines, wrapped.size());
                }

                int cellHeight = maxLines * 12 + 6;
                int baseY = y;

                for (int col = 1; col < colCount; col++) {

                    List<String> lines = wrappedRow.get(col - 1);

                    content.addRect(x, y - 5, colWidths[col], cellHeight);
                    content.stroke();

                    int textY = baseY;

                    for (String line : lines) {

                        content.beginText();
                        content.newLineAtOffset(x + 5, textY);
                        content.showText(line);
                        content.endText();

                        textY -= 12;
                    }

                    x += colWidths[col];
                }

                y -= cellHeight;

                if (y < 60) {

                    content.close();

                    PDPage newPage = new PDPage();
                    document.addPage(newPage);

                    content = new PDPageContentStream(document, newPage);
                    content.setFont(PDType1Font.HELVETICA, 9);

                    y = startY;
                }
            }

            content.close();
            document.save(outputFile);

            System.out.println("Auto-sized PDF exported: " +
                    outputFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -----------------------------
    // WRAP TEXT
    // -----------------------------
    private List<String> wrapText(String text, int maxChars) {

        List<String> lines = new ArrayList<>();

        if (text == null || text.isEmpty()) {
            lines.add("");
            return lines;
        }

        while (text.length() > maxChars) {
            lines.add(text.substring(0, maxChars));
            text = text.substring(maxChars);
        }

        lines.add(text);

        return lines;
    }
}