package br.com.dealercar.domain.automotivos;

import br.com.dealercar.domain.EntidadeDominio;

public class Modelo extends EntidadeDominio {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private Fabricante fabricante;
	
	public Modelo() {

	}
	
	public Modelo(int id) {
		this.setId(id);
	}
	
	public Modelo(String nome) {
		this.setNome(nome);
	}
	
	public Modelo(String nome, Fabricante fabricante) {
		this.setNome(nome);
		this.setFabricante(fabricante);
	}
	
	public Modelo(int id, String nome, Fabricante fabricante) {
		this.setId(id);
		this.setNome(nome);
		this.setFabricante(fabricante);
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
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
		
	@Override
	public String toString() {
		StringBuffer retorno =  new StringBuffer();
		
		retorno.append("Id: ");
		retorno.append(this.getId());
		retorno.append(" Nome: ");
		retorno.append(this.getNome());
		retorno.append(" Fabricante: ");
		retorno.append(this.getFabricante().getNome());
		retorno.append("\n");
		
		return retorno.toString();
	}

}
