package br.com.dealercar.domain.taxasadicionais;

public class TaxasAdicionais {

	private String descricao;
	private double valor;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();
		
		retorno.append(" Valor: ");
		retorno.append(this.getValor());
		retorno.append("\nDescrição: ");
		retorno.append(this.getDescricao());
		
		return retorno.toString();
	}
}
