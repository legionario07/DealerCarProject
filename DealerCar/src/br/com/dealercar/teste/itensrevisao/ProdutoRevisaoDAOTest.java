package br.com.dealercar.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensrevisao.AmortecedorDAO;
import br.com.dealercar.dao.itensrevisao.PneuDAO;
import br.com.dealercar.dao.itensrevisao.ProdutoRevisaoDAO;
import br.com.dealercar.dao.itensrevisao.VelasIgnicaoDAO;
import br.com.dealercar.domain.produtosrevisao.Amortecedor;
import br.com.dealercar.domain.produtosrevisao.Pneu;
import br.com.dealercar.domain.produtosrevisao.ProdutoRevisao;
import br.com.dealercar.domain.produtosrevisao.VelasIgnicao;

public class ProdutoRevisaoDAOTest {

	public static void cadastrar() {


		List<ProdutoRevisao> produtos = new ArrayList<ProdutoRevisao>();

		Amortecedor amortecedor = new Amortecedor();
		amortecedor.setId(1);
		amortecedor = new AmortecedorDAO().pesquisarPorID(amortecedor);
		amortecedor.setQuantidade(2);

		Pneu pneu = new Pneu();
		pneu.setId(1);
		pneu = new PneuDAO().pesquisarPorID(pneu);
		pneu.setQuantidade(2);

		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao.setId(1);
		velasIgnicao = new VelasIgnicaoDAO().pesquisarPorID(velasIgnicao);
		velasIgnicao.setQuantidade(1);

		produtos.add(amortecedor);
		produtos.add(pneu);
		produtos.add(velasIgnicao);

		new ProdutoRevisaoDAO().cadastrar(produtos);

	}

	public static void pesquisarPorId() {

		List<ProdutoRevisao> produtos = new ArrayList<ProdutoRevisao>();

		ProdutoRevisao produtoRevisao = new ProdutoRevisao();
		produtoRevisao.setId(3);

		produtos = new ProdutoRevisaoDAO().pesquisarProdutoPorID(produtoRevisao);

		for (ProdutoRevisao p : produtos) {
			System.out.println(p);
		}

	}

	public static void pesquisarPorUltimoCadastrado() {

		List<ProdutoRevisao> produtos = new ArrayList<ProdutoRevisao>();


		produtos = new ProdutoRevisaoDAO().pesquisarPorUltimoCadastrado();

		for (ProdutoRevisao p : produtos) {
			System.out.println(p);
		}
	}

	public static void main(String[] args) {

		// cadastrar();
		//pesquisarPorId();
		pesquisarPorUltimoCadastrado();

	}
}
