package br.com.dealercar.domain.itensrevisao;

public abstract class Componente {
	
	private int id;
	private String descricao;
	private boolean ok;
	
	public Componente() {
		
	}
	/**
	 * 
	 * @param id
	 */
	public Componente(int id) {
		this.setId(id);
	}
	/**
	 * 
	 * @param descricao
	 */
	public Componente(String descricao) {
		this.setDescricao(descricao);
	}
	/**
	 * 
	 * @param id
	 * @param descricao
	 */
	public Componente(int id, String descricao) {
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


	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
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
