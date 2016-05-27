package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.conducao.Retirada;
import br.com.dealercar.web.command.ICommand;

public abstract class AbstractGraficoBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected List<String> meses = new ArrayList<String>();
	protected Map<String, String> mesesInicio = new HashMap<String, String>();
	protected Map<String, String> mesesFim = new HashMap<String, String>();
	protected List<String> listaAnos = new ArrayList<String>();
	protected String ano;
	protected String tipoDados;

	public AbstractGraficoBean() {
		meses.add("Janeiro");
		meses.add("Fevereiro");
		meses.add("Março");
		meses.add("Abril");
		meses.add("Maio");
		meses.add("Junho");
		meses.add("Julho");
		meses.add("Agosto");
		meses.add("Setembro");
		meses.add("Outubro");
		meses.add("Novembro");
		meses.add("Dezembro");

		int i = -1;
		// Setando os hash com as datas inicio do meses
		mesesInicio.put(meses.get(++i), "01/01/");
		mesesInicio.put(meses.get(++i), "01/02/");
		mesesInicio.put(meses.get(++i), "01/03/");
		mesesInicio.put(meses.get(++i), "01/04/");
		mesesInicio.put(meses.get(++i), "01/05/");
		mesesInicio.put(meses.get(++i), "01/06/");
		mesesInicio.put(meses.get(++i), "01/07/");
		mesesInicio.put(meses.get(++i), "01/08/");
		mesesInicio.put(meses.get(++i), "01/09/");
		mesesInicio.put(meses.get(++i), "01/10/");
		mesesInicio.put(meses.get(++i), "01/11/");
		mesesInicio.put(meses.get(++i), "01/12/");

		i = -1;

		// Setando os hash com as datas finais dos meses
		mesesFim.put(meses.get(++i), "31/01/");
		mesesFim.put(meses.get(++i), "29/02/");
		mesesFim.put(meses.get(++i), "31/03/");
		mesesFim.put(meses.get(++i), "30/04/");
		mesesFim.put(meses.get(++i), "31/05/");
		mesesFim.put(meses.get(++i), "30/06/");
		mesesFim.put(meses.get(++i), "31/07/");
		mesesFim.put(meses.get(++i), "31/08/");
		mesesFim.put(meses.get(++i), "30/09/");
		mesesFim.put(meses.get(++i), "31/10/");
		mesesFim.put(meses.get(++i), "30/11/");
		mesesFim.put(meses.get(++i), "31/12/");

		// inicialia a lista de anos para ser exibida na view
		popularListaDeAnos();
		
	}

	public List<String> getMeses() {
		return meses;
	}

	public void setMeses(List<String> meses) {
		this.meses = meses;
	}

	public Map<String, String> getMesesInicio() {
		return mesesInicio;
	}

	public void setMesesInicio(Map<String, String> mesesInicio) {
		this.mesesInicio = mesesInicio;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getTipoDados() {
		return tipoDados;
	}

	public void setTipoDados(String tipoDados) {
		this.tipoDados = tipoDados;
	}

	public List<String> getListaAnos() {
		return listaAnos;
	}

	public void setListaAnos(List<String> listaAnos) {
		this.listaAnos = listaAnos;
	}

	public Map<String, String> getMesesFim() {
		return mesesFim;
	}

	public void setMesesFim(Map<String, String> mesesFim) {
		this.mesesFim = mesesFim;
	}

	private void popularListaDeAnos() {

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapConducaoCommands.get("LISTAR");

		Resultado resultado = new Resultado();
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		resultado = command.execute(new Retirada());
		if (resultado != null) {
			lista = resultado.getEntidades();
		}

		int maiorAno = 0;
		int menorAno = 9999;

		for (EntidadeDominio e : lista) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			// Recebendo o maior ano
			if (Integer.parseInt(sdf.format(((Retirada) e).getDataRetirada())) > maiorAno) {
				maiorAno = Integer.parseInt(sdf.format(((Retirada) e).getDataRetirada()));
			}
			// Recebendo o menor ano
			if (Integer.parseInt(sdf.format(((Retirada) e).getDataRetirada())) < menorAno) {
				menorAno = Integer.parseInt(sdf.format(((Retirada) e).getDataRetirada()));
			}
		}

		// recebe o intervalo do menorAno até o maiorAno
		for (; menorAno <= maiorAno; menorAno++) {
			listaAnos.add(String.valueOf(menorAno));
		}

	}
	
	protected abstract void limparGrafico();

}
