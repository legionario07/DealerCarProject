package br.com.dealercar.core.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidadorEmail implements Validator<Object>{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String email = (String) arg2;
		
		if(email == null){
			return;
		}
		if (email.indexOf('@') == -1 || email.contains(" ") || email.length()<3 || email.endsWith("@") ||
				email.startsWith("@")){
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Email inválido!", 
					"Favor informar um Email válido."));
		}
		
	}
	

}
