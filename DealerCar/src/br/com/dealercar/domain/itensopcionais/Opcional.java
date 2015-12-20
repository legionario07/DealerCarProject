package br.com.dealercar.domain.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.EntidadeDominio;

public class Opcional extends EntidadeDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private List<Itens> itens = new ArrayList<Itens>();
	private ArCondicionado arCondicionado;
	private Seguro seguro;

	public Opcional() {
	}

	public Opcional(int id) {
		this.setId(id);
	}

	public ArCondicionado getArCondicionado() {
		return arCondicionado;
	}

	public void setArCondicionado(ArCondicionado arCondicionado) {
		this.arCondicionado = arCondicionado;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		retorno.append("\nId: ");
		retorno.append(this.getId());
		retorno.append("\n");
		retorno.append(this.getSeguro().toString());
		retorno.append("\n");
		if (this.getArCondicionado().getCodigo() > 0) {
			retorno.append(this.getArCondicionado().toString());
			retorno.append("\n");
		}
		
		for (Itens i : itens) {
			if (i.getCodigo() != 0) {
				retorno.append(i.toString());
			}
		}

		return retorno.toString();
	}

}
