package br.com.dealercar.domain.taxasadicionais;

import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe responsável pelas Taxas Cobradas no momento da Devolução
 */
public class TaxasAdicionais extends EntidadeDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String descricao;
	private double valor;
	private boolean foiCobrado = false;
	private TaxaCombustivel combustivel;
	private TaxaLavagem lavagem;
	
	public TaxaCombustivel getCombustivel() {
		return combustivel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCombustivel(TaxaCombustivel combustivel) {
		this.combustivel = combustivel;
	}
	public TaxaLavagem getLavagem() {
		return lavagem;
	}
	public void setLavagem(TaxaLavagem lavagem) {
		this.lavagem = lavagem;
	}
	
	public boolean isFoiCobrado() {
		return foiCobrado;
	}
	public void setFoiCobrado(boolean foiCobrado) {
		this.foiCobrado = foiCobrado;
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
	
	@Override
	public String toString() {
		
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nValor: ");
		retorno.append(this.getValor());
		
		return retorno.toString();
		
	}
	
}
