package br.com.dealercar.domain;

public class BebeConforto extends Itens{
	
	private int mesesBebe;
	
	public BebeConforto() {

	}
	
	public BebeConforto(String descricao, double valor, int mesesBebe) {
		super();
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
		super.toString();
		retorno.append("\nMeses do Bebê: ");
		retorno.append(this.getMesesBebe());
		retorno.append("\n\n");
		
		return retorno.toString();
	}
}
