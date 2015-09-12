package br.com.dealercar.domain;

import java.util.Date;

import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.enums.SituacaoReserva;

public class Reserva extends EntidadeDominio{

	private int id;
	private SituacaoReserva situacao;
	private Date dataInicio;
	private Date dataFim;
	private Modelo modelo;
	private Cliente cliente;
	private Funcionario funcionario;
	
	public Reserva() {
		
	}
	
	/**
	 * 
	 * @param id
	 */
	public Reserva(int id) {
		this.setId(id);
	}
	
	/**
	 * 
	 * @param situacao
	 * @param dataInicio
	 * @param dataFim
	 * @param modelo
	 * @param cliente
	 * @param funcionario
	 */
	public Reserva(SituacaoReserva situacao, Date dataInicio, Date dataFim,
				Modelo modelo, Cliente cliente, Funcionario funcionario) {
		
		this.setSituacao(situacao);
		this.setDataInicio(dataInicio);
		this.setDataFim(dataFim);
		this.setModelo(modelo);
		this.setCliente(cliente);
		this.setFuncionario(funcionario);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SituacaoReserva getSituacao() {
		return situacao;
	}
	public void setSituacao(SituacaoReserva situacao) {
		this.situacao = situacao;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		retorno.append("\nData Inicio: ");
		retorno.append(this.getDataInicio());
		retorno.append("\nData Fim: ");
		retorno.append(this.getDataFim());
		retorno.append("\nModelo: ");
		retorno.append(this.getModelo().getNome());
		retorno.append("\nCliente: ");
		retorno.append(this.getCliente().getNome());
		retorno.append("\nFuncionário Responvel pela Reserva: ");
		retorno.append(this.getFuncionario().getNome());
		retorno.append("\n\n");
		
		return retorno.toString();
	}
}
