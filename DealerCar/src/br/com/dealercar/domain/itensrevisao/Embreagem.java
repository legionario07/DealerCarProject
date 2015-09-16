package br.com.dealercar.domain.itensrevisao;

import br.com.dealercar.domain.ItemParaVerificar;

public class Embreagem extends ItemParaVerificar{
	
	public Embreagem(int id) {
		super(id);
	}
	public Embreagem(String descricao) {
		super(descricao);
	}
	public Embreagem(int id, String descricao) {
		super(id, descricao);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
