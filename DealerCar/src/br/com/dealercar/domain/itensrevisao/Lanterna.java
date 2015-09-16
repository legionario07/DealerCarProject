package br.com.dealercar.domain.itensrevisao;

import br.com.dealercar.domain.ItemParaVerificar;

public class Lanterna extends ItemParaVerificar{

	public Lanterna(int id) {
		super(id);
	}
	public Lanterna(String descricao) {
		super(descricao);
	}
	public Lanterna(int id, String descricao) {
		super(id, descricao);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
