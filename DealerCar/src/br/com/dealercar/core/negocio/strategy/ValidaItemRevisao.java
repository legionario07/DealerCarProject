package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.produtosrevisao.Amortecedor;
import br.com.dealercar.domain.produtosrevisao.CorreiaDentada;
import br.com.dealercar.domain.produtosrevisao.Embreagem;
import br.com.dealercar.domain.produtosrevisao.Farol;
import br.com.dealercar.domain.produtosrevisao.FiltroDeAr;
import br.com.dealercar.domain.produtosrevisao.FiltroDeOleoMotor;
import br.com.dealercar.domain.produtosrevisao.FluidoDeFreio;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;
import br.com.dealercar.domain.produtosrevisao.PastilhaFreio;
import br.com.dealercar.domain.produtosrevisao.Pneu;
import br.com.dealercar.domain.produtosrevisao.ProdutoRevisao;
import br.com.dealercar.domain.produtosrevisao.VelasIgnicao;

/**
 * Classe Strategy reponsável pela Validação de um Produto Revisao
 * 
 * @author Paulinho
 *
 */
public class ValidaItemRevisao implements IValidacaoStrategy {

	/**
	 * Valida um Item Da REvisao e retorna null se
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer msg = null;

		// Verifica se a classe passada no Parametro eh um objeto Amortecedor
		if (entDominio instanceof Amortecedor) {

			Amortecedor retorno = (Amortecedor) entDominio;
			msg = new StringBuffer();
			msg.append(validarProduto(retorno));

		} else {
			return null;
		}

		// Verifica se a classe passada no Parametro eh um objeto CorreiaDentada
		if (entDominio instanceof CorreiaDentada) {

			CorreiaDentada retorno = (CorreiaDentada) entDominio;
			msg = new StringBuffer();
			msg.append(validarProduto(retorno));

		} else {
			return null;
		}

		// Verifica se a classe passada no Parametro eh um objeto Embreagem
		if (entDominio instanceof Embreagem) {

			Embreagem retorno = (Embreagem) entDominio;
			msg = new StringBuffer();
			msg.append(validarProduto(retorno));

		} else {
			return null;
		}

		// Verifica se a classe passada no Parametro eh um objeto Farol
		if (entDominio instanceof Farol) {

			Farol retorno = (Farol) entDominio;
			msg = new StringBuffer();
			msg.append(validarProduto(retorno));

		} else {
			return null;
		}

		// Verifica se a classe passada no Parametro eh um objeto FiltroDeAr
		if (entDominio instanceof FiltroDeAr) {

			FiltroDeAr retorno = (FiltroDeAr) entDominio;
			msg = new StringBuffer();
			msg.append(validarProduto(retorno));

		} else {
			return null;
		}

		// Verifica se a classe passada no Parametro eh um objeto FiltroDeOleoMotor
		if (entDominio instanceof FiltroDeOleoMotor) {

			FiltroDeOleoMotor retorno = (FiltroDeOleoMotor) entDominio;
			msg = new StringBuffer();
			msg.append(validarProduto(retorno));

		} else {
			return null;
		}

		// Verifica se a classe passada no Parametro eh um objeto FluidoDeFreio
		if (entDominio instanceof FluidoDeFreio) {

			FluidoDeFreio retorno = (FluidoDeFreio) entDominio;
			msg = new StringBuffer();
			msg.append(validarProduto(retorno));

		} else {
			return null;
		}

		// Verifica se a classe passada no Parametro eh um objeto PastilhaFreio
		if (entDominio instanceof PastilhaFreio) {

			PastilhaFreio retorno = (PastilhaFreio) entDominio;
			msg = new StringBuffer();
			msg.append(validarProduto(retorno));

		} else {
			return null;
		}

		// Verifica se a classe passada no Parametro eh um objeto Pneu
		if (entDominio instanceof Pneu) {

			Pneu retorno = (Pneu) entDominio;
			msg = new StringBuffer();
			msg.append(validarProduto(retorno));

		} else {
			return null;
		}
		
		// Verifica se a classe passada no Parametro eh um objeto VelasIgnicao
		if (entDominio instanceof VelasIgnicao) {

			VelasIgnicao retorno = (VelasIgnicao) entDominio;
			msg = new StringBuffer();
			msg.append(validarProduto(retorno));

		} else {
			return null;
		}
		
		// Verifica se a classe passada no Parametro eh um objeto FormaDeVenda
		if (entDominio instanceof FormaDeVenda) {
			
			FormaDeVenda retorno = (FormaDeVenda) entDominio;
			msg = new StringBuffer();
			
			if(retorno.getDescricao().equals("")){
				msg = new StringBuffer();
				msg.append("A Descrição deve ser preenchida");
				return msg.toString();
			}
			
		} else {
			return null;
		}

		return null;
	}

	private static String validarProduto(ProdutoRevisao retorno) {

		StringBuffer msg = null;

		if (retorno.getDescricao().equals("")) {
			msg = new StringBuffer();
			msg.append("O nome do Produto deve ser preenchido");
			return msg.toString();
		}
		if (retorno.getMarca().equals("")) {
			msg = new StringBuffer();
			msg.append("A marca do Produto deve ser preenchido");
			return msg.toString();
		}

		if (retorno.getTipo().equals("")) {
			msg = new StringBuffer();
			msg.append("O Tipo do Produto deve ser preenchido");
			return msg.toString();
		}

		if (retorno.getFormaDeVenda().getDescricao().equals("")) {
			msg = new StringBuffer();
			msg.append("A Forma de Venda deve ser preenchida");
			return msg.toString();
		}

		if (retorno.getQuantidade() < 0) {
			msg = new StringBuffer();
			msg.append("A Quantidade do Produto não pode ser negativa");
			return msg.toString();
		}
		if (retorno.getValor() < 0) {
			msg = new StringBuffer();
			msg.append("O valor do Produto deve ser maior que 0");
			return msg.toString();
		}

		return null;

	}
}
