package br.com.dealercar.domain.itensopcionais;

import br.com.dealercar.domain.EntidadeDominio;

public class Seguro extends EntidadeDominio{
	
	private int codigo;
	private String descricao;
	private double valor;
	private TipoSeguro tipoSeguro;
	
	public Seguro() {

	}
	
	public Seguro(int codigo) {
		this.setCodigo(codigo);
	}

	public Seguro(String descricao, double valor, TipoSeguro tipo){
		this.setDescricao(descricao);
		this.setValor(valor);
		this.setTipoSeguro(tipo);
	}


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

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

	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}
	
	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	
	
	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("Codigo: ");
		retorno.append(this.getCodigo());
		retorno.append("\nDescricao: ");
		retorno.append(this.getDescricao());
		retorno.append("\nValor: ");
		retorno.append(this.getValor());
		
		retorno.append("\nTipo Seguro: ");
		retorno.append(this.tipoSeguro.getNome().getDescricao());
		retorno.append("\nValor acrescido: ");
		retorno.append(this.getTipoSeguro().getValorAcrescido());
		retorno.append("\n\n");
		
		return retorno.toString();
	}
}
