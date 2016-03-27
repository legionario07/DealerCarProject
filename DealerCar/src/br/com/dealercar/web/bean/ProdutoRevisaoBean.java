package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.EntidadeDominio;
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
import br.com.dealercar.web.command.ICommand;

@ManagedBean(name = "MBProdutoRevisao")
@ViewScoped
public class ProdutoRevisaoBean extends AbstractBean implements Serializable {

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

	private List<EntidadeDominio> listaAmortecedores = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaCorreiaDentada = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaEmbreagem = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaFarol = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaFiltroDeAr = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaFiltroDeOleoMotor = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaFluidoDeFreio = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaFormaDeVenda = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaPastilhaFreio = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaVelasIgnicao = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaPneu = new ArrayList<EntidadeDominio>();

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

	public List<EntidadeDominio> getListaEmbreagem() {
		return listaEmbreagem;
	}

	public void setListaEmbreagem(List<EntidadeDominio> listaEmbreagem) {
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

	public List<EntidadeDominio> getListaPneu() {
		return listaPneu;
	}

	public void setListaPneu(List<EntidadeDominio> listaPneu) {
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

	public List<EntidadeDominio> getListaAmortecedores() {
		return listaAmortecedores;
	}

	public void setListaAmortecedores(List<EntidadeDominio> listaAmortecedores) {
		this.listaAmortecedores = listaAmortecedores;
	}

	public List<EntidadeDominio> getListaCorreiaDentada() {
		return listaCorreiaDentada;
	}

	public void setListaCorreiaDentada(List<EntidadeDominio> listaCorreiaDentada) {
		this.listaCorreiaDentada = listaCorreiaDentada;
	}

	public List<EntidadeDominio> getListaFarol() {
		return listaFarol;
	}

	public void setListaFarol(List<EntidadeDominio> listaFarol) {
		this.listaFarol = listaFarol;
	}

	public List<EntidadeDominio> getListaFiltroDeAr() {
		return listaFiltroDeAr;
	}

	public void setListaFiltroDeAr(List<EntidadeDominio> listaFiltroDeAr) {
		this.listaFiltroDeAr = listaFiltroDeAr;
	}

	public List<EntidadeDominio> getListaFiltroDeOleoMotor() {
		return listaFiltroDeOleoMotor;
	}

	public void setListaFiltroDeOleoMotor(List<EntidadeDominio> listaFiltroDeOleoMotor) {
		this.listaFiltroDeOleoMotor = listaFiltroDeOleoMotor;
	}

	public List<EntidadeDominio> getListaFluidoDeFreio() {
		return listaFluidoDeFreio;
	}

	public void setListaFluidoDeFreio(List<EntidadeDominio> listaFluidoDeFreio) {
		this.listaFluidoDeFreio = listaFluidoDeFreio;
	}

	public List<EntidadeDominio> getListaFormaDeVenda() {
		return listaFormaDeVenda;
	}

	public void setListaFormaDeVenda(List<EntidadeDominio> listaFormaDeVenda) {
		this.listaFormaDeVenda = listaFormaDeVenda;
	}

	public List<EntidadeDominio> getListaPastilhaFreio() {
		return listaPastilhaFreio;
	}

	public void setListaPastilhaFreio(List<EntidadeDominio> listaPastilhaFreio) {
		this.listaPastilhaFreio = listaPastilhaFreio;
	}

	public List<EntidadeDominio> getListaVelasIgnicao() {
		return listaVelasIgnicao;
	}

	public void setListaVelasIgnicao(List<EntidadeDominio> listaVelasIgnicao) {
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

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("LISTAR");
		Resultado resultado = new Resultado();

		resultado = command.execute(new Amortecedor());
		if (resultado != null) {
			listaAmortecedores = resultado.getEntidades();
		}
		resultado = command.execute(new CorreiaDentada());
		if (resultado != null) {
			listaCorreiaDentada = resultado.getEntidades();
		}
		resultado = command.execute(new Embreagem());
		if (resultado != null) {
			listaEmbreagem = resultado.getEntidades();
		}
		resultado = command.execute(new Farol());
		if (resultado != null) {
			listaFarol = resultado.getEntidades();
		}
		resultado = command.execute(new FiltroDeAr());
		if (resultado != null) {
			listaFiltroDeAr = resultado.getEntidades();
		}
		resultado = command.execute(new FiltroDeOleoMotor());
		if (resultado != null) {
			listaFiltroDeOleoMotor = resultado.getEntidades();
		}
		resultado = command.execute(new FluidoDeFreio());
		if (resultado != null) {
			listaFluidoDeFreio = resultado.getEntidades();
		}
		resultado = command.execute(new FormaDeVenda());
		if (resultado != null) {
			listaFormaDeVenda = resultado.getEntidades();
		}
		resultado = command.execute(new PastilhaFreio());
		if (resultado != null) {
			listaPastilhaFreio = resultado.getEntidades();
		}
		resultado = command.execute(new VelasIgnicao());
		if (resultado != null) {
			listaVelasIgnicao = resultado.getEntidades();
		}
		resultado = command.execute(new Pneu());
		if (resultado != null) {
			listaPneu = resultado.getEntidades();
		}

	}

	/**
	 * Prepara para Cadastrar um novo Opcional verifica na View qual o Opcional
	 * Clicado e seta um indice
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
		} else if (event.getComponent().getId().equals("botaoPneu")) {
			indice = 12;
		}

	}

	/**
	 * Cadastra um novo Opcional de Acordo com o Indice
	 */
	public void executar() {

		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get(operacao);

		if (indice == 2 || produtoRevisao instanceof Amortecedor) {
			amortecedor.setId(produtoRevisao.getId());
			amortecedor.setDescricao(produtoRevisao.getDescricao());
			amortecedor.setMarca(produtoRevisao.getMarca());
			amortecedor.setTipo(produtoRevisao.getTipo());
			amortecedor.setQuantidade(produtoRevisao.getQuantidade());
			amortecedor.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			amortecedor.setValor(produtoRevisao.getValor());

			command.execute(amortecedor);

			amortecedor = new Amortecedor();

		} else if (indice == 3 || produtoRevisao instanceof CorreiaDentada) {
			correiaDentada.setId(produtoRevisao.getId());
			correiaDentada.setDescricao(produtoRevisao.getDescricao());
			correiaDentada.setMarca(produtoRevisao.getMarca());
			correiaDentada.setTipo(produtoRevisao.getTipo());
			correiaDentada.setQuantidade(produtoRevisao.getQuantidade());
			correiaDentada.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			correiaDentada.setValor(produtoRevisao.getValor());

			command.execute(correiaDentada);

			correiaDentada = new CorreiaDentada();

		} else if (indice == 4 || produtoRevisao instanceof Farol) {
			farol.setId(produtoRevisao.getId());
			farol.setDescricao(produtoRevisao.getDescricao());
			farol.setMarca(produtoRevisao.getMarca());
			farol.setTipo(produtoRevisao.getTipo());
			farol.setQuantidade(produtoRevisao.getQuantidade());
			farol.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			farol.setValor(produtoRevisao.getValor());

			command.execute(farol);

			farol = new Farol();

		} else if (indice == 5 || produtoRevisao instanceof FiltroDeAr) {
			filtroDeAr.setId(produtoRevisao.getId());
			filtroDeAr.setDescricao(produtoRevisao.getDescricao());
			filtroDeAr.setMarca(produtoRevisao.getMarca());
			filtroDeAr.setTipo(produtoRevisao.getTipo());
			filtroDeAr.setQuantidade(produtoRevisao.getQuantidade());
			filtroDeAr.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			filtroDeAr.setValor(produtoRevisao.getValor());

			command.execute(filtroDeAr);

			filtroDeAr = new FiltroDeAr();

		} else if (indice == 6 || produtoRevisao instanceof FiltroDeOleoMotor) {
			filtroDeOleoMotor.setId(produtoRevisao.getId());
			filtroDeOleoMotor.setDescricao(produtoRevisao.getDescricao());
			filtroDeOleoMotor.setMarca(produtoRevisao.getMarca());
			filtroDeOleoMotor.setTipo(produtoRevisao.getTipo());
			filtroDeOleoMotor.setQuantidade(produtoRevisao.getQuantidade());
			filtroDeOleoMotor.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			filtroDeOleoMotor.setValor(produtoRevisao.getValor());

			command.execute(filtroDeOleoMotor);

			filtroDeOleoMotor = new FiltroDeOleoMotor();

		} else if (indice == 7 || produtoRevisao instanceof FluidoDeFreio) {
			fluidoDeFreio.setId(produtoRevisao.getId());
			fluidoDeFreio.setDescricao(produtoRevisao.getDescricao());
			fluidoDeFreio.setMarca(produtoRevisao.getMarca());
			fluidoDeFreio.setTipo(produtoRevisao.getTipo());
			fluidoDeFreio.setQuantidade(produtoRevisao.getQuantidade());
			fluidoDeFreio.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			fluidoDeFreio.setValor(produtoRevisao.getValor());

			command.execute(fluidoDeFreio);

			fluidoDeFreio = new FluidoDeFreio();

		} else if (indice == 8) {

			command.execute(formaDeVenda);
			formaDeVenda = new FormaDeVenda();

		} else if (indice == 9 || produtoRevisao instanceof PastilhaFreio) {
			pastilhaFreio.setId(produtoRevisao.getId());
			pastilhaFreio.setDescricao(produtoRevisao.getDescricao());
			pastilhaFreio.setMarca(produtoRevisao.getMarca());
			pastilhaFreio.setTipo(produtoRevisao.getTipo());
			pastilhaFreio.setQuantidade(produtoRevisao.getQuantidade());
			pastilhaFreio.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			pastilhaFreio.setValor(produtoRevisao.getValor());

			command.execute(pastilhaFreio);

			pastilhaFreio = new PastilhaFreio();

		} else if (indice == 10 || produtoRevisao instanceof VelasIgnicao) {
			velasIgnicao.setId(produtoRevisao.getId());
			velasIgnicao.setDescricao(produtoRevisao.getDescricao());
			velasIgnicao.setMarca(produtoRevisao.getMarca());
			velasIgnicao.setTipo(produtoRevisao.getTipo());
			velasIgnicao.setQuantidade(produtoRevisao.getQuantidade());
			velasIgnicao.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			velasIgnicao.setValor(produtoRevisao.getValor());

			command.execute(velasIgnicao);

			velasIgnicao = new VelasIgnicao();

		} else if (indice == 11 || produtoRevisao instanceof Embreagem) {
			embreagem.setId(produtoRevisao.getId());
			embreagem.setDescricao(produtoRevisao.getDescricao());
			embreagem.setMarca(produtoRevisao.getMarca());
			embreagem.setTipo(produtoRevisao.getTipo());
			embreagem.setQuantidade(produtoRevisao.getQuantidade());
			embreagem.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			embreagem.setValor(produtoRevisao.getValor());

			command.execute(embreagem);

			embreagem = new Embreagem();

		} else if (indice == 12 || produtoRevisao instanceof Pneu) {
			pneu.setId(produtoRevisao.getId());
			pneu.setDescricao(produtoRevisao.getDescricao());
			pneu.setMarca(produtoRevisao.getMarca());
			pneu.setTipo(produtoRevisao.getTipo());
			pneu.setQuantidade(produtoRevisao.getQuantidade());
			pneu.setFormaDeVenda(produtoRevisao.getFormaDeVenda());
			pneu.setValor(produtoRevisao.getValor());

			command.execute(pneu);

			pneu = new Pneu();

		}

		indice = 0;
		produtoRevisao = new ProdutoRevisao();

	}

	/**
	 * Verifica qual o opcional clicado
	 */
	public void verificarProdutoClicado() {

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("CONSULTAR");
		Resultado resultado = new Resultado();

		if (produtoRevisao instanceof Amortecedor) {
			indice = 2;
			amortecedor.setId(produtoRevisao.getId());
			
			resultado = command.execute(amortecedor);
			amortecedor = (Amortecedor) resultado.getEntidades().get(0);

		} else if (produtoRevisao instanceof CorreiaDentada) {
			indice = 3;
			correiaDentada.setId(produtoRevisao.getId());
			
			resultado = command.execute(correiaDentada);
			correiaDentada = (CorreiaDentada) resultado.getEntidades().get(0);

		} else if (produtoRevisao instanceof Farol) {
			indice = 4;
			farol.setId(produtoRevisao.getId());
			
			resultado = command.execute(farol);
			farol = (Farol) resultado.getEntidades().get(0);

		} else if (produtoRevisao instanceof FiltroDeAr) {
			indice = 5;
			filtroDeAr.setId(produtoRevisao.getId());
			
			resultado = command.execute(filtroDeAr);
			filtroDeAr = (FiltroDeAr) resultado.getEntidades().get(0);

		} else if (produtoRevisao instanceof FiltroDeOleoMotor) {
			indice = 6;
			filtroDeOleoMotor.setId(produtoRevisao.getId());
			
			resultado = command.execute(filtroDeOleoMotor);
			filtroDeOleoMotor = (FiltroDeOleoMotor) resultado.getEntidades().get(0);

		} else if (produtoRevisao instanceof FluidoDeFreio) {
			indice = 7;
			fluidoDeFreio.setId(produtoRevisao.getId());
			
			resultado = command.execute(fluidoDeFreio);
			fluidoDeFreio = (FluidoDeFreio) resultado.getEntidades().get(0);

		} else if (produtoRevisao instanceof PastilhaFreio) {
			indice = 9;
			pastilhaFreio.setId(produtoRevisao.getId());
			
			resultado = command.execute(pastilhaFreio);
			pastilhaFreio = (PastilhaFreio) resultado.getEntidades().get(0);

		} else if (produtoRevisao instanceof VelasIgnicao) {
			indice = 10;
			velasIgnicao.setId(produtoRevisao.getId());
			
			resultado = command.execute(velasIgnicao);
			velasIgnicao = (VelasIgnicao) resultado.getEntidades().get(0);

		} else if (produtoRevisao instanceof Embreagem) {
			indice = 11;
			embreagem.setId(produtoRevisao.getId());
			
			resultado = command.execute(embreagem);
			embreagem = (Embreagem) resultado.getEntidades().get(0);

		} else if (produtoRevisao instanceof Pneu) {
			indice = 12;
			pneu.setId(produtoRevisao.getId());
			
			resultado = command.execute(pneu);
			pneu = (Pneu) resultado.getEntidades().get(0);
		} else {
			indice = 8;
		}

	}

	public void limparObjetos() {

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
