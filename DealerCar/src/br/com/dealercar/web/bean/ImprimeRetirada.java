package br.com.dealercar.web.bean;

import java.io.File;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;

import br.com.dealercar.core.relatorios.GeraRelatorio;
import br.com.dealercar.core.util.SessionUtil;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.conducao.Devolucao;
import br.com.dealercar.domain.conducao.Retirada;

@ManagedBean(name = "MBImprimirRetirada")
public class ImprimeRetirada {

	public void carregarListagem() {
		Retirada retirada = null;
		Devolucao devolucao = null;
		Cliente cliente = null;

		// recebe o caminho do relatorio
		File jasper = (File) SessionUtil.getParam("url");

		// verifica qual o objeto passado
		if (SessionUtil.getParam("objetoRelatorio") instanceof Retirada) {
			retirada = new Retirada();
			retirada = (Retirada) SessionUtil.getParam("objetoRelatorio");
			GeraRelatorio.exportarListPDF(new HashMap<String, Object>(), jasper, retirada);

		} else if (SessionUtil.getParam("objetoRelatorio") instanceof Devolucao) {
			devolucao = new Devolucao();
			devolucao = (Devolucao) SessionUtil.getParam("objetoRelatorio");
			GeraRelatorio.exportarListPDF(new HashMap<String, Object>(), jasper, devolucao);

		} else if (SessionUtil.getParam("objetoRelatorio") instanceof Cliente) {
			cliente = new Cliente();
			cliente = (Cliente) SessionUtil.getParam("objetoRelatorio");
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("cpf_cliente", cliente.getCPF());
			parametros.put("nome_cliente", cliente.getNome());
			GeraRelatorio.exportarListPDF(parametros, jasper);

		} else if (SessionUtil.getParam("objetoRelatorio") instanceof String) {

			GeraRelatorio.exportarListPDF(new HashMap<String, Object>(), jasper);
		}

		SessionUtil.remove("objetoRelatorio");

	}

}
