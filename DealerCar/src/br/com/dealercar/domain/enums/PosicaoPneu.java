package br.com.dealercar.domain.enums;

/**
 * 
 * @author Paulinho
 *	Enum que lista as respectivas posições dos pneus de um carro
 */
public enum PosicaoPneu {
	
	DIANTEIRO_ESQUERDO ("DIANTEIRO ESQUERDO"),
	DIANTEIRO_DIREITO ("DIANTEIRO DIREITO"),
	TRASEIRO_ESQUERDO ("TRASEIRO ESQUERDO"),
	TRASEIRO_DIREITO ("TRASEIRO DIREITO"),
	ESTEPE ("ESTEPE");

	private String descricao;
	
	private PosicaoPneu(String descricao) {
		this.descricao = descricao.toUpperCase();
	}

	public String getDescricao() {
		return descricao.toUpperCase();
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}
	
	
}
