package br.com.dealercar.core.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensrevisao.FiltroDeOleoMotorDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.produtosrevisao.FiltroDeOleoMotor;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class FiltroDeOleoMotorDAOTest {

	@Test
	@Ignore
	public void cadastrar(){
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor.setMarca("TEste");
		filtroDeOleoMotor.setDescricao("FiltroDeOleoMotor Para Carros");
		filtroDeOleoMotor.setTipo("Traseiro");
		filtroDeOleoMotor.setValor(17.98d);
		filtroDeOleoMotor.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		filtroDeOleoMotor.setFormaDeVenda(formaDeVenda);
		
		new FiltroDeOleoMotorDAO().cadastrar(filtroDeOleoMotor);
		
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor.setId(3);
		
		new FiltroDeOleoMotorDAO().excluir(filtroDeOleoMotor);
		
	}
	
	@Test
	@Ignore
	public void editar(){
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor.setId(2);
		filtroDeOleoMotor.setMarca("Cofab");
		filtroDeOleoMotor.setDescricao("FiltroDeOleoMotor Para Carros");
		filtroDeOleoMotor.setTipo("Dianteiro");
		filtroDeOleoMotor.setValor(17.98d);
		filtroDeOleoMotor.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		filtroDeOleoMotor.setFormaDeVenda(formaDeVenda);
		
		new FiltroDeOleoMotorDAO().editar(filtroDeOleoMotor);
		
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorID(){
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor.setId(1);
		
		filtroDeOleoMotor = new FiltroDeOleoMotorDAO().pesquisarPorID(filtroDeOleoMotor);
		
		System.out.println(filtroDeOleoMotor);
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorMarca(){
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor.setMarca("Cofab");
		
		List<FiltroDeOleoMotor> lista = new ArrayList<FiltroDeOleoMotor>();
		
		lista = new FiltroDeOleoMotorDAO().pesquisarPorMarca(filtroDeOleoMotor);
		
		for(FiltroDeOleoMotor a : lista){
			System.out.println(a);
		}
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		lista = new FiltroDeOleoMotorDAO().listarTodos();
		
		for(EntidadeDominio a : lista){
			System.out.println(a);
		}
	}
	
	@Test
	@Ignore
	public void pesquisarPorDescricaoMarcaTipo(){
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		
		String stringFiltroDeOleoMotor = "FILTRO DE ÓLEO - FRAM - MOTOR EM GERAL";
		
		filtroDeOleoMotor = new FiltroDeOleoMotorDAO().pesquisarPorDescricaoMarcaTipo(stringFiltroDeOleoMotor);
		
		System.out.println(filtroDeOleoMotor.getId());
		System.out.println(filtroDeOleoMotor.getDescricao());
		System.out.println(filtroDeOleoMotor.getMarca());
		System.out.println(filtroDeOleoMotor.getTipo());
		System.out.println(filtroDeOleoMotor.getValor());
		
	}
	
	
}
