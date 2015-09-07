package br.com.dealercar.enums;
/**
 * 
 * @author Paulinho
 * Classe enum que exibe as possiveis situações para o carro
 * Locado: O carro esta com o Cliente
 * Disponivel: O carro está na locadora esperando para ser Locado
 * Manutenção: O carro esta na oficina para algum tipo de manutenção
 * 				seja ela preventiva ou necessária
 */
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
