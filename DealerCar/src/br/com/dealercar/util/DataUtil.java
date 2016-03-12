package br.com.dealercar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtil {

	/**
	 * Recebe duas data e realiza a comparação
	 * 
	 * @param Date
	 *            DataCadastro
	 * @param Date
	 *            DataDEstino
	 * @return uma inteiro com os seguintes valores: -1 = a data de cadastro é
	 *         maior que a dataDestino 0 = ambas das datas são iguais 1 = a
	 *         dataDestno é maior que a data de Cadastro
	 */
	public static int compararDatas(Date dataCadastro, Date dataDestino) {

		int i = dataDestino.compareTo(dataCadastro);

		return i;
	}

	/**
	 * 
	 * @return Uma Date com a data atual do Sistema
	 */

	public static Date pegarDataAtualDoSistema() {

		// Pegando a data atual do sistema
		Date data = Calendar.getInstance().getTime();
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
		String strDataAtual = stf.format(data);

		try {
			data = stf.parse(strDataAtual);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * @param recebe
	 *            uma Date com a Idade e devolve um int com a idade em anos
	 * @return um inteiro com o valor em Ano
	 */
	public static int devolverDataEmAnos(Date idade) {
		Date dataAtual = DataUtil.pegarDataAtualDoSistema();

		long diferenca = dataAtual.getTime() - idade.getTime();

		return (int) (((((diferenca / 1000) / 60) / 60) / 24) / 365);

	}

	/**
	 * @param recebe
	 *            uma Date e devolve um int com a diferença em dias
	 * @return um inteiro com o valor em Dias
	 */
	public static int devolverDataEmDias(Date dataAnterior) {
		Date dataAtual = DataUtil.pegarDataAtualDoSistema();

		long diferenca = dataAtual.getTime() - dataAnterior.getTime();

		return (int) ((((diferenca / 1000) / 60) / 60) / 24);

	}

	/**
	 * Retorna o primeiro dia do mes de uma data Passada
	 * 
	 * @param ano
	 *            String com o Ano
	 * @param mes
	 *            String com o Mes - de 0(Janeiro) a 11(Dezembro)
	 * @param dia
	 * @return Um Date com o Primeiro dia do Mes
	 */
	public static Date getPrimeiroDiaDoMesAtual(String ano, String mes, String dia) {
		Calendar c = Calendar.getInstance();

		c.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		d = c.getTime();

		String data = sdf.format(d.getTime());
		try {
			return sdf.parse(data);
		} catch (ParseException e) {
			return null;
		}

	}

	/**
	 * Retorna o ultimo dia do mes de uma data Passada
	 * 
	 * @param ano
	 *            String com o Ano
	 * @param mes
	 *            String com o Mes - de 0(Janeiro) a 11(Dezembro)
	 * @param dia
	 * @return Um Date com o ultimo dia do Mes
	 */
	public static Date getUltimoDiaDoMesAtual(String ano, String mes, String dia) {
		Calendar c = Calendar.getInstance();

		c.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		d = c.getTime();

		String data = sdf.format(d.getTime());
		try {
			return sdf.parse(data);
		} catch (ParseException e) {
			return null;
		}

	}

	/**
	 * Valida a idade maxima do Cliente, não podendo ter nascido depois de
	 * 01/01/1900
	 * 
	 * @param data
	 * @return um inteiro devendo ser diferente de -1 1 a data de nascimento é
	 *         posterior a 01/01/1900 0 a data de nascimento é igual a
	 *         01/01/1900 -1 a data de nascimento é inferior a 01/01/1900
	 */
	public static int validarIdadeMaxima(Date data) {
		int i;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String minData = "01/01/1900";
		Date dataMin = null;
		try {
			dataMin = sdf.parse(minData);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		i = DataUtil.compararDatas(dataMin, data);

		return i;

	}

}
