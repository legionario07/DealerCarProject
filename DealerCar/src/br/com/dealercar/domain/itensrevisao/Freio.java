package br.com.dealercar.domain.itensrevisao;

public class Freio extends Componente{
	
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
