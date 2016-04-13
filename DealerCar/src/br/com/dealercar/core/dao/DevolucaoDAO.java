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
import br.com.dealercar.core.dao.automotivos.CarroDAO;
import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.negocio.Devolucao;
import br.com.dealercar.core.negocio.Reserva;
import br.com.dealercar.core.negocio.Retirada;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.enums.SituacaoType;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;

/**
 * Classe responsavel por realizar a persinstência das Devoluções no Banco de
 * Dados
 * 
 * @author Paulinho
 *
 */
public class DevolucaoDAO extends AbstractPesquisaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	
	/**
	 * Persiste as devoluções realizadas no sistema no BD
	 * 
	 * @param devolucao
	 */
	@Override
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Devolucao))
			return;
		
		Devolucao devolucao = new Devolucao();
		devolucao = (Devolucao) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into devolucoes ");
		sql.append("(data, quilometragem, placa, qtde_diarias, id_cliente, id_funcionario, ");
		sql.append("taxas_adicionais, id_reserva, id_retirada, vlr_total, observacao, taxas_cobradas, aguardando_revisao) ");
		sql.append("values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		//recebendo a conexao
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			//colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
			String dataNascimento = stf.format(devolucao.getDataDevolucao());
			pstm.setString(++i, dataNascimento);
			
			pstm.setString(++i, devolucao.getQuilometragem());
			pstm.setString(++i, devolucao.getRetirada().getCarro().getPlaca());
			pstm.setInt(++i, devolucao.getQtdeDiarias());
			pstm.setInt(++i, devolucao.getRetirada().getCliente().getId());
			pstm.setInt(++i, devolucao.getFuncionario().getId());

			// calculando o valor em Taxas Adicionais
			Double valor = 0d;
			for (TaxasAdicionais t : devolucao.getTaxasAdicionais()) {
				valor += t.getValor();
			}
			
			pstm.setDouble(++i, valor);

			// verificando se a devolucao é originado por alguma reserva
			if (devolucao.getRetirada().getReserva().getId() != 99 && devolucao.getRetirada().getReserva().getId() > 0) {
				pstm.setInt(++i, devolucao.getRetirada().getReserva().getId());
			} else {
				pstm.setInt(++i, 99);
			}

			pstm.setInt(++i, devolucao.getRetirada().getId());
			pstm.setDouble(++i, devolucao.getValorFinal());
			pstm.setString(++i, devolucao.getObservacao());
			pstm.setString(++i, devolucao.getTaxasCobradas());
			pstm.setString(++i, String.valueOf(devolucao.isAguardaRevisao()));

			pstm.executeUpdate();

			// Alterando a retirada o campo Ativo da Retirada no BD para FALSE
			devolucao.getRetirada().setEhAtivo(false);
			//Fazendo as alterações no Banco de Dados
			new RetiradaDAO().editar(devolucao.getRetirada());
			
			//Alterando o campo Situacao do Carro para Disponivel
			devolucao.getRetirada().getCarro().setSituacao(SituacaoType.Disponivel);
			//Fazendo as alterações no Banco de Dados
			new CarroDAO().editar(devolucao.getRetirada().getCarro());

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	/**
	 * Apenas é chamado para editar a Revisão - 
	 */
	@Override
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Devolucao))
			return;
		
		Devolucao devolucao = new Devolucao();
		devolucao = (Devolucao) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update devolucoes set ");
		sql.append("aguardando_revisao = ? ");
		sql.append("where id = ?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, String.valueOf(devolucao.isAguardaRevisao()));
			pstm.setInt(2, devolucao.getId());

			pstm.executeUpdate();

		}catch(Exception e){
			e.getMessage();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * Retorna todas as devoluções cadastradas no BD
	 * 
	 * @return List<Devolucao>
	 */
	@Override
	public List<EntidadeDominio> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from devolucoes");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Devolucao devolucaoRetorno = new Devolucao();

				devolucaoRetorno.setId(rSet.getInt("id"));
				devolucaoRetorno.setDataDevolucao(rSet.getDate("data"));
				devolucaoRetorno.setQuilometragem(rSet.getString("quilometragem"));
				devolucaoRetorno.setQtdeDiarias(rSet.getInt("qtde_diarias"));
				devolucaoRetorno.setObservacao(rSet.getString("observacao"));
				devolucaoRetorno.setValorFinal(rSet.getDouble("vlr_total"));
				devolucaoRetorno.setAguardaRevisao(rSet.getBoolean("aguardando_revisao"));

				List<TaxasAdicionais> taxas = new ArrayList<TaxasAdicionais>();
				TaxasAdicionais taxa = new TaxasAdicionais();
				taxa.setValor(rSet.getDouble("taxas_adicionais"));
				taxas.add(taxa);

				devolucaoRetorno.setTaxasAdicionais(taxas);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				devolucaoRetorno.setFuncionario(funcionario);

				Retirada retirada = new Retirada(rSet.getInt("id_retirada"));
				retirada = new RetiradaDAO().pesquisarPorID(retirada);
				devolucaoRetorno.setRetirada(retirada);

				Reserva reserva = new Reserva(rSet.getInt("id_reserva"));
				reserva = (Reserva) new ReservaDAO().pesquisarPorID(reserva);
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
	 * 
	 * @param devolucao
	 * @return Devolucao
	 */
	@Override
	public Devolucao pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Devolucao))
			return null;
		
		Devolucao devolucao = new Devolucao();
		devolucao = (Devolucao) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from devolucoes where id = ? ");

		Devolucao devolucaoRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, devolucao.getId());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				devolucaoRetorno = new Devolucao();

				devolucaoRetorno.setId(rSet.getInt("id"));
				devolucaoRetorno.setDataDevolucao(rSet.getDate("data"));
				devolucaoRetorno.setQuilometragem(rSet.getString("quilometragem"));
				devolucaoRetorno.setQtdeDiarias(rSet.getInt("qtde_diarias"));
				devolucaoRetorno.setValorFinal(rSet.getDouble("vlr_total"));
				devolucaoRetorno.setObservacao(rSet.getString("observacao"));
				devolucaoRetorno.setTaxasCobradas(rSet.getString("taxas_cobradas"));
				devolucaoRetorno.setAguardaRevisao(rSet.getBoolean("aguardando_revisao"));

				List<TaxasAdicionais> taxas = new ArrayList<TaxasAdicionais>();
				TaxasAdicionais taxa = new TaxasAdicionais();
				taxa.setValor(rSet.getDouble("taxas_adicionais"));
				taxas.add(taxa);
				devolucaoRetorno.setTaxasAdicionais(taxas);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				devolucaoRetorno.setFuncionario(funcionario);

				Retirada retirada = new Retirada(rSet.getInt("id_retirada"));
				retirada = new RetiradaDAO().pesquisarPorID(retirada);
				devolucaoRetorno.setRetirada(retirada);

				Reserva reserva = new Reserva(rSet.getInt("id_reserva"));
				reserva = (Reserva) new ReservaDAO().pesquisarPorID(reserva);
				devolucaoRetorno.setReserva(reserva);

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return devolucaoRetorno;

	}

	@Override
	public List<EntidadeDominio> pesquisarPorNome(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * REaliza a pesquisa nas Devoluções de acordo com o CPF do Cliente
	 * 
	 * @param devolucao
	 * @return List<Devolucao>
	 */
	public List<EntidadeDominio> pesquisarPorCPF(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Devolucao))
			return null;
		
		Devolucao devolucao = new Devolucao();
		devolucao = (Devolucao) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from devolucoes ");
		sql.append("inner join clientes on devolucoes.id_cliente = clientes.id ");
		sql.append("where clientes.cpf = ?");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, devolucao.getRetirada().getCliente().getCPF());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Devolucao devolucaoRetorno = new Devolucao();

				devolucaoRetorno.setId(rSet.getInt("id"));
				devolucaoRetorno.setDataDevolucao(rSet.getDate("data"));
				devolucaoRetorno.setQuilometragem(rSet.getString("quilometragem"));
				devolucaoRetorno.setQtdeDiarias(rSet.getInt("qtde_diarias"));
				devolucaoRetorno.setValorFinal(rSet.getDouble("vlr_total"));
				devolucaoRetorno.setObservacao(rSet.getString("observacao"));
				devolucaoRetorno.setTaxasCobradas(rSet.getString("taxas_cobradas"));
				devolucaoRetorno.setAguardaRevisao(rSet.getBoolean("aguardando_revisao"));

				List<TaxasAdicionais> taxas = new ArrayList<TaxasAdicionais>();
				TaxasAdicionais taxa = new TaxasAdicionais();
				taxa.setValor(rSet.getDouble("taxas_adicionais"));
				taxas.add(taxa);
				devolucaoRetorno.setTaxasAdicionais(taxas);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				devolucaoRetorno.setFuncionario(funcionario);

				Retirada retirada = new Retirada(rSet.getInt("id_retirada"));
				retirada = new RetiradaDAO().pesquisarPorID(retirada);
				devolucaoRetorno.setRetirada(retirada);

				Reserva reserva = new Reserva(rSet.getInt("id_reserva"));
				reserva = (Reserva) new ReservaDAO().pesquisarPorID(reserva);
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
	 * Retorna todas as devoluções cadastradas no BD que aguardam Revisao
	 * 
	 * @return List<Devolucao>
	 */
	public List<EntidadeDominio> listarDevolucaoAguardandoRevisao() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from devolucoes ");
		sql.append("where aguardando_revisao = 'true'");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Devolucao devolucaoRetorno = new Devolucao();

				devolucaoRetorno.setId(rSet.getInt("id"));
				devolucaoRetorno.setDataDevolucao(rSet.getDate("data"));
				devolucaoRetorno.setQuilometragem(rSet.getString("quilometragem"));
				devolucaoRetorno.setQtdeDiarias(rSet.getInt("qtde_diarias"));
				devolucaoRetorno.setObservacao(rSet.getString("observacao"));
				devolucaoRetorno.setValorFinal(rSet.getDouble("vlr_total"));
				devolucaoRetorno.setAguardaRevisao(rSet.getBoolean("aguardando_revisao"));

				List<TaxasAdicionais> taxas = new ArrayList<TaxasAdicionais>();
				TaxasAdicionais taxa = new TaxasAdicionais();
				taxa.setValor(rSet.getDouble("taxas_adicionais"));
				taxas.add(taxa);

				devolucaoRetorno.setTaxasAdicionais(taxas);

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				devolucaoRetorno.setFuncionario(funcionario);

				Retirada retirada = new Retirada(rSet.getInt("id_retirada"));
				retirada = new RetiradaDAO().pesquisarPorID(retirada);
				devolucaoRetorno.setRetirada(retirada);

				Reserva reserva = new Reserva(rSet.getInt("id_reserva"));
				reserva = (Reserva) new ReservaDAO().pesquisarPorID(reserva);
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
