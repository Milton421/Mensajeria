package com.mensajeria.util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mensajeria.model.Envio;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/** Genera reportes de envios en PDF. */
public final class ReportePDF {

    private static final DateTimeFormatter FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Color MARINO = new Color(0x14213D);

    private ReportePDF() {
    }

    private static String f(LocalDate d) {
        return d == null ? "" : d.format(FECHA);
    }

    private static String s(String x) {
        return x == null ? "" : x;
    }

    public static void generar(File destino, String titulo, List<Envio> envios, String usuario) throws IOException {
        Document doc = new Document(PageSize.A4.rotate(), 28, 28, 30, 30);
        try (FileOutputStream out = new FileOutputStream(destino)) {
            PdfWriter.getInstance(doc, out);
            doc.open();

            Font fTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, MARINO);
            Font fSub = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.GRAY);
            Font fHead = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, Color.WHITE);
            Font fCelda = FontFactory.getFont(FontFactory.HELVETICA, 8.5f, Color.BLACK);

            doc.add(new Paragraph("Sistema de Control de Mensajería", fSub));
            doc.add(new Paragraph(titulo, fTitulo));
            Paragraph meta = new Paragraph("Generado el "
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                    + " por " + usuario + "  |  Total de registros: " + envios.size(), fSub);
            meta.setSpacingAfter(12);
            doc.add(meta);

            String[] cols = {"No.", "Fecha", "Cliente", "Tipo", "Descripción", "Destinatario",
                "Dirección destino", "Mensajero", "Estado", "Entrega"};
            float[] anchos = {4, 8, 14, 7, 16, 13, 18, 12, 9, 9};
            PdfPTable t = new PdfPTable(cols.length);
            t.setWidthPercentage(100);
            t.setWidths(anchos);
            t.setHeaderRows(1);
            for (String c : cols) {
                PdfPCell h = new PdfPCell(new Phrase(c, fHead));
                h.setBackgroundColor(MARINO);
                h.setPadding(5);
                h.setHorizontalAlignment(Element.ALIGN_CENTER);
                t.addCell(h);
            }
            boolean alterno = false;
            for (Envio e : envios) {
                Color fondo = alterno ? new Color(0xF4F6FA) : Color.WHITE;
                String[] fila = {String.valueOf(e.id()), f(e.fechaEnvio()), s(e.clienteNombre()),
                    s(e.tipoPaquete()), s(e.descripcionPaquete()), s(e.nombreDestinatario()),
                    s(e.direccionDestino()), e.mensajeroNombre() == null ? "Sin asignar" : e.mensajeroNombre(),
                    s(e.estado()), f(e.fechaEntrega())};
                for (String v : fila) {
                    PdfPCell c = new PdfPCell(new Phrase(v, fCelda));
                    c.setBackgroundColor(fondo);
                    c.setPadding(4);
                    c.setBorderColor(new Color(0xDDE2EC));
                    t.addCell(c);
                }
                alterno = !alterno;
            }
            if (envios.isEmpty()) {
                PdfPCell vacio = new PdfPCell(new Phrase("No hay registros para este reporte.", fCelda));
                vacio.setColspan(cols.length);
                vacio.setPadding(12);
                vacio.setHorizontalAlignment(Element.ALIGN_CENTER);
                t.addCell(vacio);
            }
            doc.add(t);
            doc.close();
        } catch (DocumentException e) {
            throw new IOException("No se pudo crear el PDF: " + e.getMessage(), e);
        } finally {
            if (doc.isOpen()) {
                doc.close();
            }
        }
    }
}
