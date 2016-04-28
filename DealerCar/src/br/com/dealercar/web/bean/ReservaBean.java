package br.com.dealercar.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.builder.GraficoPizzaBuilder;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.core.util.SessionUtil;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.conducao.Reserva;
import br.com.dealercar.domain.produtosrevisao.Amortecedor;
import br.com.dealercar.domain.produtosrevisao.CorreiaDentada;
import br.com.dealercar.domain.produtosrevisao.Embreagem;
import br.com.dealercar.domain.produtosrevisao.Farol;
import br.com.dealercar.domain.produtosrevisao.FiltroDeAr;
import br.com.dealercar.domain.produtosrevisao.FiltroDeOleoMotor;
import br.com.dealercar.domain.produtosrevisao.FluidoDeFreio;
import br.com.dealercar.domain.produtosrevisao.PastilhaFreio;
import br.com.dealercar.domain.produtosrevisao.Pneu;
import br.com.dealercar.domain.produtosrevisao.ProdutoRevisao;
import br.com.dealercar.domain.produtosrevisao.VelasIgnicao;
import br.com.dealercar.web.command.ICommand;

@ManagedBean(name = "MBReserva")
@SessionScoped
public class ReservaBean extends AbstractBean {

	private Reserva reserva = new Reserva();

	private List<EntidadeDominio> listaReservas = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaModelosDisponiveis = new ArrayList<EntidadeDominio>();

	private PieChartModel pieReserva;

	private int totalReservas;

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<EntidadeDominio> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(List<EntidadeDominio> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public PieChartModel getPieReserva() {
		return pieReserva;
	}

	public void setPieReserva(PieChartModel pieReserva) {
		this.pieReserva = pieReserva;
	}

	public List<EntidadeDominio> getListaModelosDisponiveis() {
		return listaModelosDisponiveis;
	}

	public void setListaModelosDisponiveis(List<EntidadeDominio> listaModelosDisponiveis) {
		this.listaModelosDisponiveis = listaModelosDisponiveis;
	}

	public int getTotalReservas() {
		return totalReservas;
	}

	public void setTotalReservas(int totalReservas) {
		this.totalReservas = totalReservas;
	}

	/**
	 * Carrega a listagem assim que a pagina inicia - das Reservas disponiveis -
	 * Modelos Disponiveis - Funcionarios - Clientes
	 */
	@Override
	public void carregarListagem() {

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapConducaoCommands.get("LISTAR");

		Resultado resultado = new Resultado();
		resultado = command.execute(new Reserva());
		if (resultado != null) {
			listaReservas = resultado.getEntidades();
		}

		resultado = command.execute(new Modelo());
		if (resultado != null) {
			listaModelosDisponiveis = resultado.getEntidades();
		}

		setTotalReservas(listaReservas.size());

		/**
		 * verifica se tem algum produto Revisao com quantidade inferior a e
		 * exibe uma mensagem na tela
		 */
		command = mapCommands.get("LISTAR");
		resultado = new Resultado();

		List<ProdutoRevisao> listaProdutos = new ArrayList<ProdutoRevisao>();
		listaProdutos.add(new Amortecedor());
		listaProdutos.add(new CorreiaDentada());
		listaProdutos.add(new Embreagem());
		listaProdutos.add(new Farol());
		listaProdutos.add(new FiltroDeAr());
		listaProdutos.add(new FiltroDeOleoMotor());
		listaProdutos.add(new FluidoDeFreio());
		listaProdutos.add(new Pneu());
		listaProdutos.add(new PastilhaFreio());
		listaProdutos.add(new VelasIgnicao());
		
		for (ProdutoRevisao p : listaProdutos) {
			resultado = command.execute(p);
			for(EntidadeDominio produto : resultado.getEntidades()){
				if(((ProdutoRevisao) produto).getQuantidade()<3){
					StringBuffer retorno = new StringBuffer();
					retorno.append(((ProdutoRevisao) produto).getDescricao());
					retorno.append(" - ");
					retorno.append(((ProdutoRevisao) produto).getMarca());
					retorno.append(" - ");
					retorno.append(((ProdutoRevisao) produto).getTipo());
					retorno.append(" esta com seu estoque em ");
					retorno.append(((ProdutoRevisao) produto).getQuantidade());
					JSFUtil.adicionarMensagemErro(retorno.toString());
				}
			}
			
		}

		// inicializando os gráficos
		gerarGrafico();

	}

	/**
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo Usuário na
	 * TEla
	 */
	public void pesquisarPorCPF() {

		setEhCadastrado(false);
		setJaPesquisei(true);

		// Retorna um estado completo de acordo com um ID
		ICommand command = mapCommands.get("CONSULTAR");

		Resultado resultado = new Resultado();
		resultado = command.execute(reserva.getCliente());

		// Cliente foi encontrado
		if (resultado.getEntidades().get(0) != null) {
			reserva.setCliente((Cliente) resultado.getEntidades().get(0));
			setEhCadastrado(true);
			setJaPesquisei(false);
			return;
		} else {
			reserva.setCliente(new Cliente());
		}

	}

	/**
	 * Cadastra uma nova reserva, o usuario ira procurar o cliente pelo CPF Será
	 * aberta uma nova caixa de dialogo para o devido cadastro
	 */
	@Override
	public void executar() {

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapConducaoCommands.get(operacao);
		Resultado resultado = new Resultado();

		// recebendo a data atual do sistema
		reserva.setDataCadastroReserva(DataUtil.pegarDataAtualDoSistema());
		// setando o funcionario que realizou a reserva
		reserva.setFuncionario((Funcionario) SessionUtil.getParam("usuarioLogado"));

		resultado = command.execute(reserva);
		if (resultado != null) {
			reserva = (Reserva) resultado.getEntidades().get(0);
		}

		reserva = new Reserva();

	}

	/**
	 * Gerando o gráfico de Reserva
	 */
	private void gerarGrafico() {

		pieReserva = new PieChartModel();

		// passando apenas os nomes para a lista de String

		List<String> listaString = new ArrayList<String>(); // Lista que ira
		for (EntidadeDominio r : listaReservas) {
			listaString.add(((Reserva) r).getModelo().getNome());
		}

		pieReserva = GraficoPizzaBuilder.gerarGrafico(listaString);
		pieReserva.setTitle("Modelos Mais Reservados");
		pieReserva.setShowDataLabels(true);
		pieReserva.setLegendPosition("w");

	}

	/**
	 * Limpa os objetos
	 */
	public void limparObjetos() {

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("MBReserva");

		reserva = new Reserva();

	}

}
