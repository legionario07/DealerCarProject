package br.com.dealercar.strategy.valida;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.ImagemCarroDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.ImagemCarro;

/**
 * Classe Strategy repons�vel pela Valida��o de uma Imagem
 * @author Paulinho
 *
 */
public class ValidaImagemCarro implements IValidacaoStrategy {
	

	/**
	 * 
	 * @param recebe um objeto ImagemCarro e faz a Valida��o pela Descri��o
	 * @return Retorna um objeto ImagemCarro do BD v�lido ou Null se n�o for encontrado
	 */
	public EntidadeDominio validar(EntidadeDominio entDominio) {

		ImagemCarro imagemCarro = null;

		// Verifica se a classe passada no Parametro eh um objeto ImagemCarro
		if (entDominio instanceof ImagemCarro) {

			ImagemCarro retorno = (ImagemCarro) entDominio;

			ImagemCarroDAO dao = new ImagemCarroDAO();

			List<ImagemCarro> lista = new ArrayList<ImagemCarro>();
			lista = dao.listarTodos();

			for (ImagemCarro c : lista) {
				if (retorno.getDescricao().toUpperCase().equals(c.getDescricao())) {

					imagemCarro = c;

				}
			}
		}

		return imagemCarro;

	}

}
