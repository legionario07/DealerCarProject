package br.com.dealercar.domain;

public class Cor {
	
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
		this.nome = nome;
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
