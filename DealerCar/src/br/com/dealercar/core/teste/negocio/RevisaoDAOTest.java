package br.com.dealercar.core.teste.negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.dao.DevolucaoDAO;
import br.com.dealercar.core.dao.FuncionarioDAO;
import br.com.dealercar.core.dao.RevisaoDAO;
import br.com.dealercar.core.dao.itensrevisao.ProdutoRevisaoDAO;
import br.com.dealercar.core.negocio.Devolucao;
import br.com.dealercar.core.negocio.Revisao;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.enums.PosicaoPneu;
import br.com.dealercar.domain.itensrevisao.Arrefecimento;
import br.com.dealercar.domain.itensrevisao.Bateria;
import br.com.dealercar.domain.itensrevisao.Componentes;
import br.com.dealercar.domain.itensrevisao.Embreagem;
import br.com.dealercar.domain.itensrevisao.Freio;
import br.com.dealercar.domain.itensrevisao.Lanterna;
import br.com.dealercar.domain.itensrevisao.Motor;
import br.com.dealercar.domain.itensrevisao.Pneu;
import br.com.dealercar.domain.itensrevisao.Suspensao;
import br.com.dealercar.domain.produtosrevisao.ProdutoRevisao;

public class RevisaoDAOTest {

	@Test
	@Ignore
	public void cadastrar() {
		Revisao revisao = new Revisao();

		Devolucao devolucao = new Devolucao();
		devolucao.setId(3);
		devolucao = new DevolucaoDAO().pesquisarPorID(devolucao);
		revisao.setDevolucao(devolucao);

		Componentes componente = new Componentes();

		Arrefecimento arrefecimento = new Arrefecimento();
		arrefecimento.setSituacao("Ok");

		Bateria bateria = new Bateria();
		bateria.setSituacao("OK");

		Embreagem embreagem = new Embreagem();
		embreagem.setSituacao("Ok");

		Freio freio = new Freio();
		freio.setSituacao("Ok");

		Lanterna lanterna = new Lanterna();
		lanterna.setSituacao("Ok");

		Motor motor = new Motor();
		motor.setSituacao("Ok");

		Suspensao suspensao = new Suspensao();
		suspensao.setSituacao("Ok");

		Pneu pneu = new Pneu();
		pneu.setPosicaoPneu(PosicaoPneu.DIANTEIRO_DIREITO);
		Pneu pneu2 = new Pneu();
		pneu.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
		Pneu pneu3 = new Pneu();
		pneu.setPosicaoPneu(PosicaoPneu.TRASEIRO_DIREITO);
		Pneu pneu4 = new Pneu();
		pneu.setPosicaoPneu(PosicaoPneu.TRASEIRO_ESQUERDO);
		Pneu pneu5 = new Pneu();
		pneu.setPosicaoPneu(PosicaoPneu.ESTEPE);
		List<Pneu> pneus = new ArrayList<Pneu>();
		pneus.add(pneu);
		pneus.add(pneu2);
		pneus.add(pneu3);
		pneus.add(pneu4);
		pneus.add(pneu5);

		componente.setArrefecimento(arrefecimento);
		componente.setBateria(bateria);
		componente.setEmbreagem(embreagem);
		componente.setFreio(freio);
		componente.setLanterna(lanterna);
		componente.setMotor(motor);
		componente.setSuspensao(suspensao);
		componente.setPneus(pneus);

		revisao.setComponentes(componente);

		Funcionario funcionario = new Funcionario(7);
		funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
		revisao.setFuncionario(funcionario);

		revisao.setCarro(devolucao.getRetirada().getCarro());
		revisao.setDataRevisao(DataUtil.pegarDataAtualDoSistema());
		revisao.setQuilometragem(200000L);
		
		revisao.setDescricao("Todos os componentes foram revisados. Todos Ok");
		
		List<ProdutoRevisao> produtos = new ArrayList<ProdutoRevisao>();
		produtos = new ProdutoRevisaoDAO().pesquisarPorUltimoCadastrado();
		
		revisao.setListaProdutoRevisao(produtos);

		RevisaoDAO revisaoDao = new RevisaoDAO();
		revisaoDao.cadastrar(revisao);

	}

	@Test
	@Ignore
	public void listarTodos() {

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		lista = new RevisaoDAO().listarTodos();

		for (EntidadeDominio r : lista) {
			System.out.println(r);
		}

		System.out.println();

		System.out.println(lista.size());
	}
	
	@Test
	@Ignore
	public void procurarPorPlaca() {

		List<Revisao> lista = new ArrayList<Revisao>();
		
		Carro carro = new Carro("VVV-9898");
		

		lista = new RevisaoDAO().pesquisarPorPlaca(carro);

		for (Revisao r : lista) {
			System.out.println(r);
		}

		System.out.println();

		System.out.println(lista.size());
	}
	
	@Test
	@Ignore
	public void procurarPorID(){
		
		Revisao revisao = new Revisao(4);
		
		revisao = new RevisaoDAO().pesquisarPorID(revisao);
		
		System.out.println(revisao);
		
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorIntervalo(){
		
		Revisao revisao = new Revisao();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataRevisao = "01/01/2016";
		String dataFinal = "22/02/2016";
		try {
			revisao.setDataRevisao(sdf.parse(dataRevisao));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Revisao> lista = new ArrayList<Revisao>();
		try {
			lista = new RevisaoDAO().pesquisarPorIntervaloData(revisao, sdf.parse(dataFinal));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(Revisao r : lista){
			System.out.println(r.getDataRevisao());
		}
	}
	

	@Test
	@Ignore
	public void procurarPorModelo() {

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		Revisao revisao = new Revisao();
		revisao.getCarro().getModelo().setId(2);
		

		lista = new RevisaoDAO().pesquisarPorModelo(revisao);

		for (EntidadeDominio r : lista) {
			System.out.println(r);
		}

		System.out.println(lista.size());
	}

	

}
