package br.com.dealercar.core.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.dao.automotivos.ModeloDAO;
import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.conducao.Reserva;
import br.com.dealercar.domain.enums.SituacaoReserva;

/**
 * Classe responsável por persistir as Reservas no BD
 * 
 * @author Paulinho
 *
 */
public class ReservaDAO extends AbstractPesquisaDAO implements Serializable {

	private Connection con = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param reserva
	 *            Recebe um reserva e cadastra no Banco de Dados
	 * 
	 */
	public synchronized void cadastrar(EntidadeDominio entidade) {

		if(!(entidade instanceof Reserva))
			return;
		
		Reserva reserva = new Reserva();
		reserva = (Reserva) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into reservas ");
		sql.append("(situacao, data_inicio, data_fim, id_modelo, cliente_cpf, id_funcionario) ");
		sql.append("values (?, ?, ?, ?, ?, ?)");

		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			reserva.setSituacao(SituacaoReserva.ATIVO);// iniciando a Situação

			pstm.setString(++i, reserva.getSituacao().getDescricao());

			// colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
			String dataCadastro = stf.format(reserva.getDataCadastroReserva());
			pstm.setString(++i, dataCadastro);

			// colocando formato string para armazenar no banco de dados
			String strDataFim = stf.format(reserva.getDataFim());
			pstm.setString(++i, strDataFim);

			pstm.setInt(++i, reserva.getModelo().getId());
			pstm.setString(++i, reserva.getCliente().getCPF());
			pstm.setInt(++i, reserva.getFuncionario().getId());

			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param reserva
	 *            Recebe uma Reserva e edita seus dados do Banco de Dados
	 * 
	 */
	public void editar(EntidadeDominio entidade) {

		if(!(entidade instanceof Reserva))
			return;
		
		Reserva reserva = new Reserva();
		reserva = (Reserva) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update reservas set situacao = ?, data_inicio = ?, data_fim = ?, id_modelo = ?, ");
		sql.append("cliente_cpf = ?, id_funcionario = ? ");
		sql.append("where id = ?");

		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());

			int i = 0;

			pstm.setString(++i, reserva.getSituacao().getDescricao());

			// colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
			String dataCadastro = stf.format(reserva.getDataCadastroReserva());
			pstm.setString(++i, dataCadastro);

			// colocando formato string para armazenar no banco de dados
			String strDataFim = stf.format(reserva.getDataFim());
			pstm.setString(++i, strDataFim);

			pstm.setInt(++i, reserva.getModelo().getId());
			pstm.setString(++i, reserva.getCliente().getCPF());
			pstm.setInt(++i, reserva.getFuncionario().getId());
			pstm.setInt(++i, reserva.getId());

			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param reserva
	 *            Recebe um Reserva e exclui do Banco de dados localizando pelo
	 *            seu Id
	 */
	public void excluir(EntidadeDominio entidade) {

		if(!(entidade instanceof Reserva))
			return;
		
		Reserva reserva = new Reserva();
		reserva = (Reserva) entidade;
		
		String sql = "delete from reservas where id = ?";

		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, reserva.getId());

			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @return Retorna todos as reservas do Banco de dados em forma de List
	 *         <Reserva>
	 */
	public List<EntidadeDominio> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from reservas ");
		sql.append("order by situacao asc");

		List<EntidadeDominio> listaReservas = new ArrayList<EntidadeDominio>();

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				Reserva reservaRetorno = new Reserva();

				// se a reserva nao for a de id 99 então salva
				if (rSet.getInt("id") != 99) {

					reservaRetorno.setId(rSet.getInt("id"));
					reservaRetorno.setDataCadastroReserva(rSet.getDate("data_inicio"));
					reservaRetorno.setDataFim(rSet.getDate("data_fim"));
					reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("situacao")));

					Modelo modelo = new Modelo(rSet.getInt("id_modelo"));
					modelo = new ModeloDAO().pesquisarPorID(modelo);
					reservaRetorno.setModelo(modelo);

					Cliente cliente = new Cliente();
					cliente.setCPF(rSet.getString("cliente_cpf"));
					cliente = new ClienteDAO().pesquisarPorCPF(cliente);
					reservaRetorno.setCliente(cliente);

					Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
					funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
					reservaRetorno.setFuncionario(funcionario);

					listaReservas.add(reservaRetorno);

				} else {
					rSet.next();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaReservas;

	}

	/**
	 * 
	 * @param reserva
	 *            Recebe um reserva e localiza pelo Id no Banco de Dados
	 * @return Retorna um Objeto de Reserva
	 */
	public EntidadeDominio pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Reserva))
			return null;
		
		Reserva reserva = new Reserva();
		reserva = (Reserva) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from reservas ");
		sql.append("where id = ? ");

		Reserva reservaRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, reserva.getId());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				reservaRetorno = new Reserva();

				// se a reserva nao for a de id 99 então salva
				if (rSet.getInt("id") != 99) {

					reservaRetorno.setId(rSet.getInt("id"));
					reservaRetorno.setDataCadastroReserva(rSet.getDate("data_inicio"));
					reservaRetorno.setDataFim(rSet.getDate("data_fim"));
					reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("situacao")));

					Modelo modelo = new Modelo(rSet.getInt("id_modelo"));
					modelo = new ModeloDAO().pesquisarPorID(modelo);
					reservaRetorno.setModelo(modelo);

					Cliente cliente = new Cliente();
					cliente.setCPF(rSet.getString("cliente_cpf"));
					cliente = new ClienteDAO().pesquisarPorCPF(cliente);
					reservaRetorno.setCliente(cliente);

					Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
					funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
					reservaRetorno.setFuncionario(funcionario);

				} else {
					rSet.next();
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return reservaRetorno;

	}

	/**
	 * 
	 * @param reserva
	 *            Recebe um objeto de Reserva e localiza no Banco de Dadoos pelo
	 *            modelo do carro
	 * @return Retorna uma lista de Objeto Reserva
	 */
	public List<EntidadeDominio> pesquisarPorModelo(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Reserva))
			return null;
		
		Reserva reserva = new Reserva();
		reserva = (Reserva) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from reservas ");
		sql.append("inner join modelos on modelos.id = reservas.id_modelo ");
		sql.append("where modelos.nome like ? order by modelos.nome asc");

		List<EntidadeDominio> listaReserva = new ArrayList<EntidadeDominio>();

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, "%" + reserva.getModelo().getNome() + "%");

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Reserva reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("id"));
				reservaRetorno.setDataCadastroReserva(rSet.getDate("data_inicio"));
				reservaRetorno.setDataFim(rSet.getDate("data_fim"));
				reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("situacao")));

				Modelo modelo = new Modelo(rSet.getInt("id_modelo"));
				modelo = new ModeloDAO().pesquisarPorID(modelo);
				reservaRetorno.setModelo(modelo);

				Cliente cliente = new Cliente();
				cliente.setCPF(rSet.getString("cliente_cpf"));
				cliente = new ClienteDAO().pesquisarPorCPF(cliente);
				reservaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				reservaRetorno.setFuncionario(funcionario);

				listaReserva.add(reservaRetorno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaReserva;

	}

	/**
	 * 
	 * @param reserva
	 *            Recebe um objeto de Reserva e localiza no Banco de Dadoos pelo
	 *            situacao Ativa
	 * @return Retorna uma lista de Objeto Reserva
	 */
	public List<EntidadeDominio> pesquisarReservasAtivas() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from reservas ");
		sql.append("inner join clientes on clientes.id = reservas.cliente_cpf ");
		sql.append("where reservas.situacao = ? order by clientes.nome asc");

		List<EntidadeDominio> listaReserva = new ArrayList<EntidadeDominio>();

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, "ATIVO");

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Reserva reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("id"));
				reservaRetorno.setDataCadastroReserva(rSet.getDate("data_inicio"));
				reservaRetorno.setDataFim(rSet.getDate("data_fim"));
				reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("situacao")));

				Modelo modelo = new Modelo(rSet.getInt("id_modelo"));
				modelo = new ModeloDAO().pesquisarPorID(modelo);
				reservaRetorno.setModelo(modelo);

				Cliente cliente = new Cliente();
				cliente.setCPF(rSet.getString("cliente_cpf"));
				cliente = new ClienteDAO().pesquisarPorCPF(cliente);
				reservaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				reservaRetorno.setFuncionario(funcionario);

				if (reservaRetorno.getId() != 99) {
					listaReserva.add(reservaRetorno);
				} else {
					rSet.next();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaReserva;

	}

	/**
	 * 
	 * @param reserva
	 *            Recebe um objeto de Reserva e localiza no Banco de Dadoos pelo
	 *            CPF do Cliente
	 * @return Retorna uma lista de Objeto Reserva
	 */
	public List<EntidadeDominio> pesquisarPorCPF(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Reserva))
			return null;
		
		Reserva reserva = new Reserva();
		reserva = (Reserva) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from reservas ");
		sql.append("inner join clientes on clientes.id = reservas.cliente_cpf ");
		sql.append("where clientes.cpf = ? order by clientes.nome asc");

		List<EntidadeDominio> listaReserva = new ArrayList<EntidadeDominio>();

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, reserva.getCliente().getCPF());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Reserva reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("id"));
				reservaRetorno.setDataCadastroReserva(rSet.getDate("data_inicio"));
				reservaRetorno.setDataFim(rSet.getDate("data_fim"));
				reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("situacao")));

				Modelo modelo = new Modelo(rSet.getInt("id_modelo"));
				modelo = new ModeloDAO().pesquisarPorID(modelo);
				reservaRetorno.setModelo(modelo);

				Cliente cliente = new Cliente();
				cliente.setCPF(rSet.getString("cliente_cpf"));
				cliente = new ClienteDAO().pesquisarPorCPF(cliente);
				reservaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				reservaRetorno.setFuncionario(funcionario);

				listaReserva.add(reservaRetorno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaReserva;

	}

	/**
	 * Realiza uma pesquisa no BD pelo nome do cliente
	 */
	@Override
	public List<EntidadeDominio> pesquisarPorNome(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Reserva))
			return null;
		
		Reserva reserva = new Reserva();
		reserva = (Reserva) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from reservas ");
		sql.append("inner join clientes on clientes.id = reservas.cliente_cpf ");
		sql.append("where clientes.nome like ? order by clientes.nome asc");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, "%" + reserva.getCliente().getNome() + "%");
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				Reserva reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("id"));
				reservaRetorno.setDataCadastroReserva(rSet.getDate("data_inicio"));
				reservaRetorno.setDataFim(rSet.getDate("data_fim"));
				reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("situacao")));

				Modelo modelo = new Modelo(rSet.getInt("id_modelo"));
				modelo = new ModeloDAO().pesquisarPorID(modelo);
				reservaRetorno.setModelo(modelo);

				Cliente cliente = new Cliente();
				cliente.setCPF(rSet.getString("cliente_cpf"));
				cliente = new ClienteDAO().pesquisarPorCPF(cliente);
				reservaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				reservaRetorno.setFuncionario(funcionario);

				lista.add(reservaRetorno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;

	}

	/**
	 * Retorna todas as Reservas cadastradas no Banco de Dados em um determinado
	 * intervalo de Tempo
	 * 
	 * @return uma lista de Reserva
	 */
	public List<EntidadeDominio> pesquisarPorIntervaloData(EntidadeDominio entidade) {

		if(!(entidade instanceof Reserva))
			return null;
		
		Reserva reserva = new Reserva();
		reserva = (Reserva) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from reservas ");
		sql.append("where data_inicio between ? and ? ");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			// colocando formato string para buscar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
			String dataInicio = stf.format(reserva.getDataCadastroReserva());
			String dataFim = stf.format(reserva.getDataFim());

			pstm.setString(++i, dataInicio);
			pstm.setString(++i, dataFim);
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Reserva reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("id"));
				reservaRetorno.setDataCadastroReserva(rSet.getDate("data_inicio"));
				reservaRetorno.setDataFim(rSet.getDate("data_fim"));
				reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("situacao")));

				Modelo modelo = new Modelo(rSet.getInt("id_modelo"));
				modelo = new ModeloDAO().pesquisarPorID(modelo);
				reservaRetorno.setModelo(modelo);

				Cliente cliente = new Cliente();
				cliente.setCPF(rSet.getString("cliente_cpf"));
				cliente = new ClienteDAO().pesquisarPorCPF(cliente);
				reservaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				reservaRetorno.setFuncionario(funcionario);

				lista.add(reservaRetorno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;

	}

	/**
	 * Retorna todas as Reservas cadastradas no Banco de Dados em um determinado
	 * intervalo de Tempo
	 * 
	 * @return uma lista de Reserva
	 */
	public List<EntidadeDominio> pesquisarPorIntervaloECriterios(Reserva reserva, String sqlCriterios,
			boolean modeloIsSelecionado, boolean situacaoIsSelecionado, boolean cpfIsSelecionado) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from reservas ");
		sql.append("inner join clientes on reservas.cliente_cpf = clientes.id ");
		sql.append("inner join modelos on reservas.id_modelo = modelos.id ");
		sql.append("where reservas.data_inicio between ? and ? ");
		sql.append(sqlCriterios);

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		Reserva reservaRetorno = null;

		con = Conexao.getConnection();

		System.out.println(sql.toString());

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			// colocando formato string para buscar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
			String dataInicio = stf.format(reserva.getDataCadastroReserva());
			String dataFim = stf.format(reserva.getDataFim());

			System.out.println(dataInicio);
			System.out.println(dataFim);
			System.out.println(reserva.getCliente().getCPF());

			pstm.setDate(++i, new java.sql.Date(reserva.getDataCadastroReserva().getTime()));
			pstm.setDate(++i, new java.sql.Date(reserva.getDataFim().getTime()));
			if (modeloIsSelecionado)
				pstm.setInt(++i, reserva.getModelo().getId());
			if (cpfIsSelecionado)
				pstm.setString(++i, reserva.getCliente().getCPF());
			if (situacaoIsSelecionado) {
				pstm.setString(++i, reserva.getSituacao().getDescricao());
			}

			ResultSet rSet = pstm.executeQuery();
			System.out.println(rSet.next());
			while (rSet.next()) {
				reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("id"));
				reservaRetorno.setDataCadastroReserva(rSet.getDate("data_inicio"));
				reservaRetorno.setDataFim(rSet.getDate("data_fim"));
				reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("situacao")));

				Modelo modelo = new Modelo(rSet.getInt("id_modelo"));
				modelo = new ModeloDAO().pesquisarPorID(modelo);
				reservaRetorno.setModelo(modelo);

				Cliente cliente = new Cliente();
				cliente.setCPF(rSet.getString("cliente_cpf"));
				cliente = new ClienteDAO().pesquisarPorCPF(cliente);
				reservaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				reservaRetorno.setFuncionario(funcionario);

				lista.add(reservaRetorno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;

	}

}