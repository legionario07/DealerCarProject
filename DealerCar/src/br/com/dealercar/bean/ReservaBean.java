package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.render.FacesRenderer;
import javax.persistence.PersistenceProperty;

import br.com.dealercar.dao.ClienteDAO;
import br.com.dealercar.dao.FuncionarioDAO;
import br.com.dealercar.dao.ReservaDAO;
import br.com.dealercar.dao.automotivos.CarroDAO;
import br.com.dealercar.dao.automotivos.ModeloDAO;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Reserva;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBReserva")
@ViewScoped
public class ReservaBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Reserva reserva = new Reserva();
	private Modelo modelo = new Modelo();
	private Funcionario funcionario = new Funcionario();
	private Cliente cliente = new Cliente();
	private Fabricante fabricante = new Fabricante();
	private Carro carro = new Carro();

	private ReservaDAO reservaDao = new ReservaDAO();
	private ClienteDAO cliDao = new ClienteDAO();
	private ModeloDAO modDao = new ModeloDAO();
	private FuncionarioDAO funDao = new FuncionarioDAO();
	private CarroDAO carroDao = new CarroDAO();

	private List<Reserva> listaReservas = new ArrayList<Reserva>();
	private List<Carro> listaModelosDisponiveis = new ArrayList<Carro>();
	private List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	
	private int totalReservas;
	private boolean ehCadastrado = false;
	
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
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
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
	public boolean isEhCadastrado() {
		return ehCadastrado;
	}
	public void setEhCadastrado(boolean ehCadastrado) {
		this.ehCadastrado = ehCadastrado;
	}
	
	/**
	 * Carrega a listagem assim que a pagina inicia
	 */
	public void carregarListagem() {
		
		listaReservas = reservaDao.listarTodos();
		
		listaModelosDisponiveis = carroDao.listarApenasDisponiveis();
		
		listaFuncionarios = funDao.listarTodos();
		
		listaClientes = cliDao.listarTodos();
		
		setTotalReservas(listaReservas.size());
		
	}
	
	/**
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo
	 * Usuário na TEla
	 */
	public void pesquisarPorCPF() {
		
		this.ehCadastrado = false;
		
		for (Cliente cli : listaClientes) {
			if (cliente.getCPF().equals(cli.getCPF())) {
				
				this.ehCadastrado = true; 
				cliente = cliDao.pesquisarPorCPF(cliente);
				
				reserva.setCliente(cliente);
				
				return;
			}
		}

		if (this.ehCadastrado == false) {
			cliente = new Cliente();
			
			JSFUtil.adicionarMensagemNaoLocalizado("Cliente Não Localizado.");
			return;
		}
		
	}
	
	
	/**
	 * Cadastra uma nova reserva, o usuario ira procurar o cliente pelo CPF
	 * Será aberta uma nova caixa de dialogo para o devido cadastro
	 */
	public void cadastrar() {

		
		//encontrando o Modelo pelo nome
		for(Carro m : listaModelosDisponiveis) {
			if(m.getModelo().getId() == modelo.getId()){
				
				modelo.setId(m.getModelo().getId());
				modelo = modDao.pesquisarPorID(modelo);
				
				reserva.setModelo(modelo);	
				
				modelo = new Modelo();
				break;
			}
		}
		
		//encontrando o Funcionario pelo nome
		for(Funcionario f : listaFuncionarios) {
			if(f.getId() == funcionario.getId()){
				funcionario = funDao.pesquisarPorID(f);
				reserva.setFuncionario(funcionario);
				funcionario = new Funcionario();
				break;
			}
		}

		//recebendo a data atual do sistema
		reserva.setDataCadastroReserva(reserva.setarDataDeCadastro());
		
		//Verifica se a data digitada para Reserva é válida
		int i = reserva.compararDatas(reserva.getDataCadastroReserva(), reserva.getDataFim());
		
		if(i!=1){
			reserva.setDataFim(null);
			JSFUtil.adicionarMensagemErro("A data para Reserva está incorreta. Deve ser maior que "
					+ reserva.getDataCadastroReserva());
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
	 * Edita os dados da reserva selecionada pelo usuario na tela
	 * Será aberta uma nova caixa de dialogo para ser feitas as alterações
	 */
	public void editar() {
		
		//Encontrando o modelo pelo nome
		for(Carro m : listaModelosDisponiveis) {
			if(m.getModelo().getNome().equals(reserva.getModelo().getNome())){
				
				modelo.setId(m.getModelo().getId());
				modelo = modDao.pesquisarPorID(modelo);
				
				modelo.setFabricante(fabricante);
				
				reserva.setModelo(modelo);
				modelo = new Modelo();
				break;
			}
		}
		
		//Encontrando o Funcionário pelo nome
		for(Funcionario f : listaFuncionarios) {
			if(f.getNome().equals(reserva.getFuncionario().getNome())){
				reserva.setFuncionario(f);
				funcionario = new Funcionario();
				break;
			}
		}
		
		
		//Verifica se a data digitada para Reserva é válida		
		int i = reserva.compararDatas(reserva.getDataCadastroReserva(), reserva.getDataFim());
		
		if(i!=1){
			reserva.setDataFim(null);
			JSFUtil.adicionarMensagemErro("A data para Reserva está incorreta. Deve ser maior que "
					+ reserva.getDataCadastroReserva());
			return;
		}
				
		reservaDao.editar(reserva);
		reserva = new Reserva();
		
		JSFUtil.adicionarMensagemSucesso("Reserva Alterada com Sucesso.");
		
	}
	
	/**
	 * Exclui os dados da reserva selecionada pelo usuario na tela
	 * Será aberta uma nova caixa de dialogo para confirmar a Exclusão
	 */
	public void excluir() {
		
		reservaDao.excluir(reserva);
		
		}

}
