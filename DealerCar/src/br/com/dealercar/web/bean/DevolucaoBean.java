package br.com.dealercar.web.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.core.util.SessionUtil;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.conducao.Devolucao;
import br.com.dealercar.domain.conducao.Reserva;
import br.com.dealercar.domain.conducao.Retirada;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;
import br.com.dealercar.web.command.ICommand;

/**
 * Classe Controller responsavel pela View devolucao.xhtml
 * 
 * @author Paulinho
 *
 */
@ManagedBean(name = "MBDevolucao")
@SessionScoped
public class DevolucaoBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Devolucao devolucao = new Devolucao();
	private Cliente cliente = new Cliente();
	private List<EntidadeDominio> listaTaxas = new ArrayList<EntidadeDominio>();
	private String[] selectedTaxas;
	private List<EntidadeDominio> listaDevolucao = new ArrayList<EntidadeDominio>();

	private int totalDevolucoes;

	public Devolucao getDevolucao() {
		return devolucao;
	}

	public String[] getSelectedTaxas() {
		return selectedTaxas;
	}

	public void setSelectedTaxas(String[] selectedTaxas) {
		this.selectedTaxas = selectedTaxas;
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public void setDevolucao(Devolucao devolucao) {
		this.devolucao = devolucao;
	}

	public List<EntidadeDominio> getListaDevolucao() {
		return listaDevolucao;
	}

	public List<EntidadeDominio> getListaTaxas() {
		return listaTaxas;
	}

	public void setListaTaxas(List<EntidadeDominio> listaTaxas) {
		this.listaTaxas = listaTaxas;
	}

	public void setListaDevolucao(List<EntidadeDominio> listaDevolucao) {
		this.listaDevolucao = listaDevolucao;
	}

	public int getTotalDevolucoes() {
		return totalDevolucoes;
	}

	public void setTotalDevolucoes(int totalDevolucoes) {
		this.totalDevolucoes = totalDevolucoes;
	}

	/**
	 * Carrega a lista de Devolucao assim que a pagina é iniciada
	 */
	@Override
	public void carregarListagem() {

		ICommand command = mapConducaoCommands.get("LISTAR");

		Resultado resultado = new Resultado();

		resultado = command.execute(new Devolucao());
		if (resultado != null) {
			listaDevolucao = resultado.getEntidades();
		}

		this.setTotalDevolucoes(listaDevolucao.size());


		// recebendo todas as taxas cadastradas no BD
		resultado = command.execute(new TaxasAdicionais());
		if (resultado != null) {
			listaTaxas = resultado.getEntidades();
		}

		// verifica se comando veio da view retiradas.xhtml
		if (devolucao.getRetirada() == null) {
			this.devolucao.setRetirada(new Retirada());
			this.devolucao.getRetirada().setCliente(new Cliente());
		}

	}
	
	
	@Override
	public void executar() {
		
		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");
		
		
		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapConducaoCommands.get(operacao);
		command.execute(devolucao);
		
		limparObjetos();
		
	}

	/**
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo Usuário na
	 * TEla
	 */
	public void pesquisarPorCPF() {

		setEhCadastrado(false);
		setJaPesquisei(true);

		Retirada retirada = new Retirada();

		ICommand command = mapConducaoCommands.get("CONSULTAR");
		Resultado resultado = new Resultado();

		// retorna uma lista com todos os clientes com locação no mommento
		resultado = command.execute(this.cliente);
		if (resultado.getEntidades().get(0) != null) {
			retirada = (Retirada) resultado.getEntidades().get(0);

			if (this.cliente.getCPF().equals(retirada.getCliente().getCPF())) {
				setEhCadastrado(true);
				setJaPesquisei(false);
				devolucao.setRetirada(retirada);
				this.cliente = new Cliente();

				// encaminha para a pagina de devolucao
				FacesContext faces = FacesContext.getCurrentInstance();
				ExternalContext exContext = faces.getExternalContext();

				try {
					exContext.redirect("efetuadevolucao.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
					JSFUtil.adicionarMensagemErro(e.getMessage());
				}

				return;
			}
		}

		// Cliente nao possui locação
		if (isEhCadastrado() == false) {
			cliente = new Cliente();
			JSFUtil.adicionarMensagemNaoLocalizado("Cliente Não tem nenhuma Locação Pendente.");
			return;
		}

	}
	
	
	/**
	 * Prepara os calculos para devolucao
	 */
	public void prepararDevolucao() {

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapConducaoCommands.get(operacao);
		Resultado resultado = null;

		devolucao.setDataDevolucao(DataUtil.pegarDataAtualDoSistema());
		devolucao.setQtdeDiarias(DataUtil.devolverDataEmDias(devolucao.getRetirada().getDataRetirada()));

		List<TaxasAdicionais> taxas = new ArrayList<TaxasAdicionais>();

		TaxasAdicionais taxa = null;
		StringBuffer taxasCobradas = new StringBuffer();

		for (int i = 0; i < selectedTaxas.length; i++) {
			resultado = new Resultado();
			taxa = new TaxasAdicionais();
			taxa.setDescricao(selectedTaxas[i]);
			resultado = command.execute(taxa);
			
			if(resultado.getEntidades().get(0) == null)
				return;
			
			taxa = (TaxasAdicionais) resultado.getEntidades().get(0);
			taxa.setFoiCobrado(true);

			taxasCobradas.append(selectedTaxas[i]);
			taxasCobradas.append(";");

			taxas.add(taxa);
		}

		devolucao.setTaxasCobradas(taxasCobradas.toString());

		devolucao.setTaxasAdicionais(taxas);
		devolucao.setValorFinal(devolucao.calcularValorFinal(devolucao, listaTaxas));

		Reserva reserva = new Reserva();
		devolucao.getRetirada().setReserva(reserva);

		devolucao.setFuncionario((Funcionario) SessionUtil.getParam("usuarioLogado"));
		

	}




	/**
	 * Limpa os Objetos
	 */
	public void limparObjetos() {
		devolucao = new Devolucao();
		selectedTaxas = null;
	}

}
