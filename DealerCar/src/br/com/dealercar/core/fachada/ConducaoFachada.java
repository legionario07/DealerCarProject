package br.com.dealercar.core.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.dao.ClienteDAO;
import br.com.dealercar.core.dao.DevolucaoDAO;
import br.com.dealercar.core.dao.IDAO;
import br.com.dealercar.core.dao.ReservaDAO;
import br.com.dealercar.core.dao.RetiradaDAO;
import br.com.dealercar.core.dao.RevisaoDAO;
import br.com.dealercar.core.dao.automotivos.CarroDAO;
import br.com.dealercar.core.dao.automotivos.ModeloDAO;
import br.com.dealercar.core.dao.automotivos.TaxasAdicionaisDAO;
import br.com.dealercar.core.dao.itensopcionais.OpcionalDAO;
import br.com.dealercar.core.dao.itensopcionais.SeguroDAO;
import br.com.dealercar.core.dao.itensrevisao.ProdutoRevisaoDAO;
import br.com.dealercar.core.negocio.strategy.IValidacaoStrategy;
import br.com.dealercar.core.negocio.strategy.ValidaDevolucao;
import br.com.dealercar.core.negocio.strategy.ValidaReserva;
import br.com.dealercar.core.negocio.strategy.ValidaRetirada;
import br.com.dealercar.core.negocio.strategy.ValidaRevisao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.conducao.Devolucao;
import br.com.dealercar.domain.conducao.Reserva;
import br.com.dealercar.domain.conducao.Retirada;
import br.com.dealercar.domain.conducao.Revisao;
import br.com.dealercar.domain.itensopcionais.Opcional;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.domain.produtosrevisao.ProdutoRevisao;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;

/**
 * 
 * @author Paulinho Fachada Responsavel pelas Implementação das Conduçoes
 *
 */
public class ConducaoFachada implements IFachada {

	private Map<String, IDAO> mapDaos;

	private Map<String, Map<String, List<IValidacaoStrategy>>> mapConducaoFachada;

	private Resultado resultado;

	public ConducaoFachada() {
		/* Intânciando o Map de DAOS */
		mapDaos = new HashMap<String, IDAO>();

		/* Intânciando o Map de Regras de Negócio */
		mapConducaoFachada = new HashMap<String, Map<String, List<IValidacaoStrategy>>>();

		/* Criando instâncias dos DAOs a serem utilizados */
		ModeloDAO modeloDAO = new ModeloDAO();
		ReservaDAO reservaDAO = new ReservaDAO();
		RetiradaDAO retiradaDAO = new RetiradaDAO();
		SeguroDAO seguroDAO = new SeguroDAO();
		CarroDAO carroDAO = new CarroDAO();
		OpcionalDAO opcionalDAO = new OpcionalDAO();
		DevolucaoDAO devolucaoDao = new DevolucaoDAO();
		TaxasAdicionaisDAO taxasAdicionaisDAO = new TaxasAdicionaisDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		RevisaoDAO revisaoDAO = new RevisaoDAO();
		ProdutoRevisaoDAO produtoRevisaoDAO = new ProdutoRevisaoDAO();

		/* Adicionando cada dao no MAP indexando pelo nome da classe */
		mapDaos.put(Modelo.class.getName(), modeloDAO);
		mapDaos.put(Reserva.class.getName(), reservaDAO);
		mapDaos.put(Retirada.class.getName(), retiradaDAO);
		mapDaos.put(Seguro.class.getName(), seguroDAO);
		mapDaos.put(Carro.class.getName(), carroDAO);
		mapDaos.put(Opcional.class.getName(), opcionalDAO);
		mapDaos.put(Devolucao.class.getName(), devolucaoDao);
		mapDaos.put(TaxasAdicionais.class.getName(), taxasAdicionaisDAO);
		mapDaos.put(Cliente.class.getName(), clienteDAO);
		mapDaos.put(Revisao.class.getName(), revisaoDAO);
		mapDaos.put(ProdutoRevisao.class.getName(), produtoRevisaoDAO);

		/* Criando instâncias de regras de negócio a serem utilizados */
		ValidaReserva validaReserva = new ValidaReserva();
		ValidaRetirada validaRetirada = new ValidaRetirada();
		ValidaDevolucao validaDevolucao = new ValidaDevolucao();
		ValidaRevisao validaRevisao = new ValidaRevisao();

		// Map regras de negocios cadastrar
		// Reserva
		List<IValidacaoStrategy> regrasDeNegocioCadastrarReserva = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarReserva.add(validaReserva);
		// Retirada
		List<IValidacaoStrategy> regrasDeNegocioCadastrarRetirada = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarRetirada.add(validaRetirada);
		// Devolucao
		List<IValidacaoStrategy> regrasDeNegocioCadastrarDevolucao = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarDevolucao.add(validaDevolucao);
		// Revisao
		List<IValidacaoStrategy> regrasDeNegocioCadastrarRevisao = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarRevisao.add(validaRevisao);

		// Map regras de negocios editar
		// Reserva
		List<IValidacaoStrategy> regrasDeNegocioEditarReserva = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarReserva.add(validaReserva);
		// Retirada
		List<IValidacaoStrategy> regrasDeNegocioEditarRetirada = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarRetirada.add(validaRetirada);
		// Devolucao
		List<IValidacaoStrategy> regrasDeNegocioEditarDevolucao = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarDevolucao.add(validaDevolucao);
		// Revisao
		List<IValidacaoStrategy> regrasDeNegocioEditarRevisao = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarRevisao.add(validaRevisao);

		// Todas regras de Negocio
		// Reserva
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioReserva = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioReserva.put("EDITAR", regrasDeNegocioEditarReserva);
		regrasDeNegocioReserva.put("CADASTRAR", regrasDeNegocioCadastrarReserva);
		// Retirada
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioRetirada = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioRetirada.put("EDITAR", regrasDeNegocioEditarRetirada);
		regrasDeNegocioRetirada.put("CADASTRAR", regrasDeNegocioCadastrarRetirada);
		// Devolucao
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioDevolucao = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioDevolucao.put("EDITAR", regrasDeNegocioEditarDevolucao);
		regrasDeNegocioDevolucao.put("CADASTRAR", regrasDeNegocioCadastrarDevolucao);
		// Revisao
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioRevisao = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioRevisao.put("EDITAR", regrasDeNegocioEditarRevisao);
		regrasDeNegocioRevisao.put("CADASTRAR", regrasDeNegocioCadastrarRevisao);

		mapConducaoFachada.put(Reserva.class.getName(), regrasDeNegocioReserva);
		mapConducaoFachada.put(Retirada.class.getName(), regrasDeNegocioRetirada);
		mapConducaoFachada.put(Devolucao.class.getName(), regrasDeNegocioDevolucao);
		mapConducaoFachada.put(Revisao.class.getName(), regrasDeNegocioRevisao);

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
			JSFUtil.adicionarMensagemSucesso("Operação Realizada com Sucesso");

		} else {
			JSFUtil.adicionarMensagemErro("Não Foi Possivel Realizar a Operação");
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
			JSFUtil.adicionarMensagemSucesso("Operação Realizada com Sucesso");
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
			if (nomeClasse.equals(Opcional.class.getName())) {
				entidade = new OpcionalDAO().pesquisarPorUltimoCadastrado();
			} else if (nomeClasse.equals(Seguro.class.getName())) {
				entidade = new SeguroDAO().pesquisarPorCodigoDoTipoSeguro(entidade);
			} else if (nomeClasse.equals(Cliente.class.getName())) {
				entidade = new RetiradaDAO().pesquisarPorCPF(entidade);
			} else if (nomeClasse.equals(TaxasAdicionais.class.getName())) {
				entidade = new TaxasAdicionaisDAO().pesquisarPorTaxa(entidade);
			} else if (nomeClasse.equals(Devolucao.class.getName())) {
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades = new DevolucaoDAO().listarDevolucaoAguardandoRevisao();
				resultado.setEntidades(entidades);
				return resultado;
			} else {
				entidade = dao.pesquisarPorID(entidade);
			}
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
			if (nomeClasse.equals(Modelo.class.getName())) {
				entidades = new ModeloDAO().listarModelosDisponiveis();

			} else if (nomeClasse.equals(Seguro.class.getName())) {
				entidades = new SeguroDAO().listarApenasNomesDiferentes();

			} else if (nomeClasse.equals(Carro.class.getName())) {
				entidades = new CarroDAO().listarModelosDisponiveis(entidade);
			} else {
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

		Map<String, List<IValidacaoStrategy>> regrasOperacao = mapConducaoFachada.get(nomeClasse);

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
