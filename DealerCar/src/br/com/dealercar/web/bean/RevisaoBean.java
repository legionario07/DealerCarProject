package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.dao.itensrevisao.ProdutoRevisaoDAO;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.core.util.SessionUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.conducao.Devolucao;
import br.com.dealercar.domain.conducao.Revisao;
import br.com.dealercar.domain.enums.PosicaoPneu;
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
import br.com.dealercar.web.command.ICommand;

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

	private List<EntidadeDominio> produtosUtilizados = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> produtosCadastrados = new ArrayList<EntidadeDominio>();

	private Map<String, Integer> quantidadeProduto = new HashMap<String, Integer>();

	private List<EntidadeDominio> listaRevisao = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaDevolucaoAguardandoRevisao = new ArrayList<EntidadeDominio>();

	int totalRevisaoNaFila;
	int totaoRevisaoRealizada;

	public RevisaoBean() {
		// setando os hashMaps
		quantidadeProduto.put(Amortecedor.class.getSimpleName(), 0);
		quantidadeProduto.put(CorreiaDentada.class.getSimpleName(), 0);
		quantidadeProduto.put(br.com.dealercar.domain.produtosrevisao.Embreagem.class.getSimpleName(), 0);
		quantidadeProduto.put(Farol.class.getSimpleName(), 0);
		quantidadeProduto.put(FiltroDeAr.class.getSimpleName(), 0);
		quantidadeProduto.put(FiltroDeOleoMotor.class.getSimpleName(), 0);
		quantidadeProduto.put(FluidoDeFreio.class.getSimpleName(), 0);
		quantidadeProduto.put(PastilhaFreio.class.getSimpleName(), 0);
		quantidadeProduto.put(br.com.dealercar.domain.produtosrevisao.Pneu.class.getSimpleName(), 0);
		quantidadeProduto.put(VelasIgnicao.class.getSimpleName(), 0);
	}

	public Revisao getRevisao() {
		return revisao;
	}

	public void setRevisao(Revisao revisao) {
		this.revisao = revisao;
	}

	public List<EntidadeDominio> getListaRevisao() {
		return listaRevisao;
	}

	public void setListaRevisao(List<EntidadeDominio> listaRevisao) {
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

	public List<EntidadeDominio> getProdutosUtilizados() {
		return produtosUtilizados;
	}

	public void setProdutosUtilizados(List<EntidadeDominio> produtosUtilizados) {
		this.produtosUtilizados = produtosUtilizados;
	}

	public List<EntidadeDominio> getProdutosCadastrados() {
		return produtosCadastrados;
	}

	public void setProdutosCadastrados(List<EntidadeDominio> produtosCadastrados) {
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

	public List<EntidadeDominio> getListaDevolucaoAguardandoRevisao() {
		return listaDevolucaoAguardandoRevisao;
	}

	public void setListaDevolucaoAguardandoRevisao(List<EntidadeDominio> listaDevolucaoAguardandoRevisao) {
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

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapConducaoCommands.get("LISTAR");
		Resultado resultado = new Resultado();

		resultado = command.execute(new Revisao());
		if (resultado != null) {
			listaRevisao = resultado.getEntidades();
		}
		totaoRevisaoRealizada = listaRevisao.size();

		command = mapConducaoCommands.get("CONSULTAR");
		resultado = new Resultado();

		resultado = command.execute(new Devolucao());
		if (resultado != null) {
			listaDevolucaoAguardandoRevisao = resultado.getEntidades();
		}

		totalRevisaoNaFila = listaDevolucaoAguardandoRevisao.size();

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

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("LISTAR");
		Resultado resultado = new Resultado();

		resultado = command.execute(new Amortecedor());
		for (EntidadeDominio p : resultado.getEntidades()) {
			if (((ProdutoRevisao) p).getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}

		resultado = command.execute(new CorreiaDentada());
		for (EntidadeDominio p : resultado.getEntidades()) {
			if (((ProdutoRevisao) p).getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}

		resultado = command.execute(new br.com.dealercar.domain.produtosrevisao.Embreagem());
		for (EntidadeDominio p : resultado.getEntidades()) {
			if (((ProdutoRevisao) p).getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}

		resultado = command.execute(new Farol());
		for (EntidadeDominio p : resultado.getEntidades()) {
			if (((ProdutoRevisao) p).getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}

		resultado = command.execute(new FiltroDeAr());
		for (EntidadeDominio p : resultado.getEntidades()) {
			if (((ProdutoRevisao) p).getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}

		resultado = command.execute(new FiltroDeOleoMotor());
		for (EntidadeDominio p : resultado.getEntidades()) {
			if (((ProdutoRevisao) p).getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}

		resultado = command.execute(new FluidoDeFreio());
		for (EntidadeDominio p : resultado.getEntidades()) {
			if (((ProdutoRevisao) p).getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}

		resultado = command.execute(new PastilhaFreio());
		for (EntidadeDominio p : resultado.getEntidades()) {
			if (((ProdutoRevisao) p).getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}

		resultado = command.execute(new br.com.dealercar.domain.produtosrevisao.Pneu());
		for (EntidadeDominio p : resultado.getEntidades()) {
			if (((ProdutoRevisao) p).getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}

		resultado = command.execute(new VelasIgnicao());
		for (EntidadeDominio p : resultado.getEntidades()) {
			if (((ProdutoRevisao) p).getQuantidade() >= 1) {
				produtosCadastrados.add(p);
			}
		}
	}

	/**
	 * pesquisa no banco de Dados por um carro de acordo com a placa
	 */
	public void pesquisarPorPlaca() {

		setEhCadastrado(false);
		setJaPesquisei(true);

		// Retorna um estado completo de acordo com um ID
		ICommand command = mapCommands.get("CONSULTAR");
		Resultado resultado = new Resultado();
		resultado = command.execute(revisao.getCarro());

		// CArro foi encontrado
		if (resultado.getEntidades().get(0) != null) {
			revisao.setCarro((Carro) resultado.getEntidades().get(0));
			setEhCadastrado(true);
			setJaPesquisei(false);
			return;
		} else {
			revisao.setCarro(new Carro());
			return;
		}

	}

	/**
	 * Efetua a revisao do carro pesquisado
	 */
	@Override
	public void executar() {

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// Retorna um estado completo de acordo com um ID
		ICommand command = mapConducaoCommands.get(operacao);

		// recebendo os componentes
		revisao.getComponentes().add(arrefecimento);
		revisao.getComponentes().add(bateria);
		revisao.getComponentes().add(embreagem);
		revisao.getComponentes().add(freio);
		revisao.getComponentes().add(lanterna);
		revisao.getComponentes().add(motor);
		revisao.getComponentes().add(suspensao);

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

		//Adicionando cada pneu da lista Pneus na lista Componentes
		for(int j = 0; j < pneus.size();j++){
			revisao.getComponentes().add(pneus.get(j));
		}
		
		// pega a data atual do sistema
		revisao.setDataRevisao(DataUtil.pegarDataAtualDoSistema());

		// pega o funcionario que realizou a Revisão
		revisao.setFuncionario((Funcionario) SessionUtil.getParam("usuarioLogado"));

		if (revisao.getDevolucao().getId() > 0) {
			revisao.getDevolucao().setAguardaRevisao(false);
			command = mapConducaoCommands.get("EDITAR");
			command.execute(revisao.getDevolucao());
		}

		// armazena a lista de produtos utilizados na Revisao
		new ProdutoRevisaoDAO().cadastrar(verificarTotalUtilizado());
		revisao.setListaProdutoRevisao(new ProdutoRevisaoDAO().pesquisarPorUltimoCadastrado());

		command = mapConducaoCommands.get(operacao);
		command.execute(revisao);

		limparObjetos();

	}

	/**
	 * Adiciona o produto utilizado na Revisão
	 * 
	 */
	public void addProduto() {

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");
		ICommand command = mapCommands.get(operacao);

		if (revisao.getQuilometragem().equals("") || revisao.getQuilometragem() < 0) {
			org.primefaces.context.RequestContext.getCurrentInstance()
					.update(":frmNovaRevisaoTab:pnlNovaRevisao :msgGlobal");
		}

		produtoRevisao.setQuantidade(produtoRevisao.getQuantidade() - 1);
		quantidadeProduto.put(produtoRevisao.getClass().getSimpleName(),
				quantidadeProduto.get(produtoRevisao.getClass().getSimpleName()) + 1);

		if (produtoRevisao instanceof Amortecedor) {
			amortecedorProduto = (Amortecedor) produtoRevisao;

			command.execute((Amortecedor) produtoRevisao);
			amortecedorProduto.setQuantidade(quantidadeProduto.get(Amortecedor.class.getSimpleName()));
			// adicionanto a table de produtos utilizados
			produtosUtilizados.add(amortecedorProduto);

		} else if (produtoRevisao instanceof CorreiaDentada) {
			correiaDentadaProduto = (CorreiaDentada) produtoRevisao;

			command.execute((CorreiaDentada) produtoRevisao);
			correiaDentadaProduto.setQuantidade(quantidadeProduto.get(CorreiaDentada.class.getSimpleName()));
			produtosUtilizados.add(correiaDentadaProduto);

		} else if (produtoRevisao instanceof br.com.dealercar.domain.produtosrevisao.Embreagem) {
			embreagemProduto = (br.com.dealercar.domain.produtosrevisao.Embreagem) produtoRevisao;

			command.execute((br.com.dealercar.domain.produtosrevisao.Embreagem) produtoRevisao);
			embreagemProduto.setQuantidade(quantidadeProduto.get(Embreagem.class.getSimpleName()));
			produtosUtilizados.add(embreagemProduto);

		} else if (produtoRevisao instanceof Farol) {
			farolProduto = (Farol) produtoRevisao;

			command.execute((Farol) produtoRevisao);
			farolProduto.setQuantidade(quantidadeProduto.get(Farol.class.getSimpleName()));
			produtosUtilizados.add(farolProduto);

		} else if (produtoRevisao instanceof FiltroDeAr) {
			filtroDeArProduto = (FiltroDeAr) produtoRevisao;

			command.execute((FiltroDeAr) produtoRevisao);
			filtroDeArProduto.setQuantidade(quantidadeProduto.get(FiltroDeAr.class.getSimpleName()));
			produtosUtilizados.add(filtroDeArProduto);

		} else if (produtoRevisao instanceof FiltroDeOleoMotor) {
			filtroDeOleoMotorProduto = (FiltroDeOleoMotor) produtoRevisao;

			command.execute((FiltroDeOleoMotor) produtoRevisao);
			filtroDeOleoMotorProduto.setQuantidade(quantidadeProduto.get(FiltroDeOleoMotor.class.getSimpleName()));
			produtosUtilizados.add(filtroDeOleoMotorProduto);

		} else if (produtoRevisao instanceof FluidoDeFreio) {
			fluidoDeFreioProduto = (FluidoDeFreio) produtoRevisao;

			command.execute((FluidoDeFreio) produtoRevisao);
			fluidoDeFreioProduto.setQuantidade(quantidadeProduto.get(FluidoDeFreio.class.getSimpleName()));
			produtosUtilizados.add(fluidoDeFreioProduto);

		} else if (produtoRevisao instanceof PastilhaFreio) {
			pastilhaFreioProduto = (PastilhaFreio) produtoRevisao;

			command.execute((PastilhaFreio) produtoRevisao);
			pastilhaFreioProduto.setQuantidade(quantidadeProduto.get(PastilhaFreio.class.getSimpleName()));
			produtosUtilizados.add(pastilhaFreioProduto);

		} else if (produtoRevisao instanceof br.com.dealercar.domain.produtosrevisao.Pneu) {
			pneuProduto = (br.com.dealercar.domain.produtosrevisao.Pneu) produtoRevisao;

			command.execute((br.com.dealercar.domain.produtosrevisao.Pneu) produtoRevisao);
			pneuProduto.setQuantidade(
					quantidadeProduto.get(br.com.dealercar.domain.produtosrevisao.Pneu.class.getSimpleName()));
			produtosUtilizados.add(pneuProduto);

		} else if (produtoRevisao instanceof VelasIgnicao) {
			velasIgnicaoProduto = (VelasIgnicao) produtoRevisao;

			command.execute((VelasIgnicao) produtoRevisao);
			velasIgnicaoProduto.setQuantidade(quantidadeProduto.get(VelasIgnicao.class.getSimpleName()));
			produtosUtilizados.add(velasIgnicaoProduto);

		}

		produtoRevisao = new ProdutoRevisao();

	}

	/**
	 * Remove um Produto Selecionado
	 */
	public void removerProduto() {

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		ICommand command = mapCommands.get(operacao);

		produtoRevisao.setQuantidade(produtoRevisao.getQuantidade() + 1);
		quantidadeProduto.put(produtoRevisao.getClass().getSimpleName(),
				quantidadeProduto.get(produtoRevisao.getClass().getSimpleName()) - 1);

		if (produtoRevisao instanceof Amortecedor) {
			amortecedorProduto = (Amortecedor) produtoRevisao;

			command.execute((Amortecedor) produtoRevisao);
			amortecedorProduto.setQuantidade(quantidadeProduto.get(Amortecedor.class.getSimpleName()));

			// adicionanto a table de produtos utilizados
			produtosUtilizados.remove(amortecedorProduto);

		} else if (produtoRevisao instanceof CorreiaDentada) {
			correiaDentadaProduto = (CorreiaDentada) produtoRevisao;

			command.execute((CorreiaDentada) produtoRevisao);
			correiaDentadaProduto.setQuantidade(quantidadeProduto.get(CorreiaDentada.class.getSimpleName()));
			produtosUtilizados.remove(correiaDentadaProduto);

		} else if (produtoRevisao instanceof br.com.dealercar.domain.produtosrevisao.Embreagem) {
			embreagemProduto = (br.com.dealercar.domain.produtosrevisao.Embreagem) produtoRevisao;

			command.execute((br.com.dealercar.domain.produtosrevisao.Embreagem) produtoRevisao);
			embreagemProduto.setQuantidade(quantidadeProduto.get(Embreagem.class.getSimpleName()));
			produtosUtilizados.remove(embreagemProduto);

		} else if (produtoRevisao instanceof Farol) {
			farolProduto = (Farol) produtoRevisao;

			command.execute((Farol) produtoRevisao);
			farolProduto.setQuantidade(quantidadeProduto.get(Farol.class.getSimpleName()));
			produtosUtilizados.remove(farolProduto);

		} else if (produtoRevisao instanceof FiltroDeAr) {
			filtroDeArProduto = (FiltroDeAr) produtoRevisao;

			command.execute((FiltroDeAr) produtoRevisao);
			filtroDeArProduto.setQuantidade(quantidadeProduto.get(FiltroDeAr.class.getSimpleName()));
			produtosUtilizados.remove(filtroDeArProduto);

		} else if (produtoRevisao instanceof FiltroDeOleoMotor) {
			filtroDeOleoMotorProduto = (FiltroDeOleoMotor) produtoRevisao;

			command.execute((FiltroDeOleoMotor) produtoRevisao);
			filtroDeOleoMotorProduto.setQuantidade(quantidadeProduto.get(FiltroDeOleoMotor.class.getSimpleName()));
			produtosUtilizados.remove(filtroDeOleoMotorProduto);

		} else if (produtoRevisao instanceof FluidoDeFreio) {
			fluidoDeFreioProduto = (FluidoDeFreio) produtoRevisao;

			command.execute((FluidoDeFreio) produtoRevisao);
			fluidoDeFreioProduto.setQuantidade(quantidadeProduto.get(FluidoDeFreio.class.getSimpleName()));
			produtosUtilizados.remove(fluidoDeFreioProduto);

		} else if (produtoRevisao instanceof PastilhaFreio) {
			pastilhaFreioProduto = (PastilhaFreio) produtoRevisao;

			command.execute((PastilhaFreio) produtoRevisao);
			pastilhaFreioProduto.setQuantidade(quantidadeProduto.get(PastilhaFreio.class.getSimpleName()));
			produtosUtilizados.remove(pastilhaFreioProduto);

		} else if (produtoRevisao instanceof br.com.dealercar.domain.produtosrevisao.Pneu) {
			pneuProduto = (br.com.dealercar.domain.produtosrevisao.Pneu) produtoRevisao;

			command.execute((br.com.dealercar.domain.produtosrevisao.Pneu) produtoRevisao);
			pneuProduto.setQuantidade(
					quantidadeProduto.get(br.com.dealercar.domain.produtosrevisao.Pneu.class.getSimpleName()));
			produtosUtilizados.remove(pneuProduto);

		} else if (produtoRevisao instanceof VelasIgnicao) {
			velasIgnicaoProduto = (VelasIgnicao) produtoRevisao;

			command.execute((VelasIgnicao) produtoRevisao);
			velasIgnicaoProduto.setQuantidade(quantidadeProduto.get(VelasIgnicao.class.getSimpleName()));
			produtosUtilizados.remove(velasIgnicaoProduto);

		}

		produtoRevisao = new ProdutoRevisao();

	}

	/**
	 * Contabiliza o total de produtos utilizados para salvar
	 */
	private List<ProdutoRevisao> verificarTotalUtilizado() {
		List<ProdutoRevisao> produtos = new ArrayList<ProdutoRevisao>();

		if (quantidadeProduto.get(Amortecedor.class.getSimpleName()) > 0) {
			produtos.add(amortecedorProduto);
		}
		if (quantidadeProduto.get(CorreiaDentada.class.getSimpleName()) > 0) {
			produtos.add(correiaDentadaProduto);
		}
		if (quantidadeProduto.get(br.com.dealercar.domain.produtosrevisao.Embreagem.class.getSimpleName()) > 0) {
			produtos.add(embreagemProduto);
		}
		if (quantidadeProduto.get(Farol.class.getSimpleName()) > 0) {
			produtos.add(farolProduto);
		}
		if (quantidadeProduto.get(FiltroDeAr.class.getSimpleName()) > 0) {
			produtos.add(filtroDeArProduto);
		}
		if (quantidadeProduto.get(FiltroDeOleoMotor.class.getSimpleName()) > 0) {
			produtos.add(filtroDeOleoMotorProduto);
		}
		if (quantidadeProduto.get(FluidoDeFreio.class.getSimpleName()) > 0) {
			produtos.add(fluidoDeFreioProduto);
		}
		if (quantidadeProduto.get(PastilhaFreio.class.getSimpleName()) > 0) {
			produtos.add(pastilhaFreioProduto);
		}
		if (quantidadeProduto.get(Pneu.class.getSimpleName()) > 0) {
			produtos.add(pneuProduto);
		}
		if (quantidadeProduto.get(VelasIgnicao.class.getSimpleName()) > 0) {
			produtos.add(velasIgnicaoProduto);
		}

		return produtos;

	}

	/**
	 * limpa a tela de pesquisa de REvisao Deixando pronto para uma nova
	 * Pesquisa
	 */
	public void limparObjetos() {

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

		// limpar Produtos

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
