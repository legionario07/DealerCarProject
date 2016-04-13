package br.com.dealercar.domain.itensopcionais;

/**
 * Classe que representa uma Cadeirinha de bebe disponivel
 * para escolha do cliente no momento da Locação
 * @author Paulinho
 *
 */
public class CadeirinhaBebe extends Itens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pesoBebe;

	public CadeirinhaBebe() {

	}

	public CadeirinhaBebe(int codigo) {
		super(codigo);
	}

	public CadeirinhaBebe(String descricao, double valor, String marca, String numeroPatrimonio, int pesoBebe) {

		super(descricao, valor, marca, numeroPatrimonio);

		this.setPesoBebe(pesoBebe);
	}

	public int getPesoBebe() {
		return pesoBebe;
	}

	public void setPesoBebe(int pesoBebe) {
		this.pesoBebe = pesoBebe;
	}

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();

		if (this.getCodigo() != 99) {
			retorno.append(super.toString());
			retorno.append("\tPeso do Bebê: ");
			retorno.append(this.getPesoBebe());
			retorno.append("\n");
		}

		return retorno.toString();
	}

}
