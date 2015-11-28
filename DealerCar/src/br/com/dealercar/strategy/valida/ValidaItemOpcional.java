package br.com.dealercar.strategy.valida;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensopcionais.ArCondicionadoDAO;
import br.com.dealercar.dao.itensopcionais.BebeConfortoDAO;
import br.com.dealercar.dao.itensopcionais.CadeirinhaBebeDAO;
import br.com.dealercar.dao.itensopcionais.GpsDAO;
import br.com.dealercar.dao.itensopcionais.RadioPlayerDAO;
import br.com.dealercar.dao.itensopcionais.SeguroDAO;
import br.com.dealercar.dao.itensopcionais.TipoSeguroDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.ArCondicionado;
import br.com.dealercar.domain.itensopcionais.BebeConforto;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;
import br.com.dealercar.domain.itensopcionais.Gps;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;

public class ValidaItemOpcional implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe um objeto EntidadeDominio e faz a Validação pela Descriçaõ
	 * @return Retorna um objeto EntidadeDominio do BD válido ou Null se não for encontrado
	 */
	public EntidadeDominio validar(EntidadeDominio entDominio) {
		
			
		//Verifica se a classe passada no Parametro eh um objeto ArCondicionado
		if (entDominio instanceof ArCondicionado) {

			
			ArCondicionado retorno = (ArCondicionado) entDominio;
			
			ArCondicionadoDAO dao = new ArCondicionadoDAO();

			List<ArCondicionado> lista = new ArrayList<ArCondicionado>();
			lista = dao.listarTodos();

			for (ArCondicionado c : lista) {
				if (retorno.getDescricao().toUpperCase().equals(c.getDescricao())) {
					entDominio = c;
					return entDominio;

				}
			}
		
		}
		
		//Verifica se a classe passada no Parametro eh um objeto BebeConforto
		if (entDominio instanceof BebeConforto) {

			
			BebeConforto retorno = (BebeConforto) entDominio;
			
			BebeConfortoDAO dao = new BebeConfortoDAO();

			List<BebeConforto> lista = new ArrayList<BebeConforto>();
			lista = dao.listarTodos();

			for (BebeConforto c : lista) {
				if (retorno.getDescricao().toUpperCase().equals(c.getDescricao())) {
					entDominio = c;
					return entDominio;

				}
			}
		
		}
		
		//Verifica se a classe passada no Parametro eh um objeto CadeirinhaBebe
		if (entDominio instanceof CadeirinhaBebe) {

			
			CadeirinhaBebe retorno = (CadeirinhaBebe) entDominio;
			
			CadeirinhaBebeDAO dao = new CadeirinhaBebeDAO();

			List<CadeirinhaBebe> lista = new ArrayList<CadeirinhaBebe>();
			lista = dao.listarTodos();

			for (CadeirinhaBebe c : lista) {
				if (retorno.getDescricao().toUpperCase().equals(c.getDescricao())) {
					entDominio = c;
					return entDominio;

				}
			}
		
		}
		
		
		//Verifica se a classe passada no Parametro eh um objeto Gps
		if (entDominio instanceof Gps) {

			
			Gps retorno = (Gps) entDominio;
			
			GpsDAO dao = new GpsDAO();

			List<Gps> lista = new ArrayList<Gps>();
			lista = dao.listarTodos();

			for (Gps c : lista) {
				if (retorno.getDescricao().toUpperCase().equals(c.getDescricao())) {
					entDominio = c;
					return entDominio;

				}
			}
		
		}
		
		//Verifica se a classe passada no Parametro eh um objeto RadioPlayer
		if (entDominio instanceof RadioPlayer) {

			
			RadioPlayer retorno = (RadioPlayer) entDominio;
			
			RadioPlayerDAO dao = new RadioPlayerDAO();

			List<RadioPlayer> lista = new ArrayList<RadioPlayer>();
			lista = dao.listarTodos();

			for (RadioPlayer c : lista) {
				if (retorno.getDescricao().toUpperCase().equals(c.getDescricao())) {
					entDominio = c;
					return entDominio;

				}
			}
		
		}
		
		//Verifica se a classe passada no Parametro eh um objeto Seguro
		if (entDominio instanceof Seguro) {

			
			Seguro retorno = (Seguro) entDominio;
			
			SeguroDAO dao = new SeguroDAO();

			List<Seguro> lista = new ArrayList<Seguro>();
			lista = dao.listarTodos();

			for (Seguro c : lista) {
				if (retorno.getDescricao().equals(c.getDescricao()) && 
						retorno.getTipoSeguro().getId() == c.getTipoSeguro().getId()) {
					entDominio = c;
					return entDominio;

				}
			}
		
		}
		
		//Verifica se a classe passada no Parametro eh um objeto TipoSeguro
		if (entDominio instanceof TipoSeguro) {

			
			TipoSeguro retorno = (TipoSeguro) entDominio;
			
			TipoSeguroDAO dao = new TipoSeguroDAO();

			List<TipoSeguro> lista = new ArrayList<TipoSeguro>();
			lista = dao.listarTodos();

			for (TipoSeguro c : lista) {
				if (retorno.getId()==c.getId()) {
					entDominio = c;
					return entDominio;

				}
			}
		
		}
	
		return entDominio;
		
	}
}
