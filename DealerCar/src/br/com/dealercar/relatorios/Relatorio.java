package br.com.dealercar.relatorios;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import br.com.dealercar.domain.EntidadeDominio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio<T extends EntidadeDominio> {

	public void gerarRelatorio(List<T> lista){
		
		//String relat = "c:\\testerelatorio.pdf";
		
		
		InputStream fonte = Relatorio.class.getResourceAsStream("/reports/rep_clientes.jrxml");
		
		System.out.println(new File("").getAbsolutePath());
		System.out.println(new File("").getPath());
		
		JasperReport report = null;
		try {
			report = JasperCompileManager.compileReport(fonte);
			JasperPrint print = JasperFillManager.fillReport(report,  null, new JRBeanCollectionDataSource(lista));
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
