package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.BebeConforto;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;
import br.com.dealercar.domain.itensopcionais.Gps;
import br.com.dealercar.domain.itensopcionais.Itens;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;

/**
 * Classe Strategy reponsável pela Validação de um Item Opcional
 * 
 * @author Paulinho
 *
 */
public class ValidaItemOpcional implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe
	 *            um objeto EntidadeDominio e faz a Validação pela Descriçaõ
	 * @return Retorna um objeto EntidadeDominio do BD válido ou Null se não for
	 *         encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer msg = null;

		// Verifica se a classe passada no Parametro eh um objeto BebeConforto
		if (entDominio instanceof BebeConforto) {

			BebeConforto retorno = (BebeConforto) entDominio;

			msg = new StringBuffer();

			msg.append(validarOpcional(retorno));

			if (retorno.getMesesBebe() <= 0) {
				msg.append("A quantidade de Mês deve ser Maior que 0");
				return retorno.toString();
			}

		} else {
			return null;
		}

		// Verifica se a classe passada no Parametro eh um objeto CadeirinhaBebe
		if (entDominio instanceof CadeirinhaBebe) {

			CadeirinhaBebe retorno = (CadeirinhaBebe) entDominio;
			msg = new StringBuffer();

			msg.append(validarOpcional(retorno));

			if (retorno.getPesoBebe() <= 0) {
				msg.append("A Peso do Bebe deve ser Maior que 0");
				return retorno.toString();
			}

		} else {
			return null;
		}

		// Verifica se a classe passada no Parametro eh um objeto Gps
		if (entDominio instanceof Gps) {

			Gps retorno = (Gps) entDominio;

			msg = new StringBuffer();

			msg.append(validarOpcional(retorno));

			if (retorno.getIdioma().equals("")) {
				msg.append("O Idioma deve ser preenchido");
				return retorno.toString();
			}

		} else {
			return null;
		}

		// Verifica se a classe passada no Parametro eh um objeto RadioPlayer
		if (entDominio instanceof RadioPlayer) {

			RadioPlayer retorno = (RadioPlayer) entDominio;

			msg = new StringBuffer();
			msg.append(validarOpcional(retorno));

			if (retorno.getModelo().equals("")) {
				msg.append("O Modelo deve ser preenchido");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;
	}

	private static String validarOpcional(Itens retorno) {

		StringBuffer msg = null;

		if (retorno.getDescricao().equals("")) {
			msg = new StringBuffer();
			msg.append("O nome do Opcional deve ser preenchido");
			return msg.toString();
		}
		if (retorno.getMarca().equals("")) {
			msg = new StringBuffer();
			msg.append("A marca do Opcional deve ser preenchido");
			return msg.toString();
		}

		if (retorno.getNumeroPatrimonio().equals("")) {
			msg = new StringBuffer();
			msg.append("O número do Patrimonio deve ser preenchido");
			return msg.toString();
		}
		if (retorno.getValor() < 0) {
			msg = new StringBuffer();
			msg.append("O valor do Opcional deve ser maior que 0");
			return msg.toString();
		}
		
		return null;

	}
}
