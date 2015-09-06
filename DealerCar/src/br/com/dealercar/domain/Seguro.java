package br.com.dealercar.domain;

public class Seguro extends Itens{
	
	private String nomeSeguradora;
	private String tipoSeguro;
	
	public Seguro() {

	}

	public Seguro(String descricao, double valor, String nomeSeguradora, String tipoSeguro){
		super();
		this.setNomeSeguradora(nomeSeguradora);
		this.setTipoSeguro(tipoSeguro);
	}

	public String getTipoSeguro() {
		return tipoSeguro.toUpperCase();
	}

	public void setTipoSeguro(String tipoSeguro) {
		this.tipoSeguro = tipoSeguro.toUpperCase();
	}


	public String getNomeSeguradora() {
		return nomeSeguradora.toUpperCase();
	}


	public void setNomeSeguradora(String nomeSeguradora) {
		this.nomeSeguradora = nomeSeguradora.toUpperCase();
	}
	
	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("Código: ");
		retorno.append(this.getCodigo());
		retorno.append("\nDescrição: ");
		retorno.append(this.getDescricao());
		retorno.append("\nValor:: ");
		retorno.append(this.getValor());
		retorno.append("\nNome da Seguradora: ");
		retorno.append(this.getNomeSeguradora());
		retorno.append("\nTipo de Seguro: ");
		retorno.append(this.getTipoSeguro());
		retorno.append("\n\n");
		
		return retorno.toString();
	}
}
