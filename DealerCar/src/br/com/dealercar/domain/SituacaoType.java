package br.com.dealercar.domain;

public enum SituacaoType {
	Locado ("Locado")
	,Disponivel("Disponivel")
	,Manutencao ("Manutencao");
	
	private String descricao;
	
	private SituacaoType(String descricao) {
		this.descricao = descricao;
	}


	public String getDescricao() {
		return descricao.toUpperCase();
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}
	
	
}
