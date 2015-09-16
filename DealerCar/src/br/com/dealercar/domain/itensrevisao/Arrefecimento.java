package br.com.dealercar.domain.itensrevisao;

import br.com.dealercar.domain.ItemParaVerificar;

public class Arrefecimento extends ItemParaVerificar{

	public Arrefecimento(int id) {
		super(id);
	}
	public Arrefecimento(String descricao) {
		super(descricao);
	}
	public Arrefecimento(int id, String descricao) {
		super(id, descricao);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
