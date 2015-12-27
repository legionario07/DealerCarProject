package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.CarroDAO;
import br.com.dealercar.dao.itensopcionais.OpcionalDAO;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Reserva;
import br.com.dealercar.domain.Retirada;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.itensopcionais.Opcional;
import br.com.dealercar.enums.SituacaoReserva;
import br.com.dealercar.enums.SituacaoType;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * Recebe um objeto dominio do tipo Retirada e persiste no Banco de DAdos
 */
public class RetiradaDAO implements IDAO<Retirada> {

	/**
	 * Cadastra um Objeto Retirada no Banco de Dados
	 */
	@Override
	public void cadastrar(Retirada retirada) {

		StringBuffer sql = new StringBuffer();

		sql.append("insert into retiradas ");
		sql.append("(data_retirada, data_devolucao, quilometragem, placa, id_cliente, ");
		sql.append("id_funcionario, id_itensopcionais, id_reserva, ativo) ");
		sql.append("values (?,?,?,?,?,?,?,?,?)");
		
		Connection con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			
			//colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
			String dataRetirada = stf.format(retirada.getDataRetirada());
			pstm.setString(++i, dataRetirada);
			
			String dataDevolucao = stf.format(retirada.getDataDevolucao());
			pstm.setString(++i, dataDevolucao);
			
			pstm.setString(++i, retirada.getQuilometragem());
			pstm.setString(++i, retirada.getCarro().getPlaca());
			pstm.setInt(++i, retirada.getCliente().getId());
			pstm.setInt(++i, retirada.getFuncionario().getId());
			pstm.setInt(++i, retirada.getOpcional().getId());

			// verificando se existia uma reserva
			// incluindo o id 99 para o idReserva que nao foi incluido (99 =
			// null)
			if (retirada.getReserva().getId() > 0) {
				pstm.setInt(++i, retirada.getReserva().getId());
				
				//Alterando a reserva no BD para FINALIZADO
				Reserva reserva = new Reserva();
				reserva = retirada.getReserva();
				reserva.setSituacao(SituacaoReserva.FINALIZADO);
				new ReservaDAO().editar(reserva);
				
			} else {
				pstm.setInt(++i, 99);
			}

			pstm.setString(++i, String.valueOf(retirada.isEhAtivo()));
			
			pstm.executeUpdate();

			pstm.close();
			con.close();
			
			//Alterando o carro locado no BD para LOCADO
			Carro carro = new Carro();
			carro.setPlaca(retirada.getCarro().getPlaca());
			carro = new CarroDAO().pesquisarPorPlaca(carro);
			carro.setSituacao(SituacaoType.Locado);
			new CarroDAO().editar(carro);
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(Retirada entidade) {
		// TODO Auto-generated method stub

	}

	/**
	 * Altera o campo da Retirada Ativo da Retirada para FALSE ou TRUE
	 * TRUE = O carro ainda nao foi devolvido
	 * False =  O carro já foi deolvido
	 */
	@Override
	public void editar(Retirada retirada) {

		StringBuffer sql = new StringBuffer();
		sql.append("update retiradas set ativo = ? ");
		sql.append("where retiradas.id = ?");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, String.valueOf(retirada.isEhAtivo()));
			pstm.setInt(++i, retirada.getId());
			
			pstm.executeUpdate();

			pstm.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		
	}

	/**
	 * Retorna todas as retiradas cadastradas no Banco de Dados
	 * 
	 * @return uma lista de Retirada
	 */
	@Override
	public List<Retirada> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from retiradas ");

		List<Retirada> lista = new ArrayList<Retirada>();
		
		Connection con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Retirada retirada = new Retirada();

				//recebendo string do BD e armazenando em DATE
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
				
				retirada.setId(rSet.getInt("id"));
				
				try {
					retirada.setDataRetirada(stf.parse(rSet.getString("data_retirada")));
					retirada.setDataDevolucao(stf.parse(rSet.getString("data_devolucao")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				retirada.setQuilometragem(rSet.getString("quilometragem"));

				Carro carro = new Carro(rSet.getString("placa"));
				carro = new CarroDAO().pesquisarPorPlaca(carro);
				retirada.setCarro(carro);

				Cliente cliente = new Cliente(rSet.getInt("id_cliente"));
				cliente = new ClienteDAO().pesquisarPorID(cliente);
				retirada.setCliente(cliente);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				retirada.setFuncionario(funcionario);

				Opcional opcional = new Opcional(rSet.getInt("id_itensopcionais"));
				opcional = new OpcionalDAO().pesquisarPorID(opcional);
				retirada.setOpcional(opcional);

				Reserva reserva = new Reserva(rSet.getInt("id_reserva"));
				reserva = new ReservaDAO().pesquisarPorID(reserva);
				retirada.setReserva(reserva);

				retirada.setEhAtivo(Boolean.parseBoolean(rSet.getString("ativo")));
				
				lista.add(retirada);

			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;

	}

	/**
	 * Realiza uma pesquisa no BD pelo Id da Retirada
	 * @return Retorna uma Retirada
	 */
	@Override
	public Retirada pesquisarPorID(Retirada retirada) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from retiradas where id = ? ");

		Retirada retiradaRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, retirada.getId());
			
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				retiradaRetorno = new Retirada();
				

				//recebendo string do BD e armazenando em DATE
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
				

				retiradaRetorno.setId(rSet.getInt("id"));
				
				try {
					retiradaRetorno.setDataRetirada(stf.parse(rSet.getString("data_retirada")));
					retiradaRetorno.setDataDevolucao(stf.parse(rSet.getString("data_devolucao")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				retiradaRetorno.setQuilometragem(rSet.getString("quilometragem"));

				Carro carro = new Carro(rSet.getString("placa"));
				carro = new CarroDAO().pesquisarPorPlaca(carro);
				retiradaRetorno.setCarro(carro);

				Cliente cliente = new Cliente(rSet.getInt("id_cliente"));
				cliente = new ClienteDAO().pesquisarPorID(cliente);
				retiradaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				retiradaRetorno.setFuncionario(funcionario);

				Opcional opcional = new Opcional(rSet.getInt("id_itensopcionais"));
				opcional = new OpcionalDAO().pesquisarPorID(opcional);
				retiradaRetorno.setOpcional(opcional);

				Reserva reserva = new Reserva(rSet.getInt("id_reserva"));
				reserva = new ReservaDAO().pesquisarPorID(reserva);
				retiradaRetorno.setReserva(reserva);

				retiradaRetorno.setEhAtivo(Boolean.parseBoolean(rSet.getString("ativo")));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return retiradaRetorno;

	}
	
	/**
	 * @param Cliente
	 * Realiza uma pesquisa no BD pelo CPF do cliente e situação da retirada "Ativo"
	 * @return Retorna uma lista de Retirada
	 */
	public List<Retirada> pesquisarPorCPF(Cliente cliente) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from retiradas ");
		sql.append("inner join clientes ");
		sql.append("on clientes.id = retiradas.id_cliente ");
		sql.append("where clientes.cpf = ? and retiradas.ativo = ?");

		List<Retirada> lista = new ArrayList<Retirada>();
		Retirada retiradaRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, cliente.getCPF());
			pstm.setString(2, "true");
			
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				retiradaRetorno = new Retirada();
				

				//recebendo string do BD e armazenando em DATE
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
				

				retiradaRetorno.setId(rSet.getInt("id"));
				
				try {
					retiradaRetorno.setDataRetirada(stf.parse(rSet.getString("data_retirada")));
					retiradaRetorno.setDataDevolucao(stf.parse(rSet.getString("data_devolucao")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				retiradaRetorno.setQuilometragem(rSet.getString("quilometragem"));

				Carro carro = new Carro(rSet.getString("placa"));
				carro = new CarroDAO().pesquisarPorPlaca(carro);
				retiradaRetorno.setCarro(carro);

				cliente = new Cliente(rSet.getInt("id_cliente"));
				cliente = new ClienteDAO().pesquisarPorID(cliente);
				retiradaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				retiradaRetorno.setFuncionario(funcionario);

				Opcional opcional = new Opcional(rSet.getInt("id_itensopcionais"));
				opcional = new OpcionalDAO().pesquisarPorID(opcional);
				retiradaRetorno.setOpcional(opcional);

				Reserva reserva = new Reserva(rSet.getInt("id_reserva"));
				reserva = new ReservaDAO().pesquisarPorID(reserva);
				retiradaRetorno.setReserva(reserva);

				retiradaRetorno.setEhAtivo(Boolean.parseBoolean(rSet.getString("ativo")));
				
				lista.add(retiradaRetorno);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return lista;

	}
	
	/**
	 * @param Carro
	 * Realiza uma pesquisa no BD pela placa do Carro
	 * @return Retorna uma lista de Retirada
	 */
	public List<Retirada> pesquisarPorPlaca(Carro carro) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from retiradas ");
		sql.append("inner join carros ");
		sql.append("on carros.placa = retiradas.placa ");
		sql.append("where carros.placa = ? ");

		List<Retirada> lista = new ArrayList<Retirada>();
		
		Retirada retiradaRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, carro.getPlaca());
			
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				retiradaRetorno = new Retirada();

				//recebendo string do BD e armazenando em DATE
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
				

				retiradaRetorno.setId(rSet.getInt("id"));
				
				try {
					retiradaRetorno.setDataRetirada(stf.parse(rSet.getString("data_retirada")));
					retiradaRetorno.setDataDevolucao(stf.parse(rSet.getString("data_devolucao")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				retiradaRetorno.setQuilometragem(rSet.getString("quilometragem"));

				carro = new Carro(rSet.getString("placa"));
				carro = new CarroDAO().pesquisarPorPlaca(carro);
				retiradaRetorno.setCarro(carro);

				Cliente cliente = new Cliente(rSet.getInt("id_cliente"));
				cliente = new ClienteDAO().pesquisarPorID(cliente);
				retiradaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				retiradaRetorno.setFuncionario(funcionario);

				Opcional opcional = new Opcional(rSet.getInt("id_itensopcionais"));
				opcional = new OpcionalDAO().pesquisarPorID(opcional);
				retiradaRetorno.setOpcional(opcional);

				Reserva reserva = new Reserva(rSet.getInt("id_reserva"));
				reserva = new ReservaDAO().pesquisarPorID(reserva);
				retiradaRetorno.setReserva(reserva);

				retiradaRetorno.setEhAtivo(Boolean.parseBoolean(rSet.getString("ativo")));
				
				lista.add(retiradaRetorno);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return lista;

	}
	
	/**
	 * @param Cliente
	 * Realiza uma pesquisa no BD pelo CPF do cliente e retorna apenas Retirada Ativa
	 * @return Retorna uma lista de Retirada
	 */
	public List<Retirada> pesquisarRetiradaAtivaPorCPF(Cliente cliente) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from retiradas ");
		sql.append("inner join clientes ");
		sql.append("on clientes.id = retiradas.id_cliente ");
		sql.append("where clientes.cpf = ? and retiradas.ativo = 'true'");

		List<Retirada> lista = new ArrayList<Retirada>();
		Retirada retiradaRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, cliente.getCPF());
			
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				retiradaRetorno = new Retirada();
				

				//recebendo string do BD e armazenando em DATE
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
				

				retiradaRetorno.setId(rSet.getInt("id"));
				
				try {
					retiradaRetorno.setDataRetirada(stf.parse(rSet.getString("data_retirada")));
					retiradaRetorno.setDataDevolucao(stf.parse(rSet.getString("data_devolucao")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				retiradaRetorno.setQuilometragem(rSet.getString("quilometragem"));

				Carro carro = new Carro(rSet.getString("placa"));
				carro = new CarroDAO().pesquisarPorPlaca(carro);
				retiradaRetorno.setCarro(carro);

				cliente = new Cliente(rSet.getInt("id_cliente"));
				cliente = new ClienteDAO().pesquisarPorID(cliente);
				retiradaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				retiradaRetorno.setFuncionario(funcionario);

				Opcional opcional = new Opcional(rSet.getInt("id_itensopcionais"));
				opcional = new OpcionalDAO().pesquisarPorID(opcional);
				retiradaRetorno.setOpcional(opcional);

				Reserva reserva = new Reserva(rSet.getInt("id_reserva"));
				reserva = new ReservaDAO().pesquisarPorID(reserva);
				retiradaRetorno.setReserva(reserva);

				retiradaRetorno.setEhAtivo(Boolean.parseBoolean(rSet.getString("ativo")));
				
				lista.add(retiradaRetorno);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return lista;

	}
	
}
