package desk.mitienda.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import desk.mitienda.model.Compra;
import desk.mitienda.model.Kardex;
import desk.mitienda.model.NotaVenta;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class ReportesService {
    private BigDecimal total = BigDecimal.ZERO;

    public void generarReporteKardex(List<Kardex> listaKardex) {

        try {
            String ruta = System.getProperty("user.home");
            FileOutputStream archivo = new FileOutputStream(ruta + "/Downloads/Reporte Kardex.pdf");
            com.itextpdf.text.Document documento = new com.itextpdf.text.Document(PageSize.LETTER.rotate());
            PdfWriter.getInstance(documento, archivo);
            documento.open();


            // Crea un objeto Font con estilo negrita
            com.itextpdf.text.Font fuenteTitulo = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 16, Font.BOLD);

            com.itextpdf.text.Font fuenteEncabezadoTabla = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, Font.BOLD);

            // Crea un Paragraph y aplica el estilo de fuente negrita
            Paragraph parrafo = new Paragraph("Reporte de membresías", fuenteTitulo);

            parrafo.setAlignment(1);
            documento.add(parrafo);

            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Reporte Kardex"));
            documento.add(new Paragraph("\n"));

            PdfPTable tabla = new PdfPTable(11);

            float[] anchosColumnas = {70f, 80f, 60f, 60f, 80f, 20f,30f, 30f, 30f, 30f, 30f}; // Ejemplo de anchos de columnas
            tabla.setTotalWidth(anchosColumnas);
            tabla.setWidths(anchosColumnas);

            tabla.addCell(new PdfPCell(new Phrase("Fecha", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Numero", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Proveedor/Cliente", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Tipo", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Producto", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Cantidad", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Precio unitario", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Precio total", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Cantidad existencia", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Costo unitario en existencia", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Costo total existencia", fuenteEncabezadoTabla)));

            tabla.completeRow();

            listaKardex.forEach(kardex -> {
                tabla.addCell(new PdfPCell(new Phrase(String.valueOf(kardex.getFecha()))));
                tabla.addCell(new PdfPCell(new Phrase(kardex.getNumero())));
                tabla.addCell(new PdfPCell(new Phrase(kardex.getProveedorCliente())));
                tabla.addCell(new PdfPCell(new Phrase(kardex.getTipo() + "")));
                tabla.addCell(new PdfPCell(new Phrase(kardex.getProducto().getNombre())));
                tabla.addCell(new PdfPCell(new Phrase(kardex.getCantidad() + "")));
                tabla.addCell(new PdfPCell(new Phrase(kardex.getPrecioUnitario() + "")));
                tabla.addCell(new PdfPCell(new Phrase(kardex.getPrecioTotal() + "")));
                tabla.addCell(new PdfPCell(new Phrase(kardex.getCantidadExistencia() + "")));
                tabla.addCell(new PdfPCell(new Phrase(kardex.getCostoUnitarioExistencia() + "")));
                tabla.addCell(new PdfPCell(new Phrase(kardex.getCostoTotalExistencia() + "")));

                tabla.completeRow();

            });

            documento.add(tabla);

            documento.close();

        } catch ( FileNotFoundException e1) {

            System.out.println(e1);
        } catch (DocumentException e1) {

            e1.printStackTrace();
        }

        String ruta = System.getProperty("user.home");
        File path = new File(ruta + "/Downloads/Reporte Kardex.pdf");
        System.out.println(path);

        if (path.exists()) {
            try {
                Desktop.getDesktop().open(path);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void generarReporteVentas(List<NotaVenta> listaNotaVenta) {

        try {
            String ruta = System.getProperty("user.home");
            FileOutputStream archivo = new FileOutputStream(ruta + "/Downloads/Reporte Ventas.pdf");
            com.itextpdf.text.Document documento = new com.itextpdf.text.Document(PageSize.LETTER.rotate());
            PdfWriter.getInstance(documento, archivo);
            documento.open();


            // Crea un objeto Font con estilo negrita
            com.itextpdf.text.Font fuenteTitulo = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 16, Font.BOLD);

            com.itextpdf.text.Font fuenteEncabezadoTabla = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, Font.BOLD);

            // Crea un Paragraph y aplica el estilo de fuente negrita
            Paragraph parrafo = new Paragraph("Reporte de ventas", fuenteTitulo);

            parrafo.setAlignment(1);
            documento.add(parrafo);

            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Reporte Ventas"));
            documento.add(new Paragraph("\n"));

            PdfPTable tabla = new PdfPTable(8);

            float[] anchosColumnas = {100f, 100f, 100f, 100f, 100f, 100f,100f, 100f}; // Ejemplo de anchos de columnas
            tabla.setTotalWidth(anchosColumnas);
            tabla.setWidths(anchosColumnas);

            tabla.addCell(new PdfPCell(new Phrase("Numero", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Punto Emision", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Establecimiento", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Fecha", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Forma de Pago", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Cliente", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Vendedor", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Total", fuenteEncabezadoTabla)));

            tabla.completeRow();

            listaNotaVenta.forEach(notaVenta -> {
                tabla.addCell(new PdfPCell(new Phrase(notaVenta.getNumero())));
                tabla.addCell(new PdfPCell(new Phrase(notaVenta.getPuntoEmision())));
                tabla.addCell(new PdfPCell(new Phrase(notaVenta.getEstablecimiento())));
                tabla.addCell(new PdfPCell(new Phrase(notaVenta.getFecha() + "")));
                tabla.addCell(new PdfPCell(new Phrase(notaVenta.getFormaPago())));
                tabla.addCell(new PdfPCell(new Phrase(notaVenta.getCliente().getNombre() + " " + notaVenta.getCliente().getApellido())));
                tabla.addCell(new PdfPCell(new Phrase(notaVenta.getUsuario().getNombre() + " " + notaVenta.getUsuario().getApellido())));
                tabla.addCell(new PdfPCell(new Phrase(notaVenta.getTotal() + "")));

                tabla.completeRow();

                this.total = this.total.add(notaVenta.getTotal());
            });

            documento.add(tabla);

            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Ventas totales: " + this.total));
            documento.add(new Paragraph("\n"));

            documento.close();

        } catch ( FileNotFoundException e1) {

            System.out.println(e1);
        } catch (DocumentException e1) {

            e1.printStackTrace();
        }

        String ruta = System.getProperty("user.home");
        File path = new File(ruta + "/Downloads/Reporte Ventas.pdf");
        System.out.println(path);

        if (path.exists()) {
            try {
                Desktop.getDesktop().open(path);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void generarReporteCompras(List<Compra> listaCompra) {

        try {
            String ruta = System.getProperty("user.home");
            FileOutputStream archivo = new FileOutputStream(ruta + "/Downloads/Reporte Compras.pdf");
            com.itextpdf.text.Document documento = new com.itextpdf.text.Document(PageSize.LETTER.rotate());
            PdfWriter.getInstance(documento, archivo);
            documento.open();


            // Crea un objeto Font con estilo negrita
            com.itextpdf.text.Font fuenteTitulo = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 16, Font.BOLD);

            com.itextpdf.text.Font fuenteEncabezadoTabla = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, Font.BOLD);

            // Crea un Paragraph y aplica el estilo de fuente negrita
            Paragraph parrafo = new Paragraph("Reporte de compras", fuenteTitulo);

            parrafo.setAlignment(1);
            documento.add(parrafo);

            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Reporte Compras"));
            documento.add(new Paragraph("\n"));

            PdfPTable tabla = new PdfPTable(9);

            float[] anchosColumnas = {100f, 100f, 100f, 100f, 100f, 100f,100f, 100f, 100f}; // Ejemplo de anchos de columnas
            tabla.setTotalWidth(anchosColumnas);
            tabla.setWidths(anchosColumnas);

            tabla.addCell(new PdfPCell(new Phrase("Numero", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Punto Emision", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Establecimiento", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Fecha", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Forma de Pago", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Proveedor", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Subtotal", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Iva", fuenteEncabezadoTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Total", fuenteEncabezadoTabla)));

            tabla.completeRow();

            listaCompra.forEach(compra -> {
                tabla.addCell(new PdfPCell(new Phrase(compra.getNumero())));
                tabla.addCell(new PdfPCell(new Phrase(compra.getPuntoEmision())));
                tabla.addCell(new PdfPCell(new Phrase(compra.getEstablecimiento())));
                tabla.addCell(new PdfPCell(new Phrase(compra.getFecha() + "")));
                tabla.addCell(new PdfPCell(new Phrase(compra.getFormaPago())));
                tabla.addCell(new PdfPCell(new Phrase(compra.getProveedor().getEmpresa())));
                tabla.addCell(new PdfPCell(new Phrase(compra.getSubtotal() + "")));
                tabla.addCell(new PdfPCell(new Phrase(compra.getIva() + "")));
                tabla.addCell(new PdfPCell(new Phrase(compra.getTotal() + "")));

                tabla.completeRow();

                this.total = this.total.add(compra.getTotal());
            });

            documento.add(tabla);

            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Compras totales: " + this.total));
            documento.add(new Paragraph("\n"));

            documento.close();

        } catch ( FileNotFoundException e1) {

            System.out.println(e1);
        } catch (DocumentException e1) {

            e1.printStackTrace();
        }

        String ruta = System.getProperty("user.home");
        File path = new File(ruta + "/Downloads/Reporte Compras.pdf");
        System.out.println(path);

        if (path.exists()) {
            try {
                Desktop.getDesktop().open(path);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
