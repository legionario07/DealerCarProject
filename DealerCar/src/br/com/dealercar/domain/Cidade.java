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
	private Estado estado;

	public Cidade() {
		estado = new Estado();

	}

	public Cidade(int idCidade) {
		this.setId(idCidade);
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.trim();
	}



	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("Id: - ");
		retorno.append(this.getId());
		retorno.append(" - Nome: ");
		retorno.append(this.getNome());
		retorno.append("\n");
		retorno.append(this.getEstado());
		retorno.append("\n\n");

		return retorno.toString();
	}

}
