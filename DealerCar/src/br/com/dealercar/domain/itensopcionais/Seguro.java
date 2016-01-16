package br.com.dealercar.domain.itensopcionais;

import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe que representa um Seguro disponivel
 * para escolha do cliente no momento da Locação
 * @author Paulinho
 *
 */
public class Seguro extends EntidadeDominio{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String descricao;
	private double valor;
	private TipoSeguro tipoSeguro;
	
	public Seguro() {
		tipoSeguro = new TipoSeguro();

	}
	
	public Seguro(int codigo) {
		this.setCodigo(codigo);
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
		this.descricao = descricao.trim();
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
		
		return retorno.toString();
	}
}
