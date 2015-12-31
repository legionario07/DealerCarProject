package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.dealercar.dao.RevisaoDAO;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Revisao;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.strategy.valida.ValidaCarro;
import br.com.dealercar.util.DataUtil;
import br.com.dealercar.util.JSFUtil;
import br.com.dealercar.viewhelper.SessionHelper;

@ManagedBean(name = "MBRevisao")
@SessionScoped
public class RevisaoBean extends AbstractBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Revisao revisao = new Revisao();
	private List<Revisao> listaRevisao = new ArrayList<Revisao>();
	
	
	public Revisao getRevisao() {
		return revisao;
	}

	public void setRevisao(Revisao revisao) {
		this.revisao = revisao;
	}

	public List<Revisao> getListaRevisao() {
		return listaRevisao;
	}

	public void setListaRevisao(List<Revisao> listaRevisao) {
		this.listaRevisao = listaRevisao;
	}


	@Override
	public void carregarListagem() {

		listaRevisao = new RevisaoDAO().listarTodos();
		
		/*verifica se ja ja tem uma Devolução preenchida
		 * Se tiver significa que foi clicado para Revisar na View Devolucao.xhtml 
		 */
		if(revisao.getDevolucao().getId()>0){
			revisao.setCarro(revisao.getDevolucao().getRetirada().getCarro());
			revisao.setQuilometragem(Long.valueOf(revisao.getDevolucao().getQuilometragem()));
			pesquisarPorPlaca();
		}
		
	}
	
	/**
	 * pesquisa no banco de Dados por um carro de acordo com a placa
	 */
	public void pesquisarPorPlaca(){
		
		setEhCadastrado(false);

		// Validando o carro
		revisao.setCarro((Carro) new ValidaCarro().validar(revisao.getCarro()));

		// veficando se o carro foi encontrado
		if (revisao.getCarro() != null) {
			setEhCadastrado(true);
			return;
		}

		if (isEhCadastrado() == false) {
			revisao.setCarro(new Carro());
			JSFUtil.adicionarMensagemNaoLocalizado("Carro Não Localizado.");
			return;
		}
		
	}
	
	/**
	 * Efetua a revisao do carro pesquisado
	 */
	public void efetuarRevisao(){
		
		//pega a data atual do sistema 
		revisao.setDataRevisao(DataUtil.pegarDataAtualDoSistema());
		
		//pega o funcionario que realizou a Revisão
		revisao.setFuncionario((Funcionario) SessionHelper.getParam("usuarioLogado"));

		
	}
	
	/**
	 * limpa a tela de pesquisa de REvisao Deixando pronto para uma
	 * nova Pesquisa
	 */
	public void limparPesquisa() {
		revisao = new Revisao();
		setEhCadastrado(false);
	}

}
