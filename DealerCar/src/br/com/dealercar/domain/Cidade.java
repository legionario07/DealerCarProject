package br.com.dealercar.domain;

/**
 * Classe que representa as cidades dos clientes e funcionarios
 * @author Paulinho
 *
 */
public class Cidade extends EntidadeDominio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String uf;

	public Cidade() {

	}

	public Cidade(int idCidade) {
		this.setId(idCidade);
	}

	public Cidade(String nome, String uf) {
		this.nome = nome;
		this.uf = uf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		retorno.append("Id: ");
		retorno.append(this.getId());
		retorno.append("\n");
		retorno.append("Nome: ");
		retorno.append(this.getNome());
		retorno.append("\n");
		retorno.append("UF: ");
		retorno.append(this.getUf());
		retorno.append("\n\n");

		return retorno.toString();
	}

}
