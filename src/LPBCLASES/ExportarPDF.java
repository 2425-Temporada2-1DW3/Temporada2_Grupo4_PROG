package LPBCLASES;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;
import com.itextpdf.text.BaseColor;


public class ExportarPDF {

    public void exportar(JTable tabla, String ruta) {
        Document documento = new Document();
        
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(new File(ruta)));
            documento.open();

            // Agregar título al PDF
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("Clasificación de Equipos", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph("\n")); // Espaciado

            // Crear tabla PDF con el mismo número de columnas que la JTable
            PdfPTable pdfTable = new PdfPTable(tabla.getColumnCount());
            pdfTable.setWidthPercentage(100);

            // Agregar encabezados al PDF
            TableModel modelo = tabla.getModel();
            for (int i = 0; i < modelo.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(modelo.getColumnName(i), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                pdfTable.addCell(cell);
            }

            // Agregar filas de la tabla
            for (int i = 0; i < modelo.getRowCount(); i++) {
                for (int j = 0; j < modelo.getColumnCount(); j++) {
                    pdfTable.addCell(new Phrase(modelo.getValueAt(i, j).toString()));
                }
            }

            documento.add(pdfTable);
            documento.close();
            
         // Abrir el archivo PDF automáticamente
            File archivoPDF = new File(ruta);
            if (archivoPDF.exists()) {
                Desktop.getDesktop().open(archivoPDF);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo PDF", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "Exportación a PDF completada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al exportar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
