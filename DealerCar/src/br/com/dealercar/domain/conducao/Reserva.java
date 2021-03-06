package br.com.dealercar.domain.conducao;

import java.util.Date;

import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.enums.SituacaoReserva;

/**
 * Classe que representa a Reserva do carro feita pelo cliente
 * @author Paulinho
 *
 */
public class Reserva extends EntidadeDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SituacaoReserva situacao;
	private Date dataCadastroReserva;
	private Date dataFim;
	private Modelo modelo;
	private Cliente cliente;
	private Funcionario funcionario;

	public Reserva() {

		 modelo = new Modelo();
		 funcionario = new Funcionario();
		 cliente = new Cliente();
	}

	/**
	 * 
	 * @param id
	 */
	public Reserva(int id) {
		this.setId(id);
	}
	
	public Reserva(Cliente cliente){
		this.setCliente(cliente);
	}

	/**
	 * 
	 * @param dataCadastroReserva
	 * @param dataFim
	 * @param modelo
	 * @param cliente
	 * @param funcionario
	 */
	public Reserva(Modelo modelo, Cliente cliente, Funcionario funcionario) {

		this.setModelo(modelo);
		this.setCliente(cliente);
		this.setFuncionario(funcionario);
	}

	public SituacaoReserva getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoReserva situacao) {
		this.situacao = situacao;
	}

	public Date getDataCadastroReserva() {
		return dataCadastroReserva;
	}

	public void setDataCadastroReserva(Date dataCadastroReserva) {

		this.dataCadastroReserva = dataCadastroReserva;

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
		retorno.append(this.getDataCadastroReserva());
		retorno.append("\nData Fim: ");
		retorno.append(this.getDataFim());
		retorno.append("\nModelo: ");
		retorno.append(this.getModelo().getNome());
		retorno.append("\nCliente: ");
		retorno.append(this.getCliente().getNome());
		retorno.append("\nTelefone Cliente: ");
		retorno.append(this.getCliente().getTelefone());
		retorno.append("\nEmail Cliente: ");
		retorno.append(this.getCliente().getEmail());
		retorno.append(this.getCliente().getEndereco());
		retorno.append("\nFuncionário Responsável pela Reserva: ");
		retorno.append(this.getFuncionario().getNome());
		retorno.append("\nSituação da Reserva: ");
		retorno.append(this.getSituacao().getDescricao());
		retorno.append("\n");

		return retorno.toString();
	}
}
