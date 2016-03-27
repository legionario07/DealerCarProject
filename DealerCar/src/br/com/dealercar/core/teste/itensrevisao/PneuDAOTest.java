package br.com.dealercar.core.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensrevisao.PneuDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;
import br.com.dealercar.domain.produtosrevisao.Pneu;

public class PneuDAOTest {

	@Test
	@Ignore
	public void cadastrar(){
		Pneu pneu = new Pneu();
		pneu.setMarca("TEste");
		pneu.setDescricao("Pneu Para Carros");
		pneu.setTipo("Traseiro");
		pneu.setValor(17.98d);
		pneu.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		pneu.setFormaDeVenda(formaDeVenda);
		
		new PneuDAO().cadastrar(pneu);
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		Pneu pneu = new Pneu();
		pneu.setId(3);
		
		new PneuDAO().excluir(pneu);
		
	}
	
	@Test
	@Ignore
	public void editar(){
		Pneu pneu = new Pneu();
		pneu.setId(2);
		pneu.setMarca("Cofab");
		pneu.setDescricao("Pneu Para Carros");
		pneu.setTipo("Dianteiro");
		pneu.setValor(17.98d);
		pneu.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		pneu.setFormaDeVenda(formaDeVenda);
		
		new PneuDAO().editar(pneu);
		
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorID(){
		Pneu pneu = new Pneu();
		pneu.setId(1);
		
		pneu = new PneuDAO().pesquisarPorID(pneu);
		
		System.out.println(pneu);
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorMarca(){
		Pneu pneu = new Pneu();
		pneu.setMarca("Cofab");
		
		List<Pneu> lista = new ArrayList<Pneu>();
		
		lista = new PneuDAO().pesquisarPorMarca(pneu);
		
		for(Pneu a : lista){
			System.out.println(a);
		}
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		lista = new PneuDAO().listarTodos();
		
		for(EntidadeDominio a : lista){
			System.out.println(a);
		}
	}
	
	@Test
	@Ignore
	public void pesquisarPorDescricaoMarcaTipo(){
		Pneu pneu = new Pneu();
		
		String stringPneu = "PNEU 175/70 - BRIDGESTONE - ARO 14";
		
		pneu = new PneuDAO().pesquisarPorDescricaoMarcaTipo(stringPneu);
		
		System.out.println(pneu.getId());
		System.out.println(pneu.getDescricao());
		System.out.println(pneu.getMarca());
		System.out.println(pneu.getTipo());
		System.out.println(pneu.getValor());
		
	}
	
	
}
