package br.com.dealercar.domain.conducao;

import java.util.Date;

import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.itensopcionais.Opcional;

/**
 * Classe que representa a Loca��o
 * @author Paulinho
 *
 */
public class Retirada extends EntidadeDominio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dataRetirada;
	private Date dataDevolucao;
	private String quilometragem;
	private Opcional opcional;
	private Funcionario funcionario;
	private Cliente cliente;
	private Reserva reserva = new Reserva();
	private Carro carro;
	private boolean ehAtivo;

	public Retirada() {
		
		funcionario = new Funcionario();
		cliente = new Cliente();
		reserva = new Reserva();
		carro =  new Carro();
		opcional = new Opcional();
		
	}

	/**
	 * 
	 * @param id
	 */
	public Retirada(int id) {
		this.setId(id);
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

	public boolean isEhAtivo() {
		return ehAtivo;
	}

	public void setEhAtivo(boolean ehAtivo) {
		this.ehAtivo = ehAtivo;
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
		retorno.append("Ativo: ");
		retorno.append(this.isEhAtivo());

		return retorno.toString();
	}

}
