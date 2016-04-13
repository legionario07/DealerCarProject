package br.com.dealercar.domain.itensopcionais;

/**
 * Classe que representa um GPS disponivel
 * para escolha do cliente no momento da Locação
 * @author Paulinho
 *
 */
public class Gps extends Itens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idioma;

	public Gps() {

	}

	public Gps(int codigo) {
		super(codigo);
	}

	public Gps(String descricao, double valor, String marca, String numeroPatrimonio, String idioma) {

		super(descricao, valor, marca, numeroPatrimonio);

		this.setIdioma(idioma);
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma.trim().toUpperCase();
	}

	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();

		if (this.getCodigo() != 99) {
			retorno.append(super.toString());

			retorno.append("\tIdioma: ");
			retorno.append(this.getIdioma());
			retorno.append("\n");
		}

		return retorno.toString();
	}

}
