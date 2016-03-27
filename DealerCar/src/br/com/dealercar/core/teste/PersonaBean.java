package br.com.dealercar.core.teste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "MBPersona")
@ViewScoped
public class PersonaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Persona> personas = new ArrayList<Persona>();

	/**
	 * Metodo que preenche os dados antes de gerar o Relatório.
	 */
	public void preencherDados() {
		Persona p = new Persona();
		p.setNome("Felipe da Silva");
		p.setApelido("Felipe");
		Calendar cal = Calendar.getInstance();
		cal.set(1992, 3, 3);
		p.setDataNascimento(cal.getTime());

		personas.add(p);

		Persona p2 = new Persona();
		p2.setNome("Paulo Silva");
		p2.setApelido("Paulo");
		Calendar cal2 = Calendar.getInstance();
		cal.set(1992, 3, 2);
		p2.setDataNascimento(cal2.getTime());
		personas.add(p2);

	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
	public void exportarPDF(){
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nomeRelatorio", "CARROS LOCADOS");
		
		
		//GeraRelatorio.exportarPDF(parametros, Conexao.getConnection());
		
	}

}
