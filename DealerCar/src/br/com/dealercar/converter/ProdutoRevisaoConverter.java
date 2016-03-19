package br.com.dealercar.converter;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dealercar.dao.itensrevisao.AmortecedorDAO;
import br.com.dealercar.dao.itensrevisao.CorreiaDentadaDAO;
import br.com.dealercar.dao.itensrevisao.EmbreagemDAO;
import br.com.dealercar.dao.itensrevisao.FarolDAO;
import br.com.dealercar.dao.itensrevisao.FiltroDeArDAO;
import br.com.dealercar.dao.itensrevisao.FiltroDeOleoMotorDAO;
import br.com.dealercar.dao.itensrevisao.FluidoDeFreioDAO;
import br.com.dealercar.dao.itensrevisao.PastilhaFreioDAO;
import br.com.dealercar.dao.itensrevisao.PneuDAO;
import br.com.dealercar.dao.itensrevisao.VelasIgnicaoDAO;
import br.com.dealercar.domain.Revisao;
import br.com.dealercar.domain.itensrevisao.Componentes;
import br.com.dealercar.domain.produtosrevisao.Amortecedor;
import br.com.dealercar.domain.produtosrevisao.CorreiaDentada;
import br.com.dealercar.domain.produtosrevisao.Embreagem;
import br.com.dealercar.domain.produtosrevisao.Farol;
import br.com.dealercar.domain.produtosrevisao.FiltroDeAr;
import br.com.dealercar.domain.produtosrevisao.FiltroDeOleoMotor;
import br.com.dealercar.domain.produtosrevisao.FluidoDeFreio;
import br.com.dealercar.domain.produtosrevisao.PastilhaFreio;
import br.com.dealercar.domain.produtosrevisao.Pneu;
import br.com.dealercar.domain.produtosrevisao.VelasIgnicao;

@FacesConverter("produtoRevisao")
public class ProdutoRevisaoConverter implements Converter, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
	
		if (value != null) {

			if(value.startsWith("AMORTECEDOR")){
				
				Amortecedor amortecedor = new Amortecedor();
				amortecedor = new AmortecedorDAO().pesquisarPorDescricaoMarcaTipo(value);
				
				return amortecedor;
			} else if(value.startsWith("CORREIA")){
				
				CorreiaDentada correiaDentada = new CorreiaDentada();
				correiaDentada = new CorreiaDentadaDAO().pesquisarPorDescricaoMarcaTipo(value);
				
				return correiaDentada;
			} else if(value.startsWith("EMBREAGEM")){
				
				Embreagem embreagem = new Embreagem();
				embreagem = new EmbreagemDAO().pesquisarPorDescricaoMarcaTipo(value);
				
				return embreagem;
			} else if(value.startsWith("LANTERNA")){
				
				Farol farol = new Farol();
				farol = new FarolDAO().pesquisarPorDescricaoMarcaTipo(value);
				
				return farol;
			} else if(value.startsWith("FILTRO DE AR")){
				
				FiltroDeAr filtroDeAr = new FiltroDeAr();
				filtroDeAr = new FiltroDeArDAO().pesquisarPorDescricaoMarcaTipo(value);
				
				return filtroDeAr;
			} else if(value.startsWith("FILTRO DE ÓLEO")){
				
				FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
				filtroDeOleoMotor = new FiltroDeOleoMotorDAO().pesquisarPorDescricaoMarcaTipo(value);
				
				return filtroDeOleoMotor;
			} else if(value.startsWith("FLUIDO DE FREIO")){
				
				FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
				fluidoDeFreio = new FluidoDeFreioDAO().pesquisarPorDescricaoMarcaTipo(value);
				
				return fluidoDeFreio;
			} else if(value.startsWith("PASTILHA")){
				
				PastilhaFreio pastilhaFreio = new PastilhaFreio();
				pastilhaFreio = new PastilhaFreioDAO().pesquisarPorDescricaoMarcaTipo(value);
				
				return pastilhaFreio;
			} else if(value.startsWith("PNEU")){
				
				Pneu pneu = new Pneu();
				pneu = new PneuDAO().pesquisarPorDescricaoMarcaTipo(value);
				
				return pneu;
			} else if(value.startsWith("VELAS")){
				
				VelasIgnicao velasIgnicao = new VelasIgnicao();
				velasIgnicao = new VelasIgnicaoDAO().pesquisarPorDescricaoMarcaTipo(value);
				
				return velasIgnicao;
			}
			
			
		}
		return null;
	}

	public String getAsString(FacesContext ctx, UIComponent component, Object value) {

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
