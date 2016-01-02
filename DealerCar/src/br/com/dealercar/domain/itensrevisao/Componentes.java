package br.com.dealercar.domain.itensrevisao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe herdadas pelos itens de Revisão
 * @author Paulinho
 *
 */
public class Componentes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String situacao;
	private Arrefecimento arrefecimento;
	private Bateria bateria;
	private Embreagem embreagem;
	private Freio freio;
	private Lanterna lanterna;
	private Motor motor;
	private List<Pneu> pneus = new ArrayList<Pneu>();

	private Suspensao suspensao;
	
	public Componentes() {
		
	}
	
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public List<Pneu> getPneus() {
		return pneus;
	}
	
	public void setPneus(List<Pneu> pneus) {
		this.pneus = pneus;
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
