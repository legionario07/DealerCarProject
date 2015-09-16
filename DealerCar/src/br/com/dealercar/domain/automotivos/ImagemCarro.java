package br.com.dealercar.domain.automotivos;

import java.io.Serializable;
import java.util.List;

import br.com.dealercar.domain.EntidadeDominio;

public class ImagemCarro extends EntidadeDominio implements Serializable {
	
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
		this.descricao = descricao.toUpperCase();
	}
	public String getCaminho() {
		return caminho.toLowerCase();
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho.toLowerCase();
	}

	/**
	 * 
	 * @param modelo Recebe um int com o Id do Modelo
	 * @param lista Recebe uma lista de Modelo
	 * @param listaImagens Recebe uma lista de ImagemCArro
	 * @return Retorna um objeto imagemCarro valido
	 */
	public ImagemCarro validaImagemCarro(int modelo, List<Modelo> lista, List<ImagemCarro> listaImagens) {
		ImagemCarro carroUrl = new ImagemCarro();
		Modelo modeloCarro = new Modelo(modelo);
		
		for(Modelo m : lista) {
			if(m.getId()==modeloCarro.getId()) {
				carroUrl.setDescricao(m.getNome());
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
		retorno.append(" Descrição: ");
		retorno.append(this.getDescricao());
		retorno.append(" Caminho: ");
		retorno.append(this.getCaminho());
		retorno.append("\n");
		
		return retorno.toString();
	}

}
