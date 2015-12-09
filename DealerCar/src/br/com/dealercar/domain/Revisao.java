package br.com.dealercar.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.automotivos.Carro;

/**
 * 
 * @author Paulinho
 * Classe responsável pela Revisão dos Carros.
 * Revisão obrigatório após uma Devolução e 
 * Revisão periodica de 3 em meses nos carros do estacionamento da Locadora 
 *
 */
public class Revisao extends EntidadeDominio{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String dataRevisao;
	private String descricao;
	private Long quilometragem;
	private Carro carro;
	private List<ItemParaVerificar> itensParaVerificar = new ArrayList<ItemParaVerificar>();

	public Revisao() {
		
	}
	
	/**
	 * 
	 * @param id
	 */
	public Revisao(int id) {
		this.setId(id);
	}
	/**
	 * 
	 * @param dataRevisao
	 * @param descricao
	 * @param quilometragem
	 * @param carro
	 * @param itensParaVerificar
	 */
	public Revisao(String dataRevisao, String descricao, 
			Long quilometragem, Carro carro, List<ItemParaVerificar> itensParaVerificar) {
		
		this.setId(id);
		this.setDescricao(descricao);
		this.setQuilometragem(quilometragem);
		this.setCarro(carro);
		this.setItensParaVerificar(itensParaVerificar);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataRevisao() {
		return dataRevisao;
	}
	public void setDataRevisao(String dataRevisao) {
		this.dataRevisao = dataRevisao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getQuilometragem() {
		return quilometragem;
	}
	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}
	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	public List<ItemParaVerificar> getItensParaVerificar() {
		return itensParaVerificar;
	}
	public void setItensParaVerificar(List<ItemParaVerificar> itensParaVerificar) {
		this.itensParaVerificar = itensParaVerificar;
	}
	
	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();
		retorno.append("Id: ");
		retorno.append(this.getId());
		retorno.append(" - Descrição: ");
		retorno.append(this.getDescricao());
		retorno.append(" - Data Revisão ");
		retorno.append(this.getDataRevisao());
		retorno.append(" - Quilometragem ");
		retorno.append(this.getQuilometragem());
		retorno.append(" - Carro: ");
		retorno.append(this.getCarro().getPlaca());
		for(ItemParaVerificar i : this.getItensParaVerificar()){
		retorno.append(i.toString());
		}
		//retorno.append(this.getItensParaVerificar().get(0).ge);
		return retorno.toString();
	}
}
