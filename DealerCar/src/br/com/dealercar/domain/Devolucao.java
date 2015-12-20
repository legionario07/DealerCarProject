package br.com.dealercar.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.dealercar.domain.itensopcionais.Itens;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;
import br.com.dealercar.util.DataUtil;

/**
 * Classe responsavel que realiza das devoluções das locações
 * @author Paulinho
 *
 */
public class Devolucao extends EntidadeDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private Date dataDevolucao;
	private String quilometragem;
	private int qtdeDiarias;
	private Reserva reserva;
	private Funcionario funcionario;
	private TaxasAdicionais taxasAdicionais;
	private Retirada retirada;
	private Double valorFinal;
	private String observacao;

	public Devolucao() {
	}
	
	public Devolucao(int id) {
		this.setId(id);
	}
	
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

	public String getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(String quilometragem) {
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

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public TaxasAdicionais getTaxasAdicionais() {
		return taxasAdicionais;
	}

	public void setTaxasAdicionais(TaxasAdicionais taxasAdicionais) {
		this.taxasAdicionais = taxasAdicionais;
	}

	public Retirada getRetirada() {
		return retirada;
	}

	public void setRetirada(Retirada retirada) {
		this.retirada = retirada;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * 
	 * @param retirada
	 *            Recebe uma Locação
	 * @return Um double com o valor dos Itens Opcionais da Locação
	 */
	public Double calcularValorItensOpcionais(Retirada retirada) {

		double valorItensAdicionais = 0;

		// Verificando se for escolhido Carro com ARcondicionado e somado o
		// valor
		if (retirada.getOpcional().getArCondicionado().getCodigo() != 99
				&& retirada.getOpcional().getArCondicionado().getDescricao() != null)
			valorItensAdicionais += retirada.getOpcional().getArCondicionado().getValor();

		// Verificando se há mais Itens Opcionais
		for (Itens i : retirada.getOpcional().getItens()) {
			if (i.getCodigo() != 99 && i.getDescricao() != null)
				valorItensAdicionais += i.getValor();
		}

		return valorItensAdicionais;

	}

	/**
	 * 
	 * @param retirada
	 *            Recebe uma Locação
	 * @return Um double com o valor das TaxasAdicionais
	 */
	public Double calcularValorTaxasAdicionais(Retirada retirada) {

		double valorTaxasAdicionais = 0;

		// Verificando se Houve Cobrança de Alguma Taxa Adicional
		if (this.getTaxasAdicionais().getCombustivel().isFoiCobrado() == true) {
			valorTaxasAdicionais += this.getTaxasAdicionais().getCombustivel().getValor();
		}
		if (this.getTaxasAdicionais().getLavagem().isFoiCobrado() == true) {
			valorTaxasAdicionais += this.getTaxasAdicionais().getLavagem().getValor();
		}
		return valorTaxasAdicionais;

	}

	/**
	 * 
	 * @param retirada
	 *            Recebe uma Locação
	 * @return Um double com o valor gasto apenas em Locação (Dias Locados *
	 *         Valor da Locação(Sem Acrescimos)
	 */
	public Double calcularValorLocacao(Retirada retirada) {

		// Recebendo o valor da Diaria do Carro
		double valorDaDiaria = retirada.getCarro().getCategoria().getValorDiaria();

		// Recebendo a quantidade de Dias de Locaçãp
		int diasLocado = DataUtil.devolverDataEmDias(retirada.getDataRetirada());

		// Se o cliente devolver o carro no mesmo dia será cobrado locação de 1
		// dia
		if (diasLocado == 0)
			diasLocado++;

		return (valorDaDiaria * diasLocado);
	}

	/**
	 * 
	 * @param retirada
	 *            Recebe uma Locação
	 * @return Um double com o valor do Tipo de Seguro escolhido
	 */
	public Double calcularValorTipoSeguro(Retirada retirada) {

		return retirada.getOpcional().getSeguro().getValor();

	}

	/**
	 * Realiza os calculos da Devolução
	 * 
	 * @param Recebe
	 *            uma Retirada
	 * @return Retorna um valor Final em formato Double
	 */
	public Double calcularValorFinal(Retirada retirada) {

		double valorFinal = 0;

		valorFinal += calcularValorLocacao(retirada);
		valorFinal += calcularValorItensOpcionais(retirada);
		valorFinal += calcularValorTaxasAdicionais(retirada);
		valorFinal += calcularValorTipoSeguro(retirada);

		return valorFinal;
	}

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		retorno.append("DADOS DO CLIENTE");
		retorno.append("\nId: ");
		retorno.append(this.getId());
		retorno.append("\tCliente: ");
		retorno.append(this.getRetirada().getCliente().getNome());
		
		retorno.append("\n\nDADOS DO CARRO");
		retorno.append("\nData da Devolução: ");
		retorno.append(sdf.format(this.getDataDevolucao()));
		retorno.append("\nCarro: ");
		retorno.append(this.getRetirada().getCarro().getModelo().getNome());
		retorno.append("\nPlaca: ");
		retorno.append(this.getRetirada().getCarro().getPlaca());
		retorno.append("\nQuilometragem: ");
		retorno.append(this.getQuilometragem());

		if (this.getTaxasAdicionais() != null) {
			retorno.append("\n\nTaxas Adicionais: ");
			retorno.append(this.getTaxasAdicionais());
		}

		retorno.append("\nId Retirada: ");
		retorno.append(this.getRetirada().getId());
		retorno.append("\n\nOpcionais: ");
		retorno.append(this.getRetirada().getOpcional().toString());
		if (this.getReserva() != null) {
			retorno.append("Reserva: ");
			retorno.append(this.getReserva());
			retorno.append("\n");
		}

		retorno.append("Funcionario: ");
		retorno.append(this.getFuncionario().getNome());
		retorno.append("\nObservação: ");
		retorno.append(this.getObservacao());
		retorno.append("\n");

		return retorno.toString();
	}

}
