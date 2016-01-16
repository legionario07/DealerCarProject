package br.com.dealercar.autenticacao;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST,
		DispatcherType.FORWARD,
		DispatcherType.ERROR,
		DispatcherType.INCLUDE
} , urlPatterns = {"/*"})
public class ControleDeAcesso implements Filter{


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		//HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		HttpSession sessao = httpServletRequest .getSession();
		
		String url = httpServletRequest.getRequestURI();
		
		
		//Está Logado?
		if(sessao.getAttribute("usuarioLogado")!=null || url.lastIndexOf("login.xhtml")>-1 || 
				httpServletRequest.getRequestURI().contains("javax.faces.resource/")){
			
			chain.doFilter(request, response);
		} else{
			((HttpServletResponse) response).sendRedirect("login.xhtml"); 
			//redireciona para login
		}
		

		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
