package br.com.dealercar.enums;

/**
 * 
 * Classe enum que exibe as possiveis situações para o Seguro do carro
 *
 * Compreensiva: Inclui os seguintes riscos: colisão, abalroamento, capotagem ou
 * derrapagem; queda sobre o veículo de objeto externo; dano causado pela carga
 * transportada; dano causado quando o veículo estiver sendo transportado; ato
 * danoso praticado por terceiros; alagamento, enchente e inundação; ressaca,
 * vendaval, granizo e terremoto; raio; incêndio ou explosão; roubo ou furto
 * total ou parcial (partes).
 * 
 * Roubo, furto e incêndio: Cobertura mais limitada, que abrange apenas os
 * riscos de raio, incêndio, explosão e roubo ou furto total.
 */

public enum SeguroType {

	COMPREENSIVA("Compreensiva".toUpperCase()), ROUBO("Roubo, Furto E Incêndio".toUpperCase());

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
