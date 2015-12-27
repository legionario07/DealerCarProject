package br.com.dealercar.domain.automotivos;

import java.io.Serializable;

import br.com.dealercar.domain.EntidadeDominio;

/**
 *Classe que representa a imagem fisica do carro
 * @author Paulinho
 *
 */
public class ImagemCarro extends EntidadeDominio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2385132673888908094L;
	private String caminho;
	private String descricao;
	
	
	public ImagemCarro() {
	}
	
	public ImagemCarro(int id) {
		this.setId(id);
	}
	
	public ImagemCarro(String caminho, String descricao) {
		this.setDescricao(descricao);
		this.setCaminho(caminho);
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}
	public String getCaminho() {
		return caminho.toLowerCase();
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho.toLowerCase();
	}

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("Id: ");
		retorno.append(this.getId());
		retorno.append(" Descri��o: ");
		retorno.append(this.getDescricao());
		retorno.append(" Caminho: ");
		retorno.append(this.getCaminho());
		retorno.append("\n");
		
		return retorno.toString();
	}

}
