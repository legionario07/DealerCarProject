package br.com.dealercar.domain.conducao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.Itens;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;

/**
 * Classe responsavel que realiza das devolu��es das loca��es
 * 
 * @author Paulinho
 *
 */
public class Devolucao extends EntidadeDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date dataDevolucao;
	private String quilometragem;
	private int qtdeDiarias;
	private Reserva reserva;
	private Funcionario funcionario;
	private List<TaxasAdicionais> taxasAdicionais = new ArrayList<TaxasAdicionais>();
	private Retirada retirada;
	private Double valorFinal;
	private String observacao;
	private String taxasCobradas;
	private boolean aguardaRevisao;

	public Devolucao() {
		funcionario = new Funcionario();
		retirada = new Retirada();

		reserva = new Reserva();
		setAguardaRevisao(true);
	}

	public Devolucao(int id) {
		this.setId(id);
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
		this.quilometragem = quilometragem.trim();
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

	public String getTaxasCobradas() {
		return taxasCobradas;
	}

	public void setTaxasCobradas(String taxasCobradas) {
		this.taxasCobradas = taxasCobradas;
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

	public List<TaxasAdicionais> getTaxasAdicionais() {
		return taxasAdicionais;
	}

	public void setTaxasAdicionais(List<TaxasAdicionais> taxasAdicionais) {
		this.taxasAdicionais = taxasAdicionais;
	}

	public Retirada getRetirada() {
		return retirada;
	}

	public void setRetirada(Retirada retirada) {
		this.retirada = retirada;
	}

	public boolean isAguardaRevisao() {
		return aguardaRevisao;
	}

	public void setAguardaRevisao(boolean aguardaRevisao) {
		this.aguardaRevisao = aguardaRevisao;
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
	 *            Recebe uma Loca��o
	 * @return Um double com o valor dos Itens Opcionais da Loca��o
	 */
	public Double calcularValorItensOpcionais(Devolucao devolucao) {

		double valorItensAdicionais = 0;

		
		if (devolucao.getRetirada().getOpcional()!=null) {
			// Verificando se h� mais Itens Opcionais
			for (Itens i : devolucao.getRetirada().getOpcional().getItens()) {
				if (i.getCodigo() != 99 && i.getDescricao() != null)
					valorItensAdicionais += i.getValor();
			}
		}

		return valorItensAdicionais;

	}

	/**
	 * 
	 * @param Lista
	 *            de TaxasAdicionais Recebe uma Loca��o
	 * @return Um double com o valor das TaxasAdicionais
	 */
	public Double calcularValorTaxasAdicionais(Devolucao devolucao, List<EntidadeDominio> taxas) {

		double valorTaxasAdicionais = 0;

		List<TaxasAdicionais> lista = new ArrayList<TaxasAdicionais>();
		lista = devolucao.getTaxasAdicionais();
		// Verificando se Houve Cobran�a de Alguma Taxa Adicional
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).isFoiCobrado() == true) {
				for (int b = 0; b < taxas.size(); b++) {
					if (lista.get(i).getDescricao().equals(((TaxasAdicionais) taxas.get(b)).getDescricao()))
						valorTaxasAdicionais += ((TaxasAdicionais) taxas.get(b)).getValor();
				}
			}
		}

		return valorTaxasAdicionais;

	}

	/**
	 * 
	 * @param retirada
	 *            Recebe uma Loca��o
	 * @return Um double com o valor gasto apenas em Loca��o (Dias Locados *
	 *         Valor da Loca��o(Sem Acrescimos)
	 */
	public Double calcularValorLocacao(Devolucao devolucao) {

		// Recebendo o valor da Diaria do Carro
		double valorDaDiaria = devolucao.getRetirada().getCarro().getCategoria().getValorDiaria();

		// Recebendo a quantidade de Dias de Loca��p
		int diasLocado = DataUtil.devolverDataEmDias(devolucao.getRetirada().getDataRetirada());

		// Se o cliente devolver o carro no mesmo dia ser� cobrado loca��o de 1
		// dia
		if (diasLocado == 0)
			diasLocado++;

		return (valorDaDiaria * diasLocado);
	}

	/**
	 * 
	 * @param retirada
	 *            Recebe uma Loca��o
	 * @return Um double com o valor do Tipo de Seguro escolhido
	 */
	public Double calcularValorTipoSeguro(Devolucao devolucao) {

		return devolucao.getRetirada().getOpcional().getSeguro().getValor() +
				devolucao.getRetirada().getOpcional().getSeguro().getTipoSeguro().getValorAcrescido();

	}

	/**
	 * Realiza os calculos da Devolu��o
	 * 
	 * @param Recebe
	 *            uma Retirada
	 * @return Retorna um valor Final em formato Double
	 */
	public Double calcularValorFinal(Devolucao devolucao, List<EntidadeDominio> taxas) {

		double valorFinal = 0;

		valorFinal += calcularValorLocacao(devolucao);
		valorFinal += calcularValorItensOpcionais(devolucao);
		valorFinal += calcularValorTaxasAdicionais(devolucao, taxas);
		valorFinal += calcularValorTipoSeguro(devolucao);

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
		retorno.append("\nData da Devolu��o: ");
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
		if (this.getReserva() != null && this.getReserva().getId()!=99 && this.getReserva().getId() != 0) {
			retorno.append("Reserva: ");
			retorno.append(this.getReserva());
			retorno.append("\n");
		}

		retorno.append("Funcionario: ");
		retorno.append(this.getFuncionario().getNome());
		retorno.append("\nObserva��o: ");
		retorno.append(this.getObservacao());
		retorno.append("\nTaxas Cobradas: ");
		retorno.append(this.getTaxasCobradas());
		retorno.append("\n");

		return retorno.toString();
	}

}
