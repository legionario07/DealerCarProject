package br.com.dealercar.domain.automotivos;

import br.com.dealercar.domain.EntidadeDominio;

public class Fabricante extends EntidadeDominio {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	
	public Fabricante(){
		
	}
	
	public Fabricante(int id) {
		this.setId(id);
	}
	
	public Fabricante(String nome){
		this.setNome(nome);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}
	

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("Id: ");
		retorno.append(this.getId());
		retorno.append(" Nome: ");
		retorno.append(this.getNome());
		retorno.append("\n");
		
		return retorno.toString();
	}
}
