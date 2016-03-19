package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.dealercar.dao.DevolucaoDAO;
import br.com.dealercar.dao.RevisaoDAO;
import br.com.dealercar.dao.itensrevisao.AmortecedorDAO;
import br.com.dealercar.dao.itensrevisao.CorreiaDentadaDAO;
import br.com.dealercar.dao.itensrevisao.EmbreagemDAO;
import br.com.dealercar.dao.itensrevisao.FarolDAO;
import br.com.dealercar.dao.itensrevisao.FiltroDeArDAO;
import br.com.dealercar.dao.itensrevisao.FiltroDeOleoMotorDAO;
import br.com.dealercar.dao.itensrevisao.FluidoDeFreioDAO;
import br.com.dealercar.dao.itensrevisao.PastilhaFreioDAO;
import br.com.dealercar.dao.itensrevisao.PneuDAO;
import br.com.dealercar.dao.itensrevisao.ProdutoRevisaoDAO;
import br.com.dealercar.dao.itensrevisao.VelasIgnicaoDAO;
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
import br.com.dealercar.domain.produtosrevisao.Amortecedor;
import br.com.dealercar.domain.produtosrevisao.CorreiaDentada;
import br.com.dealercar.domain.produtosrevisao.Farol;
import br.com.dealercar.domain.produtosrevisao.FiltroDeAr;
import br.com.dealercar.domain.produtosrevisao.FiltroDeOleoMotor;
import br.com.dealercar.domain.produtosrevisao.FluidoDeFreio;
import br.com.dealercar.domain.produtosrevisao.PastilhaFreio;
import br.com.dealercar.domain.produtosrevisao.ProdutoRevisao;
import br.com.dealercar.domain.produtosrevisao.VelasIgnicao;
import br.com.dealercar.enums.PosicaoPneu;
import br.com.dealercar.strategy.valida.ValidaCarro;
import br.com.dealercar.util.DataUtil;
import br.com.dealercar.util.JSFUtil;
import br.com.dealercar.util.SessionUtil;

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
	private Pneu dianteiroDireito = new Pneu();
	private Pneu dianteiroEsquerdo = new Pneu();
	private Pneu traseiroDireito = new Pneu();
	private Pneu traseiroEsquerdo = new Pneu();
	private Pneu estepe = new Pneu();

	private Amortecedor amortecedorProduto = new Amortecedor();
	private CorreiaDentada correiaDentadaProduto = new CorreiaDentada();
	private br.com.dealercar.domain.produtosrevisao.Embreagem embreagemProduto = new br.com.dealercar.domain.produtosrevisao.Embreagem();
	private Farol farolProduto = new Farol();
	private FiltroDeAr filtroDeArProduto = new FiltroDeAr();
	private FiltroDeOleoMotor filtroDeOleoMotorProduto = new FiltroDeOleoMotor();
	private FluidoDeFreio fluidoDeFreioProduto = new FluidoDeFreio();
	private PastilhaFreio pastilhaFreioProduto = new PastilhaFreio();
	private br.com.dealercar.domain.produtosrevisao.Pneu pneuProduto = new br.com.dealercar.domain.produtosrevisao.Pneu();
	private VelasIgnicao velasIgnicaoProduto = new VelasIgnicao();

	private ProdutoRevisao produtoRevisao = new ProdutoRevisao();

	private List<ProdutoRevisao> produtosUtilizados = new ArrayList<ProdutoRevisao>();
	private List<ProdutoRevisao> produtosCadastrados = new ArrayList<ProdutoRevisao>();

	private Map<String, Integer> quantidadeProduto = new HashMap<String, Integer>();

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

	public List<ProdutoRevisao> getProdutosUtilizados() {
		return produtosUtilizados;
	}

	public void setProdutosUtilizados(List<ProdutoRevisao> produtosUtilizados) {
		this.produtosUtilizados = produtosUtilizados;
	}

	public List<ProdutoRevisao> getProdutosCadastrados() {
		return produtosCadastrados;
	}

	public void setProdutosCadastrados(List<ProdutoRevisao> produtosCadastrados) {
		this.produtosCadastrados = produtosCadastrados;
	}

	public Amortecedor getAmortecedorProduto() {
		return amortecedorProduto;
	}

	public void setAmortecedorProduto(Amortecedor amortecedorProduto) {
		this.amortecedorProduto = amortecedorProduto;
	}

	public CorreiaDentada getCorreiaDentadaProduto() {
		return correiaDentadaProduto;
	}

	public void setCorreiaDentadaProduto(CorreiaDentada correiaDentadaProduto) {
		this.correiaDentadaProduto = correiaDentadaProduto;
	}

	public br.com.dealercar.domain.produtosrevisao.Embreagem getEmbreagemProduto() {
		return embreagemProduto;
	}

	public void setEmbreagemProduto(br.com.dealercar.domain.produtosrevisao.Embreagem embreagemProduto) {
		this.embreagemProduto = embreagemProduto;
	}

	public Farol getFarolProduto() {
		return farolProduto;
	}

	public void setFarolProduto(Farol farolProduto) {
		this.farolProduto = farolProduto;
	}

	public FiltroDeAr getFiltroDeArProduto() {
		return filtroDeArProduto;
	}

	public void setFiltroDeArProduto(FiltroDeAr filtroDeArProduto) {
		this.filtroDeArProduto = filtroDeArProduto;
	}

	public FiltroDeOleoMotor getFiltroDeOleoMotorProduto() {
		return filtroDeOleoMotorProduto;
	}

	public void setFiltroDeOleoMotorProduto(FiltroDeOleoMotor filtroDeOleoMotorProduto) {
		this.filtroDeOleoMotorProduto = filtroDeOleoMotorProduto;
	}

	public FluidoDeFreio getFluidoDeFreioProduto() {
		return fluidoDeFreioProduto;
	}

	public void setFluidoDeFreioProduto(FluidoDeFreio fluidoDeFreioProduto) {
		this.fluidoDeFreioProduto = fluidoDeFreioProduto;
	}

	public PastilhaFreio getPastilhaFreioProduto() {
		return pastilhaFreioProduto;
	}

	public void setPastilhaFreioProduto(PastilhaFreio pastilhaFreioProduto) {
		this.pastilhaFreioProduto = pastilhaFreioProduto;
	}

	public br.com.dealercar.domain.produtosrevisao.Pneu getPneuProduto() {
		return pneuProduto;
	}

	public void setPneuProduto(br.com.dealercar.domain.produtosrevisao.Pneu pneuProduto) {
		this.pneuProduto = pneuProduto;
	}

	public VelasIgnicao getVelasIgnicaoProduto() {
		return velasIgnicaoProduto;
	}

	public void setVelasIgnicaoProduto(VelasIgnicao velasIgnicaoProduto) {
		this.velasIgnicaoProduto = velasIgnicaoProduto;
	}

	public ProdutoRevisao getProdutoRevisao() {
		return produtoRevisao;
	}

	public void setProdutoRevisao(ProdutoRevisao produtoRevisao) {
		this.produtoRevisao = produtoRevisao;
	}

	public Map<String, Integer> getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(Map<String, Integer> quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
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

		// setando os hashMaps
		quantidadeProduto.put("amortecedor", 0);
		quantidadeProduto.put("correiaDentada", 0);
		quantidadeProduto.put("embreagem", 0);
		quantidadeProduto.put("farol", 0);
		quantidadeProduto.put("filtroDeAr", 0);
		quantidadeProduto.put("filtroDeOleoMotor", 0);
		quantidadeProduto.put("fluidoDeFreio", 0);
		quantidadeProduto.put("pastilhaFreio", 0);
		quantidadeProduto.put("pneu", 0);
		quantidadeProduto.put("velasIgnicao", 0);

		// se nao for vazio limpa para nao ocorrer duplicação
		if (!produtosCadastrados.isEmpty()) {
			produtosCadastrados.clear();
		}
		carregarProdutosCadastrados();

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
	 * Carrega a lista com todos os produtos cadastrados para exibicao no
	 * SelectOneMenu
	 * 
	 */
	private void carregarProdutosCadastrados() {

		for (ProdutoRevisao p : new AmortecedorDAO().listarTodos()) {
			if (p.getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}

		}
		for (ProdutoRevisao p : new CorreiaDentadaDAO().listarTodos()) {
			if (p.getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}
		for (ProdutoRevisao p : new EmbreagemDAO().listarTodos()) {
			if (p.getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}
		for (ProdutoRevisao p : new FarolDAO().listarTodos()) {
			if (p.getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}
		for (ProdutoRevisao p : new FiltroDeArDAO().listarTodos()) {
			if (p.getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}
		for (ProdutoRevisao p : new FiltroDeOleoMotorDAO().listarTodos()) {
			if (p.getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}
		for (ProdutoRevisao p : new FluidoDeFreioDAO().listarTodos()) {
			if (p.getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}
		for (ProdutoRevisao p : new PastilhaFreioDAO().listarTodos()) {
			if (p.getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}
		for (ProdutoRevisao p : new PneuDAO().listarTodos()) {
			if (p.getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}
		for (ProdutoRevisao p : new VelasIgnicaoDAO().listarTodos()) {
			if (p.getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
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

		// recebendo os pneus
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
		revisao.setFuncionario((Funcionario) SessionUtil.getParam("usuarioLogado"));

		if (revisao.getDevolucao().getId() > 0) {
			revisao.getDevolucao().setAguardaRevisao(false);
			new DevolucaoDAO().editar(revisao.getDevolucao());
		}

		//armazena a lista de produtos utilizados na Revisao
		new ProdutoRevisaoDAO().cadastrar(verificarTotalUtilizado());
		revisao.setListaProdutoRevisao(new ProdutoRevisaoDAO().pesquisarPorUltimoCadastrado());
		
		new RevisaoDAO().cadastrar(revisao);

		JSFUtil.adicionarMensagemSucesso("Revisão Efetuada com Sucesso");

		limparPesquisa();

	}

	/**
	 * Adiciona o produto utilizado na Revisão
	 * 
	 */
	public void addProduto() {

		if (produtoRevisao instanceof Amortecedor) {

			amortecedorProduto = (Amortecedor) produtoRevisao;

			produtoRevisao.setQuantidade(produtoRevisao.getQuantidade() - 1);

			quantidadeProduto.put("amortecedor", quantidadeProduto.get("amortecedor") + 1);
			amortecedorProduto.setQuantidade(quantidadeProduto.get("amortecedor"));

			new AmortecedorDAO().editar((Amortecedor) produtoRevisao);
			// adicionanto a table de produtos utilizados
			produtosUtilizados.add(amortecedorProduto);

		} else if (produtoRevisao instanceof CorreiaDentada) {

			correiaDentadaProduto = (CorreiaDentada) produtoRevisao;
			produtoRevisao.setQuantidade(produtoRevisao.getQuantidade() - 1);

			quantidadeProduto.put("correiaDentada", quantidadeProduto.get("correiaDentada") + 1);
			correiaDentadaProduto.setQuantidade(quantidadeProduto.get("correiaDentada"));

			new CorreiaDentadaDAO().editar((CorreiaDentada) produtoRevisao);
			produtosUtilizados.add(correiaDentadaProduto);

		} else if (produtoRevisao instanceof br.com.dealercar.domain.produtosrevisao.Embreagem) {

			embreagemProduto = (br.com.dealercar.domain.produtosrevisao.Embreagem) produtoRevisao;

			produtoRevisao.setQuantidade(produtoRevisao.getQuantidade() - 1);
			quantidadeProduto.put("embreagem", quantidadeProduto.get("embreagem") + 1);
			embreagemProduto.setQuantidade(quantidadeProduto.get("embreagem"));
			new EmbreagemDAO().editar((br.com.dealercar.domain.produtosrevisao.Embreagem) produtoRevisao);
			produtosUtilizados.add(embreagemProduto);

		} else if (produtoRevisao instanceof Farol) {

			farolProduto = (Farol) produtoRevisao;

			produtoRevisao.setQuantidade(produtoRevisao.getQuantidade() - 1);
			quantidadeProduto.put("farol", quantidadeProduto.get("farol") + 1);
			farolProduto.setQuantidade(quantidadeProduto.get("farol"));
			new FarolDAO().editar((Farol) produtoRevisao);
			produtosUtilizados.add(farolProduto);

		} else if (produtoRevisao instanceof FiltroDeAr) {

			filtroDeArProduto = (FiltroDeAr) produtoRevisao;

			produtoRevisao.setQuantidade(produtoRevisao.getQuantidade() - 1);
			quantidadeProduto.put("filtroDeAr", quantidadeProduto.get("filtroDeAr") + 1);
			filtroDeArProduto.setQuantidade(quantidadeProduto.get("filtroDeAr"));

			new FiltroDeArDAO().editar((FiltroDeAr) produtoRevisao);
			produtosUtilizados.add(filtroDeArProduto);

		} else if (produtoRevisao instanceof FiltroDeOleoMotor) {

			filtroDeOleoMotorProduto = (FiltroDeOleoMotor) produtoRevisao;

			produtoRevisao.setQuantidade(produtoRevisao.getQuantidade() - 1);
			quantidadeProduto.put("filtroDeOleoMotor", quantidadeProduto.get("filtroDeOleoMotor") + 1);
			filtroDeOleoMotorProduto.setQuantidade(quantidadeProduto.get("filtroDeOleoMotor"));

			new FiltroDeOleoMotorDAO().editar((FiltroDeOleoMotor) produtoRevisao);
			produtosUtilizados.add(filtroDeOleoMotorProduto);

		} else if (produtoRevisao instanceof FluidoDeFreio) {

			fluidoDeFreioProduto = (FluidoDeFreio) produtoRevisao;

			produtoRevisao.setQuantidade(produtoRevisao.getQuantidade() - 1);
			quantidadeProduto.put("fluidoDeFreio", quantidadeProduto.get("fluidoDeFreio") + 1);
			fluidoDeFreioProduto.setQuantidade(quantidadeProduto.get("fluidoDeFreio"));

			new FluidoDeFreioDAO().editar((FluidoDeFreio) produtoRevisao);
			produtosUtilizados.add(fluidoDeFreioProduto);

		} else if (produtoRevisao instanceof PastilhaFreio) {

			pastilhaFreioProduto = (PastilhaFreio) produtoRevisao;

			produtoRevisao.setQuantidade(produtoRevisao.getQuantidade() - 1);
			quantidadeProduto.put("pastilhaFreio", quantidadeProduto.get("pastilhaFreio") + 1);
			pastilhaFreioProduto.setQuantidade(quantidadeProduto.get("pastilhaFreio"));

			new PastilhaFreioDAO().editar((PastilhaFreio) produtoRevisao);
			produtosUtilizados.add(pastilhaFreioProduto);

		} else if (produtoRevisao instanceof br.com.dealercar.domain.produtosrevisao.Pneu) {

			pneuProduto = (br.com.dealercar.domain.produtosrevisao.Pneu) produtoRevisao;

			produtoRevisao.setQuantidade(produtoRevisao.getQuantidade() - 1);
			quantidadeProduto.put("pneu", quantidadeProduto.get("pneu") + 1);
			pneuProduto.setQuantidade(quantidadeProduto.get("pneu"));

			new PneuDAO().editar((br.com.dealercar.domain.produtosrevisao.Pneu) produtoRevisao);
			produtosUtilizados.add(pneuProduto);

		} else if (produtoRevisao instanceof VelasIgnicao) {

			velasIgnicaoProduto = (VelasIgnicao) produtoRevisao;

			produtoRevisao.setQuantidade(produtoRevisao.getQuantidade() - 1);
			quantidadeProduto.put("velasIgnicao", quantidadeProduto.get("velasIgnicao") + 1);
			velasIgnicaoProduto.setQuantidade(quantidadeProduto.get("velasIgnicao"));

			new VelasIgnicaoDAO().editar((VelasIgnicao) produtoRevisao);
			produtosUtilizados.add(velasIgnicaoProduto);

		}

		produtoRevisao = new ProdutoRevisao();

	}

	/**
	 * Contabiliza o total de produtos utilizados para salvar
	 */
	private List<ProdutoRevisao> verificarTotalUtilizado() {
		List<ProdutoRevisao> produtos = new ArrayList<ProdutoRevisao>();

		if (quantidadeProduto.get("amortecedor") > 0) {
			produtos.add(amortecedorProduto);
		}
		if (quantidadeProduto.get("correiaDentada") > 0) {
			produtos.add(correiaDentadaProduto);
		}
		if(quantidadeProduto.get("embreagem") >0 ){
			produtos.add(embreagemProduto);
		}
		if(quantidadeProduto.get("farol") >0 ){
			produtos.add(farolProduto);
		}
		if(quantidadeProduto.get("filtroDeAr") >0 ){
			produtos.add(filtroDeArProduto);
		}
		if(quantidadeProduto.get("filtroDeOleoMotor") >0 ){
			produtos.add(filtroDeOleoMotorProduto);
		}
		if(quantidadeProduto.get("fluidoDeFreio") >0 ){
			produtos.add(fluidoDeFreioProduto);
		}
		if(quantidadeProduto.get("pastilhaFreio") >0 ){
			produtos.add(pastilhaFreioProduto);
		}
		if(quantidadeProduto.get("pneu") >0 ){
			produtos.add(pneuProduto);
		}
		if(quantidadeProduto.get("velasIgnicao") >0 ){
			produtos.add(velasIgnicaoProduto);
		}
		
		return produtos;

	}

	/**
	 * limpa a tela de pesquisa de REvisao Deixando pronto para uma nova
	 * Pesquisa
	 */
	public void limparPesquisa() {

		revisao = new Revisao();
		revisao.setCarro(new Carro());

		arrefecimento = new Arrefecimento();
		bateria = new Bateria();
		embreagem = new Embreagem();
		freio = new Freio();
		motor = new Motor();
		suspensao = new Suspensao();
		dianteiroDireito = new Pneu();
		dianteiroEsquerdo = new Pneu();
		traseiroDireito = new Pneu();
		traseiroEsquerdo = new Pneu();
		estepe = new Pneu();

		
		//limpar Produtos
		
		amortecedorProduto = new Amortecedor();
		correiaDentadaProduto = new CorreiaDentada();
		embreagemProduto = new br.com.dealercar.domain.produtosrevisao.Embreagem();
		farolProduto = new Farol();
		filtroDeArProduto = new FiltroDeAr();
		filtroDeOleoMotorProduto = new FiltroDeOleoMotor();
		fluidoDeFreioProduto = new FluidoDeFreio();
		pastilhaFreioProduto = new PastilhaFreio();
		pneuProduto = new br.com.dealercar.domain.produtosrevisao.Pneu();
		velasIgnicaoProduto = new VelasIgnicao();
		
		pneus.clear();

		produtosCadastrados.clear();
		produtosUtilizados.clear();
		quantidadeProduto.clear();

		setEhCadastrado(false);
		setJaPesquisei(false);

	}

}
