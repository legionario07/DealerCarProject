package br.com.dealercar.domain.itensopcionais;

public class Gps extends Itens{
	
	private String idioma;
	
	public Gps() {
		
	}
	
	public Gps(String descricao, double valor, String idioma) {
		super();
		this.setIdioma(idioma);
	}

	public String getIdioma() {
		return idioma.toUpperCase();
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma.toUpperCase();
	}
	
	@Override
	public String toString() {
		
		StringBuffer retorno = new StringBuffer();
		super.toString();
		/*retorno.append("Código: ");
		retorno.append(this.getCodigo());
		retorno.append("\nDescrição: ");
		retorno.append(this.getDescricao());
		retorno.append("\nValor:: ");
		retorno.append(this.getValor());*/
		retorno.append("\nIdioma: ");
		retorno.append(this.getIdioma());
		retorno.append("\n\n");
		
		return retorno.toString();
	}

}
