package br.com.dealercar.core.negocio.strategy;

import java.util.Calendar;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.enums.SituacaoType;
/**
 * Classe Strategy reponsável pela Validação de um Carro
 * @author Paulinho
 *
 */
public class ValidaCarro implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe um objeto Carro
	 * @return Retorna uma String null se todos os dados foram válidados
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Carro carro = null;

		// Verifica se a classe passada no Parametro eh um objeto Carro
		if (entDominio instanceof Carro) {
			retorno = new StringBuffer();
			carro = (Carro) entDominio;
			
			if (carro.getCategoria().getId()<=0) {
				retorno.append("O Carro deve pertencer a uma Categoria");
				return retorno.toString();
			}
			
			int anoCarro = Integer.parseInt(carro.getAno());
			Calendar c = Calendar.getInstance();
			int ano = c.get(Calendar.YEAR);
			
			/**
			 * Ano de Fabricação deve ser inferior a 16 anor
			 */
			if ((ano - anoCarro)>=16) {
				retorno.append("O carro deve ter seu ano de Fabricação inferior a 16 anos");
				return retorno.toString();
			}
			
			/**
			 * Verifica se a placa nao esta vazia
			 */
			if (carro.getPlaca().equals("")) {
				retorno.append("O Placa do Carro deve ser preenchida");
				return retorno.toString();
			}
			
			/**
			 * Verifica se a placa esta preenchida corretamente
			 */
			if(!carro.getPlaca().matches("\\w{3}-\\d{4}")){
				retorno.append("Placa Invalida");
				return retorno.toString();
			}
			
			/**
			 * Verifica se a Situação esta obedecendo o enum SituaçãoType
			 */
			boolean flagEhIgual = false;
			for(SituacaoType s : SituacaoType.values()){
				if(carro.getSituacao().getDescricao().toUpperCase().equals(s.getDescricao())){
					flagEhIgual = true;
				}
			}
			if(flagEhIgual==false){
				retorno.append("A situação do veiculo esta Incorreta");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;

	}
}