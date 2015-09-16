package br.com.dealercar.domain.itensrevisao;

import br.com.dealercar.domain.ItemParaVerificar;

public class Freio extends ItemParaVerificar{
	
	public Freio(int id) {
		super(id);
	}
	public Freio(String descricao) {
		super(descricao);
	}
	public Freio(int id, String descricao) {
		super(id, descricao);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
