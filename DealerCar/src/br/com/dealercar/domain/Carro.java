package br.com.dealercar.domain;

public class Carro {
	
	private String placa;
	private String ano;
	private SituacaoType situacao;
	private int qtdePortas;
	private int qtdeMalasSuportadas;
	private Cor cor;
	private Modelo modelo;
	private Categoria categoria;
	private ImagemCarro carroUrl;
	
	public Carro(){
		
	}
	
	public Carro(String placa) {
		this.setPlaca(placa);
	}
	
	public Carro(String placa, String ano, int qtdePortas, int qtdeMalasSuportadas,
			Cor cor, Modelo modelo, Categoria categoria, SituacaoType situacao) {
	
	this.setPlaca(placa);
	this.setAno(ano);
	this.setQtdePortas(qtdePortas);
	this.setQtdeMalasSuportadas(qtdeMalasSuportadas);
	this.setCor(cor);
	this.setModelo(modelo);
	this.setCategoria(categoria);
	this.setSituacao(situacao);
}

	
	public Carro(String placa, String ano, int qtdePortas, int qtdeMalasSuportadas,
				Cor cor, Modelo modelo, Categoria categoria, ImagemCarro carroUrl,
				SituacaoType situacao) {
		
		this.setPlaca(placa);
		this.setAno(ano);
		this.setQtdePortas(qtdePortas);
		this.setQtdeMalasSuportadas(qtdeMalasSuportadas);
		this.setCor(cor);
		this.setModelo(modelo);
		this.setCategoria(categoria);
		this.setCarroUrl(carroUrl);
		this.setSituacao(situacao);
	}
	
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
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
	public ImagemCarro getCarroUrl() {
		return carroUrl;
	}
	public void setCarroUrl(ImagemCarro carroUrl) {
		this.carroUrl = carroUrl;
	}
	
	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("Modelo: ");
		retorno.append(this.getModelo().getNome());
		retorno.append("\nPlaca: ");
		retorno.append(this.getPlaca());
		retorno.append("\nAno: ");
		retorno.append(this.getAno());
		retorno.append("\nNúmero de Portas: ");
		retorno.append(this.getQtdePortas());
		retorno.append("\nQtde Malas Suportadas: ");
		retorno.append(this.getQtdeMalasSuportadas());
		retorno.append("\nCor: ");
		retorno.append(this.getCor().getNome());
		
		retorno.append("\nCategoria: ");
		retorno.append(this.getCategoria().getNome());
		retorno.append("\nUrl Imagem: ");
		retorno.append(this.getCarroUrl().getCaminho());
		retorno.append("\nSituação: ");
		retorno.append(this.getSituacao().getDescricao());
		retorno.append("\n\n");
		
		return retorno.toString();
	}

}
