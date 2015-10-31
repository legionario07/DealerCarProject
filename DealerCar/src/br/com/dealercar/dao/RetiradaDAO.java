package br.com.dealercar.dao;

import java.util.List;

import br.com.dealercar.domain.Retirada;
import br.com.dealercar.domain.itensopcionais.Itens;

public class RetiradaDAO implements IDAO<Retirada>{

	@Override
	public void cadastrar(Retirada entidade) {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("insert into retirada ");
		sql.append("values(data_retirada, quilometragem, placa, id_cliente, id_funcionario, id_itensopcionais) ");
	}

	@Override
	public void excluir(Retirada entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(Retirada entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Retirada> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Retirada pesquisarPorID(Retirada entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
