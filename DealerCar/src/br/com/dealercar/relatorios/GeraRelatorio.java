package br.com.dealercar.relatorios;

import br.com.dealercar.domain.EntidadeDominio;


public class GeraRelatorio<T extends EntidadeDominio> {

/*
	
	public void exportarPDF(List<T> lista){
		
		String caminhoAP = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
		String caminhoRelatorio = caminhoAP + File.separator + "WEB-INF/relatorios/clientes.jasper";

		List<Cliente> clientes = new ArrayList<Cliente>();
		
		Cliente cli = new Cliente();
		cli.setCelular("1241241234");
		cli.setNome("Teste");
		cli.setCPF("12341341324");
		cli.setNomeMae("Maria");
		
		clientes.add(cli);
		
		Cliente cli2 = new Cliente();
		cli.setCelular("a213241");
		cli.setNome("Teste2 ");
		cli.setCPF("12341341324");
		cli.setNomeMae("Maria2");
		
		clientes.add(cli2);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		Funcionario funcionario = (Funcionario) SessionHelper.getParam("usuarioLogado");
		parameters.put("txtUsuario", funcionario.getNome());
		
		File jasper = new File(caminhoRelatorio);
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, new JRBeanCollectionDataSource(clientes));
			
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Conten-disposition", "attachment; filename=rep_clientes.pdf");
			ServletOutputStream stream = response.getOutputStream();
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			
			stream.flush();
			stream.close();
			
			FacesContext.getCurrentInstance().responseComplete();
			
		} catch (JRException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	*/

}
