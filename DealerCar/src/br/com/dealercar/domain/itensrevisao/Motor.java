package br.com.dealercar.domain.itensrevisao;

public class Motor extends Componente{
	
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
