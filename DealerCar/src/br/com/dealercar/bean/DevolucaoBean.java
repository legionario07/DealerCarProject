package br.com.dealercar.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.dealercar.dao.DevolucaoDAO;
import br.com.dealercar.dao.RetiradaDAO;
import br.com.dealercar.dao.automotivos.TaxasAdicionaisDAO;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.Devolucao;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Reserva;
import br.com.dealercar.domain.Retirada;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;
import br.com.dealercar.util.DataUtil;
import br.com.dealercar.util.JSFUtil;
import br.com.dealercar.util.SessionUtil;

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
	private List<TaxasAdicionais> listaTaxas = new ArrayList<TaxasAdicionais>();
	private List<String> taxasAdicionais;
	private String[] selectedTaxas;
	private List<Devolucao> listaDevolucao = new ArrayList<Devolucao>();

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

	public List<String> getTaxasAdicionais() {
		return taxasAdicionais;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setTaxasAdicionais(List<String> taxasAdicionais) {
		this.taxasAdicionais = taxasAdicionais;
	}

	public void setDevolucao(Devolucao devolucao) {
		this.devolucao = devolucao;
	}

	public List<Devolucao> getListaDevolucao() {
		return listaDevolucao;
	}

	public List<TaxasAdicionais> getListaTaxas() {
		return listaTaxas;
	}

	public void setListaTaxas(List<TaxasAdicionais> listaTaxas) {
		this.listaTaxas = listaTaxas;
	}

	public void setListaDevolucao(List<Devolucao> listaDevolucao) {
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

		listaDevolucao = new DevolucaoDAO().listarTodos();

		taxasAdicionais = new ArrayList<String>();

		this.setTotalDevolucoes(listaDevolucao.size());

		// recebendo todas as taxas cadastradas no BD
		listaTaxas = new TaxasAdicionaisDAO().listarTodos();

		// passando o nome das taxas para ser exibido na VIEW
		for (int i = 0; i < listaTaxas.size(); i++) {
			taxasAdicionais.add(listaTaxas.get(i).getDescricao());
		}

		if (devolucao.getRetirada() == null) {
			this.devolucao.setRetirada(new Retirada());
			this.devolucao.getRetirada().setCliente(new Cliente());
		}

	}

	/**
	 * Prepara os calculos para devolucao
	 */
	public void prepararDevolucao() {

		devolucao.setDataDevolucao(DataUtil.pegarDataAtualDoSistema());
		devolucao.setQtdeDiarias(DataUtil.devolverDataEmDias(devolucao.getRetirada().getDataRetirada()));

		List<TaxasAdicionais> taxas = new ArrayList<TaxasAdicionais>();

		TaxasAdicionais taxa = null;
		StringBuffer taxasCobradas = new StringBuffer();

		for (int i = 0; i < selectedTaxas.length; i++) {
			taxa = new TaxasAdicionais();
			taxa = new TaxasAdicionaisDAO().pesquisarPorTaxa(selectedTaxas[i]);
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
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo Usuário na
	 * TEla
	 */
	public void pesquisarPorCPF() {

		setEhCadastrado(false);
		setJaPesquisei(true);

		List<Retirada> listaClientesComRetirada = new ArrayList<Retirada>();
		listaClientesComRetirada = new RetiradaDAO().pesquisarPorCPF(this.cliente);

		// Verifica se o CPF tem alguma locação
		for (Retirada r : listaClientesComRetirada) {
			if (this.cliente.getCPF().equals(r.getCliente().getCPF())) {
				setEhCadastrado(true);
				setJaPesquisei(false);
				devolucao.setRetirada(r);
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
	 * Efetua a devolução
	 */
	public void efetuarDevolucao() {

		new DevolucaoDAO().cadastrar(devolucao);

		limparPesquisas();

		JSFUtil.adicionarMensagemSucesso("Devolução Efetuada com Sucesso");
		
		//fecha o <p:Dialog>
		org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dlgDevolucaoEfetuar').hide();");
				

	}

	/**
	 * Limpa os Objetos
	 */
	private void limparPesquisas() {
		devolucao = new Devolucao();
		selectedTaxas = null;
	}

}
