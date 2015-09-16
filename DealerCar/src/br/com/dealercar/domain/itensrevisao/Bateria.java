package br.com.dealercar.domain.itensrevisao;

import br.com.dealercar.domain.ItemParaVerificar;

public class Bateria extends ItemParaVerificar{

	public Bateria(int id) {
		super(id);
	}
	public Bateria(String descricao) {
		super(descricao);
	}
	public Bateria(int id, String descricao) {
		super(id, descricao);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
