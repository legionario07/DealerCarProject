package br.com.dealercar.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.dealercar.dao.ReservaDAO;
import br.com.dealercar.dao.automotivos.CarroDAO;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Reserva;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.enums.SituacaoReserva;
import br.com.dealercar.strategy.valida.ValidaCliente;
import br.com.dealercar.strategy.valida.ValidaModelo;
import br.com.dealercar.util.DataUtil;
import br.com.dealercar.util.JSFUtil;
import br.com.dealercar.viewhelper.SessionHelper;

@ManagedBean(name = "MBReserva")
@SessionScoped
public class ReservaBean extends AbstractBean {


	private Reserva reserva = new Reserva();

	private ReservaDAO reservaDao = new ReservaDAO();
	private CarroDAO carroDao = new CarroDAO();

	private List<Reserva> listaReservas = new ArrayList<Reserva>();
	private List<Carro> listaModelosDisponiveis = new ArrayList<Carro>();

	private int totalReservas;

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public ReservaDAO getReservaDao() {
		return reservaDao;
	}

	public void setReservaDao(ReservaDAO reservaDao) {
		this.reservaDao = reservaDao;
	}

	public List<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public List<Carro> getListaModelosDisponiveis() {
		return listaModelosDisponiveis;
	}

	public void setListaModelosDisponiveis(List<Carro> listaModelosDisponiveis) {
		this.listaModelosDisponiveis = listaModelosDisponiveis;
	}

	public int getTotalReservas() {
		return totalReservas;
	}

	public void setTotalReservas(int totalReservas) {
		this.totalReservas = totalReservas;
	}

	/**
	 * Carrega a listagem assim que a pagina inicia - das Reservas disponiveis -
	 * Modelos Disponiveis - Funcionarios - Clientes
	 */
	@Override
	public void carregarListagem() {

		
		listaReservas = reservaDao.listarTodos();
		
		int atualizouReservas = atualizarStatusReserva();
		if(atualizouReservas > 0){
			listaReservas = reservaDao.listarTodos();
		}
		
		
		listaModelosDisponiveis = carroDao.listarApenasDisponiveis();

		setTotalReservas(listaReservas.size());

	}
	
	/**
	 * Atualiza o Status das Reservas Ativas assim que abre a pagina
	 * Se passou o dia Ela cancela automaticamente
	 */
	private int atualizarStatusReserva(){
		int entrouNoLoop = 0;
		
		for(Reserva r : listaReservas){
			if(r.getSituacao().equals("ATIVO")){
				//verifica se ja passou a data
				int i = DataUtil.compararDatas(DataUtil.pegarDataAtualDoSistema(), r.getDataFim());
				if(i == -1){
					entrouNoLoop += 1;
					//como passou a data Fim é alterado o Status para Cancelado
					r.setSituacao(SituacaoReserva.CANCELADO);
					new ReservaDAO().editar(r);
				}
			}
		}
		
		return entrouNoLoop;
	}

	/**
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo Usuário na
	 * TEla
	 */
	public void pesquisarPorCPF() {

		setEhCadastrado(false);
		setJaPesquisei(true);

		// Validando o cliente
		reserva.setCliente((Cliente) new ValidaCliente().validar(reserva.getCliente()));

		// veficando se o cliente foi encontrado
		if (reserva.getCliente() != null) {
			setEhCadastrado(true);
			setJaPesquisei(false);
			return;
		}

		if (isEhCadastrado() == false) {
			reserva.setCliente(new Cliente());
			JSFUtil.adicionarMensagemNaoLocalizado("Cliente Não Cadastrado.");
			return;
		}

	}

	/**
	 * Cadastra uma nova reserva, o usuario ira procurar o cliente pelo CPF Será
	 * aberta uma nova caixa de dialogo para o devido cadastro
	 */
	public void cadastrar() {

		// validando o modelo pelo nome
		reserva.setModelo((Modelo) new ValidaModelo().validar(reserva.getModelo()));

		// recebendo a data atual do sistema
		reserva.setDataCadastroReserva(DataUtil.pegarDataAtualDoSistema());

		// Verifica se a data digitada para Reserva é válida
		int i = DataUtil.compararDatas(reserva.getDataCadastroReserva(), reserva.getDataFim());

		// se i diferente de 1 a data esta incorreta
		if (i != 1) {
			// colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");

			JSFUtil.adicionarMensagemErro("A data para Reserva está incorreta. " + "Deve ser maior que "
					+ stf.format(reserva.getDataCadastroReserva()));
			return;
		}

		// setando o funcionario que realizou a reserva
		reserva.setFuncionario((Funcionario) SessionHelper.getParam("usuarioLogado"));

		reservaDao.cadastrar(reserva);

		reserva = new Reserva();

		JSFUtil.adicionarMensagemSucesso("Reserva Cadastrada com Sucesso.");

		// Se não houve nenhum erro fecha o <p:Dialog>
		org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dlgReservaNova').hide();"); 
	}

	/**
	 * Edita os dados da reserva selecionada pelo usuario na tela Será aberta
	 * uma nova caixa de dialogo para ser feitas as alterações
	 */
	public void editar() {

		// validando o modelo pelo nome
		reserva.setModelo((Modelo) new ValidaModelo().validar(reserva.getModelo()));

		// recebendo a data atual do sistema
		reserva.setDataCadastroReserva(DataUtil.pegarDataAtualDoSistema());

		// Verifica se a data digitada para Reserva é válida
		int i = DataUtil.compararDatas(reserva.getDataCadastroReserva(), reserva.getDataFim());

		// se i diferente de 1 a data esta incorreta
		if (i != 1) {
			// colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");

			JSFUtil.adicionarMensagemErro("A data para Reserva está incorreta. " + "Deve ser maior que "
					+ stf.format(reserva.getDataCadastroReserva()));
			return;
		}

		// setando o funcionario que realizou a reserva
		reserva.setFuncionario((Funcionario) SessionHelper.getParam("usuarioLogado"));
		
		reservaDao.editar(reserva);
		reserva = new Reserva();

		JSFUtil.adicionarMensagemSucesso("Reserva Alterada com Sucesso.");
		
		// Se não houve nenhum erro fecha o <p:Dialog>
		org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dlgReservaEditar').hide();"); 

	}

	/**
	 * Exclui os dados da reserva selecionada pelo usuario na tela Será aberta
	 * uma nova caixa de dialogo para confirmar a Exclusão
	 */
	public void excluir() {

		reservaDao.excluir(reserva);

		JSFUtil.adicionarMensagemSucesso("Reserva excluida com Sucesso.");

	}

}
