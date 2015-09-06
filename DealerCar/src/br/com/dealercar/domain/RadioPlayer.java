package br.com.dealercar.domain;

public class RadioPlayer extends Itens{
	
	private String modelo;

	public RadioPlayer() {

	}
	
	public RadioPlayer(String descricao, double valor, String modelo) {
		super();
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
		super.toString();
		
		/*retorno.append("C�digo: ");
		retorno.append(this.getCodigo());
		retorno.append("\nDescri��o: ");
		retorno.append(this.getDescricao());
		retorno.append("\nValor:: ");
		retorno.append(this.getValor());
		retorno.append("\nMarca: ");
		retorno.append(this.getMarca());
		retorno.append("\nNumeroPatrim�nio: ");
		retorno.append(this.getNumeroPatrimonio());*/
		retorno.append("\nModelo: ");
		retorno.append(this.getModelo());
		retorno.append("\n\n");
		
		return retorno.toString();
	}
}
