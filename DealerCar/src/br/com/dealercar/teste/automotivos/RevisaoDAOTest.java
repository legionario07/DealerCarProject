package br.com.dealercar.teste.automotivos;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.RevisaoDAO;
import br.com.dealercar.domain.Revisao;
import br.com.dealercar.domain.automotivos.Carro;

public class RevisaoDAOTest {

	public static void cadastrar() {
		/*
		Revisao revisao = new Revisao();

		Devolucao devolucao = new Devolucao();
		devolucao.setId(3);
		devolucao = new DevolucaoDAO().pesquisarPorID(devolucao);
		revisao.setDevolucao(devolucao);

		Componentes componente = new Componentes();

		Arrefecimento arrefecimento = new Arrefecimento();
		arrefecimento.setOk(true);

		Bateria bateria = new Bateria();
		bateria.setOk(true);

		Embreagem embreagem = new Embreagem();
		embreagem.setOk(true);

		Freio freio = new Freio();
		freio.setOk(true);

		Lanterna lanterna = new Lanterna();
		lanterna.setOk(true);

		Motor motor = new Motor();
		motor.setOk(true);

		Suspensao suspensao = new Suspensao();
		suspensao.setOk(true);

		Pneu pneu = new Pneu();
		pneu.getPosicaoPneu();
		pneu.setOk(true);

		componente.setArrefecimento(arrefecimento);
		componente.setBateria(bateria);
		componente.setEmbreagem(embreagem);
		componente.setFreio(freio);
		componente.setLanterna(lanterna);
		componente.setMotor(motor);
		componente.setSuspensao(suspensao);
		componente.setPneu(pneu);

		//revisao.setItensParaVerificar(componente);

		Funcionario funcionario = new Funcionario(7);
		funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
		revisao.setFuncionario(funcionario);

		revisao.setCarro(devolucao.getRetirada().getCarro());
		revisao.setDataRevisao(DataUtil.pegarDataAtualDoSistema());
		revisao.setQuilometragem(200000L);

		revisao.setDescricao("Todos os componentes foram revisados. Todos Ok");

		RevisaoDAO revisaoDao = new RevisaoDAO();
		revisaoDao.cadastrar(revisao);
		*/

	}

	public static void listarTodos() {

		List<Revisao> lista = new ArrayList<Revisao>();

		lista = new RevisaoDAO().listarTodos();

		for (Revisao r : lista) {
			System.out.println(r);
		}

		System.out.println();

		System.out.println(lista.size());
	}
	

	public static void procurarPorPlaca() {

		List<Revisao> lista = new ArrayList<Revisao>();
		
		Carro carro = new Carro("VVV-9898");
		

		lista = new RevisaoDAO().pesquisarPorPlaca(carro);

		for (Revisao r : lista) {
			System.out.println(r);
		}

		System.out.println();

		System.out.println(lista.size());
	}
	


	
	public static void procurarPorID(){
		
		Revisao revisao = new Revisao(4);
		
		revisao = new RevisaoDAO().pesquisarPorID(revisao);
		
		System.out.println(revisao);
		
		
	}

	public static void main(String[] args) {

		// cadastrar();
		//listarTodos();
		procurarPorPlaca();
		//procurarPorID();
	}

}
