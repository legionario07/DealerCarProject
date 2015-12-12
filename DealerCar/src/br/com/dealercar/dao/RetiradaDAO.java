package br.com.dealercar.dao;

import java.sql.Date;
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
import br.com.dealercar.enums.SituacaoType;
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
		sql.append("id_funcionario, id_itensopcionais, id_reserva) ");
		sql.append("values (?,?,?,?,?,?,?,?)");


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
			if (retirada.getReserva() == null) {
				pstm.setInt(++i, 99);
			} else {
				pstm.setInt(++i, retirada.getReserva().getId());
			}

			pstm.executeUpdate();
			
			//Alterando o carro locado na tabela Carro
			Carro carro = new Carro();
			carro.setPlaca(retirada.getCarro().getPlaca());
			carro = new CarroDAO().pesquisarPorPlaca(carro);
			carro.setSituacao(SituacaoType.Locado);
			new CarroDAO().editar(carro);
			
			pstm.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(Retirada entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editar(Retirada entidade) {
		// TODO Auto-generated method stub

	}

	/**
	 * Retorna todas as retiradas cadastradas no Banco de Dados
	 * 
	 * @return
	 */
	@Override
	public List<Retirada> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from retiradas ");

		List<Retirada> lista = new ArrayList<Retirada>();

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

				lista.add(retirada);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;

	}

	@Override
	public Retirada pesquisarPorID(Retirada retirada) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from retiradas where id = ? ");

		Retirada retiradaRetorno = null;
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, retirada.getId());
			
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				retiradaRetorno = new Retirada();
				
				retiradaRetorno.setId(rSet.getInt("id"));
				retiradaRetorno.setDataRetirada(Date.valueOf(rSet.getString("data_retirada")));
				retiradaRetorno.setDataDevolucao(Date.valueOf(rSet.getString("data_devolucao")));
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

			}
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return retiradaRetorno;

	}
}
