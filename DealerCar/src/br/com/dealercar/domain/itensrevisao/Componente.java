package br.com.dealercar.domain.itensrevisao;

public class Componente {
	
	private String descricao;
	private boolean ok = false;
	private Arrefecimento arrefecimento;
	private Bateria bateria;
	private Embreagem embreagem;
	private Freio freio;
	private Lanterna lanterna;
	private Motor motor;
	private Pneu pneu;
	private Suspensao suspensao;
	
	public Componente() {
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public Arrefecimento getArrefecimento() {
		return arrefecimento;
	}

	public void setArrefecimento(Arrefecimento arrefecimento) {
		this.arrefecimento = arrefecimento;
	}

	public Bateria getBateria() {
		return bateria;
	}

	public void setBateria(Bateria bateria) {
		this.bateria = bateria;
	}

	public Embreagem getEmbreagem() {
		return embreagem;
	}

	public void setEmbreagem(Embreagem embreagem) {
		this.embreagem = embreagem;
	}

	public Freio getFreio() {
		return freio;
	}

	public void setFreio(Freio freio) {
		this.freio = freio;
	}

	public Lanterna getLanterna() {
		return lanterna;
	}

	public void setLanterna(Lanterna lanterna) {
		this.lanterna = lanterna;
	}

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}

	public Pneu getPneu() {
		return pneu;
	}

	public void setPneu(Pneu pneu) {
		this.pneu = pneu;
	}

	public Suspensao getSuspensao() {
		return suspensao;
	}

	public void setSuspensao(Suspensao suspensao) {
		this.suspensao = suspensao;
	}

	@Override
	public String toString() {
	
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nItens Verificados: ");
		retorno.append(this.getArrefecimento());
		retorno.append(this.getBateria());
		retorno.append(this.getEmbreagem());
		retorno.append(this.getFreio());
		retorno.append(this.getLanterna());
		retorno.append(this.getMotor());
		retorno.append(this.getSuspensao());
		
		return retorno.toString();
	}
}
