package br.com.dealercar.domain;

import java.util.List;

public class Cor extends EntidadeDominio {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	
	public Cor() {
		
	}
	
	public Cor(int id) {
		this.setId(id);
	}
	
	public Cor(String cor){
		this.setNome(cor);
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

	public Cor validaCor(String cor, List<Cor> lista) {
		Cor corRetorno = new Cor(cor);

		for(Cor c : lista) {
			if(corRetorno.getNome().equals(c.getNome())){
				corRetorno.setId(c.getId());
			}
		}
		
		return corRetorno;
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
