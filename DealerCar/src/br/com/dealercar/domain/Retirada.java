package br.com.dealercar.domain;

import java.util.Date;

import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.itensopcionais.Opcional;

/**
 * Classe que representa a Locação
 * @author Paulinho
 *
 */
public class Retirada extends EntidadeDominio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date dataRetirada;
	private Date dataDevolucao;
	private String quilometragem;
	private Opcional opcional = new Opcional();
	private Funcionario funcionario;
	private Cliente cliente;
	private Reserva reserva;
	private Carro carro;

	public Retirada() {

	}

	/**
	 * 
	 * @param id
	 */
	public Retirada(int id) {
		this.setId(id);
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(String quilometragem2) {
		this.quilometragem = quilometragem2;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Opcional getOpcional() {
		return opcional;
	}

	public void setOpcional(Opcional opcional) {
		this.opcional = opcional;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();

		retorno.append("Id: ");
		retorno.append(this.getId());
		retorno.append("\nCliente: ");
		retorno.append(this.getCliente().getNome());
		retorno.append("\nModelo: ");
		retorno.append(this.getCarro().getModelo().getNome());
		retorno.append("\nPlaca: ");
		retorno.append(this.getCarro().getPlaca());
		retorno.append("\nQuilometragem: ");
		retorno.append(this.getQuilometragem());
		if (this.getReserva() != null) {
			retorno.append("\nId Reserva: ");
			retorno.append(this.getReserva().getId());
		}
		retorno.append("\nData retirada: ");
		retorno.append(this.getDataRetirada());
		retorno.append("\nData Devolucao: ");
		retorno.append(this.getDataDevolucao());
		retorno.append("\nFuncionario Responsavel: ");
		retorno.append(this.getFuncionario().getNome());
		retorno.append(this.getOpcional().toString());

		return retorno.toString();
	}

}
