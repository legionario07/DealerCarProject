package br.com.dealercar.core.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensrevisao.VelasIgnicaoDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;
import br.com.dealercar.domain.produtosrevisao.VelasIgnicao;

public class VelasIgnicaoDAOTest {

	@Test
	@Ignore
	public void cadastrar(){
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao.setMarca("TEste");
		velasIgnicao.setDescricao("VelasIgnicao Para Carros");
		velasIgnicao.setTipo("Traseiro");
		velasIgnicao.setValor(17.98d);
		velasIgnicao.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		velasIgnicao.setFormaDeVenda(formaDeVenda);
		
		new VelasIgnicaoDAO().cadastrar(velasIgnicao);
	}
	
	@Test
	@Ignore
	public void excluir(){
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao.setId(3);
		
		new VelasIgnicaoDAO().excluir(velasIgnicao);
		
	}
	
	@Test
	@Ignore
	public void editar(){
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao.setId(2);
		velasIgnicao.setMarca("Cofab");
		velasIgnicao.setDescricao("VelasIgnicao Para Carros");
		velasIgnicao.setTipo("Dianteiro");
		velasIgnicao.setValor(17.98d);
		velasIgnicao.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		velasIgnicao.setFormaDeVenda(formaDeVenda);
		
		new VelasIgnicaoDAO().editar(velasIgnicao);
		
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorID(){
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao.setId(1);
		
		velasIgnicao = new VelasIgnicaoDAO().pesquisarPorID(velasIgnicao);
		
		System.out.println(velasIgnicao);
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorMarca(){
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao.setMarca("Cofab");
		
		List<VelasIgnicao> lista = new ArrayList<VelasIgnicao>();
		
		lista = new VelasIgnicaoDAO().pesquisarPorMarca(velasIgnicao);
		
		for(VelasIgnicao a : lista){
			System.out.println(a);
		}
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		lista = new VelasIgnicaoDAO().listarTodos();
		
		for(EntidadeDominio a : lista){
			System.out.println(a);
		}
	}
	
	@Test
	@Ignore
	public void pesquisarPorDescricaoMarcaTipo(){
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		
		String stringVelasIgnicao = "VELAS DE IGNICAO - NGK - MOTOR EM GERAL";
		
		velasIgnicao = new VelasIgnicaoDAO().pesquisarPorDescricaoMarcaTipo(stringVelasIgnicao);
		
		System.out.println(velasIgnicao.getId());
		System.out.println(velasIgnicao.getDescricao());
		System.out.println(velasIgnicao.getMarca());
		System.out.println(velasIgnicao.getTipo());
		System.out.println(velasIgnicao.getValor());
		
	}
	
}
