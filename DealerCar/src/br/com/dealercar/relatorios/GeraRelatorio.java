package br.com.dealercar.relatorios;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.util.JSFUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GeraRelatorio<T extends EntidadeDominio> {

	private JasperPrint carregarReport(List<T> lista) {

		JasperPrint jasperPrint = null;

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lista);
		String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("rep_clientes.jrxml");
		try {
			jasperPrint = JasperFillManager.fillReport(reportPath, null, beanCollectionDataSource);
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return jasperPrint;
	}

	public void PDF(List<T> lista) {

		JasperPrint  jasperPrint = carregarReport(lista);

		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
		try {
			ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao gerar PDF");
		} catch (JRException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao gerar PDF");
		}

	}


}
