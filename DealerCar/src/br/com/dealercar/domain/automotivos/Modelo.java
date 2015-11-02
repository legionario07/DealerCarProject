package br.com.dealercar.domain.automotivos;

import java.util.List;

import br.com.dealercar.domain.EntidadeDominio;

public class Modelo extends EntidadeDominio {
	
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
	
	/**
	 * 
	 * @param modelo Recebe uma String com o nome do Modelo
	 * @param lista Recebe uma Lista de Modelos
	 * @return Retorna um objeto Modelo Válido do BD
	 */
	public Modelo validaModelo(String modelo, List<Modelo> lista) {
		Modelo rModelo = new Modelo(modelo);
		
		for(Modelo m : lista) {
			if(rModelo.getNome().equals(m.getNome())){
				rModelo.setId(m.getId());
				rModelo.setFabricante(m.getFabricante());
			}
		}
		
		return rModelo;
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
