package br.com.dealercar.domain.itensopcionais;

public class Gps extends Itens{
	
	private String idioma;
	
	public Gps() {

	}
	
	public Gps(int codigo){
		super(codigo);
	}
	
	public Gps(String descricao, double valor, String marca, String numeroPatrimonio, String idioma) {
		
		super(descricao, valor, marca, numeroPatrimonio);
		
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
		
		retorno.append(super.toString());
		
		retorno.append("\nIdioma: ");
		retorno.append(this.getIdioma());
		retorno.append("\n\n");
		
		return retorno.toString();
	}

}
