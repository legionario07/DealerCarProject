package br.com.dealercar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Devolucao;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Reserva;
import br.com.dealercar.domain.Retirada;
import br.com.dealercar.domain.taxasadicionais.TaxaCombustivel;
import br.com.dealercar.domain.taxasadicionais.TaxaLavagem;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe responsavel por realizar a persinstência das Devoluções no Banco de
 * Dados
 * 
 * @author Paulinho
 *
 */
public class DevolucaoDAO extends AbstractPesquisaDAO<Devolucao> {

	/**
	 * Persiste as devoluções realizadas no sistema no BD
	 * @param devolucao
	 */
	@Override
	public void cadastrar(Devolucao devolucao) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into devolucoes ");
		sql.append("(data, quilometragem, placa, qtde_diarias, id_cliente, id_funcionario, ");
		sql.append("lavagem, combustivel, id_reserva, id_retirada, vlr_total, observacao) ");
		sql.append("values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			// colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
			String strDataDevolucao = stf.format(devolucao.getDataDevolucao());

			pstm.setString(++i, strDataDevolucao);
			pstm.setString(++i, devolucao.getQuilometragem());
			pstm.setString(++i, devolucao.getRetirada().getCarro().getPlaca());
			pstm.setInt(++i, devolucao.getQtdeDiarias());
			pstm.setInt(++i, devolucao.getRetirada().getCliente().getId());
			pstm.setInt(++i, devolucao.getFuncionario().getId());

			// verificando se a Lavagem foi cobrada
			if (devolucao.getTaxasAdicionais().getLavagem().isFoiCobrado() == false) {
				pstm.setString(++i, devolucao.getTaxasAdicionais().getLavagem().toString());
			} else {
				pstm.setString(++i, String.valueOf(devolucao.getTaxasAdicionais().getLavagem().getValor()));
			}

			// verificando se o Combustivel foi cobrado
			if (devolucao.getTaxasAdicionais().getCombustivel().isFoiCobrado() == false) {
				pstm.setString(++i, devolucao.getTaxasAdicionais().getCombustivel().toString());
			} else {
				pstm.setString(++i, String.valueOf(devolucao.getTaxasAdicionais().getCombustivel().getValor()));
			}

			// verificando se a devolucao é originado por alguma reserva
			if (devolucao.getReserva().getId() != 99 && devolucao.getReserva().getId()>0) {
				pstm.setInt(++i, devolucao.getReserva().getId());
			} else {
				pstm.setInt(++i, 0);
			}

			pstm.setInt(++i, devolucao.getRetirada().getId());
			pstm.setDouble(++i, devolucao.getValorFinal());
			if (devolucao.getObservacao() != null)
				pstm.setString(++i, devolucao.getObservacao());
			else {
				pstm.setString(++i, "");
			}

			pstm.executeUpdate();
			
			//Alterando a retirada o campo Ativo da Retirada no BD para FALSE
			devolucao.getRetirada().setEhAtivo(false);
			new RetiradaDAO().editar(devolucao.getRetirada());
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(Devolucao devolucao) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editar(Devolucao devolucao) {
		// TODO Auto-generated method stub

	}

	/**
	 * Retorna todas as devoluções cadastradas no BD
	 * @return List<Devolucao>
	 */
	@Override
	public List<Devolucao> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from devolucoes");

		List<Devolucao> lista = new ArrayList<Devolucao>();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Devolucao devolucaoRetorno = new Devolucao();

				// recebendo string do BD e armazenando em DATE
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");

				devolucaoRetorno.setId(rSet.getInt("id"));

				try {
					devolucaoRetorno.setDataDevolucao(stf.parse(rSet.getString("data")));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				devolucaoRetorno.setQuilometragem(rSet.getString("quilometragem"));
				devolucaoRetorno.setQtdeDiarias(rSet.getInt("qtde_diarias"));
				devolucaoRetorno.setObservacao(rSet.getString("observacao"));
				devolucaoRetorno.setValorFinal(rSet.getDouble("vlr_total"));

				TaxasAdicionais taxas = new TaxasAdicionais();
				TaxaCombustivel combustivel = new TaxaCombustivel();
				combustivel.setFoiCobrado(rSet.getBoolean("combustivel"));
				
				if(!rSet.getString("combustivel").equals("False")){
					combustivel.setFoiCobrado(true);
					combustivel.setValor(Double.parseDouble(rSet.getString("combustivel")));
				}

				TaxaLavagem lavagem = new TaxaLavagem();
				lavagem.setFoiCobrado(rSet.getBoolean("lavagem"));
				
				if(!rSet.getString("lavagem").equals("False")){
					lavagem.setFoiCobrado(true);
					lavagem.setValor(Double.parseDouble(rSet.getString("lavagem")));
				}

				taxas.setCombustivel(combustivel);
				taxas.setLavagem(lavagem);

				devolucaoRetorno.setTaxasAdicionais(taxas);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				devolucaoRetorno.setFuncionario(funcionario);

				Retirada retirada = new Retirada(rSet.getInt("id_retirada"));
				retirada = new RetiradaDAO().pesquisarPorID(retirada);
				devolucaoRetorno.setRetirada(retirada);

				Reserva reserva = new Reserva(rSet.getInt("id_reserva"));
				reserva = new ReservaDAO().pesquisarPorID(reserva);
				devolucaoRetorno.setReserva(reserva);
				
				lista.add(devolucaoRetorno);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;
	}

	/**
	 * Pesquisa no BD uma devolução por seu numero de ID
	 * @param devolucao
	 * @return Devolucao
	 */
	@Override
	public Devolucao pesquisarPorID(Devolucao devolucao) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from devolucoes where id = ? ");

		Devolucao devolucaoRetorno = null;

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, devolucao.getId());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				devolucaoRetorno = new Devolucao();

				// recebendo string do BD e armazenando em DATE
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");

				devolucaoRetorno.setId(rSet.getInt("id"));

				try {
					devolucaoRetorno.setDataDevolucao(stf.parse(rSet.getString("data")));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				devolucaoRetorno.setQuilometragem(rSet.getString("quilometragem"));
				devolucaoRetorno.setQtdeDiarias(rSet.getInt("qtde_diarias"));
				devolucaoRetorno.setObservacao(rSet.getString("observacao"));
				devolucaoRetorno.setValorFinal(rSet.getDouble("vlr_total"));

				TaxasAdicionais taxas = new TaxasAdicionais();
				TaxaCombustivel combustivel = new TaxaCombustivel();
				combustivel.setFoiCobrado(rSet.getBoolean("combustivel"));
				
				if(!rSet.getString("combustivel").equals("False")){
					combustivel.setFoiCobrado(true);
					combustivel.setValor(Double.parseDouble(rSet.getString("combustivel")));
				}

				TaxaLavagem lavagem = new TaxaLavagem();
				lavagem.setFoiCobrado(rSet.getBoolean("lavagem"));
				
				if(!rSet.getString("lavagem").equals("False")){
					lavagem.setFoiCobrado(true);
					lavagem.setValor(Double.parseDouble(rSet.getString("lavagem")));
				}

				taxas.setCombustivel(combustivel);
				taxas.setLavagem(lavagem);

				devolucaoRetorno.setTaxasAdicionais(taxas);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				devolucaoRetorno.setFuncionario(funcionario);

				Retirada retirada = new Retirada(rSet.getInt("id_retirada"));
				retirada = new RetiradaDAO().pesquisarPorID(retirada);
				devolucaoRetorno.setRetirada(retirada);

				Reserva reserva = new Reserva(rSet.getInt("id_reserva"));
				reserva = new ReservaDAO().pesquisarPorID(reserva);
				devolucaoRetorno.setReserva(reserva);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return devolucaoRetorno;

	}

	@Override
	public List<Devolucao> pesquisarPorNome(Devolucao devolucao) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * REaliza a pesquisa nas Devoluções de acordo com o CPF do Cliente
	 * @param devolucao
	 * @return List<Devolucao>
	 */
	public List<Devolucao> pesquisarPorCPF(Devolucao devolucao){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from devolucoes ");
		sql.append("inner join clientes on devolucoes.id_cliente = clientes.id ");
		sql.append("where clientes.cpf = ?");
		

		List<Devolucao> lista = new ArrayList<Devolucao>();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, devolucao.getRetirada().getCliente().getCPF());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Devolucao devolucaoRetorno = new Devolucao();

				// recebendo string do BD e armazenando em DATE
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");

				devolucaoRetorno.setId(rSet.getInt("id"));

				try {
					devolucaoRetorno.setDataDevolucao(stf.parse(rSet.getString("data")));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				devolucaoRetorno.setQuilometragem(rSet.getString("quilometragem"));
				devolucaoRetorno.setQtdeDiarias(rSet.getInt("qtde_diarias"));
				devolucaoRetorno.setObservacao(rSet.getString("observacao"));
				devolucaoRetorno.setValorFinal(rSet.getDouble("vlr_total"));

				TaxasAdicionais taxas = new TaxasAdicionais();
				TaxaCombustivel combustivel = new TaxaCombustivel();
				combustivel.setFoiCobrado(rSet.getBoolean("combustivel"));
				
				if(!rSet.getString("combustivel").equals("False")){
					combustivel.setFoiCobrado(true);
					combustivel.setValor(Double.parseDouble(rSet.getString("combustivel")));
				}

				TaxaLavagem lavagem = new TaxaLavagem();
				lavagem.setFoiCobrado(rSet.getBoolean("lavagem"));
				
				if(!rSet.getString("lavagem").equals("False")){
					lavagem.setFoiCobrado(true);
					lavagem.setValor(Double.parseDouble(rSet.getString("lavagem")));
				};

				taxas.setCombustivel(combustivel);
				taxas.setLavagem(lavagem);

				devolucaoRetorno.setTaxasAdicionais(taxas);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				devolucaoRetorno.setFuncionario(funcionario);

				Retirada retirada = new Retirada(rSet.getInt("id_retirada"));
				retirada = new RetiradaDAO().pesquisarPorID(retirada);
				devolucaoRetorno.setRetirada(retirada);

				Reserva reserva = new Reserva(rSet.getInt("id_reserva"));
				reserva = new ReservaDAO().pesquisarPorID(reserva);
				devolucaoRetorno.setReserva(reserva);
				
				lista.add(devolucaoRetorno);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;
	}

}
