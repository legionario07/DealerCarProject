package br.com.dealercar.domain;

import br.com.dealercar.dao.FabricanteDAO;

public class Fabricante {
	
	private int id;
	private String nome;
	
	public Fabricante(){
		
	}
	
	public Fabricante(int id) {
		this.setId(id);
	}
	
	public Fabricante(String nome){
		this.setNome(nome);
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
	
	public Fabricante validaFabricante(int fabricante) {
		Fabricante rFabricante = new Fabricante(fabricante);
		
		FabricanteDAO fDao = new FabricanteDAO();
		rFabricante = fDao.pesquisarPorID(rFabricante);
		
		return rFabricante;
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
