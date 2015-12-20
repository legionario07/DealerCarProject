package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.ClienteDAO;
import br.com.dealercar.dao.FuncionarioDAO;
import br.com.dealercar.dao.ReservaDAO;
import br.com.dealercar.dao.automotivos.CarroDAO;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Reserva;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.strategy.valida.IValidacaoStrategy;
import br.com.dealercar.strategy.valida.ValidaCliente;
import br.com.dealercar.strategy.valida.ValidaFabricante;
import br.com.dealercar.strategy.valida.ValidaModelo;
import br.com.dealercar.util.DataUtil;
import br.com.dealercar.util.JSFUtil;
import br.com.dealercar.viewhelper.SessionHelper;

@ManagedBean(name = "MBReserva")
@ViewScoped
public class ReservaBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Reserva reserva = new Reserva();
	private Modelo modelo = new Modelo();
	private Cliente cliente = new Cliente();
	private Fabricante fabricante = new Fabricante();
	private Carro carro = new Carro();
	private Date dataReserva = null;

	IValidacaoStrategy validaStrategy = null;

	private ReservaDAO reservaDao = new ReservaDAO();
	private ClienteDAO cliDao = new ClienteDAO();
	private FuncionarioDAO funDao = new FuncionarioDAO();
	private CarroDAO carroDao = new CarroDAO();

	private List<Reserva> listaReservas = new ArrayList<Reserva>();
	private List<Carro> listaModelosDisponiveis = new ArrayList<Carro>();
	private List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
	private List<Cliente> listaClientes = new ArrayList<Cliente>();

	private int totalReservas;

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ReservaDAO getReservaDao() {
		return reservaDao;
	}

	public void setReservaDao(ReservaDAO reservaDao) {
		this.reservaDao = reservaDao;
	}

	public List<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Carro> getListaModelosDisponiveis() {
		return listaModelosDisponiveis;
	}

	public void setListaModelosDisponiveis(List<Carro> listaModelosDisponiveis) {
		this.listaModelosDisponiveis = listaModelosDisponiveis;
	}

	public int getTotalReservas() {
		return totalReservas;
	}

	public void setTotalReservas(int totalReservas) {
		this.totalReservas = totalReservas;
	}


	/**
	 * Carrega a listagem assim que a pagina inicia 
	 * - das Reservas disponiveis
	 *  - Modelos Disponiveis
	 *  - Funcionarios
	 *  - Clientes
	 */
	@Override
	public void carregarListagem() {

		listaReservas = reservaDao.listarTodos();

		listaModelosDisponiveis = carroDao.listarApenasDisponiveis();

		listaFuncionarios = funDao.listarTodos();

		listaClientes = cliDao.listarTodos();

		setTotalReservas(listaReservas.size());

	}

	/**
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo Usuário na
	 * TEla
	 */
	public void pesquisarPorCPF() {

		setEhCadastrado(false);
		setJaPesquisei(false);

		validaStrategy = new ValidaCliente();
		cliente = (Cliente) validaStrategy.validar(cliente);

		if (cliente != null) {
			setEhCadastrado(true);

			reserva.setCliente(cliente);

			return;
		}

		if (isEhCadastrado() == false) {
			cliente = new Cliente();
			setJaPesquisei(true);

			JSFUtil.adicionarMensagemNaoLocalizado("Cliente Não Localizado.");
			return;
		}

	}

	/**
	 * Cadastra uma nova reserva, o usuario ira procurar o cliente pelo CPF Será
	 * aberta uma nova caixa de dialogo para o devido cadastro
	 */
	public void cadastrar() {

		// validando o modelo pelo nome
		validaStrategy = new ValidaModelo();
		modelo = (Modelo) validaStrategy.validar(modelo);
		reserva.setModelo(modelo);

		// validando o fabricante pelo modelo
		validaStrategy = new ValidaFabricante();
		fabricante = (Fabricante) validaStrategy.validar(modelo);
		modelo.setFabricante(fabricante);

		Funcionario funcionario = (Funcionario) SessionHelper.getParam("usuarioLogado");
		reserva.setFuncionario(funcionario);

		// recebendo a data atual do sistema
		reserva.setDataCadastroReserva(DataUtil.pegarDataAtualDoSistema());

		// Verifica se a data digitada para Reserva é válida
		int i = DataUtil.compararDatas(reserva.getDataCadastroReserva(), reserva.getDataFim());

		if (i != 1) {
			JSFUtil.adicionarMensagemErro("A data para Reserva está incorreta. "
					+ "Deve ser maior que " + reserva.getDataCadastroReserva());
			return;
		}

		reservaDao.cadastrar(reserva);

		reserva = new Reserva();

		modelo = new Modelo();
		funcionario = new Funcionario();
		cliente = new Cliente();

		setEhCadastrado(false);

		JSFUtil.adicionarMensagemSucesso("Reserva Cadastrada com Sucesso.");

	}

	/**
	 * Edita os dados da reserva selecionada pelo usuario na tela Será aberta
	 * uma nova caixa de dialogo para ser feitas as alterações
	 */
	public void editar() {

		// validando o modelo pelo nome
		validaStrategy = new ValidaModelo();
		modelo = (Modelo) validaStrategy.validar(reserva.getModelo());
		reserva.setModelo(modelo);

		// validando o fabricante pelo modelo
		validaStrategy = new ValidaFabricante();
		fabricante = (Fabricante) validaStrategy.validar(modelo);
		modelo.setFabricante(fabricante);

		Funcionario funcionario = (Funcionario) SessionHelper.getParam("usuarioLogado");
		reserva.setFuncionario(funcionario);

		// recebendo a data atual do sistema
		reserva.setDataCadastroReserva(DataUtil.pegarDataAtualDoSistema());

		// Se a alteração for apenas para CANCELADO não é necessário validar a
		// Data
		if (!reserva.getSituacao().getDescricao().equals("CANCELADO")) {
			// Verifica se a data digitada para Reserva é válida
			int i = DataUtil.compararDatas(reserva.getDataCadastroReserva(), reserva.getDataFim());

			if (i != 1) {
				reserva.setDataFim(null);
				JSFUtil.adicionarMensagemErro(
						"A data para Reserva está incorreta. Deve ser maior que " + reserva.getDataCadastroReserva());
				return;
			}
		}

		reservaDao.editar(reserva);
		reserva = new Reserva();

		JSFUtil.adicionarMensagemSucesso("Reserva Alterada com Sucesso.");

	}

	/**
	 * Exclui os dados da reserva selecionada pelo usuario na tela Será aberta
	 * uma nova caixa de dialogo para confirmar a Exclusão
	 */
	public void excluir() {

		reservaDao.excluir(reserva);

		JSFUtil.adicionarMensagemSucesso("Reserva excluida com Sucesso.");

	}

}
