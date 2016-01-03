package br.com.dealercar.relatorios;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.dealercar.util.JSFUtil;

public class GeraRelatorio {

	public static void main(String[] args) {
	
		//cria o documento
		Document document = null;
		java.io.OutputStream os = null;
		
		try {
			
			//recebendo a instancia de uma documento A4
			document = new Document(PageSize.A4);
			
			//cria a entrada
			os = new FileOutputStream("C://SegundoPDF.pdf");
			PdfWriter.getInstance(document, os);
			//abre documento para Escrita
			document.open();
			//setando margens
			document.setMargins(72, 72, 72, 72);
			

			Font f = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
			Paragraph p1 = new Paragraph("Este é um Teste", f);
			p1.setAlignment(Element.ALIGN_CENTER);
			document.add(p1);
			
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}finally {
			document.close();
		}
				
		
	}
	
}
