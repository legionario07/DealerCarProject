package br.com.dealercar.domain;

public class ImagensCarros {

	private int id;
	private String caminho;
	private String descricao;
	
	public ImagensCarros(){
		
	}
	
	public ImagensCarros(String caminho, String descricao){
		this.setDescricao(descricao);
		this.setCaminho(caminho);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
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
		retorno.append(" Descrição: ");
		retorno.append(this.getDescricao());
		retorno.append(" Caminho: ");
		retorno.append(this.getCaminho());
		retorno.append("\n");
		
		return retorno.toString();
	}
}
