package com.qrassistance.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;

import org.hibernate.proxy.ProxyConfiguration;

import net.glxn.qrgen.QRCode;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import net.glxn.qrgen.image.ImageType;

public class Template_PDF_Credentials {
	String rutaLogoEmpresa="D:\\CIBERTEC\\CURSOS CIBERTEC V\\D_APP_WEB I\\Proyecto\\QRAssistance\\src\\main\\webapp\\images\\logo_qr_assistance.png";
    String rutaPDF="D:\\CIBERTEC\\CURSOS CIBERTEC V\\D_APP_WEB I\\Proyecto\\QRAssistance\\src\\main\\webapp\\pdfs\\main.pdf";
    public void CrearPDF(String nombre, String apellidoPaterno, String apellidoMaterno, String cod, String fechaI, String QR, String ImgEmple) throws IOException {
    	try {
			PdfWriter writer= new PdfWriter(rutaPDF);
			PdfDocument pdf= new PdfDocument(writer);
			Document document= new Document(pdf,PageSize.A4);
			document.setMargins(50, 90, 20, 90);
			
			PdfFont font= PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
			PdfFont bold= PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
			
			
			Image logoEmpresa =new Image(ImageDataFactory.create(rutaLogoEmpresa));
			logoEmpresa.scaleAbsolute(80, 50);
			logoEmpresa.setHorizontalAlignment(HorizontalAlignment.CENTER);
			document.add(logoEmpresa);
			
			document.add(new Paragraph());
			document.add(new Paragraph());
			
			Paragraph titulo=new Paragraph("**** QRAssitence ****");
			titulo.setFontSize(20f);
			titulo.setTextAlignment(TextAlignment.CENTER);
			document.add(titulo.setFont(bold));			
			
			document.add(new Paragraph());
			document.add(new Paragraph());
			document.add(new Paragraph());
			
			Paragraph CodigoEmple= new Paragraph();
			CodigoEmple.add(blueText("Cod. Empleado: ", font));
			CodigoEmple.add(blackText(cod.toUpperCase(), bold));
			document.add(CodigoEmple);
			
			Paragraph apePEmple=new Paragraph();
			apePEmple.add(blueText("Ap. Paterno: ", font)); 
			apePEmple.add(blackText(apellidoPaterno.toUpperCase(), bold));
			document.add(apePEmple);
			
			Paragraph apeMEmple=new Paragraph();
			apeMEmple.add(blueText("Ap. Materno: ", font));               
			apeMEmple.add(blackText(apellidoMaterno.toUpperCase(), bold));
			document.add(apeMEmple);
						
			Paragraph nombEmple= new Paragraph();
			nombEmple.add(blueText("Nombres: ", font));                
			nombEmple.add(blackText(nombre.toUpperCase(), bold)); 
			document.add(nombEmple);
			
			document.add(new Paragraph());
			
			Paragraph fechaIngreso= new Paragraph();
			fechaIngreso.add(blueText("Fecha de Ingreso: ", font));           
			fechaIngreso.add(blackText(fechaI.toUpperCase(), bold)); 
			fechaIngreso.setFontSize(12f);
			fechaIngreso.setTextAlignment(TextAlignment.CENTER);
			document.add(fechaIngreso);
			
			Image FotoEmpleado =new Image(ImageDataFactory.create(ImgEmple));
			FotoEmpleado.scaleAbsolute(110, 130);
			FotoEmpleado.setFixedPosition(415, 550);
			document.add(FotoEmpleado);
			
			ByteArrayOutputStream out=QRCode.from(QR).to(ImageType.JPG).stream();
	        Image CodQR =new Image(ImageDataFactory.create(out.toByteArray()));
	        CodQR.scaleAbsolute(310, 310);
			CodQR.setHorizontalAlignment(HorizontalAlignment.CENTER);
			document.add(CodQR);
			
			document.close();
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public Text blueText(String cadena, PdfFont font) {
    	Text blueText=null;
    	return blueText= new Text(cadena).setFontColor(ColorConstants.BLUE).setFont(font);
    }
    public Text blackText(String cadena, PdfFont font) {
    	Text blackText=null;
    	return blackText= new Text(cadena).setFontColor(ColorConstants.BLACK).setFont(font);
    }

}
