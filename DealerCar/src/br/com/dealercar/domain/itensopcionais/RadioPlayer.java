package br.com.dealercar.domain.itensopcionais;

/**
 * Classe que representa um RAdio Player disponivel para escolha do cliente no
 * momento da Loca��o
 * 
 * @author Paulinho
 *
 */
public class RadioPlayer extends Itens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String modelo;

	public RadioPlayer() {

	}

	public RadioPlayer(int codigo) {
		super(codigo);
	}

	public RadioPlayer(String descricao, double valor, String marca, String numeroPatrimonio, String modelo) {

		super(descricao, valor, marca, numeroPatrimonio);

		this.setModelo(modelo);
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo.toUpperCase();
	}

	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();

		if (this.getCodigo() != 99) {
			retorno.append(super.toString());

			retorno.append("\tModelo: ");
			retorno.append(this.getModelo());
			retorno.append("\n");
		}

		return retorno.toString();
	}
}
