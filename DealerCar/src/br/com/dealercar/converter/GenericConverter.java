package br.com.dealercar.converter;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dealercar.domain.Revisao;
import br.com.dealercar.domain.itensrevisao.Componentes;
import br.com.dealercar.domain.itensrevisao.Pneu;

@FacesConverter("generic")
public class GenericConverter implements Converter, Serializable {
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
