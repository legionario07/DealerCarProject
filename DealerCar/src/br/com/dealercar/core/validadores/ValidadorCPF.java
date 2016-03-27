package br.com.dealercar.core.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidadorCPF implements Validator<Object> {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String cpf = (String) arg2;
		
		if(!validarCPF(cpf)){
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "CPF inválido!", 
					"Favor informar um CPF válido."));
		}
		
	}
	
	private static boolean validarCPF(String cpf) {

		Boolean ehValido = true;

		// retirando ponto e traço do cpf
		String novoCpf = cpf.replace(".", "");
		novoCpf = novoCpf.replace("-", "");
		int totalPosicoes = novoCpf.length();
		int aux = 10;
		int somaPrimeiroDigito = 0;
		int somaSegundoDigito = 0;

		// se não tiver 11 digito sai retorna FALSE
		if (totalPosicoes != 11) {
			return false;
		}
		
		if(novoCpf.equals("11111111111") ||
				novoCpf.equals("22222222222") ||
				novoCpf.equals("33333333333") ||
				novoCpf.equals("44444444444") ||
				novoCpf.equals("55555555555") ||
				novoCpf.equals("66666666666") ||
				novoCpf.equals("77777777777") ||
				novoCpf.equals("88888888888") ||
				novoCpf.equals("99999999999") ||
				novoCpf.equals("00000000000")) {
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
