package br.com.dealercar.web.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.builder.GraficoPizzaBuilder;
import br.com.dealercar.core.dao.ClienteDAO;
import br.com.dealercar.core.dao.ReservaDAO;
import br.com.dealercar.core.negocio.Reserva;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.core.util.SessionUtil;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.web.command.ICommand;

@ManagedBean(name = "MBReserva")
@SessionScoped
public class ReservaBean extends AbstractBean{


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

		// inicializando os gráficos
		gerarGrafico();

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapReservaCommands.get("LISTAR");

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

	}


	/**
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo Usuário na
	 * TEla
	 */
	public void pesquisarPorCPF() {

		setEhCadastrado(false);
		setJaPesquisei(true);

		// Validando o cliente
		reserva.setCliente(new ClienteDAO().pesquisarPorCPF(reserva.getCliente()));

		// veficando se o cliente foi encontrado
		if (reserva.getCliente() != null) {
			setEhCadastrado(true);
			setJaPesquisei(false);
			return;
		}

		if (isEhCadastrado() == false) {
			reserva.setCliente(new Cliente());
			JSFUtil.adicionarMensagemNaoLocalizado("Cliente Não Cadastrado.");
			return;
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
		ICommand command = mapReservaCommands.get(operacao);
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
	 * Edita os dados da reserva selecionada pelo usuario na tela Será aberta
	 * uma nova caixa de dialogo para ser feitas as alterações
	 */
	/*
	public void editar() {

		// validando o modelo pelo nome
		reserva.setModelo((Modelo) new ValidaModelo().validar(reserva.getModelo()));

		// recebendo a data atual do sistema
		reserva.setDataCadastroReserva(DataUtil.pegarDataAtualDoSistema());

		// Verifica se a data digitada para Reserva é válida
		int i = DataUtil.compararDatas(reserva.getDataCadastroReserva(), reserva.getDataFim());

		// se i diferente de 1 a data esta incorreta
		if (i != 1) {
			// colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");

			JSFUtil.adicionarMensagemErro("A data para Reserva está incorreta. " + "Deve ser maior que "
					+ stf.format(reserva.getDataCadastroReserva()));
			return;
		}

		// setando o funcionario que realizou a reserva
		reserva.setFuncionario((Funcionario) SessionUtil.getParam("usuarioLogado"));

		reservaDao.editar(reserva);
		reserva = new Reserva();

		JSFUtil.adicionarMensagemSucesso("Reserva Alterada com Sucesso.");

		// Se não houve nenhum erro fecha o <p:Dialog>
		org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dlgReservaEditar').hide();");

	}

	/**
	 * Exclui os dados da reserva selecionada pelo usuario na tela Será aberta
	 * uma nova caixa de dialogo para confirmar a Exclusão
	 */
	/*
	public void excluir() {

		reservaDao.excluir(reserva);

		JSFUtil.adicionarMensagemSucesso("Reserva excluida com Sucesso.");

	}

	/**
	 * Gerando o gráfico de Reserva
	 */
	private void gerarGrafico() {

		pieReserva = new PieChartModel();

		// lista que recebe todos os itens do BD
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		lista = new ReservaDAO().listarTodos();
		// passando apenas os nomes para a lista de String

		List<String> listaString = new ArrayList<String>(); // Lista que ira
		for (EntidadeDominio r : lista) {
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

		// Encaminha o usuario para a pagina de Cadastrar novo cliente
		FacesContext faces = FacesContext.getCurrentInstance();
		ExternalContext exContext = faces.getExternalContext();

		try {
			exContext.redirect("ncliente.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

}
