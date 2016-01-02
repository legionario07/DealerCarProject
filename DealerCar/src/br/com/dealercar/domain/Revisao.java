package br.com.dealercar.domain;

import java.util.Date;

import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.itensrevisao.Componentes;

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
	private static final long serialVersionUID = -2803744370760715140L;
	/**
	 * 
	 */
	private Date dataRevisao;
	private String descricao;
	private Long quilometragem;
	private Componentes componentes;
	private Carro carro;
	private Funcionario funcionario;
	private Devolucao devolucao;
	
	public Revisao() {
		
		carro = new Carro();
		componentes = new Componentes();
		funcionario = new Funcionario();
		devolucao = new Devolucao();
		
	}
	
	/**
	 * 
	 * @param id
	 */
	public Revisao(int id) {
		this.setId(id);
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
	public Componentes getComponentes() {
		return componentes;
	}

	public void setComponentes(Componentes componentes) {
		this.componentes = componentes;
	}

	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
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
		retorno.append(this.getComponentes());
		retorno.append("\nDescrição: ");
		retorno.append(this.getDescricao());
		
		return retorno.toString();
	}
}
