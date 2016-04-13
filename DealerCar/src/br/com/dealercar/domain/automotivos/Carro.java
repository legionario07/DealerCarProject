package br.com.dealercar.domain.automotivos;

import java.io.Serializable;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.enums.SituacaoType;

/**
 * Classe que representa o Objeto Carro
 * @author Paulinho
 *
 */
public class Carro extends EntidadeDominio implements Serializable {

	/**
	 * Controlando a evolu��o dos objetos serialidos.... Ex.: Salva um objeto em
	 * um arquivo, meses depois em que adicionar um m�todo e ou atributo na sua
	 * classe. Quando tentar deserializar o objeto na�o � permitido mais.
	 * Mantendo o serialVersionUID este erro n�o ocorre. Assim � permitido
	 * deserializar objetos que foram modificados.
	 */
	private static final long serialVersionUID = 319718927662886570L;

	private String placa;
	private String ano;
	private SituacaoType situacao;
	private int qtdePortas;
	private int qtdeMalasSuportadas;
	private Cor cor;
	private Modelo modelo;
	private Categoria categoria;
	private String urlImagem;
	

	public Carro() {
		
		cor = new Cor();
		modelo = new Modelo();
		categoria = new Categoria();

	}

	public Carro(String placa) {
		this.setPlaca(placa);
	}

	public Carro(String placa, String ano, int qtdePortas, int qtdeMalasSuportadas, Cor cor, Modelo modelo,
			Categoria categoria, SituacaoType situacao) {

		this.setPlaca(placa);
		this.setAno(ano);
		this.setQtdePortas(qtdePortas);
		this.setQtdeMalasSuportadas(qtdeMalasSuportadas);
		this.setCor(cor);
		this.setModelo(modelo);
		this.setCategoria(categoria);
		this.setSituacao(situacao);
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa.toUpperCase();
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public SituacaoType getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoType situacao) {
		this.situacao = situacao;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem.trim();
	}

	public int getQtdePortas() {
		return qtdePortas;
	}

	public void setQtdePortas(int qtdePortas) {
		this.qtdePortas = qtdePortas;
	}

	public int getQtdeMalasSuportadas() {
		return qtdeMalasSuportadas;
	}

	public void setQtdeMalasSuportadas(int qtdeMalasSuportadas) {
		this.qtdeMalasSuportadas = qtdeMalasSuportadas;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		try {
			retorno.append("Modelo: ");
			retorno.append(this.getModelo().getNome());
			retorno.append("\nPlaca: ");
			retorno.append(this.getPlaca());
			retorno.append("\nAno: ");
			retorno.append(this.getAno());
			retorno.append("\nN�mero de Portas: ");
			retorno.append(this.getQtdePortas());
			retorno.append("\nQtde Malas Suportadas: ");
			retorno.append(this.getQtdeMalasSuportadas());
			retorno.append("\nCor: ");
			retorno.append(this.getCor().getNome());

			retorno.append("\nCategoria: ");
			retorno.append(this.getCategoria().getNome());
			retorno.append("\nUrl Imagem: ");
			retorno.append(this.getUrlImagem());
			retorno.append("\nSitua��o: ");
			retorno.append(this.getSituacao().getDescricao());
			retorno.append("\n\n");
		} catch (Exception e) {
			System.out.println("\nN�o foi possivel localizar o Carro");
			return null;
		}
		return retorno.toString();
	}

}
