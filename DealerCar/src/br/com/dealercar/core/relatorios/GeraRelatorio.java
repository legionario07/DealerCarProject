package br.com.dealercar.core.relatorios;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.conducao.Devolucao;
import br.com.dealercar.domain.conducao.Retirada;
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
	public static void exportarListPDF(HashMap<String, Object> parametros, File jasper, EntidadeDominio entidade) {

		String nome = "report" + gerarNomeComData() + ".pdf";

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		if (entidade instanceof Retirada) {
			lista.add((Retirada) entidade);
		} else if (entidade instanceof Devolucao) {
			lista.add((Devolucao) entidade);
		} else if (entidade instanceof Cliente) {
			lista.add((Cliente) entidade);
		}

		JasperPrint jasperPrint;
		try {
			jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros,
					new JRBeanCollectionDataSource(lista));
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			response.addHeader("Content-disposition", "attachment; filename=" + nome);
			ServletOutputStream stream = response.getOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			stream.flush();
			stream.close();
			FacesContext.getCurrentInstance().responseComplete();

		} catch (JRException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gera os Relatorios Sintéticos
	 * 
	 * @param parametros
	 *            - Parametros para gerar os relatorios, passado na VIew
	 * @param jasper
	 *            - Url do Relatorio.jasper
	 */
	public static void exportarListPDF(HashMap<String, Object> parametros, File jasper) {

		String nome = "report" + gerarNomeComData() + ".pdf";

		JasperPrint jasperPrint;
		try {
			jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, Conexao.getConnection());
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			response.addHeader("Content-disposition", "attachment; filename=" + nome);
			ServletOutputStream stream = response.getOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			stream.flush();
			stream.close();
			FacesContext.getCurrentInstance().responseComplete();

		} catch (JRException | IOException e) {
			e.printStackTrace();
		}

	}

	private static String gerarNomeComData() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd _ hh:mm:ss");

		Calendar c = Calendar.getInstance();
		String s = sdf.format(c.getTime());
		return s.replace("/", "").replace(" ", "").replace(":", "");
	}

}
