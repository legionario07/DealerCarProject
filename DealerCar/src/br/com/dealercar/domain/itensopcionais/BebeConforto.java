package br.com.dealercar.domain.itensopcionais;

/**
 * Classe que representa um BebeConforto disponivel
 * para escolha do cliente no momento da Locação
 * @author Paulinho
 *
 */
public class BebeConforto extends Itens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mesesBebe;

	public BebeConforto() {

	}

	public BebeConforto(int codigo) {
		super(codigo);
	}

	public BebeConforto(String descricao, double valor, String marca, String numeroPatrimonio, int mesesBebe) {

		super(descricao, valor, marca, numeroPatrimonio);

		this.setMesesBebe(mesesBebe);
	}

	public int getMesesBebe() {
		return mesesBebe;
	}

	public void setMesesBebe(int mesesBebe) {
		this.mesesBebe = mesesBebe;
	}

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		if (this.getCodigo() != 99) {
			retorno.append(super.toString());
			retorno.append("\tMeses do Bebê: ");
			retorno.append(this.getMesesBebe());
			retorno.append("\n");
		}

		return retorno.toString();
	}
}
