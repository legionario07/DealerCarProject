package br.com.dealercar.domain.itensopcionais;

public class CadeirinhaBebe extends Itens{
	
	private float pesoBebe;
	
	public CadeirinhaBebe() {

	}
	
	public CadeirinhaBebe(String descricao, double valor, float pesoBebe) {
		super();
		this.setPesoBebe(pesoBebe);
	}

	public float getPesoBebe() {
		return pesoBebe;
	}

	public void setPesoBebe(float pesoBebe) {
		this.pesoBebe = pesoBebe;
	}
	
	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		
		super.toString();
		retorno.append("\nPeso do Bebê: ");
		retorno.append(this.getPesoBebe());
		retorno.append("\n\n");
		
		return retorno.toString();
	}

}
