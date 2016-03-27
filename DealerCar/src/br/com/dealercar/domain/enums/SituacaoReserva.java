package br.com.dealercar.domain.enums;

/**
 * 
 * @author Paulinho
 * Uma reserva só pode ter tre tipos de Situação 
 * Ativo: Ela esta no tempo de vida
 * Finalizado: Quando a Reserva Vira Locação
 * Cancelado: a Reserva não tem mais utilidade
 *
 */

public enum SituacaoReserva {

	ATIVO ("Ativo".toUpperCase())
	,FINALIZADO ("Finalizado".toUpperCase())
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
