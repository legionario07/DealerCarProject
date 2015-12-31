package br.com.dealercar.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.Endereco;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Reserva;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.enums.SituacaoReserva;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe responsável por persistir as Reservas no BD
 * @author Paulinho
 *
 */
public class ReservaDAO extends AbstractPesquisaDAO<Reserva> implements Serializable {

	Connection con = Conexao.getConnection();
	
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
	public void cadastrar(Reserva reserva) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into reservas ");
		sql.append("(situacao, data_inicio, data_fim, id_modelo, id_cliente, id_funcionario) ");
		sql.append("values (?, ?, ?, ?, ?, ?)");

		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			reserva.setSituacao(SituacaoReserva.ATIVO);// iniciando a Situação

			pstm.setString(++i, reserva.getSituacao().getDescricao());
			
			//colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
			String strDataCadastro = stf.format(reserva.getDataCadastroReserva());

			//colocando formato string para armazenar no banco de dados
			String strDataFim = stf.format(reserva.getDataFim());
			
			pstm.setString(++i, strDataCadastro);
			pstm.setString(++i, strDataFim);
			pstm.setInt(++i, reserva.getModelo().getId());
			pstm.setInt(++i, reserva.getCliente().getId());
			pstm.setInt(++i, reserva.getFuncionario().getId());

			pstm.executeUpdate();
			
			pstm.close();
			con.close();

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
	public void editar(Reserva reserva) {

		StringBuffer sql = new StringBuffer();
		sql.append("update reservas set situacao = ?, data_inicio = ?, data_fim = ?, id_modelo = ?, ");
		sql.append("id_cliente = ?, id_funcionario = ? ");
		sql.append("where id = ?");
		
		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());

			int i = 0;

			pstm.setString(++i, reserva.getSituacao().getDescricao());
			
			//colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
			String strDataCadastro = stf.format(reserva.getDataCadastroReserva());

			//colocando formato string para armazenar no banco de dados
			String strDataFim = stf.format(reserva.getDataFim());
			
			pstm.setString(++i, strDataCadastro);
			pstm.setString(++i, strDataFim);
			
			pstm.setInt(++i, reserva.getModelo().getId());
			pstm.setInt(++i, reserva.getCliente().getId());
			pstm.setInt(++i, reserva.getFuncionario().getId());
			pstm.setInt(++i, reserva.getId());

			pstm.executeUpdate();
			
			pstm.close();
			con.close();

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
	public void excluir(Reserva reserva) {

		String sql = "delete from reservas where id = ?";

		con = Conexao.getConnection();
		
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, reserva.getId());

			pstm.executeUpdate();
			
			pstm.close();
			con.close();

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
	public List<Reserva> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select reservas.id, reservas.situacao, reservas.data_inicio, ");
		sql.append("reservas.data_fim, reservas.id_modelo, modelos.nome, ");
		sql.append("modelos.id_fabricante, fabricantes.nome, ");
		sql.append("reservas.id_cliente, clientes.nome, clientes.telefone, clientes.celular, ");
		sql.append("clientes.email, clientes.endereco, clientes.cpf, clientes.id_cidade, cidades.nome, ");
		sql.append("reservas.id_funcionario, funcionarios.nome ");
		sql.append("from reservas inner join modelos on reservas.id_modelo = modelos.id ");
		sql.append("inner join clientes on reservas.id_cliente = clientes.id ");
		sql.append("inner join funcionarios on reservas.id_funcionario = funcionarios.id ");
		sql.append("inner join fabricantes on modelos.id_fabricante = fabricantes.id ");
		sql.append("inner join cidades on clientes.id_cidade = cidades.id ");
		sql.append("order by reservas.situacao asc");

		List<Reserva> listaReservas = new ArrayList<Reserva>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				Reserva reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("reservas.id"));
				
				//recebendo string do BD e armazenando em DATE
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
				
				try {
					reservaRetorno.setDataCadastroReserva(stf.parse(rSet.getString("reservas.data_inicio")));
					reservaRetorno.setDataFim(stf.parse(rSet.getString("reservas.data_fim")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("reservas.situacao")));

				Modelo modelo = new Modelo();
				modelo.setId(rSet.getInt("reservas.id_modelo"));
				modelo.setNome(rSet.getString("modelos.nome"));

				Fabricante fabricante = new Fabricante();
				fabricante.setId(rSet.getInt("modelos.id_fabricante"));
				fabricante.setNome(rSet.getString("fabricantes.nome"));
				modelo.setFabricante(fabricante);

				reservaRetorno.setModelo(modelo);

				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("reservas.id_cliente"));
				cliente.setNome(rSet.getString("clientes.nome"));
				cliente.setCelular(rSet.getString("clientes.celular"));
				cliente.setTelefone(rSet.getString("clientes.telefone"));
				cliente.setEmail(rSet.getString("clientes.email"));
				cliente.setCPF(rSet.getString("clientes.cpf"));

				Endereco end = new Endereco();
				// Alterando o formato de armazenamento da endereço para o Banco
				// de
				// Dados Aceitar
				String[] endereco = rSet.getString("clientes.endereco").split(",");

				if (endereco.length == 4) {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setComplemento(endereco[2].trim());
					end.setBairro(endereco[3].trim());
				} else {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setBairro(endereco[2].trim());
				}


				Cidade cidade = new Cidade();
				cidade.setId(rSet.getInt("clientes.id_cidade"));
				cidade.setNome(rSet.getString("cidades.nome"));

				end.setCidade(cidade);
				
				cliente.setEndereco(end);
				
				reservaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario();
				funcionario.setId(rSet.getInt("reservas.id_funcionario"));
				funcionario.setNome(rSet.getString("funcionarios.nome"));

				reservaRetorno.setFuncionario(funcionario);

				if (reservaRetorno.getId() != 99) {
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
	public Reserva pesquisarPorID(Reserva reserva) {

		StringBuffer sql = new StringBuffer();
		sql.append("select reservas.id, reservas.situacao, reservas.data_inicio, ");
		sql.append("reservas.data_fim, reservas.id_modelo, modelos.nome, ");
		sql.append("modelos.id_fabricante, fabricantes.nome, ");
		sql.append("reservas.id_cliente, clientes.nome, clientes.telefone, clientes.celular, ");
		sql.append("clientes.email, clientes.endereco, clientes.cpf, clientes.id_cidade, cidades.nome, ");
		sql.append("reservas.id_funcionario, funcionarios.nome ");
		sql.append("from reservas inner join modelos on reservas.id_modelo = modelos.id ");
		sql.append("inner join clientes on reservas.id_cliente = clientes.id ");
		sql.append("inner join funcionarios on reservas.id_funcionario = funcionarios.id ");
		sql.append("inner join fabricantes on modelos.id_fabricante = fabricantes.id ");
		sql.append("inner join cidades on clientes.id_cidade = cidades.id ");
		sql.append("where reservas.id = ? ");

		Reserva reservaRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, reserva.getId());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				reservaRetorno = new Reserva();

				//se a reserva nao for a de id 99 então salva
				if (reserva.getId() != 99) {

					reservaRetorno.setId(rSet.getInt("reservas.id"));
					
					//recebendo string do BD e armazenando em DATE
					SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
					
					try {
						reservaRetorno.setDataCadastroReserva(stf.parse(rSet.getString("reservas.data_inicio")));
						reservaRetorno.setDataFim(stf.parse(rSet.getString("reservas.data_fim")));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("reservas.situacao")));

					Modelo modelo = new Modelo();
					modelo.setId(rSet.getInt("reservas.id_modelo"));
					modelo.setNome(rSet.getString("modelos.nome"));

					Fabricante fabricante = new Fabricante();
					fabricante.setId(rSet.getInt("modelos.id_fabricante"));
					fabricante.setNome(rSet.getString("fabricantes.nome"));
					modelo.setFabricante(fabricante);

					reservaRetorno.setModelo(modelo);

					Cliente cliente = new Cliente();
					cliente.setId(rSet.getInt("reservas.id_cliente"));
					cliente.setNome(rSet.getString("clientes.nome"));
					cliente.setCelular(rSet.getString("clientes.celular"));
					cliente.setTelefone(rSet.getString("clientes.telefone"));
					cliente.setEmail(rSet.getString("clientes.email"));
					cliente.setCPF(rSet.getString("clientes.cpf"));

					Endereco end = new Endereco();
					// Alterando o formato de armazenamento da endereço para o
					// Banco
					// de
					// Dados Aceitar
					String[] endereco = rSet.getString("clientes.endereco").split(",");

					if (endereco.length == 4) {
						end.setRua(endereco[0].trim());
						end.setNumero(endereco[1].trim());
						end.setComplemento(endereco[2].trim());
						end.setBairro(endereco[3].trim());
					} else {
						end.setRua(endereco[0].trim());
						end.setNumero(endereco[1].trim());
						end.setBairro(endereco[2].trim());
					}


					Cidade cidade = new Cidade();
					cidade.setId(rSet.getInt("clientes.id_cidade"));
					cidade.setNome(rSet.getString("cidades.nome"));

					end.setCidade(cidade);
					
					cliente.setEndereco(end);
					
					reservaRetorno.setCliente(cliente);

					Funcionario funcionario = new Funcionario();
					funcionario.setId(rSet.getInt("reservas.id_funcionario"));
					funcionario.setNome(rSet.getString("funcionarios.nome"));

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
	public List<Reserva> pesquisarPorModelo(Reserva reserva) {

		StringBuffer sql = new StringBuffer();
		sql.append("select reservas.id, reservas.situacao, reservas.data_inicio, ");
		sql.append("reservas.data_fim, reservas.id_modelo, modelos.nome, ");
		sql.append("modelos.id_fabricante, fabricantes.nome, ");
		sql.append("reservas.id_cliente, clientes.nome, clientes.telefone, clientes.celular, ");
		sql.append("clientes.email, clientes.endereco, clientes.cpf, clientes.id_cidade, cidades.nome, ");
		sql.append("reservas.id_funcionario, funcionarios.nome ");
		sql.append("from reservas inner join modelos on reservas.id_modelo = modelos.id ");
		sql.append("inner join clientes on reservas.id_cliente = clientes.id ");
		sql.append("inner join funcionarios on reservas.id_funcionario = funcionarios.id ");
		sql.append("inner join fabricantes on modelos.id_fabricante = fabricantes.id ");
		sql.append("inner join cidades on clientes.id_cidade = cidades.id ");
		sql.append("where modelos.nome like ? order by clientes.nome asc");

		List<Reserva> listaReserva = new ArrayList<Reserva>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, "%" + reserva.getModelo().getNome() + "%");

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Reserva reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("reservas.id"));
				
				//recebendo string do BD e armazenando em DATE
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
				
				try {
					reservaRetorno.setDataCadastroReserva(stf.parse(rSet.getString("reservas.data_inicio")));
					reservaRetorno.setDataFim(stf.parse(rSet.getString("reservas.data_fim")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("reservas.situacao")));

				Modelo modelo = new Modelo();
				modelo.setId(rSet.getInt("reservas.id_modelo"));
				modelo.setNome(rSet.getString("modelos.nome"));

				Fabricante fabricante = new Fabricante();
				fabricante.setId(rSet.getInt("modelos.id_fabricante"));
				fabricante.setNome(rSet.getString("fabricantes.nome"));
				modelo.setFabricante(fabricante);

				reservaRetorno.setModelo(modelo);

				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("reservas.id_cliente"));
				cliente.setNome(rSet.getString("clientes.nome"));
				cliente.setCelular(rSet.getString("clientes.celular"));
				cliente.setTelefone(rSet.getString("clientes.telefone"));
				cliente.setEmail(rSet.getString("clientes.email"));
				cliente.setCPF(rSet.getString("clientes.cpf"));

				Endereco end = new Endereco();
				// Alterando o formato de armazenamento da endereço para o Banco
				// de
				// Dados Aceitar
				String[] endereco = rSet.getString("clientes.endereco").split(",");

				if (endereco.length == 4) {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setComplemento(endereco[2].trim());
					end.setBairro(endereco[3].trim());
				} else {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setBairro(endereco[2].trim());
				}


				Cidade cidade = new Cidade();
				cidade.setId(rSet.getInt("clientes.id_cidade"));
				cidade.setNome(rSet.getString("cidades.nome"));

				end.setCidade(cidade);
				
				cliente.setEndereco(end);
				
				reservaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario();
				funcionario.setId(rSet.getInt("reservas.id_funcionario"));
				funcionario.setNome(rSet.getString("funcionarios.nome"));

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
	public List<Reserva> pesquisarReservasAtivas() {

		StringBuffer sql = new StringBuffer();
		sql.append("select reservas.id, reservas.situacao, reservas.data_inicio, ");
		sql.append("reservas.data_fim, reservas.id_modelo, modelos.nome, ");
		sql.append("modelos.id_fabricante, fabricantes.nome, ");
		sql.append("reservas.id_cliente, clientes.nome, clientes.telefone, clientes.celular, ");
		sql.append("clientes.email, clientes.endereco, clientes.cpf, clientes.id_cidade, cidades.nome, ");
		sql.append("reservas.id_funcionario, funcionarios.nome ");
		sql.append("from reservas inner join modelos on reservas.id_modelo = modelos.id ");
		sql.append("inner join clientes on reservas.id_cliente = clientes.id ");
		sql.append("inner join funcionarios on reservas.id_funcionario = funcionarios.id ");
		sql.append("inner join fabricantes on modelos.id_fabricante = fabricantes.id ");
		sql.append("inner join cidades on clientes.id_cidade = cidades.id ");
		sql.append("where reservas.situacao = ? order by clientes.nome asc");

		List<Reserva> listaReserva = new ArrayList<Reserva>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, "ATIVO");

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Reserva reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("reservas.id"));
				
				//recebendo string do BD e armazenando em DATE
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
				
				try {
					reservaRetorno.setDataCadastroReserva(stf.parse(rSet.getString("reservas.data_inicio")));
					reservaRetorno.setDataFim(stf.parse(rSet.getString("reservas.data_fim")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("reservas.situacao")));

				Modelo modelo = new Modelo();
				modelo.setId(rSet.getInt("reservas.id_modelo"));
				modelo.setNome(rSet.getString("modelos.nome"));

				Fabricante fabricante = new Fabricante();
				fabricante.setId(rSet.getInt("modelos.id_fabricante"));
				fabricante.setNome(rSet.getString("fabricantes.nome"));
				modelo.setFabricante(fabricante);

				reservaRetorno.setModelo(modelo);

				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("reservas.id_cliente"));
				cliente.setNome(rSet.getString("clientes.nome"));
				cliente.setCelular(rSet.getString("clientes.celular"));
				cliente.setTelefone(rSet.getString("clientes.telefone"));
				cliente.setEmail(rSet.getString("clientes.email"));
				cliente.setCPF(rSet.getString("clientes.cpf"));

				Endereco end = new Endereco();
				// Alterando o formato de armazenamento da endereço para o Banco
				// de
				// Dados Aceitar
				String[] endereco = rSet.getString("clientes.endereco").split(",");

				if (endereco.length == 4) {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setComplemento(endereco[2].trim());
					end.setBairro(endereco[3].trim());
				} else {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setBairro(endereco[2].trim());
				}


				Cidade cidade = new Cidade();
				cidade.setId(rSet.getInt("clientes.id_cidade"));
				cidade.setNome(rSet.getString("cidades.nome"));

				end.setCidade(cidade);
				
				cliente.setEndereco(end);
				
				reservaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario();
				funcionario.setId(rSet.getInt("reservas.id_funcionario"));
				funcionario.setNome(rSet.getString("funcionarios.nome"));

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
	public List<Reserva> pesquisarPorCPF(Reserva reserva) {

		StringBuffer sql = new StringBuffer();
		sql.append("select reservas.id, reservas.situacao, reservas.data_inicio, ");
		sql.append("reservas.data_fim, reservas.id_modelo, modelos.nome, ");
		sql.append("modelos.id_fabricante, fabricantes.nome, ");
		sql.append("reservas.id_cliente, clientes.nome, clientes.telefone, clientes.celular, ");
		sql.append("clientes.email, clientes.endereco, clientes.cpf, clientes.id_cidade, cidades.nome, ");
		sql.append("reservas.id_funcionario, funcionarios.nome ");
		sql.append("from reservas inner join modelos on reservas.id_modelo = modelos.id ");
		sql.append("inner join clientes on reservas.id_cliente = clientes.id ");
		sql.append("inner join funcionarios on reservas.id_funcionario = funcionarios.id ");
		sql.append("inner join fabricantes on modelos.id_fabricante = fabricantes.id ");
		sql.append("inner join cidades on clientes.id_cidade = cidades.id ");
		sql.append("where clientes.cpf = ? order by clientes.nome asc");

		List<Reserva> listaReserva = new ArrayList<Reserva>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, reserva.getCliente().getCPF());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Reserva reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("reservas.id"));
				
				//recebendo string do BD e armazenando em DATE
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
				
				try {
					reservaRetorno.setDataCadastroReserva(stf.parse(rSet.getString("reservas.data_inicio")));
					reservaRetorno.setDataFim(stf.parse(rSet.getString("reservas.data_fim")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("reservas.situacao")));

				Modelo modelo = new Modelo();
				modelo.setId(rSet.getInt("reservas.id_modelo"));
				modelo.setNome(rSet.getString("modelos.nome"));

				Fabricante fabricante = new Fabricante();
				fabricante.setId(rSet.getInt("modelos.id_fabricante"));
				fabricante.setNome(rSet.getString("fabricantes.nome"));
				modelo.setFabricante(fabricante);

				reservaRetorno.setModelo(modelo);

				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("reservas.id_cliente"));
				cliente.setNome(rSet.getString("clientes.nome"));
				cliente.setCelular(rSet.getString("clientes.celular"));
				cliente.setTelefone(rSet.getString("clientes.telefone"));
				cliente.setEmail(rSet.getString("clientes.email"));
				cliente.setCPF(rSet.getString("clientes.cpf"));

				Endereco end = new Endereco();
				// Alterando o formato de armazenamento da endereço para o Banco
				// de
				// Dados Aceitar
				String[] endereco = rSet.getString("clientes.endereco").split(",");

				if (endereco.length == 4) {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setComplemento(endereco[2].trim());
					end.setBairro(endereco[3].trim());
				} else {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setBairro(endereco[2].trim());
				}


				Cidade cidade = new Cidade();
				cidade.setId(rSet.getInt("clientes.id_cidade"));
				cidade.setNome(rSet.getString("cidades.nome"));
				
				end.setCidade(cidade);
				
				cliente.setEndereco(end);

				reservaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario();
				funcionario.setId(rSet.getInt("reservas.id_funcionario"));
				funcionario.setNome(rSet.getString("funcionarios.nome"));

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
	public List<Reserva> pesquisarPorNome(Reserva reserva) {
		
		StringBuffer  sql = new StringBuffer();
		sql.append("select reservas.id, reservas.situacao, reservas.data_inicio, ");
		sql.append("reservas.data_fim, reservas.id_modelo, modelos.nome, ");
		sql.append("modelos.id_fabricante, fabricantes.nome, ");
		sql.append("reservas.id_cliente, clientes.nome, clientes.telefone, clientes.celular, ");
		sql.append("clientes.email, clientes.endereco, clientes.cpf, clientes.id_cidade, cidades.nome, ");
		sql.append("reservas.id_funcionario, funcionarios.nome ");
		sql.append("from reservas inner join modelos on reservas.id_modelo = modelos.id ");
		sql.append("inner join clientes on reservas.id_cliente = clientes.id ");
		sql.append("inner join funcionarios on reservas.id_funcionario = funcionarios.id ");
		sql.append("inner join fabricantes on modelos.id_fabricante = fabricantes.id ");
		sql.append("inner join cidades on clientes.id_cidade = cidades.id ");
		sql.append("where clientes.nome = ? order by clientes.nome asc");
		
		List<Reserva> lista = new ArrayList<Reserva>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, reserva.getCliente().getNome());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()){
				
				Reserva reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("reservas.id"));
				
				//recebendo string do BD e armazenando em DATE
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
				
				try {
					reservaRetorno.setDataCadastroReserva(stf.parse(rSet.getString("reservas.data_inicio")));
					reservaRetorno.setDataFim(stf.parse(rSet.getString("reservas.data_fim")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				reservaRetorno.setSituacao(SituacaoReserva.valueOf(rSet.getString("reservas.situacao")));

				Modelo modelo = new Modelo();
				modelo.setId(rSet.getInt("reservas.id_modelo"));
				modelo.setNome(rSet.getString("modelos.nome"));

				Fabricante fabricante = new Fabricante();
				fabricante.setId(rSet.getInt("modelos.id_fabricante"));
				fabricante.setNome(rSet.getString("fabricantes.nome"));
				modelo.setFabricante(fabricante);

				reservaRetorno.setModelo(modelo);

				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("reservas.id_cliente"));
				cliente.setNome(rSet.getString("clientes.nome"));
				cliente.setCelular(rSet.getString("clientes.celular"));
				cliente.setTelefone(rSet.getString("clientes.telefone"));
				cliente.setEmail(rSet.getString("clientes.email"));
				cliente.setCPF(rSet.getString("clientes.cpf"));

				Endereco end = new Endereco();
				// Alterando o formato de armazenamento da endereço para o Banco
				// de
				// Dados Aceitar
				String[] endereco = rSet.getString("clientes.endereco").split(",");

				if (endereco.length == 4) {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setComplemento(endereco[2].trim());
					end.setBairro(endereco[3].trim());
				} else {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setBairro(endereco[2].trim());
				}


				Cidade cidade = new Cidade();
				cidade.setId(rSet.getInt("clientes.id_cidade"));
				cidade.setNome(rSet.getString("cidades.nome"));
				
				end.setCidade(cidade);
				
				cliente.setEndereco(end);

				reservaRetorno.setCliente(cliente);

				Funcionario funcionario = new Funcionario();
				funcionario.setId(rSet.getInt("reservas.id_funcionario"));
				funcionario.setNome(rSet.getString("funcionarios.nome"));

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