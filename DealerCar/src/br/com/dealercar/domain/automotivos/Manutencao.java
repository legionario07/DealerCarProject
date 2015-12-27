package br.com.dealercar.domain.automotivos;

import java.util.Date;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.Funcionario;

/**
 * Classe reponsavel pela manutenção dos carros
 * @author Paulinho
 *
 */
public class Manutencao extends EntidadeDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Carro carro;
	private Date dataManutencao;
	private Long quilometragem;
	private String descricao;

	private Funcionario funcionario;

	public Date getDataManutencao() {
		return dataManutencao;
	}

	public void setDataManutencao(Date dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Long getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		retorno.append(" Data da Manutenção: ");
		retorno.append(this.getDataManutencao());
		retorno.append(" Carro: ");
		retorno.append(this.getCarro().getModelo().getNome());
		retorno.append(" Placa: ");
		retorno.append(this.getCarro().getPlaca());
		retorno.append(" Quilometragem: ");
		retorno.append(this.getQuilometragem());
		retorno.append(" Funcionario: ");
		retorno.append(this.getFuncionario().getNome());
		retorno.append("\n");

		return retorno.toString();
	}
}
