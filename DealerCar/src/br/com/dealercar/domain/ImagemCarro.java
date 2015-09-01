package br.com.dealercar.domain;

import java.io.Serializable;
import java.util.List;

public class ImagemCarro implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2385132673888908094L;
	private int id;
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
	public String getCaminho() {
		return caminho.toLowerCase();
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho.toLowerCase();
	}
	
	public ImagemCarro validaImagemCarro(int modelo, List<Modelo> lista, List<ImagemCarro> listaImagens) {
		ImagemCarro carroUrl = new ImagemCarro();
		Modelo modeloCarro = new Modelo(modelo);
		
		for(Modelo m : lista) {
			if(m.getId()==modeloCarro.getId()) {
				carroUrl.setDescricao(m.getNome());
				break;
			}
		}
		
		
		for(ImagemCarro im : listaImagens) {
			if(im.getDescricao().toUpperCase().equals(carroUrl.getDescricao().toUpperCase())){
				carroUrl.setCaminho(im.getCaminho().toLowerCase());
				carroUrl.setId(im.getId());
			}
		}
		
		return carroUrl;
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
