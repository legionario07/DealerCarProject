package br.com.dealercar.core.relatorios;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.com.dealercar.core.negocio.Retirada;
import br.com.dealercar.domain.EntidadeDominio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author Paulinho Classe responsável por Gerar Relatórios
 * @param <T>
 */
public class GeraRelatorio<T extends EntidadeDominio> {

	/**
	 * Método responsavel por gerar Relatório
	 * 
	 * @param parametros
	 *            Recebe uma Map<String, Object>
	 * @param con
	 *            Recebe uma conexao com o Banco de Dados
	 */
	public static void exportarListPDF(HashMap<String, Object> parametros, File jasper, List<Retirada> lista) {

		// parametros.put("nomeGerador",
		// ((Funcionario)SessionHelper.getParam("usuarioLogado")).getNome());

		JasperPrint jasperPrint;
		try {
			jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros,
					new JRBeanCollectionDataSource(lista));
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			response.addHeader("Content-disposition", "attachment; filename=report.pdf");
			ServletOutputStream stream = response.getOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			stream.flush();
			stream.close();
			FacesContext.getCurrentInstance().responseComplete();

		} catch (JRException | IOException e) {
			e.printStackTrace();
		}

	}

}
