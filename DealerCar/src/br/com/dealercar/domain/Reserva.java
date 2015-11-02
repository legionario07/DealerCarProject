package br.com.dealercar.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.enums.SituacaoReserva;
import br.com.dealercar.util.JSFUtil;

public class Reserva extends EntidadeDominio {

	private int id;
	private SituacaoReserva situacao;
	private String dataCadastroReserva;
	private String dataFim;
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

	public String getDataCadastroReserva() {
		return dataCadastroReserva;
	}

	public void setDataCadastroReserva(String dataCadastroReserva) {

		this.dataCadastroReserva = dataCadastroReserva;

	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
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

	/**
	 * 
	 * @return Uma String com a data atual do Sistema
	 */
	public String setarDataDeCadastro() {

		// Setando a Data inicio como a data do Cadastro
		Date data = Calendar.getInstance().getTime();
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
		String dataInicio = stf.format(data);

		return dataInicio;

	}

	/**
	 * Note: Recebe duas datas em forma de String
	 * 		String dataCadastro: representa a data do cadastramento da Reserva
	 * 		String dataReserva: representa a data que o cliente deseja retirar o carro
	 * 		Se a data que o cliente deseja retirar o carro for menor que a data do cadastro
	 * 		o sistema irá invalidar a reserva.
	 * 		A dataReserva deve ser maior que a data de Cadastro da Reserva, e nunca menor ou no mesmo diqa.
	 * 		
	 * @param dataCadastro
	 * @param dataReserva
	 * @return uma inteiro com os seguintes valores:	-1 = a data de cadastro é maior que a data da Reserva
	 * 													0 = ambas das datas são iguais
	 * 													1 = a data de reserva é maior que a data de Cadastro
	 * 												
	 */
	public int compararDatas(String dataCadastro, String dataReserva) {

		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date dtCadastro = null; 

		Date dtReserva = null;
		try {
			dtCadastro = sdf.parse(dataCadastro);
			dtReserva = sdf.parse(dataReserva);
		} catch (ParseException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		int i = dtReserva.compareTo(dtCadastro);

		return i;
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
		retorno.append("\n\n");

		return retorno.toString();
	}
}
