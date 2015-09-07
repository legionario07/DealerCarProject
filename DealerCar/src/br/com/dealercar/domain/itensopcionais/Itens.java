package br.com.dealercar.domain.itensopcionais;

public class Itens extends Opcional {

	private String marca;
	private String numeroPatrimonio;

	public String getMarca() {
		return marca.toUpperCase();
	}

	public void setMarca(String marca) {
		this.marca = marca.toUpperCase();
	}

	public String getNumeroPatrimonio() {
		return numeroPatrimonio.toUpperCase();
	}

	public void setNumeroPatrimonio(String numeroPatrimonio) {
		this.numeroPatrimonio = numeroPatrimonio.toUpperCase();
	}

	public String toString() {
	
		StringBuffer retorno = new StringBuffer();
		super.toString();
		retorno.append("\nMarca: ");
		retorno.append(this.getMarca());
		retorno.append("\nNúmero Patrimônio: ");
		retorno.append(this.getNumeroPatrimonio());
		
		return retorno.toString();
	}

}
