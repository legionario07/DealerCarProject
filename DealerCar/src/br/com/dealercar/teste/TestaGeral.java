package br.com.dealercar.teste;

import java.text.ParseException;

import br.com.dealercar.dao.ClienteDAO;
import br.com.dealercar.domain.Cliente;

public class TestaGeral {

	public static void main(String[] args) throws ParseException {
		

		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		cliente1.setId(1); //cpf 369.429.508-90
		cliente2.setId(2); //cpf 139.492.883-98
		
		cliente1 = new ClienteDAO().pesquisarPorID(cliente1);
		cliente2 = new ClienteDAO().pesquisarPorID(cliente2);
		
		
	}
}
