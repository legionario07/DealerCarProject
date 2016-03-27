package br.com.dealercar.core.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensrevisao.FluidoDeFreioDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.produtosrevisao.FluidoDeFreio;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class FluidoDeFreioDAOTest {

	@Test
	@Ignore
	public void cadastrar(){
		FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
		fluidoDeFreio.setMarca("TEste");
		fluidoDeFreio.setDescricao("FluidoDeFreio Para Carros");
		fluidoDeFreio.setTipo("Traseiro");
		fluidoDeFreio.setValor(17.98d);
		fluidoDeFreio.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		fluidoDeFreio.setFormaDeVenda(formaDeVenda);
		
		new FluidoDeFreioDAO().cadastrar(fluidoDeFreio);
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
		fluidoDeFreio.setId(3);
		
		new FluidoDeFreioDAO().excluir(fluidoDeFreio);
		
	}
	
	@Test
	@Ignore
	public void editar(){
		FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
		fluidoDeFreio.setId(2);
		fluidoDeFreio.setMarca("Cofab");
		fluidoDeFreio.setDescricao("FluidoDeFreio Para Carros");
		fluidoDeFreio.setTipo("Dianteiro");
		fluidoDeFreio.setValor(17.98d);
		fluidoDeFreio.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		fluidoDeFreio.setFormaDeVenda(formaDeVenda);
		
		new FluidoDeFreioDAO().editar(fluidoDeFreio);
		
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorID(){
		FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
		fluidoDeFreio.setId(1);
		
		fluidoDeFreio = new FluidoDeFreioDAO().pesquisarPorID(fluidoDeFreio);
		
		System.out.println(fluidoDeFreio);
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorMarca(){
		FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
		fluidoDeFreio.setMarca("Cofab");
		
		List<FluidoDeFreio> lista = new ArrayList<FluidoDeFreio>();
		
		lista = new FluidoDeFreioDAO().pesquisarPorMarca(fluidoDeFreio);
		
		for(FluidoDeFreio a : lista){
			System.out.println(a);
		}
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		lista = new FluidoDeFreioDAO().listarTodos();
		
		for(EntidadeDominio a : lista){
			System.out.println(a);
		}
	}

	@Test
	@Ignore
	public void pesquisarPorDescricaoMarcaTipo(){
		FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
		
		String stringFluidoDeFreio = "FLUIDO DE FREIO - KOUBE - DOT 4";
		
		fluidoDeFreio = new FluidoDeFreioDAO().pesquisarPorDescricaoMarcaTipo(stringFluidoDeFreio);
		
		System.out.println(fluidoDeFreio.getId());
		System.out.println(fluidoDeFreio.getDescricao());
		System.out.println(fluidoDeFreio.getMarca());
		System.out.println(fluidoDeFreio.getTipo());
		System.out.println(fluidoDeFreio.getValor());
		
	}
}
