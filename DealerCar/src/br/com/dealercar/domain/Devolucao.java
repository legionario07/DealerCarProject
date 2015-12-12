package br.com.dealercar.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;

public class Devolucao extends EntidadeDominio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private Cliente cliente;
	private Date dataDevolucao;
	private Long quilometragem;
	private int qtdeDiarias;
	private Reserva reserva;
	private Funcionario funcionario;
	private List<TaxasAdicionais> taxasAdicionais = new ArrayList<TaxasAdicionais>();
	private Carro carro;
	private Retirada retirada;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Long getQuilometragem() {
		return quilometragem;
	}
	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}
	public int getQtdeDiarias() {
		return qtdeDiarias;
	}
	public void setQtdeDiarias(int qtdeDiarias) {
		this.qtdeDiarias = qtdeDiarias;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<TaxasAdicionais> getTaxasAdicionais() {
		return taxasAdicionais;
	}
	public void setTaxasAdicionais(List<TaxasAdicionais> taxasAdicionais) {
		this.taxasAdicionais = taxasAdicionais;
	}
	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	public Retirada getRetirada() {
		return retirada;
	}
	public void setRetirada(Retirada retirada) {
		this.retirada = retirada;
	}
	
	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();

		retorno.append("Id: ");
		retorno.append(this.getId());
		retorno.append(" Cliente: ");
		retorno.append(this.getCliente());
		retorno.append(" Data da Devolução: ");
		retorno.append(this.getDataDevolucao());
		retorno.append(" Carro: ");
		retorno.append(this.getCarro().getModelo().getNome());
		retorno.append(" Placa: ");
		retorno.append(this.getCarro().getPlaca());
		retorno.append(" Quilometragem: ");
		retorno.append(this.getQuilometragem());
		retorno.append(" Funcionario: ");
		retorno.append(this.getFuncionario().getNome());
		
		retorno.append(" Taxas Adicionais: \n");
		for(TaxasAdicionais t : taxasAdicionais){
			if (t!= null) {
				retorno.append(t.toString());
				retorno.append("\n");
			}
		}
		
		retorno.append(" Reserva: ");
		retorno.append(this.getReserva());
		retorno.append(" Retirada: ");
		retorno.append(this.getRetirada());
		retorno.append("\n");

		return retorno.toString();
	}
	
}

