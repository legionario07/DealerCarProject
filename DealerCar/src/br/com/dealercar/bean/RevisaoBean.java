package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.dealercar.dao.DevolucaoDAO;
import br.com.dealercar.dao.RevisaoDAO;
import br.com.dealercar.domain.Devolucao;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Revisao;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.itensrevisao.Arrefecimento;
import br.com.dealercar.domain.itensrevisao.Bateria;
import br.com.dealercar.domain.itensrevisao.Embreagem;
import br.com.dealercar.domain.itensrevisao.Freio;
import br.com.dealercar.domain.itensrevisao.Lanterna;
import br.com.dealercar.domain.itensrevisao.Motor;
import br.com.dealercar.domain.itensrevisao.Pneu;
import br.com.dealercar.domain.itensrevisao.Suspensao;
import br.com.dealercar.enums.PosicaoPneu;
import br.com.dealercar.strategy.valida.ValidaCarro;
import br.com.dealercar.util.DataUtil;
import br.com.dealercar.util.JSFUtil;
import br.com.dealercar.viewhelper.SessionHelper;

@ManagedBean(name = "MBRevisao")
@SessionScoped
public class RevisaoBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Revisao revisao = new Revisao();

	private Arrefecimento arrefecimento = new Arrefecimento();
	private Bateria bateria = new Bateria();
	private Embreagem embreagem = new Embreagem();
	private Freio freio = new Freio();
	private Lanterna lanterna = new Lanterna();
	private Motor motor = new Motor();
	private Suspensao suspensao = new Suspensao();
	
	private List<Pneu> pneus = new ArrayList<Pneu>();
	private Pneu dianteiroDireito   = new Pneu();
	private Pneu dianteiroEsquerdo  = new Pneu();
	private Pneu traseiroDireito    = new Pneu();
	private Pneu traseiroEsquerdo   = new Pneu();
	private Pneu estepe             = new Pneu();
	
	private List<Revisao> listaRevisao = new ArrayList<Revisao>();
	private List<Devolucao> listaDevolucaoAguardandoRevisao = new ArrayList<Devolucao>();
	
	int totalRevisaoNaFila;
	int totaoRevisaoRealizada;
	

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

	public Arrefecimento getArrefecimento() {
		return arrefecimento;
	}

	public void setArrefecimento(Arrefecimento arrefecimento) {
		this.arrefecimento = arrefecimento;
	}

	public Bateria getBateria() {
		return bateria;
	}

	public void setBateria(Bateria bateria) {
		this.bateria = bateria;
	}

	public Embreagem getEmbreagem() {
		return embreagem;
	}

	public void setEmbreagem(Embreagem embreagem) {
		this.embreagem = embreagem;
	}

	public Freio getFreio() {
		return freio;
	}

	public void setFreio(Freio freio) {
		this.freio = freio;
	}

	public Lanterna getLanterna() {
		return lanterna;
	}

	public void setLanterna(Lanterna lanterna) {
		this.lanterna = lanterna;
	}

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}


	public List<Pneu> getPneus() {
		return pneus;
	}

	public void setPneus(List<Pneu> pneus) {
		this.pneus = pneus;
	}

	public Pneu getDianteiroDireito() {
		return dianteiroDireito;
	}

	public void setDianteiroDireito(Pneu dianteiroDireito) {
		this.dianteiroDireito = dianteiroDireito;
	}

	public Pneu getDianteiroEsquerdo() {
		return dianteiroEsquerdo;
	}

	public void setDianteiroEsquerdo(Pneu dianteiroEsquerdo) {
		this.dianteiroEsquerdo = dianteiroEsquerdo;
	}

	public Pneu getTraseiroDireito() {
		return traseiroDireito;
	}

	public void setTraseiroDireito(Pneu traseiroDireito) {
		this.traseiroDireito = traseiroDireito;
	}

	public Pneu getTraseiroEsquerdo() {
		return traseiroEsquerdo;
	}

	public void setTraseiroEsquerdo(Pneu traseiroEsquerdo) {
		this.traseiroEsquerdo = traseiroEsquerdo;
	}

	public int getTotalRevisaoNaFila() {
		return totalRevisaoNaFila;
	}

	public void setTotalRevisaoNaFila(int totalRevisaoNaFila) {
		this.totalRevisaoNaFila = totalRevisaoNaFila;
	}

	public int getTotaoRevisaoRealizada() {
		return totaoRevisaoRealizada;
	}

	public void setTotaoRevisaoRealizada(int totaoRevisaoRealizada) {
		this.totaoRevisaoRealizada = totaoRevisaoRealizada;
	}

	public List<Devolucao> getListaDevolucaoAguardandoRevisao() {
		return listaDevolucaoAguardandoRevisao;
	}

	public void setListaDevolucaoAguardandoRevisao(List<Devolucao> listaDevolucaoAguardandoRevisao) {
		this.listaDevolucaoAguardandoRevisao = listaDevolucaoAguardandoRevisao;
	}

	public Pneu getEstepe() {
		return estepe;
	}

	public void setEstepe(Pneu estepe) {
		this.estepe = estepe;
	}

	public Suspensao getSuspensao() {
		return suspensao;
	}

	public void setSuspensao(Suspensao suspensao) {
		this.suspensao = suspensao;
	}


	@Override
	public void carregarListagem() {

		listaRevisao = new RevisaoDAO().listarTodos();
		listaDevolucaoAguardandoRevisao = new DevolucaoDAO().listarDevolucaoAguardandoRevisao();
		
		totalRevisaoNaFila = listaDevolucaoAguardandoRevisao.size();
		totaoRevisaoRealizada = listaRevisao.size();
		
		/*
		 * verifica se ja ja tem uma Devolução preenchida Se tiver significa que
		 * foi clicado para Revisar na View Devolucao.xhtml
		 */
		if (revisao.getDevolucao().getId() > 0) {
			revisao.setCarro(revisao.getDevolucao().getRetirada().getCarro());
			revisao.setQuilometragem(Long.valueOf(revisao.getDevolucao().getQuilometragem()));
			pesquisarPorPlaca();
		}

	}

	/**
	 * pesquisa no banco de Dados por um carro de acordo com a placa
	 */
	public void pesquisarPorPlaca() {

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
	public void efetuarRevisao() {

		// recebendo os componentes
		revisao.getComponentes().setArrefecimento(arrefecimento);
		revisao.getComponentes().setBateria(bateria);
		revisao.getComponentes().setEmbreagem(embreagem);
		revisao.getComponentes().setFreio(freio);
		revisao.getComponentes().setLanterna(lanterna);
		revisao.getComponentes().setMotor(motor);
		revisao.getComponentes().setSuspensao(suspensao);
		
		//recebendo os pneus
		dianteiroDireito.setPosicaoPneu(PosicaoPneu.DIANTEIRO_DIREITO);
		dianteiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
		traseiroDireito.setPosicaoPneu(PosicaoPneu.TRASEIRO_DIREITO);
		traseiroEsquerdo.setPosicaoPneu(PosicaoPneu.TRASEIRO_ESQUERDO);
		estepe.setPosicaoPneu(PosicaoPneu.ESTEPE);
		pneus.add(dianteiroDireito);
		pneus.add(dianteiroEsquerdo);
		pneus.add(traseiroDireito);
		pneus.add(traseiroEsquerdo);
		pneus.add(estepe);
		
		revisao.getComponentes().setPneus(pneus);
		
		// pega a data atual do sistema
		revisao.setDataRevisao(DataUtil.pegarDataAtualDoSistema());

		// pega o funcionario que realizou a Revisão
		revisao.setFuncionario((Funcionario) SessionHelper.getParam("usuarioLogado"));

		if(revisao.getDevolucao().getId()>0){
			revisao.getDevolucao().setAguardaRevisao(false);
			new DevolucaoDAO().editar(revisao.getDevolucao());
		}
		
		new RevisaoDAO().cadastrar(revisao);
		
		JSFUtil.adicionarMensagemSucesso("Revisão Efetuada com Sucesso");
		
		limparPesquisa();
		
	}

	/**
	 * limpa a tela de pesquisa de REvisao Deixando pronto para uma nova
	 * Pesquisa
	 */
	public void limparPesquisa() {
		
		revisao = new Revisao();
		
		arrefecimento = new Arrefecimento();
		bateria = new Bateria();
		embreagem = new Embreagem();
		freio = new Freio();
		motor = new Motor();
		suspensao = new Suspensao();
		dianteiroDireito = new Pneu();
		dianteiroEsquerdo = new Pneu();
		traseiroDireito   = new Pneu();
		traseiroEsquerdo  = new Pneu();
		estepe            = new Pneu();
		
		pneus.clear();
		
		setEhCadastrado(false);

	}

}
