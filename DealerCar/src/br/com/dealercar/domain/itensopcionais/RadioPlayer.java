package br.com.dealercar.domain.itensopcionais;

public class RadioPlayer extends Itens{
	
	private String modelo;

	public RadioPlayer() {

	}
	
	public RadioPlayer(int codigo){
		super(codigo);
	}
	
	public RadioPlayer(String descricao, double valor, String marca, String numeroPatrimonio, String modelo) {
		
		super(descricao, valor, marca, numeroPatrimonio);
		
		this.setModelo(modelo);
	}

	public String getModelo() {
		return modelo.toUpperCase();
	}

	public void setModelo(String modelo) {
		this.modelo = modelo.toUpperCase();
	}

	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();
		
		retorno.append(super.toString());
		
		retorno.append("\nModelo: ");
		retorno.append(this.getModelo());
		retorno.append("\n\n");
		
		return retorno.toString();
	}
}
