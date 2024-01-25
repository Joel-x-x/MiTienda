package desk.mitienda.service;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import desk.mitienda.model.DatosEmpresa;
import desk.mitienda.model.DetalleNota;
import desk.mitienda.model.NotaVenta;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ComprobantesService {

    public void generarNotaVentaPdf(NotaVenta notaVenta, DatosEmpresa datosEmpresa) {

        try {
            List<DetalleNota> detalles =  notaVenta.getDetalle();
            String ruta = System.getProperty("user.home");
            FileOutputStream archivo = new FileOutputStream(ruta + "/Downloads/" +notaVenta.getNumero()+".pdf");
            Document documento = new Document();
            PdfWriter.getInstance(documento, archivo);
            documento.open();

            Font fontTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("Nota de Venta");
            titulo.setFont(fontTitulo);
            titulo.setAlignment(1);
            documento.add(titulo);

            Paragraph parrafo = new Paragraph("Número " + notaVenta.getPuntoEmision() + "-" + notaVenta.getEstablecimiento() + "-" + notaVenta.getNumero());
            documento.add(parrafo);

            documento.add(new Paragraph("Cliente: " + notaVenta.getCliente().getNombre() + " " + notaVenta.getCliente().getApellido()));
            documento.add(new Paragraph("Identificación: " + notaVenta.getCliente().getIdentificacion()));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Propietario: " + datosEmpresa.getNombres() + "                                    CI/RUC: " + datosEmpresa.getIdentificacion()));
            documento.add(new Paragraph("Establecimiento: " + datosEmpresa.getNombreEmpresa()));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Fecha de emision: "+ notaVenta.getFecha()));
            documento.add(new Paragraph("Forma de pago: " + notaVenta.getFormaPago()));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));


            PdfPTable tablaDetalles = new PdfPTable(4);
            tablaDetalles.setTotalWidth(new float[]{100,20,20,20});
            tablaDetalles.setWidthPercentage(100); // Tamaño de cada columna
            tablaDetalles.getDefaultCell().setBorder(0); // Definimos bordes 0

            tablaDetalles.addCell("Producto");
            tablaDetalles.addCell("Cantidad");
            tablaDetalles.addCell("Precio Unitario");
            tablaDetalles.addCell("Total");

            detalles.forEach(detalle -> {
                tablaDetalles.addCell(detalle.getProducto().getNombre());
                tablaDetalles.addCell(detalle.getCantidad() + "");
                tablaDetalles.addCell(detalle.getPrecio() + "");
                tablaDetalles.addCell(detalle.getTotal() + "");
            });

            documento.add(tablaDetalles);

            documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            documento.add(new Paragraph("Total: " + notaVenta.getTotal()));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Dirección: " + datosEmpresa.getDireccion() + "                                    Celular: " + datosEmpresa.getCelular()));
            documento.add(new Paragraph("Correo: " + datosEmpresa.getCorreo()));
            documento.add(new Paragraph("Vendedor: " + notaVenta.getUsuario().getNombre() + " " + notaVenta.getUsuario().getApellido()));
            documento.add(new Paragraph("IVA Incluido en el precio"));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Gracias por tu compra " + notaVenta.getCliente().getNombre()));
            documento.close();

        } catch ( FileNotFoundException e1) {

            System.out.println(e1);
        } catch (DocumentException e1) {

            e1.printStackTrace();
        }

        String ruta = System.getProperty("user.home");
        File path = new File(ruta +"/Downloads/" + notaVenta.getNumero() + ".pdf");
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
