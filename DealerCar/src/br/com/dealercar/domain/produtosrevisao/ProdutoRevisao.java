package br.com.dealercar.domain.produtosrevisao;

import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe responsavel por herdar os atributos necessários 
 * dos Produtos Usados nas Revisões
 * @author Paulinho
 *
 */
public class ProdutoRevisao extends EntidadeDominio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private String marca;
	private String tipo;
	private FormaDeVenda formaDeVenda;
	private double valor;
	private int quantidade;
	
	public ProdutoRevisao() {
		this.setFormaDeVenda(new FormaDeVenda());
		
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public FormaDeVenda getFormaDeVenda() {
		return formaDeVenda;
	}
	public void setFormaDeVenda(FormaDeVenda formaDeVenda) {
		this.formaDeVenda = formaDeVenda;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public String toString() {
	
		StringBuffer retorno = new StringBuffer();
		retorno.append("ID: ");
		retorno.append(this.getId());
		retorno.append("\nDescricao: ");
		retorno.append(this.getDescricao());
		retorno.append("\nMarca: ");
		retorno.append(this.getMarca());
		retorno.append("\nTipo: ");
		retorno.append(this.getTipo());
		retorno.append(this.getFormaDeVenda().toString());
		retorno.append("\nValor: ");
		retorno.append(this.getValor());
		retorno.append("\nQuantidade em Estoque: ");
		retorno.append(this.getQuantidade());
		retorno.append("\n");
		
		return retorno.toString();
	}

}
