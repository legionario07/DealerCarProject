package br.com.dealercar.relatorios;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.viewhelper.SessionHelper;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * 
 * @author Paulinho
 * Classe responsável por Gerar Relatórios
 * @param <T> 
 */
public class GeraRelatorio<T extends EntidadeDominio> {

	/**
	 * Método responsavel por gerar Relatório
	 * @param parametros Recebe uma Map<String, Object>
	 * @param con Recebe uma conexao com o Banco de Dados
	 */
	public static void exportarPDF(Map<String, Object> parametros, Connection con){
		
		parametros.put("nomeGerador", ((Funcionario)SessionHelper.getParam("usuarioLogado")).getNome());
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/persona2.jasper"));		
		
		JasperPrint jasperPrint;
		try {
			jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, con);
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition", "attachment; filename=report.pdf");
			ServletOutputStream stream = response.getOutputStream();
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			stream.flush();
			stream.close();
			con.close();
			FacesContext.getCurrentInstance().responseComplete();
			
		} catch (JRException | IOException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
