package br.com.dealercar.web.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.Cor;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Categoria;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.enums.SituacaoType;
import br.com.dealercar.web.command.ICommand;

@ManagedBean(name = "MBCarro")
@ViewScoped
public class CarroBean extends AbstractBean implements Serializable {

	/**
	 * Controlando a evolução dos objetos serialidos.... Ex.: Salva um objeto em
	 * um arquivo, meses depois em que adicionar um método e ou atributo na sua
	 * classe. Quando tentar deserializar o objeto naão é permitido mais.
	 * Mantendo o serialVersionUID este erro não ocorre. Assim é permitido
	 * deserializar objetos que foram modificados.
	 */
	private static final long serialVersionUID = -3692960075593703235L;

	private Carro carro = new Carro();

	private List<EntidadeDominio> listaCarros = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaCores = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaModelos = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaCategoria = new ArrayList<EntidadeDominio>();
	private List<SituacaoType> listaSituacao = new ArrayList<SituacaoType>();

	private StringBuffer caminho = new StringBuffer();
	
	private int totalCarros;
	
	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<EntidadeDominio> getListaCarros() {
		return listaCarros;
	}

	public void setListaCarros(List<EntidadeDominio> listaCarros) {
		this.listaCarros = listaCarros;
	}

	public int getTotalCarros() {
		return totalCarros;
	}

	public void setTotalCarros(int totalCarros) {
		this.totalCarros = totalCarros;
	}

	public List<EntidadeDominio> getListaCores() {
		return listaCores;
	}

	public void setListaCores(List<EntidadeDominio> listaCores) {
		this.listaCores = listaCores;
	}

	public List<EntidadeDominio> getListaModelos() {
		return listaModelos;
	}

	public void setListaModelos(List<EntidadeDominio> listaModelos) {
		this.listaModelos = listaModelos;
	}

	public List<EntidadeDominio> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<EntidadeDominio> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public List<SituacaoType> getListaSituacao() {
		return listaSituacao;
	}

	public void setListaSituacao(List<SituacaoType> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}


	/**
	 * Metodo que carrega as listagem de todas as Categoria, Carros, Modelos,
	 * Cores e Imagens do Banco de Dados assim que a tela é iniciada
	 * 
	 */
	@Override
	public void carregarListagem() {
		
		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("LISTAR");

		Resultado resultado = new Resultado();
		resultado = command.execute(new Carro());
		if (resultado != null) {
			listaCarros = resultado.getEntidades();
		}
		resultado = command.execute(new Categoria());
		if (resultado != null) {
			listaCategoria = resultado.getEntidades();
		}
		resultado = command.execute(new Modelo());
		if (resultado != null) {
			listaModelos = resultado.getEntidades();
		}
		resultado = command.execute(new Cor());
		if (resultado != null) {
			listaCores = resultado.getEntidades();
		}

		listaSituacao = Arrays.asList(SituacaoType.values());

		this.setTotalCarros(listaCarros.size());

	}

	/**
	 * Método que localiza um objeto de Carro pela placa digitada na tela pelo
	 * Usuario
	 */
	public void pesquisarPorPlaca() {

		setEhCadastrado(false);
		setJaPesquisei(true);

		// Retorna um estado completo de acordo com um ID
		ICommand command = mapCommands.get("CONSULTAR");

		Resultado resultado = new Resultado();
		resultado = command.execute(carro);

		// Cliente foi encontrado
		if (resultado.getEntidades().get(0) != null) {
			carro = (Carro) resultado.getEntidades().get(0);
			setEhCadastrado(true);
			setJaPesquisei(false);
			return;
		} else {
			String placa = carro.getPlaca();
			carro = new Carro();
			carro.setPlaca(placa);
		}

	}
	
	@Override
	public void executar() {

		//setando a url da imagem
		carro.setUrlImagem(caminho.toString());
		
		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param"); 

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get(operacao);
		Resultado resultado = new Resultado();
		
		resultado = command.execute(carro);
		if (resultado != null) {
			carro = (Carro) resultado.getEntidades().get(0);
		}
		caminho = new StringBuffer();
		carro = new Carro();
		setEhCadastrado(false);
		setJaPesquisei(false);
	}


	/**
	 * Limpa a pesquisa anteriomente realizada pelo Usuário
	 */
	public void limparPesquisa() {
		carro = new Carro();
		setEhCadastrado(false);
		setJaPesquisei(false);
	}
	
	/**
	 * Realia o Upload da Imagem para a pasta da aplicação
	 * @param event
	 * @throws IOException
	 */
	public void upload(FileUploadEvent event){
		
		System.out.println("Entrou aki no inicio");
		
		file = event.getFile();
		System.out.println(event.getFile().getFileName());
		System.out.println(event.getFile().getSize());
		System.out.println(event.getFile().getContents());
		InputStream in = null;
		try {
			in = file.getInputstream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String CAMINHO = "resources\\images\\carros\\";
		File pastaDestino = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("")+"\\"+CAMINHO);

		System.out.println("Entrou aki no inicio depois de FIle");
		
		try {
			OutputStream out = new FileOutputStream(new File(pastaDestino, event.getFile().getFileName()));
			int read = 0;
			byte[] bytes = event.getFile().getContents();
			System.out.println(bytes.length);
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			
			in.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (file != null) {
			System.out.println("Entrou aqui");
			caminho.append("carros/");
			caminho.append(event.getFile().getFileName());
			JSFUtil.adicionarMensagemSucesso("Imagem Carregada com Sucesso");

		}else{
			caminho.append("carros/");
			System.out.println("não entrou");
		}

	}

}
