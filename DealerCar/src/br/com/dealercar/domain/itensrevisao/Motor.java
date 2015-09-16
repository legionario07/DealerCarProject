package br.com.dealercar.domain.itensrevisao;

import br.com.dealercar.domain.ItemParaVerificar;

public class Motor extends ItemParaVerificar{
	
	public Motor(int id) {
		super(id);
	}
	public Motor(String descricao) {
		super(descricao);
	}
	public Motor(int id, String descricao) {
		super(id, descricao);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
