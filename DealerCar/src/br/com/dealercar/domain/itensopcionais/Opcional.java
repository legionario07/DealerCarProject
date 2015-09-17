package br.com.dealercar.domain.itensopcionais;

import br.com.dealercar.domain.EntidadeDominio;

public abstract class Opcional extends EntidadeDominio{
	
	private int codigo;
	private String descricao;
	private double valor;
	
	public Opcional() {
	}
	
	public Opcional(String descricao, double valor) {
		this.setDescricao(descricao.toUpperCase());
		this.setValor(valor);
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao.toUpperCase();
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
		retorno.append("Id: ");
		retorno.append(getCodigo());
		retorno.append(" \nDescricao: ");
		retorno.append(getDescricao());
		retorno.append(" \nValor: ");
		retorno.append(getValor());
				
		return retorno.toString();
	}

}
