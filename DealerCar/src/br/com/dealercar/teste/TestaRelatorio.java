package br.com.dealercar.teste;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.ClienteDAO;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.relatorios.Relatorio;

public class TestaRelatorio {

	public static void main(String[] args) {
		
		List<Cliente> lista = new ArrayList<Cliente>();
		
		lista = new ClienteDAO().listarTodos();
		
		Relatorio<Cliente> relatorio = new Relatorio<Cliente>();
		System.out.println(new File("").getAbsolutePath());
		System.out.println(new File("").getPath());
		
		//relatorio.gerarRelatorio(lista);
		
	}
}
