package br.com.dealercar.core.fachada;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.EntidadeDominio;

public interface IFachada {
	
	public Resultado cadastrar(EntidadeDominio entidade);
	public Resultado editar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
	public Resultado listar(EntidadeDominio entidade);

}
