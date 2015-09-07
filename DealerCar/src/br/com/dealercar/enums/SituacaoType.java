package br.com.dealercar.enums;
/**
 * 
 * @author Paulinho
 * Classe enum que exibe as possiveis situa��es para o carro
 * Locado: O carro esta com o Cliente
 * Disponivel: O carro est� na locadora esperando para ser Locado
 * Manuten��o: O carro esta na oficina para algum tipo de manuten��o
 * 				seja ela preventiva ou necess�ria
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
