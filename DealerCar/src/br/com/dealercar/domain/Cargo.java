package br.com.dealercar.domain;

/**
 * Classe de Dominio responsavel por armazenar o tipo de Cargo de Trabalho do Funcionário
 * @author Paulinho
 *
 */
public class Cargo extends EntidadeDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();
		retorno.append("ID: ");
		retorno.append(this.getId());
		retorno.append("\tNome: ");
		retorno.append(this.getNome());
		retorno.append("\n");
		return retorno.toString();
	}
}
