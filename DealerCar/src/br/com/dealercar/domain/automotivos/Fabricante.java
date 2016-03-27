package br.com.dealercar.domain.automotivos;

import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe que representa o Fabricante de determinado modelo de veiculo
 * @author Paulinho
 *
 */
public class Fabricante extends EntidadeDominio {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	
	public Fabricante(){
		
	}
	
	public Fabricante(int id) {
		this.setId(id);
	}
	
	public Fabricante(String nome){
		this.setNome(nome);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.trim().toUpperCase();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
			if (obj == null)
			return false;
			if (!(obj instanceof Fabricante))
			return false;
			Fabricante other = (Fabricante) obj;
			if (getNome() == null){
			if (other.getNome() != null)
			return false;
			} else if (!getNome().equals(other.getNome()))
			return false;
			return true;
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
