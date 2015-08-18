package br.com.dealercar.domain;

public class Cidade {
	
	private int id;
	private String nome;
	private String uf;
	
	public Cidade(){
		
	}
	
	
	
	public Cidade( String nome, String uf) {
		this.nome = nome;
		this.uf = uf;
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
		this.nome = nome;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@Override
	public String toString() {
		String retorno;
		retorno = "Id: " + this.getId() + "\n";
		retorno+= "Nome: " + this.getNome() + "\n";
		retorno+= "UF: " + this.getUf() + "\n\n";
		
		return retorno;
	}
}
