package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.dealercar.dao.itensrevisao.AmortecedorDAO;
import br.com.dealercar.dao.itensrevisao.CorreiaDentadaDAO;
import br.com.dealercar.dao.itensrevisao.EmbreagemDAO;
import br.com.dealercar.dao.itensrevisao.FarolDAO;
import br.com.dealercar.dao.itensrevisao.FiltroDeArDAO;
import br.com.dealercar.dao.itensrevisao.FiltroDeOleoMotorDAO;
import br.com.dealercar.dao.itensrevisao.FluidoDeFreioDAO;
import br.com.dealercar.dao.itensrevisao.FormaDeVendaDAO;
import br.com.dealercar.dao.itensrevisao.PastilhaFreioDAO;
import br.com.dealercar.dao.itensrevisao.PneuDAO;
import br.com.dealercar.dao.itensrevisao.VelasIgnicaoDAO;
import br.com.dealercar.domain.produtosrevisao.Amortecedor;
import br.com.dealercar.domain.produtosrevisao.CorreiaDentada;
import br.com.dealercar.domain.produtosrevisao.Embreagem;
import br.com.dealercar.domain.produtosrevisao.Farol;
import br.com.dealercar.domain.produtosrevisao.FiltroDeAr;
import br.com.dealercar.domain.produtosrevisao.FiltroDeOleoMotor;
import br.com.dealercar.domain.produtosrevisao.FluidoDeFreio;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;
import br.com.dealercar.domain.produtosrevisao.PastilhaFreio;
import br.com.dealercar.domain.produtosrevisao.Pneu;
import br.com.dealercar.domain.produtosrevisao.ProdutoRevisao;
import br.com.dealercar.domain.produtosrevisao.VelasIgnicao;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBProdutoRevisao")
@ViewScoped
public class ProdutoRevisaoBean implements IBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Amortecedor amortecedor = new Amortecedor();
	private CorreiaDentada correiaDentada = new CorreiaDentada();
	private Embreagem embreagem = new Embreagem();
	private Farol farol = new Farol();
	private FiltroDeAr filtroDeAr = new FiltroDeAr();
	private FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
	private FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
	private FormaDeVenda formaDeVenda = new FormaDeVenda();
	private PastilhaFreio pastilhaFreio = new PastilhaFreio();
	private VelasIgnicao velasIgnicao = new VelasIgnicao();
	private Pneu pneu = new Pneu();

	private ProdutoRevisao produtoRevisao = new ProdutoRevisao();

	private List<Amortecedor> listaAmortecedores = new ArrayList<Amortecedor>();
	private List<CorreiaDentada> listaCorreiaDentada = new ArrayList<CorreiaDentada>();
	private List<Embreagem> listaEmbreagem = new ArrayList<Embreagem>();
	private List<Farol> listaFarol = new ArrayList<Farol>();
	private List<FiltroDeAr> listaFiltroDeAr = new ArrayList<FiltroDeAr>();
	private List<FiltroDeOleoMotor> listaFiltroDeOleoMotor = new ArrayList<FiltroDeOleoMotor>();
	private List<FluidoDeFreio> listaFluidoDeFreio = new ArrayList<FluidoDeFreio>();
	private List<FormaDeVenda> listaFormaDeVenda = new ArrayList<FormaDeVenda>();
	private List<PastilhaFreio> listaPastilhaFreio = new ArrayList<PastilhaFreio>();
	private List<VelasIgnicao> listaVelasIgnicao = new ArrayList<VelasIgnicao>();
	private List<Pneu> listaPneu = new ArrayList<Pneu>();

	private int indice;

	public Amortecedor getAmortecedor() {
		return amortecedor;
	}

	public void setAmortecedor(Amortecedor amortecedor) {
		this.amortecedor = amortecedor;
	}

	public CorreiaDentada getCorreiaDentada() {
		return correiaDentada;
	}

	public void setCorreiaDentada(CorreiaDentada correiaDentada) {
		this.correiaDentada = correiaDentada;
	}

	public Farol getFarol() {
		return farol;
	}

	public void setFarol(Farol farol) {
		this.farol = farol;
	}

	public Embreagem getEmbreagem() {
		return embreagem;
	}

	public void setEmbreagem(Embreagem embreagem) {
		this.embreagem = embreagem;
	}

	public List<Embreagem> getListaEmbreagem() {
		return listaEmbreagem;
	}

	public void setListaEmbreagem(List<Embreagem> listaEmbreagem) {
		this.listaEmbreagem = listaEmbreagem;
	}

	public FiltroDeAr getFiltroDeAr() {
		return filtroDeAr;
	}

	public void setFiltroDeAr(FiltroDeAr filtroDeAr) {
		this.filtroDeAr = filtroDeAr;
	}

	public FiltroDeOleoMotor getFiltroDeOleoMotor() {
		return filtroDeOleoMotor;
	}

	public void setFiltroDeOleoMotor(FiltroDeOleoMotor filtroDeOleoMotor) {
		this.filtroDeOleoMotor = filtroDeOleoMotor;
	}

	public FluidoDeFreio getFluidoDeFreio() {
		return fluidoDeFreio;
	}

	public void setFluidoDeFreio(FluidoDeFreio fluidoDeFreio) {
		this.fluidoDeFreio = fluidoDeFreio;
	}

	public FormaDeVenda getFormaDeVenda() {
		return formaDeVenda;
	}

	public void setFormaDeVenda(FormaDeVenda formaDeVenda) {
		this.formaDeVenda = formaDeVenda;
	}

	public PastilhaFreio getPastilhaFreio() {
		return pastilhaFreio;
	}

	public void setPastilhaFreio(PastilhaFreio pastilhaFreio) {
		this.pastilhaFreio = pastilhaFreio;
	}

	public Pneu getPneu() {
		return pneu;
	}

	public void setPneu(Pneu pneu) {
		this.pneu = pneu;
	}

	public List<Pneu> getListaPneu() {
		return listaPneu;
	}

	public void setListaPneu(List<Pneu> listaPneu) {
		this.listaPneu = listaPneu;
	}

	public VelasIgnicao getVelasIgnicao() {
		return velasIgnicao;
	}

	public void setVelasIgnicao(VelasIgnicao velasIgnicao) {
		this.velasIgnicao = velasIgnicao;
	}

	public ProdutoRevisao getProdutoRevisao() {
		return produtoRevisao;
	}

	public void setProdutoRevisao(ProdutoRevisao produtoRevisao) {
		this.produtoRevisao = produtoRevisao;
	}

	public List<Amortecedor> getListaAmortecedores() {
		return listaAmortecedores;
	}

	public void setListaAmortecedores(List<Amortecedor> listaAmortecedores) {
		this.listaAmortecedores = listaAmortecedores;
	}

	public List<CorreiaDentada> getListaCorreiaDentada() {
		return listaCorreiaDentada;
	}

	public void setListaCorreiaDentada(List<CorreiaDentada> listaCorreiaDentada) {
		this.listaCorreiaDentada = listaCorreiaDentada;
	}

	public List<Farol> getListaFarol() {
		return listaFarol;
	}

	public void setListaFarol(List<Farol> listaFarol) {
		this.listaFarol = listaFarol;
	}

	public List<FiltroDeAr> getListaFiltroDeAr() {
		return listaFiltroDeAr;
	}

	public void setListaFiltroDeAr(List<FiltroDeAr> listaFiltroDeAr) {
		this.listaFiltroDeAr = listaFiltroDeAr;
	}

	public List<FiltroDeOleoMotor> getListaFiltroDeOleoMotor() {
		return listaFiltroDeOleoMotor;
	}

	public void setListaFiltroDeOleoMotor(List<FiltroDeOleoMotor> listaFiltroDeOleoMotor) {
		this.listaFiltroDeOleoMotor = listaFiltroDeOleoMotor;
	}

	public List<FluidoDeFreio> getListaFluidoDeFreio() {
		return listaFluidoDeFreio;
	}

	public void setListaFluidoDeFreio(List<FluidoDeFreio> listaFluidoDeFreio) {
		this.listaFluidoDeFreio = listaFluidoDeFreio;
	}

	public List<FormaDeVenda> getListaFormaDeVenda() {
		return listaFormaDeVenda;
	}

	public void setListaFormaDeVenda(List<FormaDeVenda> listaFormaDeVenda) {
		this.listaFormaDeVenda = listaFormaDeVenda;
	}

	public List<PastilhaFreio> getListaPastilhaFreio() {
		return listaPastilhaFreio;
	}

	public void setListaPastilhaFreio(List<PastilhaFreio> listaPastilhaFreio) {
		this.listaPastilhaFreio = listaPastilhaFreio;
	}

	public List<VelasIgnicao> getListaVelasIgnicao() {
		return listaVelasIgnicao;
	}

	public void setListaVelasIgnicao(List<VelasIgnicao> listaVelasIgnicao) {
		this.listaVelasIgnicao = listaVelasIgnicao;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	@Override
	public void carregarListagem() {

		listaAmortecedores = new AmortecedorDAO().listarTodos();
		listaCorreiaDentada = new CorreiaDentadaDAO().listarTodos();
		listaEmbreagem = new EmbreagemDAO().listarTodos();
		listaFarol = new FarolDAO().listarTodos();
		listaFiltroDeAr = new FiltroDeArDAO().listarTodos();
		listaFiltroDeOleoMotor = new FiltroDeOleoMotorDAO().listarTodos();
		listaFluidoDeFreio = new FluidoDeFreioDAO().listarTodos();
		listaFormaDeVenda = new FormaDeVendaDAO().listarTodos();
		listaPastilhaFreio = new PastilhaFreioDAO().listarTodos();
		listaVelasIgnicao = new VelasIgnicaoDAO().listarTodos();
		listaPneu = new PneuDAO().listarTodos();

	}

	/**
	 * Prepara para Cadastrar um novo Opcional
	 * verifica na View qual o Opcional Clicado e seta um indice
	 */
	public void prepararCadastrar(ActionEvent event) {

		indice = 0;

		if (event.getComponent().getId().equals("botaoAmortecedor")) {
			indice = 2;
		} else if (event.getComponent().getId().equals("botaoCorreiaDentada")) {
			indice = 3;
		} else if (event.getComponent().getId().equals("botaoFarol")) {
			indice = 4;
		} else if (event.getComponent().getId().equals("botaoFiltroDeAr")) {
			indice = 5;
		} else if (event.getComponent().getId().equals("botaoFiltroDeOleoMotor")) {
			indice = 6;
		} else if (event.getComponent().getId().equals("botaoFluidoDeFreio")) {
			indice = 7;
		} else if (event.getComponent().getId().equals("botaoFormaDeVenda")) {
			indice = 8;
		} else if (event.getComponent().getId().equals("botaoPastilhaFreio")) {
			indice = 9;
		} else if (event.getComponent().getId().equals("botaoVelasIgnicao")) {
			indice = 10;
		} else if (event.getComponent().getId().equals("botaoEmbreagem")) {
			indice = 11;
		} else if (event.getComponent().getId().equals("botaoPneu")){
			indice = 12;
		}

	}

	/**
	 * Cadastra um novo Opcional de Acordo com o Indice
	 */
	public void cadastrar(ActionEvent event) {

		if (indice == 2) {
			amortecedor.setId(produtoRevisao.getId());
			amortecedor.setDescricao(produtoRevisao.getDescricao());
			amortecedor.setMarca(produtoRevisao.getMarca());
			amortecedor.setTipo(produtoRevisao.getTipo());
			amortecedor.setQuantidade(produtoRevisao.getQuantidade());
			amortecedor.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			amortecedor.setValor(produtoRevisao.getValor());

			new AmortecedorDAO().cadastrar(amortecedor);

			amortecedor = new Amortecedor();

		} else if (indice == 3) {
			correiaDentada.setId(produtoRevisao.getId());
			correiaDentada.setDescricao(produtoRevisao.getDescricao());
			correiaDentada.setMarca(produtoRevisao.getMarca());
			correiaDentada.setTipo(produtoRevisao.getTipo());
			correiaDentada.setQuantidade(produtoRevisao.getQuantidade());
			correiaDentada.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			correiaDentada.setValor(produtoRevisao.getValor());

			new CorreiaDentadaDAO().cadastrar(correiaDentada);

			correiaDentada = new CorreiaDentada();

		} else if (indice == 4) {
			farol.setId(produtoRevisao.getId());
			farol.setDescricao(produtoRevisao.getDescricao());
			farol.setMarca(produtoRevisao.getMarca());
			farol.setTipo(produtoRevisao.getTipo());
			farol.setQuantidade(produtoRevisao.getQuantidade());
			farol.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			farol.setValor(produtoRevisao.getValor());

			new FarolDAO().cadastrar(farol);

			farol = new Farol();

		} else if (indice == 5) {
			filtroDeAr.setId(produtoRevisao.getId());
			filtroDeAr.setDescricao(produtoRevisao.getDescricao());
			filtroDeAr.setMarca(produtoRevisao.getMarca());
			filtroDeAr.setTipo(produtoRevisao.getTipo());
			filtroDeAr.setQuantidade(produtoRevisao.getQuantidade());
			filtroDeAr.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			filtroDeAr.setValor(produtoRevisao.getValor());

			new FiltroDeArDAO().cadastrar(filtroDeAr);

			filtroDeAr = new FiltroDeAr();

		} else if (indice == 6) {
			filtroDeOleoMotor.setId(produtoRevisao.getId());
			filtroDeOleoMotor.setDescricao(produtoRevisao.getDescricao());
			filtroDeOleoMotor.setMarca(produtoRevisao.getMarca());
			filtroDeOleoMotor.setTipo(produtoRevisao.getTipo());
			filtroDeOleoMotor.setQuantidade(produtoRevisao.getQuantidade());
			filtroDeOleoMotor.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			filtroDeOleoMotor.setValor(produtoRevisao.getValor());

			new FiltroDeOleoMotorDAO().cadastrar(filtroDeOleoMotor);

			filtroDeOleoMotor = new FiltroDeOleoMotor();

		} else if (indice == 7) {
			fluidoDeFreio.setId(produtoRevisao.getId());
			fluidoDeFreio.setDescricao(produtoRevisao.getDescricao());
			fluidoDeFreio.setMarca(produtoRevisao.getMarca());
			fluidoDeFreio.setTipo(produtoRevisao.getTipo());
			fluidoDeFreio.setQuantidade(produtoRevisao.getQuantidade());
			fluidoDeFreio.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			fluidoDeFreio.setValor(produtoRevisao.getValor());

			new FluidoDeFreioDAO().cadastrar(fluidoDeFreio);

			fluidoDeFreio = new FluidoDeFreio();

		} else if (indice == 8) {

			new FormaDeVendaDAO().cadastrar(formaDeVenda);
			formaDeVenda = new FormaDeVenda();

		} else if (indice == 9) {
			pastilhaFreio.setId(produtoRevisao.getId());
			pastilhaFreio.setDescricao(produtoRevisao.getDescricao());
			pastilhaFreio.setMarca(produtoRevisao.getMarca());
			pastilhaFreio.setTipo(produtoRevisao.getTipo());
			pastilhaFreio.setQuantidade(produtoRevisao.getQuantidade());
			pastilhaFreio.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			pastilhaFreio.setValor(produtoRevisao.getValor());

			new PastilhaFreioDAO().cadastrar(pastilhaFreio);

			pastilhaFreio = new PastilhaFreio();

		} else if (indice == 10) {
			velasIgnicao.setId(produtoRevisao.getId());
			velasIgnicao.setDescricao(produtoRevisao.getDescricao());
			velasIgnicao.setMarca(produtoRevisao.getMarca());
			velasIgnicao.setTipo(produtoRevisao.getTipo());
			velasIgnicao.setQuantidade(produtoRevisao.getQuantidade());
			velasIgnicao.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			velasIgnicao.setValor(produtoRevisao.getValor());

			new VelasIgnicaoDAO().cadastrar(velasIgnicao);

			velasIgnicao = new VelasIgnicao();

		} else if (indice == 11) {
			embreagem.setId(produtoRevisao.getId());
			embreagem.setDescricao(produtoRevisao.getDescricao());
			embreagem.setMarca(produtoRevisao.getMarca());
			embreagem.setTipo(produtoRevisao.getTipo());
			embreagem.setQuantidade(produtoRevisao.getQuantidade());
			embreagem.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			embreagem.setValor(produtoRevisao.getValor());

			new EmbreagemDAO().cadastrar(embreagem);

			embreagem = new Embreagem();

		} else if (indice == 12) {
			pneu.setId(produtoRevisao.getId());
			pneu.setDescricao(produtoRevisao.getDescricao());
			pneu.setMarca(produtoRevisao.getMarca());
			pneu.setTipo(produtoRevisao.getTipo());
			pneu.setQuantidade(produtoRevisao.getQuantidade());
			pneu.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			pneu.setValor(produtoRevisao.getValor());

			new PneuDAO().cadastrar(pneu);

			pneu = new Pneu();
			
		}

		indice = 0;
		produtoRevisao = new ProdutoRevisao();
		JSFUtil.adicionarMensagemSucesso("Produto cadastrado com sucesso!");

	}

	/**
	 * Edita Produto clicado na View
	 */
	public void editar() {

		if (produtoRevisao instanceof Amortecedor) {
			amortecedor = (Amortecedor) produtoRevisao;
			new AmortecedorDAO().editar(amortecedor);

			amortecedor = new Amortecedor();

		} else if (produtoRevisao instanceof CorreiaDentada) {
			correiaDentada = (CorreiaDentada) produtoRevisao;
			new CorreiaDentadaDAO().editar(correiaDentada);

			correiaDentada = new CorreiaDentada();

		} else if (produtoRevisao instanceof Farol) {
			farol = (Farol) produtoRevisao;
			new FarolDAO().editar(farol);

			farol = new Farol();

		} else if (produtoRevisao instanceof FiltroDeAr) {
			filtroDeAr = (FiltroDeAr) produtoRevisao;
			new FiltroDeArDAO().editar(filtroDeAr);

			filtroDeAr = new FiltroDeAr();

		} else if (produtoRevisao instanceof FiltroDeOleoMotor) {
			filtroDeOleoMotor = (FiltroDeOleoMotor) produtoRevisao;
			new FiltroDeOleoMotorDAO().editar(filtroDeOleoMotor);

			filtroDeOleoMotor = new FiltroDeOleoMotor();

		} else if (produtoRevisao instanceof FluidoDeFreio) {
			fluidoDeFreio = (FluidoDeFreio) produtoRevisao;
			new FluidoDeFreioDAO().editar(fluidoDeFreio);

			fluidoDeFreio = new FluidoDeFreio();

		} else if (produtoRevisao instanceof PastilhaFreio) {
			pastilhaFreio = (PastilhaFreio) produtoRevisao;
			new PastilhaFreioDAO().editar(pastilhaFreio);

			pastilhaFreio = new PastilhaFreio();

		} else if (produtoRevisao instanceof VelasIgnicao) {
			velasIgnicao = (VelasIgnicao) produtoRevisao;
			new VelasIgnicaoDAO().editar(velasIgnicao);

			velasIgnicao = new VelasIgnicao();

		} else if (produtoRevisao instanceof Embreagem) {
			embreagem = (Embreagem) produtoRevisao;
			new EmbreagemDAO().editar(embreagem);

			embreagem = new Embreagem();
			
		} else if (produtoRevisao instanceof Pneu) {
			pneu = (Pneu) produtoRevisao;
			new PneuDAO().editar(pneu);

			pneu = new Pneu();

		} else {
			new FormaDeVendaDAO().editar(formaDeVenda);

			formaDeVenda = new FormaDeVenda();
		}

		indice = 0;
		produtoRevisao = new ProdutoRevisao();
		JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso");
	}

	/**
	 * Exclui Produto clicado na View
	 */
	public void excluir() {

		if (produtoRevisao instanceof Amortecedor) {
			amortecedor = (Amortecedor) produtoRevisao;
			new AmortecedorDAO().excluir(amortecedor);

			amortecedor = new Amortecedor();

		} else if (produtoRevisao instanceof CorreiaDentada) {
			correiaDentada = (CorreiaDentada) produtoRevisao;
			new CorreiaDentadaDAO().excluir(correiaDentada);

			correiaDentada = new CorreiaDentada();

		} else if (produtoRevisao instanceof Farol) {
			farol = (Farol) produtoRevisao;
			new FarolDAO().excluir(farol);

			farol = new Farol();

		} else if (produtoRevisao instanceof FiltroDeAr) {
			filtroDeAr = (FiltroDeAr) produtoRevisao;
			new FiltroDeArDAO().excluir(filtroDeAr);

			filtroDeAr = new FiltroDeAr();

		} else if (produtoRevisao instanceof FiltroDeOleoMotor) {
			filtroDeOleoMotor = (FiltroDeOleoMotor) produtoRevisao;
			new FiltroDeOleoMotorDAO().excluir(filtroDeOleoMotor);

			filtroDeOleoMotor = new FiltroDeOleoMotor();

		} else if (produtoRevisao instanceof FluidoDeFreio) {
			fluidoDeFreio = (FluidoDeFreio) produtoRevisao;
			new FluidoDeFreioDAO().excluir(fluidoDeFreio);

			fluidoDeFreio = new FluidoDeFreio();

		} else if (produtoRevisao instanceof PastilhaFreio) {
			pastilhaFreio = (PastilhaFreio) produtoRevisao;
			new PastilhaFreioDAO().excluir(pastilhaFreio);

			pastilhaFreio = new PastilhaFreio();

		} else if (produtoRevisao instanceof VelasIgnicao) {
			velasIgnicao = (VelasIgnicao) produtoRevisao;
			new VelasIgnicaoDAO().excluir(velasIgnicao);

			velasIgnicao = new VelasIgnicao();

		} else if (produtoRevisao instanceof Embreagem) {
			embreagem = (Embreagem) produtoRevisao;
			new EmbreagemDAO().excluir(embreagem);

			embreagem = new Embreagem();
			
		} else if (produtoRevisao instanceof Pneu) {
			pneu = (Pneu) produtoRevisao;
			new PneuDAO().excluir(pneu);

			pneu = new Pneu();

		} else {
			new FormaDeVendaDAO().excluir(formaDeVenda);

			formaDeVenda = new FormaDeVenda();
		}

		indice = 0;
		produtoRevisao = new ProdutoRevisao();
		JSFUtil.adicionarMensagemSucesso("Produto excluido com sucesso!");

	}

	/**
	 * Verifica qual o opcional clicado
	 */
	public void verificarProdutoClicado() {

		if (produtoRevisao instanceof Amortecedor) {
			indice = 2;
			amortecedor.setId(produtoRevisao.getId());
			amortecedor = new AmortecedorDAO().pesquisarPorID(amortecedor);

		} else if (produtoRevisao instanceof CorreiaDentada) {
			indice = 3;
			correiaDentada.setId(produtoRevisao.getId());
			correiaDentada = new CorreiaDentadaDAO().pesquisarPorID(correiaDentada);

		} else if (produtoRevisao instanceof Farol) {
			indice = 4;
			farol.setId(produtoRevisao.getId());
			farol = new FarolDAO().pesquisarPorID(farol);
			
		} else if (produtoRevisao instanceof FiltroDeAr) {
			indice = 5;
			filtroDeAr.setId(produtoRevisao.getId());
			filtroDeAr = new FiltroDeArDAO().pesquisarPorID(filtroDeAr);
			
		} else if (produtoRevisao instanceof FiltroDeOleoMotor) {
			indice = 6;
			filtroDeOleoMotor.setId(produtoRevisao.getId());
			filtroDeOleoMotor = new FiltroDeOleoMotorDAO().pesquisarPorID(filtroDeOleoMotor);
			
		} else if (produtoRevisao instanceof FluidoDeFreio) {
			indice = 7;
			fluidoDeFreio.setId(produtoRevisao.getId());
			fluidoDeFreio = new FluidoDeFreioDAO().pesquisarPorID(fluidoDeFreio);
			
		} else if (produtoRevisao instanceof PastilhaFreio) {
			indice = 9;
			pastilhaFreio.setId(produtoRevisao.getId());
			pastilhaFreio = new PastilhaFreioDAO().pesquisarPorID(pastilhaFreio);
			
		} else if (produtoRevisao instanceof VelasIgnicao) {
			indice = 10;
			velasIgnicao.setId(produtoRevisao.getId());
			velasIgnicao = new VelasIgnicaoDAO().pesquisarPorID(velasIgnicao);
			
		} else if (produtoRevisao instanceof Embreagem) {
			indice = 11;
			embreagem.setId(produtoRevisao.getId());
			embreagem = new EmbreagemDAO().pesquisarPorID(embreagem);
			
		} else if (produtoRevisao instanceof Pneu) {
			indice = 12;
			pneu.setId(produtoRevisao.getId());
			pneu = new PneuDAO().pesquisarPorID(pneu);
		}

	}
	
	public void limparObjetos(){
		
		produtoRevisao = new ProdutoRevisao();
		indice = 0;
		amortecedor = new Amortecedor();
		correiaDentada = new CorreiaDentada();
		embreagem = new Embreagem();
		farol = new Farol();
		formaDeVenda = new FormaDeVenda();
		filtroDeAr = new FiltroDeAr();
		filtroDeOleoMotor = new FiltroDeOleoMotor();
		fluidoDeFreio = new FluidoDeFreio();
		pastilhaFreio = new PastilhaFreio();
		velasIgnicao = new VelasIgnicao();
		pneu = new Pneu();
		
	}
	
}
