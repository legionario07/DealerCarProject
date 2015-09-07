package br.com.dealercar.domain.itensopcionais;

public class Seguro extends Opcional{
	
	private TipoSeguro tipoSeguro;
	
	public Seguro() {

	}
	
	public Seguro(int codigo) {
		this.setCodigo(codigo);
	}

	public Seguro(String descricao, double valor, TipoSeguro tipo){
		this.setDescricao(descricao);
		this.setValor(valor);
		this.setTipoSeguro(tipo);
	}


	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}
	
	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	
	
	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append(super.toString());
		retorno.append("\nTipo Seguro: ");
		retorno.append(this.tipoSeguro.getNome().getDescricao());
		retorno.append("\nValor acrescido: ");
		retorno.append(this.getTipoSeguro().getValorAcrescido());
		retorno.append("\n\n");
		
		return retorno.toString();
	}
}
