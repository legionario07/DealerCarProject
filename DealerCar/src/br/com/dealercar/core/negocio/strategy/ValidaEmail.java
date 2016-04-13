package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;

public class ValidaEmail implements IValidacaoStrategy {

	@Override
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;

		if (entDominio instanceof Cliente) {
			retorno = new StringBuffer();
			Cliente cliente = (Cliente) entDominio;
			String email = cliente.getEmail();

			if (email == null) {
				return null;
			}
			if (email.indexOf('@') == -1 || email.contains(" ") || email.length() < 3 || email.endsWith("@")
					|| email.startsWith("@")) {
				retorno.append("Email inválido");
				return retorno.toString();
			}
			
			return null;
		}

		return null;

	}

}
