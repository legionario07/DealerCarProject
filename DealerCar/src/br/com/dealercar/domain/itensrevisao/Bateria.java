package br.com.dealercar.domain.itensrevisao;

public class Bateria extends Componente{

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
