package br.com.dealercar.domain.itensopcionais;

public class CadeirinhaBebe extends Itens{
	
	private int pesoBebe;
	
	public CadeirinhaBebe() {

	}
	
	public CadeirinhaBebe(int codigo){
		super(codigo);
	}
	
	public CadeirinhaBebe(String descricao, double valor, String marca, String numeroPatrimonio, int pesoBebe) {
		
		super(descricao, valor, marca, numeroPatrimonio);
		
		this.setPesoBebe(pesoBebe);
	}

	public int getPesoBebe() {
		return pesoBebe;
	}

	public void setPesoBebe(int pesoBebe) {
		this.pesoBebe = pesoBebe;
	}
	
	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		
		retorno.append(super.toString());
		retorno.append("\nPeso do Bebê: ");
		retorno.append(this.getPesoBebe());
		retorno.append("\n\n");
		
		return retorno.toString();
	}

}
