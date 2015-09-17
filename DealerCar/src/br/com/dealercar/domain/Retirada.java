package br.com.dealercar.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.itensopcionais.Opcional;

public class Retirada extends EntidadeDominio{

	private int id;
	private String dataRetirada;
	private Long quilometragem;
	private List<Opcional> listaDeOpcional = new ArrayList<Opcional>();
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
	
	/**
	 * 
	 * @param carro
	 * @param dataRetirada
	 * @param quilometragem
	 * @param listaDeOpcional
	 * @param funcionario
	 * @param cliente
	 * @param reserva
	 */
	public Retirada(Carro carro, String dataRetirada, Long quilometragem, Cliente cliente,
			Funcionario funcionario, List<Opcional> listaDeOpcional, Reserva reserva) {
		
		this.setCarro(carro);
		this.setDataRetirada(dataRetirada);
		this.setQuilometragem(quilometragem);
		this.setCliente(cliente);
		this.setFuncionario(funcionario);
		this.setListaDeOpcional(listaDeOpcional);
		this.setReserva(reserva);
		
	}
	
	/**
	 * 
	 * @param carro
	 * @param dataRetirada
	 * @param quilometragem
	 * @param cliente
	 * @param funcionario
	 */
	public Retirada(Carro carro, String dataRetirada, Long quilometragem, Cliente cliente,
			Funcionario funcionario) {
		
		this.setCarro(carro);
		this.setDataRetirada(dataRetirada);
		this.setQuilometragem(quilometragem);
		this.setCliente(cliente);
		this.setFuncionario(funcionario);
		this.setListaDeOpcional(listaDeOpcional);
		this.setReserva(reserva);
		
	}
	
	/**
	 * 
	 * @param carro
	 * @param dataRetirada
	 * @param quilometragem
	 * @param cliente
	 * @param funcionario
	 * @param listaDeOpcional
	 */
	public Retirada(Carro carro, String dataRetirada, Long quilometragem, Cliente cliente,
			Funcionario funcionario, List<Opcional> listaDeOpcional) {
		
		this.setCarro(carro);
		this.setDataRetirada(dataRetirada);
		this.setQuilometragem(quilometragem);
		this.setCliente(cliente);
		this.setFuncionario(funcionario);
		this.setListaDeOpcional(listaDeOpcional);
		this.setReserva(reserva);
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(String dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public Long getQuilometragem() {
		return quilometragem;
	}
	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}
	public List<Opcional> getListaDeOpcional() {
		return listaDeOpcional;
	}
	public void setListaDeOpcional(List<Opcional> listaDeOpcional) {
		this.listaDeOpcional = listaDeOpcional;
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
	
	
}
