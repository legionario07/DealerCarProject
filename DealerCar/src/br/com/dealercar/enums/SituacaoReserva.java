package br.com.dealercar.enums;

/**
 * 
 * @author Paulinho
 * Uma reserva s� pode ter dois tipos de Situa��o 
 * Ativo: Ela esta no tempo de vida
 * Cancelado: a Reserva n�o tem mais utilidade
 *
 */

public enum SituacaoReserva {

	ATIVO ("Ativo".toUpperCase())
	,CANCELADO ("Cancelado".toUpperCase());
	
	private String descricao;

	private SituacaoReserva(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao.toUpperCase();
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}
	
}
