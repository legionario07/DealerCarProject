package br.com.dealercar.domain.automotivos;

import java.util.List;

import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe que representa as cores dos carros
 * @author Paulinho
 *
 */
public class Cor extends EntidadeDominio {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	
	public Cor() {
		
	}
	
	public Cor(int id) {
		this.setId(id);
	}
	
	public Cor(String cor){
		this.setNome(cor);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome.trim().toUpperCase();
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
