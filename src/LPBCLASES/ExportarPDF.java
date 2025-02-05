package LPBCLASES;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;

public class ExportarPDF {

    public void exportar(JTable tabla, String ruta, Temporada temporada) {
        // 🔹 Configurar el documento en horizontal (landscape) y color de fondo #FFF6C6
        BaseColor colorFondo = new BaseColor(255, 246, 198); // Color #FFF6C6
        Document documento = new Document(PageSize.A4.rotate(), 30, 30, 10, 50);

        try {
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(new File(ruta)));
            documento.open();

            // 🔹 Agregar color de fondo a la página
            PdfContentByte canvas = writer.getDirectContentUnder();
            Rectangle rect = new Rectangle(documento.getPageSize());
            rect.setBackgroundColor(colorFondo);
            canvas.rectangle(rect);

            // 🔹 Agregar un LOGO con tamaño 200x200
            Image logo = Image.getInstance("src/imagenes/logo500.png");
            logo.scaleToFit(200, 200);

            // 🔹 Obtener la temporada desde el objeto temporada
            String periodoTemporada = temporada.getPeriodo(); 

            // 🔹 Estilos para el título y subtítulo
            BaseColor colorTitulo = new BaseColor(19, 66, 126); // Azul oscuro (0x13427E)
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD, colorTitulo);
            Font fontSubtitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.ITALIC, BaseColor.DARK_GRAY); 

            // 🔹 Crear un párrafo con el título
            Paragraph titulo = new Paragraph("Clasificación de Equipos", fontTitulo);
            titulo.setAlignment(Element.ALIGN_LEFT);

            // 🔹 Crear un párrafo con el subtítulo (temporada obtenida)
            Paragraph subtitulo = new Paragraph("Temporada " + periodoTemporada, fontSubtitulo);
            subtitulo.setAlignment(Element.ALIGN_LEFT);

            // 🔹 Usar una tabla de 2 columnas para alinear logo y título + subtítulo
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            float[] columnWidths = {25f, 75f}; 
            headerTable.setWidths(columnWidths);

            // Celda del logo
            PdfPCell cellLogo = new PdfPCell(logo);
            cellLogo.setBorder(Rectangle.NO_BORDER);
            cellLogo.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellLogo.setPaddingRight(10f);
            headerTable.addCell(cellLogo);

            // Celda del título y subtítulo juntos
            PdfPCell cellTexto = new PdfPCell();
            cellTexto.setBorder(Rectangle.NO_BORDER);
            cellTexto.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTexto.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTexto.addElement(titulo);
            cellTexto.addElement(subtitulo);
            headerTable.addCell(cellTexto);

            // Agregar la tabla con logo y títulos al documento
            documento.add(headerTable);

            // 🔹 Agregar la fecha actual
            Font fontFecha = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.DARK_GRAY);
            Paragraph fecha = new Paragraph("Generado el: " + new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date()), fontFecha);
            fecha.setAlignment(Element.ALIGN_RIGHT);
            documento.add(fecha);
            documento.add(new Paragraph("\n")); 

            // 🔹 Crear tabla PDF
            PdfPTable pdfTable = new PdfPTable(tabla.getColumnCount());
            pdfTable.setWidthPercentage(100);
            pdfTable.setSpacingBefore(10f);
            pdfTable.setSpacingAfter(10f);

            // Definir anchos de columnas
            float[] columnTableWidths = {5f, 30f, 10f, 10f, 10f, 10f, 10f, 10f, 10f};
            pdfTable.setWidths(columnTableWidths);

            // 🔹 Cambiar el color del encabezado a #012e6b
            TableModel modelo = tabla.getModel();
            Font fontEncabezado = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
            BaseColor colorEncabezado = new BaseColor(1, 46, 107); // Azul oscuro

            for (int i = 0; i < modelo.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(modelo.getColumnName(i), fontEncabezado));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(colorEncabezado);
                cell.setPadding(8);
                pdfTable.addCell(cell);
            }

            // 🔹 Agregar datos con filas alternas en gris claro y gris medio claro
            Font fontDatos = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
            BaseColor colorFilaPar = new BaseColor(234, 234, 234);
            BaseColor colorFilaImpar = new BaseColor(214, 214, 214);

            for (int i = 0; i < modelo.getRowCount(); i++) {
                for (int j = 0; j < modelo.getColumnCount(); j++) {
                    PdfPCell cell;

                    if (j == 1) { // Columna "Equipo" (índice 1)
                        String equipoNombre = modelo.getValueAt(i, j).toString();
                        String rutaImagen = "src/imagenes/temporadas/Temporada " + temporada.getPeriodo() + "/" + equipoNombre + "/" + equipoNombre + ".png";

                        PdfPTable equipoTable = new PdfPTable(2);
                        equipoTable.setWidths(new float[]{30f, 70f});

                        try {
                            Image equipoLogo = Image.getInstance(rutaImagen);
                            equipoLogo.scaleToFit(30, 30);
                            PdfPCell cellImagen = new PdfPCell(equipoLogo);
                            cellImagen.setBorder(Rectangle.NO_BORDER);
                            cellImagen.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cellImagen.setPaddingRight(10f);
                            equipoTable.addCell(cellImagen);
                        } catch (Exception e) {
                            PdfPCell cellImagen = new PdfPCell(new Phrase("No Img"));
                            cellImagen.setBorder(Rectangle.NO_BORDER);
                            cellImagen.setHorizontalAlignment(Element.ALIGN_CENTER);
                            equipoTable.addCell(cellImagen);
                        }

                        PdfPCell cellTextoEquipo = new PdfPCell(new Phrase(equipoNombre, fontDatos));
                        cellTextoEquipo.setBorder(Rectangle.NO_BORDER);
                        cellTextoEquipo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        equipoTable.addCell(cellTextoEquipo);

                        cell = new PdfPCell(equipoTable);
                    } else {
                        cell = new PdfPCell(new Phrase(modelo.getValueAt(i, j).toString(), fontDatos));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    }

                    cell.setPadding(6);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setBackgroundColor((i % 2 == 0) ? colorFilaPar : colorFilaImpar);
                    pdfTable.addCell(cell);
                }
            }

            documento.add(pdfTable);
            documento.close();

            // 🔹 Abrir el PDF automáticamente
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