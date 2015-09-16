package br.com.dealercar.domain;

public class ItemParaVerificar {
	
	private int id;
	private String descricao;
	
	
	public ItemParaVerificar() {
		
	}
	/**
	 * 
	 * @param id
	 */
	public ItemParaVerificar(int id) {
		this.setId(id);
	}
	/**
	 * 
	 * @param descricao
	 */
	public ItemParaVerificar(String descricao) {
		this.setDescricao(descricao);
	}
	/**
	 * 
	 * @param id
	 * @param descricao
	 */
	public ItemParaVerificar(int id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	@Override
	public String toString() {
	
		StringBuffer retorno = new StringBuffer();
		retorno.append("Id: ");
		retorno.append(this.getId());
		retorno.append(" - Descrição: ");
		retorno.append(this.getDescricao());
		
		return retorno.toString();
	}
}
