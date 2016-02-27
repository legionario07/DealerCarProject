package br.com.dealercar.teste;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean(name = "MBPersona")
@ViewScoped
public class PersonaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Persona> personas = new ArrayList<Persona>();

	/**
	 * Metodo que preenche os dados antes de gerar o Relatório.
	 */
	public void preencherDados() {
		Persona p = new Persona();
		p.setNome("Felipe da Silva");
		p.setApelido("Felipe");
		Calendar cal = Calendar.getInstance();
		cal.set(1992, 3, 3);
		p.setDataNascimento(cal.getTime());

		personas.add(p);

		Persona p2 = new Persona();
		p2.setNome("Paulo Silva");
		p2.setApelido("Paulo");
		Calendar cal2 = Calendar.getInstance();
		cal.set(1992, 3, 2);
		p2.setDataNascimento(cal2.getTime());
		personas.add(p2);

	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
/*
	public void exportarPDF() {

		preencherDados(); // chamando método para preencher os dados

		Map<String, Object> parametros = new HashMap<String, Object>();
		Funcionario funcionario = (Funcionario) SessionHelper.getParam("usuarioLogado");
		parametros.put("txtUsuario", funcionario.getNome());

		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/persona2.jasper"));
		// InputStream reportStream =
		// FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/persona2.jasper");
		JasperPrint jasperPrint;
		try {
			jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros,
					new JRBeanCollectionDataSource(personas));
			HttpServletResponse reponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			reponse.setContentType("application/pdf");
			reponse.addHeader("Content-disposition", "attachment; filename=jsfReporte.pdf");
			ServletOutputStream stream = reponse.getOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			// JasperRunManager.runReportToPdfStream(reportStream,
			// stream,parametros);

			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void exportarPDF2() {

		// metodo que preenche os dados na lista
		preencherDados();

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(personas);
		FacesContext context = FacesContext.getCurrentInstance();
		String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/persona2.jasper");
		try {
			System.out.println(reportPath);
			System.out.println(beanCollectionDataSource.getData());
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, null, beanCollectionDataSource);
			// JasperPrint jasperPrint =
			// JasperFillManager.fillReport(reportPath,null,beanCollectionDataSource);

			HttpServletResponse httpServletResponse = (HttpServletResponse) context.getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
			// InputStream reportStream =
			// context.getExternalContext().getClass().getClassLoader().getResourceAsStream(reportPath);
			ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

			// JasperRunManager.runReportToPdfStream(reportStream,
			// servletOutputStream, new HashMap<String, Object>(),
			// beanCollectionDataSource);

			servletOutputStream.flush();
			servletOutputStream.close();

			context.responseComplete();

		} catch (JRException | IOException e) {
			e.printStackTrace();
		}

	}

	public void exportarPDF3() {

		preencherDados();

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(personas);
		FacesContext context = FacesContext.getCurrentInstance();
		String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/persona2.jasper");
		JasperPrint impressao = null;
		InputStream reportStream = context.getExternalContext().getClass().getClassLoader()
				.getResourceAsStream(reportPath);

		JasperReport jasper = null;
		byte[] bytes = null;
		try {
			jasper = JasperCompileManager.compileReport(reportStream);
			// Conversão do Formato Jasper para PDF. Aqui irá gerar o Arquivo
			// para o
			// usuário.
			impressao = JasperFillManager.fillReport(jasper, null, beanCollectionDataSource);
			bytes = JasperExportManager.exportReportToPdf(impressao);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext();
		response.setHeader("Content-Disposition", "attachment; filename=PRPG.pdf");
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void exportarPDF5(){
		
		preencherDados();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/persona2.jasper"));		
		
		JasperPrint jasperPrint;
		try {
			jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(personas));
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
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
