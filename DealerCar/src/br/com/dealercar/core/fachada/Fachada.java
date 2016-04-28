package br.com.dealercar.core.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.autenticacao.Permissao;
import br.com.dealercar.core.dao.CargoDAO;
import br.com.dealercar.core.dao.CidadeDAO;
import br.com.dealercar.core.dao.ClienteDAO;
import br.com.dealercar.core.dao.CorDAO;
import br.com.dealercar.core.dao.DevolucaoDAO;
import br.com.dealercar.core.dao.EstadoDAO;
import br.com.dealercar.core.dao.FuncionarioDAO;
import br.com.dealercar.core.dao.IDAO;
import br.com.dealercar.core.dao.PermissaoDAO;
import br.com.dealercar.core.dao.RetiradaDAO;
import br.com.dealercar.core.dao.automotivos.CarroDAO;
import br.com.dealercar.core.dao.automotivos.CategoriaDAO;
import br.com.dealercar.core.dao.automotivos.FabricanteDAO;
import br.com.dealercar.core.dao.automotivos.ModeloDAO;
import br.com.dealercar.core.dao.automotivos.TaxasAdicionaisDAO;
import br.com.dealercar.core.dao.itensopcionais.BebeConfortoDAO;
import br.com.dealercar.core.dao.itensopcionais.CadeirinhaBebeDAO;
import br.com.dealercar.core.dao.itensopcionais.GpsDAO;
import br.com.dealercar.core.dao.itensopcionais.RadioPlayerDAO;
import br.com.dealercar.core.dao.itensopcionais.SeguroDAO;
import br.com.dealercar.core.dao.itensopcionais.TipoSeguroDAO;
import br.com.dealercar.core.dao.itensrevisao.AmortecedorDAO;
import br.com.dealercar.core.dao.itensrevisao.CorreiaDentadaDAO;
import br.com.dealercar.core.dao.itensrevisao.EmbreagemDAO;
import br.com.dealercar.core.dao.itensrevisao.FarolDAO;
import br.com.dealercar.core.dao.itensrevisao.FiltroDeArDAO;
import br.com.dealercar.core.dao.itensrevisao.FiltroDeOleoMotorDAO;
import br.com.dealercar.core.dao.itensrevisao.FluidoDeFreioDAO;
import br.com.dealercar.core.dao.itensrevisao.FormaDeVendaDAO;
import br.com.dealercar.core.dao.itensrevisao.PastilhaFreioDAO;
import br.com.dealercar.core.dao.itensrevisao.PneuDAO;
import br.com.dealercar.core.dao.itensrevisao.VelasIgnicaoDAO;
import br.com.dealercar.core.negocio.strategy.IValidacaoStrategy;
import br.com.dealercar.core.negocio.strategy.ValidaCPF;
import br.com.dealercar.core.negocio.strategy.ValidaCargo;
import br.com.dealercar.core.negocio.strategy.ValidaCarro;
import br.com.dealercar.core.negocio.strategy.ValidaCategoria;
import br.com.dealercar.core.negocio.strategy.ValidaCidade;
import br.com.dealercar.core.negocio.strategy.ValidaCliente;
import br.com.dealercar.core.negocio.strategy.ValidaCor;
import br.com.dealercar.core.negocio.strategy.ValidaEmail;
import br.com.dealercar.core.negocio.strategy.ValidaEstado;
import br.com.dealercar.core.negocio.strategy.ValidaFabricante;
import br.com.dealercar.core.negocio.strategy.ValidaFuncionario;
import br.com.dealercar.core.negocio.strategy.ValidaItemOpcional;
import br.com.dealercar.core.negocio.strategy.ValidaItemRevisao;
import br.com.dealercar.core.negocio.strategy.ValidaModelo;
import br.com.dealercar.core.negocio.strategy.ValidaPermissao;
import br.com.dealercar.core.negocio.strategy.ValidaPessoa;
import br.com.dealercar.core.negocio.strategy.ValidaSeguro;
import br.com.dealercar.core.negocio.strategy.ValidaTaxasAdicionais;
import br.com.dealercar.core.negocio.strategy.ValidaTipoSeguro;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.Cargo;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.Estado;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Categoria;
import br.com.dealercar.domain.automotivos.Cor;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.conducao.Devolucao;
import br.com.dealercar.domain.conducao.Retirada;
import br.com.dealercar.domain.itensopcionais.BebeConforto;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;
import br.com.dealercar.domain.itensopcionais.Gps;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;
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
import br.com.dealercar.domain.produtosrevisao.VelasIgnicao;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;

/**
 * 
 * @author Paulinho
 * Fachada Responsavel pelos CRUD
 */
public class Fachada implements IFachada {

	/**
	 * Mapa de DAOS, será indexado pelo nome da entidade O valor é uma instância
	 * do DAO para uma dada entidade;
	 */
	private Map<String, IDAO> mapDaos;

	/**
	 * Mapa para conter as regras de negócio de todas operações por entidade; O
	 * valor é um mapa que de regras de negócio indexado pela operação
	 */
	private Map<String, Map<String, List<IValidacaoStrategy>>> mapRegrasDeNegocios;

	private Resultado resultado;

	public Fachada() {

		/* Intânciando o Map de DAOS */
		mapDaos = new HashMap<String, IDAO>();

		/* Intânciando o Map de Regras de Negócio */
		mapRegrasDeNegocios = new HashMap<String, Map<String, List<IValidacaoStrategy>>>();

		/* Criando instâncias dos DAOs a serem utilizados */
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		CorDAO corDAO = new CorDAO();
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		ModeloDAO modeloDAO = new ModeloDAO();
		TaxasAdicionaisDAO taxasAdicionaisDAO = new TaxasAdicionaisDAO();
		SeguroDAO seguroDAO = new SeguroDAO();
		TipoSeguroDAO tipoSeguroDAO = new TipoSeguroDAO();
		BebeConfortoDAO bebeConfortoDAO = new BebeConfortoDAO();
		CadeirinhaBebeDAO cadeirinhaBebeDAO = new CadeirinhaBebeDAO();
		GpsDAO gpsDAO = new GpsDAO();
		RadioPlayerDAO radioPlayerDAO = new RadioPlayerDAO();
		AmortecedorDAO amortecedorDAO = new AmortecedorDAO();
		CorreiaDentadaDAO correiaDentadaDAO = new CorreiaDentadaDAO();
		EmbreagemDAO embreagemDAO = new EmbreagemDAO();
		FarolDAO farolDAO = new FarolDAO();
		FiltroDeArDAO filtroDeArDAO = new FiltroDeArDAO();
		FiltroDeOleoMotorDAO filtroDeOleoMotor = new FiltroDeOleoMotorDAO();
		FluidoDeFreioDAO fluidoDeFreioDAO = new FluidoDeFreioDAO();
		FormaDeVendaDAO formaDeVendaDAO = new FormaDeVendaDAO();
		PastilhaFreioDAO pastilhaFreioDAO = new PastilhaFreioDAO();
		PneuDAO pneuDAO = new PneuDAO();
		RetiradaDAO retiradaDAO = new RetiradaDAO();
		DevolucaoDAO devolucaoDAO = new DevolucaoDAO();
		VelasIgnicaoDAO velasIgnicaoDAO = new VelasIgnicaoDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		PermissaoDAO permissaoDAO = new PermissaoDAO();
		CidadeDAO cidadeDAO = new CidadeDAO();
		EstadoDAO estadoDAO = new EstadoDAO();
		CarroDAO carroDAO = new CarroDAO();
		CargoDAO cargoDAO = new CargoDAO();

		/* Adicionando cada dao no MAP indexando pelo nome da classe */
		mapDaos.put(Categoria.class.getName(), categoriaDAO);
		mapDaos.put(Cor.class.getName(), corDAO);
		mapDaos.put(Fabricante.class.getName(), fabricanteDAO);
		mapDaos.put(Modelo.class.getName(), modeloDAO);
		mapDaos.put(TaxasAdicionais.class.getName(), taxasAdicionaisDAO);
		mapDaos.put(Seguro.class.getName(), seguroDAO);
		mapDaos.put(TipoSeguro.class.getName(), tipoSeguroDAO);
		mapDaos.put(BebeConforto.class.getName(), bebeConfortoDAO);
		mapDaos.put(CadeirinhaBebe.class.getName(), cadeirinhaBebeDAO);
		mapDaos.put(Gps.class.getName(), gpsDAO);
		mapDaos.put(RadioPlayer.class.getName(), radioPlayerDAO);
		mapDaos.put(Amortecedor.class.getName(), amortecedorDAO);
		mapDaos.put(CorreiaDentada.class.getName(), correiaDentadaDAO);
		mapDaos.put(Embreagem.class.getName(), embreagemDAO);
		mapDaos.put(Farol.class.getName(), farolDAO);
		mapDaos.put(FiltroDeAr.class.getName(), filtroDeArDAO);
		mapDaos.put(FiltroDeOleoMotor.class.getName(), filtroDeOleoMotor);
		mapDaos.put(FluidoDeFreio.class.getName(), fluidoDeFreioDAO);
		mapDaos.put(FormaDeVenda.class.getName(), formaDeVendaDAO);
		mapDaos.put(PastilhaFreio.class.getName(), pastilhaFreioDAO);
		mapDaos.put(Pneu.class.getName(), pneuDAO);
		mapDaos.put(VelasIgnicao.class.getName(), velasIgnicaoDAO);
		mapDaos.put(Cliente.class.getName(), clienteDAO);
		mapDaos.put(Funcionario.class.getName(), funcionarioDAO);
		mapDaos.put(Permissao.class.getName(), permissaoDAO);
		mapDaos.put(Cidade.class.getName(), cidadeDAO);
		mapDaos.put(Estado.class.getName(), estadoDAO);
		mapDaos.put(Carro.class.getName(), carroDAO);
		mapDaos.put(Retirada.class.getName(), retiradaDAO);
		mapDaos.put(Devolucao.class.getName(), devolucaoDAO);
		mapDaos.put(Cargo.class.getName(), cargoDAO);

		/* Criando instâncias de regras de negócio a serem utilizados */
		ValidaCategoria validaCategoria = new ValidaCategoria();
		ValidaCor validaCor = new ValidaCor();
		ValidaFabricante validaFabricante = new ValidaFabricante();
		ValidaModelo validaModelo = new ValidaModelo();
		ValidaTaxasAdicionais validaTaxasAdicionais = new ValidaTaxasAdicionais();
		ValidaSeguro validaSeguro = new ValidaSeguro();
		ValidaTipoSeguro validaTipoSeguro = new ValidaTipoSeguro();
		ValidaItemOpcional validaItemOpcional = new ValidaItemOpcional();
		ValidaItemRevisao validaItemRevisao = new ValidaItemRevisao();
		ValidaCliente validaCliente = new ValidaCliente();
		ValidaFuncionario validaFuncionario = new ValidaFuncionario();
		ValidaPessoa validaPessoa = new ValidaPessoa();
		ValidaPermissao validaPermissao = new ValidaPermissao();
		ValidaCidade validaCidade = new ValidaCidade();
		ValidaEstado validaEstado = new ValidaEstado();
		ValidaCPF validaCPF = new ValidaCPF();
		ValidaEmail validaEmail = new ValidaEmail();
		ValidaCarro validaCarro = new ValidaCarro();
		ValidaCargo validaCargo = new ValidaCargo();

		// Map regras de negocios cadastrar
		// Categoria
		List<IValidacaoStrategy> regrasDeNegocioCadastrarCategoria = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarCategoria.add(validaCategoria);
		// Cor
		List<IValidacaoStrategy> regrasDeNegocioCadastrarCor = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarCor.add(validaCor);
		// Fabricante
		List<IValidacaoStrategy> regrasDeNegocioCadastrarFabricante = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarFabricante.add(validaFabricante);
		// Modelo
		List<IValidacaoStrategy> regrasDeNegocioCadastrarModelo = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarModelo.add(validaModelo);
		// TaxasAdicionais
		List<IValidacaoStrategy> regrasDeNegocioCadastrarTaxasAdicionais = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarTaxasAdicionais.add(validaTaxasAdicionais);
		// Seguro
		List<IValidacaoStrategy> regrasDeNegocioCadastrarSeguro = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarSeguro.add(validaSeguro);
		// TipoSeguro
		List<IValidacaoStrategy> regrasDeNegocioCadastrarTipoSeguro = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarTipoSeguro.add(validaTipoSeguro);
		// Item Opcional
		List<IValidacaoStrategy> regrasDeNegocioCadastrarItemOpcional = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarItemOpcional.add(validaItemOpcional);
		// Produto Revisao
		List<IValidacaoStrategy> regrasDeNegocioCadastrarProdutoRevisao = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarProdutoRevisao.add(validaItemRevisao);
		// Cliente
		List<IValidacaoStrategy> regrasDeNegocioCadastrarCliente = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarCliente.add(validaCliente);
		regrasDeNegocioCadastrarCliente.add(validaCPF);
		regrasDeNegocioCadastrarCliente.add(validaPessoa);
		regrasDeNegocioCadastrarCliente.add(validaEmail);
		// Funcionario
		List<IValidacaoStrategy> regrasDeNegocioCadastrarFuncionario = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarFuncionario.add(validaFuncionario);
		regrasDeNegocioCadastrarFuncionario.add(validaPessoa);
		regrasDeNegocioCadastrarFuncionario.add(validaCargo);
		// Permissao
		List<IValidacaoStrategy> regrasDeNegocioCadastrarPermissao = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarPermissao.add(validaPermissao);
		// Cidade
		List<IValidacaoStrategy> regrasDeNegocioCadastrarCidade = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarCidade.add(validaCidade);
		// Estado
		List<IValidacaoStrategy> regrasDeNegocioCadastrarEstado = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarEstado.add(validaEstado);
		// Carro
		List<IValidacaoStrategy> regrasDeNegocioCadastrarCarro = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarCarro.add(validaCarro);
		//Cargo
		List<IValidacaoStrategy> regrasDeNegocioCadastrarCargo = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioCadastrarCargo.add(validaCargo);

		// Map regras de negocios editar
		// Categoria
		List<IValidacaoStrategy> regrasDeNegocioEditarCategoria = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarCategoria.add(validaCategoria);
		// Cor
		List<IValidacaoStrategy> regrasDeNegocioEditarCor = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarCor.add(validaCor);
		// Fabricante
		List<IValidacaoStrategy> regrasDeNegocioEditarFabricante = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarFabricante.add(validaFabricante);
		// Modelo
		List<IValidacaoStrategy> regrasDeNegocioEditarModelo = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarModelo.add(validaModelo);
		// TaxasAdicionais
		List<IValidacaoStrategy> regrasDeNegocioEditarTaxasAdicionais = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarTaxasAdicionais.add(validaTaxasAdicionais);
		// Seguro
		List<IValidacaoStrategy> regrasDeNegocioEditarSeguro = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarSeguro.add(validaSeguro);
		// TipoSeguro
		List<IValidacaoStrategy> regrasDeNegocioEditarTipoSeguro = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarTipoSeguro.add(validaTipoSeguro);
		// Item Opcional
		List<IValidacaoStrategy> regrasDeNegocioEditarItemOpcional = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarItemOpcional.add(validaItemOpcional);
		// Produto Revisao
		List<IValidacaoStrategy> regrasDeNegocioEditarProdutoRevisao = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarProdutoRevisao.add(validaItemRevisao);
		// Cliente
		List<IValidacaoStrategy> regrasDeNegocioEditarCliente = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarCliente.add(validaCliente);
		regrasDeNegocioEditarCliente.add(validaCPF);
		regrasDeNegocioEditarCliente.add(validaPessoa);
		regrasDeNegocioEditarCliente.add(validaEmail);
		// Funcionario
		List<IValidacaoStrategy> regrasDeNegocioEditarFuncionario = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarFuncionario.add(validaFuncionario);
		regrasDeNegocioEditarFuncionario.add(validaPessoa);
		regrasDeNegocioEditarFuncionario.add(validaCargo);
		// Permissao
		List<IValidacaoStrategy> regrasDeNegocioEditarPermissao = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarPermissao.add(validaPermissao);
		// Cidade
		List<IValidacaoStrategy> regrasDeNegocioEditarCidade = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarCidade.add(validaCidade);
		// Estado
		List<IValidacaoStrategy> regrasDeNegocioEditarEstado = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarEstado.add(validaEstado);
		// Carro
		List<IValidacaoStrategy> regrasDeNegocioEditarCarro = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarCarro.add(validaCarro);
		//Cargo
		List<IValidacaoStrategy> regrasDeNegocioEditarCargo = new ArrayList<IValidacaoStrategy>();
		regrasDeNegocioEditarCargo.add(validaCargo);
		

		// Todas regras de Negocio
		// Categoria
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioCategoria = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioCategoria.put("EDITAR", regrasDeNegocioEditarCategoria);
		regrasDeNegocioCategoria.put("CADASTRAR", regrasDeNegocioCadastrarCategoria);
		// Cor
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioCor = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioCor.put("EDITAR", regrasDeNegocioEditarCor);
		regrasDeNegocioCor.put("CADASTRAR", regrasDeNegocioCadastrarCor);
		// Fabricante
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioFabricante = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioFabricante.put("EDITAR", regrasDeNegocioEditarFabricante);
		regrasDeNegocioFabricante.put("CADASTRAR", regrasDeNegocioCadastrarFabricante);
		// Modelo
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioModelo = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioModelo.put("EDITAR", regrasDeNegocioEditarModelo);
		regrasDeNegocioModelo.put("CADASTRAR", regrasDeNegocioCadastrarModelo);
		// TaxasAdicionais
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioTaxasAdicionais = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioTaxasAdicionais.put("EDITAR", regrasDeNegocioEditarTaxasAdicionais);
		regrasDeNegocioTaxasAdicionais.put("CADASTRAR", regrasDeNegocioCadastrarTaxasAdicionais);
		// Seguro
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioSeguro = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioSeguro.put("EDITAR", regrasDeNegocioEditarSeguro);
		regrasDeNegocioSeguro.put("CADASTRAR", regrasDeNegocioCadastrarSeguro);
		// TipoSeguro
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioTipoSeguro = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioTipoSeguro.put("EDITAR", regrasDeNegocioEditarTipoSeguro);
		regrasDeNegocioTipoSeguro.put("CADASTRAR", regrasDeNegocioCadastrarTipoSeguro);
		// Item Opcional
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioItemOpcional = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioItemOpcional.put("EDITAR", regrasDeNegocioEditarItemOpcional);
		regrasDeNegocioItemOpcional.put("CADASTRAR", regrasDeNegocioCadastrarItemOpcional);
		// Produto Revisao
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioProdutoRevisao = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioProdutoRevisao.put("EDITAR", regrasDeNegocioEditarProdutoRevisao);
		regrasDeNegocioProdutoRevisao.put("CADASTRAR", regrasDeNegocioCadastrarProdutoRevisao);
		// Cliente
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioCliente = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioCliente.put("EDITAR", regrasDeNegocioEditarCliente);
		regrasDeNegocioCliente.put("CADASTRAR", regrasDeNegocioCadastrarCliente);
		// Funcionario
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioFuncionario = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioFuncionario.put("EDITAR", regrasDeNegocioEditarFuncionario);
		regrasDeNegocioFuncionario.put("CADASTRAR", regrasDeNegocioCadastrarFuncionario);
		// Permissao
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioPermissao = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioPermissao.put("EDITAR", regrasDeNegocioEditarPermissao);
		regrasDeNegocioPermissao.put("CADASTRAR", regrasDeNegocioCadastrarPermissao);
		// Cidade
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioCidade = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioCidade.put("EDITAR", regrasDeNegocioEditarCidade);
		regrasDeNegocioCidade.put("CADASTRAR", regrasDeNegocioCadastrarCidade);
		// Estado
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioEstado = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioEstado.put("EDITAR", regrasDeNegocioEditarEstado);
		regrasDeNegocioEstado.put("CADASTRAR", regrasDeNegocioCadastrarEstado);
		//Carro
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioCarro = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioCarro.put("EDITAR", regrasDeNegocioEditarCarro);
		regrasDeNegocioCarro.put("CADASTRAR", regrasDeNegocioCadastrarCarro);
		//Cargo
		Map<String, List<IValidacaoStrategy>> regrasDeNegocioCargo = new HashMap<String, List<IValidacaoStrategy>>();
		regrasDeNegocioCargo.put("EDITAR", regrasDeNegocioEditarCargo);
		regrasDeNegocioCargo.put("CADASTRAR", regrasDeNegocioCadastrarCargo);
		
		

		mapRegrasDeNegocios.put(Categoria.class.getName(), regrasDeNegocioCategoria);
		mapRegrasDeNegocios.put(Cor.class.getName(), regrasDeNegocioCor);
		mapRegrasDeNegocios.put(Fabricante.class.getName(), regrasDeNegocioFabricante);
		mapRegrasDeNegocios.put(Modelo.class.getName(), regrasDeNegocioModelo);
		mapRegrasDeNegocios.put(TaxasAdicionais.class.getName(), regrasDeNegocioTaxasAdicionais);
		mapRegrasDeNegocios.put(Seguro.class.getName(), regrasDeNegocioSeguro);
		mapRegrasDeNegocios.put(TipoSeguro.class.getName(), regrasDeNegocioTipoSeguro);
		mapRegrasDeNegocios.put(BebeConforto.class.getName(), regrasDeNegocioItemOpcional);
		mapRegrasDeNegocios.put(CadeirinhaBebe.class.getName(), regrasDeNegocioItemOpcional);
		mapRegrasDeNegocios.put(Gps.class.getName(), regrasDeNegocioItemOpcional);
		mapRegrasDeNegocios.put(RadioPlayer.class.getName(), regrasDeNegocioItemOpcional);
		mapRegrasDeNegocios.put(Amortecedor.class.getName(), regrasDeNegocioProdutoRevisao);
		mapRegrasDeNegocios.put(CorreiaDentada.class.getName(), regrasDeNegocioProdutoRevisao);
		mapRegrasDeNegocios.put(Embreagem.class.getName(), regrasDeNegocioProdutoRevisao);
		mapRegrasDeNegocios.put(Farol.class.getName(), regrasDeNegocioProdutoRevisao);
		mapRegrasDeNegocios.put(FiltroDeAr.class.getName(), regrasDeNegocioProdutoRevisao);
		mapRegrasDeNegocios.put(FiltroDeOleoMotor.class.getName(), regrasDeNegocioProdutoRevisao);
		mapRegrasDeNegocios.put(FluidoDeFreio.class.getName(), regrasDeNegocioProdutoRevisao);
		mapRegrasDeNegocios.put(FormaDeVenda.class.getName(), regrasDeNegocioProdutoRevisao);
		mapRegrasDeNegocios.put(PastilhaFreio.class.getName(), regrasDeNegocioProdutoRevisao);
		mapRegrasDeNegocios.put(Pneu.class.getName(), regrasDeNegocioProdutoRevisao);
		mapRegrasDeNegocios.put(VelasIgnicao.class.getName(), regrasDeNegocioProdutoRevisao);
		mapRegrasDeNegocios.put(Cliente.class.getName(), regrasDeNegocioCliente);
		mapRegrasDeNegocios.put(Funcionario.class.getName(), regrasDeNegocioFuncionario);
		mapRegrasDeNegocios.put(Permissao.class.getName(), regrasDeNegocioPermissao);
		mapRegrasDeNegocios.put(Cidade.class.getName(), regrasDeNegocioCidade);
		mapRegrasDeNegocios.put(Estado.class.getName(), regrasDeNegocioEstado);
		mapRegrasDeNegocios.put(Carro.class.getName(), regrasDeNegocioCarro);
		mapRegrasDeNegocios.put(Cargo.class.getName(), regrasDeNegocioCargo);

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
			JSFUtil.adicionarMensagemSucesso("Editado com Sucesso");
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
			if (nomeClasse == Cliente.class.getName()) {
				entidade = new ClienteDAO().pesquisarPorCPF(entidade);
			} else if (nomeClasse == Carro.class.getName()){
				entidade = new CarroDAO().pesquisarPorPlaca(entidade);
			}
			else {
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

	@Override
	public Resultado listar(EntidadeDominio entidade) {

		resultado = null;

		String nomeClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "LISTAR");

		if (msg == null || msg.length() < 1) {
			resultado = new Resultado();
			IDAO dao = mapDaos.get(nomeClasse);
			List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
			if (nomeClasse == Cidade.class.getName()) {
				entidades = new CidadeDAO().listarTodos(entidade);
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

		Map<String, List<IValidacaoStrategy>> regrasOperacao = mapRegrasDeNegocios.get(nomeClasse);

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
