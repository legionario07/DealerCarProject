package br.com.dealercar.viewhelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.dealercar.util.DataUtil;

public class ViewHelper {

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

	/**
	 * Valida o CPF na View
	 * 
	 * @param cpf
	 * @return
	 */
	public static boolean validarCPF(String cpf) {

		Boolean ehValido = true;

		// retirando ponto e tra�o do cpf
		String novoCpf = cpf.replace(".", "");
		novoCpf = novoCpf.replace("-", "");
		int totalPosicoes = novoCpf.length();
		int aux = 10;
		int somaPrimeiroDigito = 0;
		int somaSegundoDigito = 0;

		// se n�o tiver 11 digito sai retorna FALSE
		if (totalPosicoes != 11) {
			return false;
		}

		// efetua o calculo do primeiro Digito
		for (int i = 0; i < 9; i++) {
			somaPrimeiroDigito += Integer.parseInt(String.valueOf(novoCpf.charAt(i))) * aux;
			aux = aux - 1;
		}

		int primeiroDigito = 11 - (somaPrimeiroDigito % 11);

		// verifica se o resultado deu 10 ou 11, se sim primeiro digito recebe 0
		if (primeiroDigito == 11 || primeiroDigito == 10) {
			primeiroDigito = 0;
		}

		// valida o primerio digito
		if (Integer.parseInt(String.valueOf(novoCpf.charAt(9))) != primeiroDigito) {
			return false;
		}
		aux = 11; // aux recebe o valor 11 para calcular o segundo digito

		// efetua o calculo do segundo digito
		for (int i = 0; i < 10; i++) {
			somaSegundoDigito += Integer.parseInt(String.valueOf(novoCpf.charAt(i))) * aux;
			aux = aux - 1;
		}

		int segundoDigito = 11 - (somaSegundoDigito % 11);
		// verifica se o resultado deu 10 ou 11, se sim primeiro digito recebe 0
		if (segundoDigito == 11 || segundoDigito == 10) {
			segundoDigito = 0;
		}

		// valida o segundo digito
		if (Integer.parseInt(String.valueOf(novoCpf.charAt(10))) != segundoDigito) {
			return false;
		}

		return ehValido;
	}




}
