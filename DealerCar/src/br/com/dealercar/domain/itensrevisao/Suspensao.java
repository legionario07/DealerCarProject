package br.com.dealercar.domain.itensrevisao;

import br.com.dealercar.domain.ItemParaVerificar;

public class Suspensao extends ItemParaVerificar{
	
	public Suspensao(int id) {
		super(id);
	}
	public Suspensao(String descricao) {
		super(descricao);
	}
	public Suspensao(int id, String descricao) {
		super(id, descricao);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
