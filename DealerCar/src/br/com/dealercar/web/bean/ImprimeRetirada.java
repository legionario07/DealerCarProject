package br.com.dealercar.web.bean;

import java.io.File;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;

import br.com.dealercar.core.negocio.Devolucao;
import br.com.dealercar.core.negocio.Retirada;
import br.com.dealercar.core.relatorios.GeraRelatorio;
import br.com.dealercar.core.util.SessionUtil;

@ManagedBean(name = "MBImprimirRetirada")
public class ImprimeRetirada {

	public void carregarListagem() {
		Retirada retirada = null;
		Devolucao devolucao = null;
		
		//recebe o caminho do relatorio
		File jasper = (File) SessionUtil.getParam("url");
		
		//verifica qual o objeto passado
		if (SessionUtil.getParam("objetoRelatorio") instanceof Retirada) {
			retirada = new Retirada();
			retirada = (Retirada) SessionUtil.getParam("objetoRelatorio");
			GeraRelatorio.exportarListPDF(new HashMap<String, Object>(), jasper, retirada);
			
		} else if (SessionUtil.getParam("objetoRelatorio") instanceof Devolucao) {
			devolucao = new Devolucao();
			devolucao = (Devolucao) SessionUtil.getParam("objetoRelatorio");
			GeraRelatorio.exportarListPDF(new HashMap<String, Object>(), jasper, devolucao);

		} else if (SessionUtil.getParam("objetoRelatorio") instanceof String){
			
			GeraRelatorio.exportarListPDF(new HashMap<String, Object>(), jasper);
		}
		
		SessionUtil.remove("objetoRelatorio");


	}

}
