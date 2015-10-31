package br.com.dealercar.domain;

public class Permissao {

	private int id;
	private String nivel;
	
	public Permissao(){
		
	}
	
	public Permissao(int i){
		this.setId(i);
	}
	
	public Permissao(int i, String nivel){
		this.setId(i);
		this.setNivel(nivel);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();
			
		retorno.append("Nível de Acesso: ");
		retorno.append(this.getNivel());
		
		return retorno.toString();
		
	}
	
}
