package br.com.dealercar.domain.itensopcionais;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.enums.SeguroType;

/**
 * Classe que representa um Tipo de Seguro disponivel
 * para escolha do cliente no momento da Locação, quando o mesmo fizer a
 * escolha do seguro apropriado
 * @author Paulinho
 *
 */
public class TipoSeguro extends EntidadeDominio{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private SeguroType nome;
	private double valorAcrescido;
	
	public TipoSeguro() {
		
	}
	
	public TipoSeguro(int id) {
		this.setId(id);
	}
	
	public TipoSeguro(SeguroType nome) {
		this.setNome(nome);
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public SeguroType getNome() {
		return nome;
	}
	public void setNome(SeguroType nome) {
		this.nome = nome;
		
		if(this.getNome() == SeguroType.COMPREENSIVA){
			this.valorAcrescido = 8.00D;
		} else if(this.getNome() == SeguroType.ROUBO){
			this.valorAcrescido = 7.00D;
		} 
		
		this.setValorAcrescido(valorAcrescido);
	}
	public double getValorAcrescido() {
		return valorAcrescido;
	}
	
	/**
	 * De acordo com o tipo de Seguro escolhido será acrescido um valor
	 * Compreensiva: + 8 reais /Dia
	 * Roubo: + 7 reais /Dia
	 */
	public void setValorAcrescido(double valorAcrescido) {
		this.valorAcrescido = valorAcrescido;
	}

	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();
		retorno.append("Id: ");
		retorno.append(this.getId());
		retorno.append("\nNome: ");
		retorno.append(this.getNome().getDescricao());
		retorno.append("\nValor Acrescido: ");
		retorno.append(this.getValorAcrescido());
		
		return retorno.toString();
	}
	
}
