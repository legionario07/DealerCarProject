package br.com.dealercar.enums;

/**
 * 
 * Classe enum que exibe as possiveis situa��es para o Seguro do carro
 *
 * Compreensiva: Inclui os seguintes riscos: colis�o, abalroamento, capotagem ou
 * derrapagem; queda sobre o ve�culo de objeto externo; dano causado pela carga
 * transportada; dano causado quando o ve�culo estiver sendo transportado; ato
 * danoso praticado por terceiros; alagamento, enchente e inunda��o; ressaca,
 * vendaval, granizo e terremoto; raio; inc�ndio ou explos�o; roubo ou furto
 * total ou parcial (partes).
 * 
 * Roubo, furto e inc�ndio: Cobertura mais limitada, que abrange apenas os
 * riscos de raio, inc�ndio, explos�o e roubo ou furto total.
 */

public enum SeguroType {

	COMPREENSIVA("Compreensiva".toUpperCase()), ROUBO("Roubo, Furto E Inc�ndio".toUpperCase());

	private String descricao;

	private SeguroType(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
