package br.com.dealercar.domain.itensopcionais;

public class BebeConforto extends Itens {

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
			retorno.append("\nMeses do Bebê: ");
			retorno.append(this.getMesesBebe());
			retorno.append("\n\n");
		}

		return retorno.toString();
	}
}
