package br.com.dealercar.web.converter;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dealercar.core.negocio.Revisao;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.itensrevisao.Componentes;
import br.com.dealercar.domain.itensrevisao.Pneu;

@FacesConverter("generic")
public class GenericConverter implements Converter<Object>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null) {

			return value;
		}
		return null;
	}

	public String getAsString(FacesContext ctx, UIComponent component, Object value) {

		if (value instanceof Pneu) {
			Pneu pneu = new Pneu();
			pneu = ((Pneu) value);

			return pneu.getSituacao();

		}

		if (value instanceof Componentes) {
			Componentes componentes = new Componentes();
			componentes = (Componentes) value;

			return componentes.getSituacao();
		}
		
		if (value instanceof Fabricante) {
			Fabricante fabricante = new Fabricante();
			fabricante = (Fabricante) value;

			return fabricante.getNome();
		}

		return null;
	}

	protected void addAttribute(UIComponent component, Revisao o) {
		String key = String.valueOf(o.getId()); // codigo da empresa como chave
												// neste caso
		this.getAttributesFrom(component).put(key, o);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}
