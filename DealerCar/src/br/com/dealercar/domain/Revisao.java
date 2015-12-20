package br.com.dealercar.domain;

import java.util.Date;

import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.itensrevisao.Componente;

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
	private Date dataRevisao;
	private String descricao;
	private Long quilometragem;
	private Carro carro;
	private Componente itensParaVerificar;
	private Funcionario funcionario;
	private Devolucao devolucao;
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
	public Revisao(Date dataRevisao, String descricao, 
			Long quilometragem, Carro carro) {
		
		this.setId(id);
		this.setDescricao(descricao);
		this.setQuilometragem(quilometragem);
		this.setCarro(carro);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDataRevisao() {
		return dataRevisao;
	}
	public void setDataRevisao(Date dataRevisao) {
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
	public Componente getItensParaVerificar() {
		return itensParaVerificar;
	}
	public void setItensParaVerificar(Componente itensParaVerificar) {
		this.itensParaVerificar = itensParaVerificar;
	}
	
	public Devolucao getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(Devolucao devolucao) {
		this.devolucao = devolucao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();
		retorno.append("Id: ");
		retorno.append(this.getId());
		retorno.append("\nFuncionario: ");
		retorno.append(this.getFuncionario().getNome());
		retorno.append("\nData Revisão: ");
		retorno.append(this.getDataRevisao());
		retorno.append("\nCarro: ");
		retorno.append(this.getCarro().getPlaca());
		retorno.append(" - Quilometragem: ");
		retorno.append(this.getQuilometragem());
		retorno.append(this.getItensParaVerificar());
		retorno.append("\nDescrição: ");
		retorno.append(this.getDescricao());
		
		return retorno.toString();
	}
}
