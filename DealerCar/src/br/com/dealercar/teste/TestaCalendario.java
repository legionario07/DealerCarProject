package br.com.dealercar.teste;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "MBTestaCalendario")
@ViewScoped
@SuppressWarnings("unused")
public class TestaCalendario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date data;
	
	  public void onDateSelect(SelectEvent event) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    }
	     
	    public void click() {
	        RequestContext requestContext = RequestContext.getCurrentInstance();
	         
	        requestContext.update("form:display");
	        requestContext.execute("PF('dlg').show()");
	    }

		public Date getData() {
			return data;
		}

		public void setData(Date data) {
			this.data = data;
		}
	
	
}
