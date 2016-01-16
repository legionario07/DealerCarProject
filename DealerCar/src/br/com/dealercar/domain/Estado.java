package br.com.dealercar.domain;

/**
 * Classe que representa um Estado da União
 * @author Paulinho
 *
 */
public class Estado extends EntidadeDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;
	private String uf;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.trim();
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();
		
		retorno.append("ID: ");
		retorno.append(this.getId());
		retorno.append(" - Nome: ");
		retorno.append(this.getNome());
		retorno.append(" - UF: ");
		retorno.append(this.getUf());
		
		return retorno.toString();
	}
	
	
}
