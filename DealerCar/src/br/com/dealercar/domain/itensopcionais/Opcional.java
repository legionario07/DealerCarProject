package br.com.dealercar.domain.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe reponsavel por gerenciar os itens opcionais
 * escolhidos pelo cliente no momento da locação
 * @author Paulinho
 *
 */
public class Opcional extends EntidadeDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Itens> itens = new ArrayList<Itens>();
	private Seguro seguro = new Seguro();

	public Opcional() {
	}

	public Opcional(int id) {
		this.setId(id);
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public List<Itens> getItens() {
		return itens;
	}

	public void setItens(List<Itens> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();
		for (Itens i : itens) {
			if (i.getCodigo() != 0) {
				retorno.append(i.toString());
			}
		}

		return retorno.toString();
	}
	
	

}
