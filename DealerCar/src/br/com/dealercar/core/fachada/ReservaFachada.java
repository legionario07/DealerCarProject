package br.com.dealercar.core.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.dao.IDAO;
import br.com.dealercar.core.dao.ReservaDAO;
import br.com.dealercar.core.dao.automotivos.ModeloDAO;
import br.com.dealercar.core.negocio.Reserva;
import br.com.dealercar.core.negocio.strategy.IValidacaoStrategy;
import br.com.dealercar.core.negocio.strategy.ValidaReserva;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Modelo;

public class ReservaFachada implements IFachada {

	private Map<String, IDAO> mapDaos;

	private Map<String, Map<String, List<IValidacaoStrategy>>> mapReservasFachada;

	private Resultado resultado;

	public ReservaFachada() {
		/* Intânciando o Map de DAOS */
		mapDaos = new HashMap<String, IDAO>();

		/* Intânciando o Map de Regras de Negócio */
		mapReservasFachada = new HashMap<String, Map<String, List<IValidacaoStrategy>>>();

		/* Criando instâncias dos DAOs a serem utilizados */
		ModeloDAO modeloDAO = new ModeloDAO();
		ReservaDAO reservaDAO = new ReservaDAO();

		/* Adicionando cada dao no MAP indexando pelo nome da classe */
		mapDaos.put(Modelo.class.getName(), modeloDAO);
		mapDaos.put(Reserva.class.getName(), reservaDAO);

		/* Criando instâncias de regras de negócio a serem utilizados */
		ValidaReserva validaReserva = new ValidaReserva();

		// Map regras de negocios cadastrar
		// Categoria
		List<IValidacaoStrategy> regrasDeNegocioCadastrarReserva = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarReserva.add(validaReserva);

		// Map regras de negocios editar
		// Categoria
		List<IValidacaoStrategy> regrasDeNegocioEditarReserva = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarReserva.add(validaReserva);

		// Todas regras de Negocio
		// Reserva
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioReserva = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioReserva.put("EDITAR", regrasDeNegocioEditarReserva);
		regrasDeNegocioReserva.put("CADASTRAR", regrasDeNegocioCadastrarReserva);

		mapReservasFachada.put(Reserva.class.getName(), regrasDeNegocioReserva);

	}

	@Override
	public Resultado cadastrar(EntidadeDominio entidade) {

		resultado = null;

		String nomeClasse = entidade.getClass().getName();
		String msg = executarRegras(entidade, "CADASTRAR");

		if (msg == null || msg.length() < 1) {
			resultado = new Resultado();

			IDAO dao = mapDaos.get(nomeClasse);
			dao.cadastrar(entidade);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);
			JSFUtil.adicionarMensagemSucesso("Cadastrado com Sucesso");

		} else {
			JSFUtil.adicionarMensagemErro("Não Foi Possivel Cadastrar");
			JSFUtil.adicionarMensagemErro(msg);
		}

		return resultado;

	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {

		resultado = null;

		String nomeClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "EXCLUIR");

		if (msg == null || msg.length() < 1) {
			resultado = new Resultado();

			IDAO dao = mapDaos.get(nomeClasse);
			dao.excluir(entidade);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);
			JSFUtil.adicionarMensagemSucesso("Excluido com Sucesso");

		} else {
			JSFUtil.adicionarMensagemErro("Não Foi Possivel Excluir");
			JSFUtil.adicionarMensagemErro(msg);
		}

		return resultado;

	}

	@Override
	public Resultado editar(EntidadeDominio entidade) {

		resultado = null;
		String nomeClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "EDITAR");

		if (msg == null || msg.length() < 1) {
			resultado = new Resultado();

			IDAO dao = mapDaos.get(nomeClasse);
			dao.editar(entidade);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);
			JSFUtil.adicionarMensagemSucesso("Reserva Editada com Sucesso");
		} else {
			JSFUtil.adicionarMensagemErro("Não Foi Possivel Editar");
			JSFUtil.adicionarMensagemErro(msg);

		}

		return resultado;

	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {

		resultado = null;

		String nomeClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "CONSULTAR");

		if (msg == null || msg.length() < 1) {
			resultado = new Resultado();

			IDAO dao = mapDaos.get(nomeClasse);
			entidade = dao.pesquisarPorID(entidade);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
			resultado.setEntidades(entidades);

		} else {
			JSFUtil.adicionarMensagemErro("Não Localizado");
		}

		return resultado;
	}

	public Resultado listar(EntidadeDominio entidade) {

		resultado = null;

		String nomeClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "LISTAR");

		if (msg == null || msg.length() < 1) {
			resultado = new Resultado();
			IDAO dao = mapDaos.get(nomeClasse);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			if(nomeClasse.equals(Modelo.class.getName())){
				entidades = new ModeloDAO().listarModelosDisponiveis();
			}else {
				entidades = dao.listarTodos();
			}
			resultado.setEntidades(entidades);

		} else {
			JSFUtil.adicionarMensagemErro("Não foi possivel listar " + nomeClasse);
		}

		return resultado;
	}

	private String executarRegras(EntidadeDominio entidade, String operacao) {

		String nomeClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<IValidacaoStrategy>> regrasOperacao = mapReservasFachada.get(nomeClasse);

		if (regrasOperacao != null) {
			List<IValidacaoStrategy> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (IValidacaoStrategy s : regras) {
					String retorno = s.validar(entidade);

					if (retorno != null) {
						msg.append(retorno);
						msg.append("\n");
					}
				}
			}

		}

		if (msg.length() > 0)
			return msg.toString();
		else
			return null;
	}

}
