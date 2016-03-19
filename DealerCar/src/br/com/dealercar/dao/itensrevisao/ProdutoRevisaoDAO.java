package br.com.dealercar.dao.itensrevisao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.IDAO;
import br.com.dealercar.domain.produtosrevisao.Amortecedor;
import br.com.dealercar.domain.produtosrevisao.CorreiaDentada;
import br.com.dealercar.domain.produtosrevisao.Embreagem;
import br.com.dealercar.domain.produtosrevisao.Farol;
import br.com.dealercar.domain.produtosrevisao.FiltroDeAr;
import br.com.dealercar.domain.produtosrevisao.FiltroDeOleoMotor;
import br.com.dealercar.domain.produtosrevisao.FluidoDeFreio;
import br.com.dealercar.domain.produtosrevisao.PastilhaFreio;
import br.com.dealercar.domain.produtosrevisao.Pneu;
import br.com.dealercar.domain.produtosrevisao.ProdutoRevisao;
import br.com.dealercar.domain.produtosrevisao.VelasIgnicao;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * REaliza a persistencia dos Itens Opcionais Escolhidos pelo Usuario
 * 
 * @author Paulinho
 *
 */
public class ProdutoRevisaoDAO implements IDAO<ProdutoRevisao>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Connection con = null;

	public void cadastrar(List<ProdutoRevisao> listaProdutoRevisao) {

		StringBuffer sql = new StringBuffer();

		sql.append("insert into produto_revisao ");
		sql.append("(id_amortecedor, qtde_amortecedor, id_correia_dentada, qtde_correia_dentada, ");
		sql.append("id_embreagem, qtde_embreagem, id_farol, qtde_farol, ");
		sql.append("id_filtro_de_ar, qtde_filtro_de_ar, id_filtro_de_oleo_motor, qtde_filtro_de_oleo_motor, ");
		sql.append("id_fluido_de_freio, qtde_fluido_de_freio, id_pastilhas_freios, qtde_pastilhas_freio, ");
		sql.append("id_pneus, qtde_pneus, id_velas_ignicao, qtde_velas_ignicao) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

		boolean amortecedor = false;
		boolean correiaDentada = false;
		boolean embreagem = false;
		boolean farol = false;
		boolean filtorDeAr = false;
		boolean filtroDeOleoMotor = false;
		boolean fluidoDeFreio = false;
		boolean pastilhaFreio = false;
		boolean pneu = false;
		boolean velasIgnicao = false;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());

			// Verifica se foi escolhido os p opcionais
			for (ProdutoRevisao p : listaProdutoRevisao) {

				if (p instanceof Amortecedor) {
					if (p.getId() > 0 && p.getId() != 99) {
						pstm.setInt(1, p.getId());
						pstm.setInt(2, p.getQuantidade());
						amortecedor = true;
					}
				} else if (p instanceof CorreiaDentada) {
					if (p.getId() > 0 && p.getId() != 99) {
						pstm.setInt(3, p.getId());
						pstm.setInt(4, p.getQuantidade());
						correiaDentada = true;
					}
				} else if (p instanceof Embreagem) {
					if (p.getId() > 0 && p.getId() != 99) {
						pstm.setInt(5, p.getId());
						pstm.setInt(6, p.getQuantidade());
						embreagem = true;
					}
				} else if (p instanceof Farol) {
					if (p.getId() > 0 && p.getId() != 99) {
						pstm.setInt(7, p.getId());
						pstm.setInt(8, p.getQuantidade());
						farol = true;
					}
				} else if (p instanceof FiltroDeAr) {
					if (p.getId() > 0 && p.getId() != 99) {
						pstm.setInt(9, p.getId());
						pstm.setInt(10, p.getQuantidade());
						filtorDeAr = true;
					}
				} else if (p instanceof FiltroDeOleoMotor) {
					if (p.getId() > 0 && p.getId() != 99) {
						pstm.setInt(11, p.getId());
						pstm.setInt(12, p.getQuantidade());
						filtroDeOleoMotor = true;
					}
				} else if (p instanceof FluidoDeFreio) {
					if (p.getId() > 0 && p.getId() != 99) {
						pstm.setInt(13, p.getId());
						pstm.setInt(14, p.getQuantidade());
						fluidoDeFreio = true;
					}
				} else if (p instanceof PastilhaFreio) {
					if (p.getId() > 0 && p.getId() != 99) {
						pstm.setInt(15, p.getId());
						pstm.setInt(16, p.getQuantidade());
						pastilhaFreio = true;
					}
				} else if (p instanceof Pneu) {
					if (p.getId() > 0 && p.getId() != 99) {
						pstm.setInt(17, p.getId());
						pstm.setInt(18, p.getQuantidade());
						pneu = true;
					}
				} else if (p instanceof VelasIgnicao) {
					if (p.getId() > 0 && p.getId() != 99) {
						pstm.setInt(19, p.getId());
						pstm.setInt(20, p.getQuantidade());
						velasIgnicao = true;
					}
				}

			}

			// verificando quais produtos não foi incluido
			// incluindo o id 99 para o produtoRevisao que nao foi incluido (99
			// =
			// null)

			if (amortecedor == false) {
				pstm.setInt(1, 99);
				pstm.setInt(2, 0);
			}
			if (correiaDentada == false) {
				pstm.setInt(3, 99);
				pstm.setInt(4, 0);
			}
			if (embreagem == false) {
				pstm.setInt(5, 99);
				pstm.setInt(6, 0);
			}
			if (farol == false) {
				pstm.setInt(7, 99);
				pstm.setInt(8, 0);
			}
			if (filtorDeAr == false) {
				pstm.setInt(9, 99);
				pstm.setInt(10, 0);
			}
			if (filtroDeOleoMotor == false) {
				pstm.setInt(11, 99);
				pstm.setInt(12, 0);
			}
			if (fluidoDeFreio == false) {
				pstm.setInt(13, 99);
				pstm.setInt(14, 0);
			}
			if (pastilhaFreio == false) {
				pstm.setInt(15, 99);
				pstm.setInt(16, 0);
			}
			if (pneu == false) {
				pstm.setInt(17, 99);
				pstm.setInt(18, 0);
			}
			if (velasIgnicao == false) {
				pstm.setInt(19, 99);
				pstm.setInt(20, 0);
			}

			pstm.executeUpdate();

		} catch (

		SQLException e)

		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(ProdutoRevisao produtoRevisao) {

		// não há exclusão no BD desse tipo de objeto
	}

	@Override
	public void editar(ProdutoRevisao produtoRevisao) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ProdutoRevisao> listarTodos() {

		return null;
	}

	/**
	 * Pesquisa nas TAbelas de ProdutosRevisao cadastrados pelo seu ID
	 */
	public List<ProdutoRevisao> pesquisarProdutoPorID(ProdutoRevisao produtoRevisao) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from produto_revisao ");
		sql.append("where id = ? ");

		List<ProdutoRevisao> lista = new ArrayList<ProdutoRevisao>();

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, produtoRevisao.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				ProdutoRevisao produtoRevisaoRetorno = new ProdutoRevisao();

				produtoRevisaoRetorno.setId(rSet.getInt("id"));
				lista.add(produtoRevisaoRetorno);

				if (rSet.getInt("id_pneus") > 0 && rSet.getInt("id_pneus") != 99) {
					Amortecedor amortecedor = new Amortecedor();
					amortecedor.setId(rSet.getInt("id_pneus"));
					amortecedor = new AmortecedorDAO().pesquisarPorID(amortecedor);
					amortecedor.setQuantidade(rSet.getInt("qtde_pneus"));
					lista.add(amortecedor);

				}

				if (rSet.getInt("id_correia_dentada") > 0 && rSet.getInt("id_correia_dentada") != 99) {
					CorreiaDentada correiaDentada = new CorreiaDentada();
					correiaDentada.setId(rSet.getInt("id_correia_dentada"));
					correiaDentada = new CorreiaDentadaDAO().pesquisarPorID(correiaDentada);
					correiaDentada.setQuantidade(rSet.getInt("qtde_correia_dentada"));
					lista.add(correiaDentada);

				}

				if (rSet.getInt("id_embreagem") > 0 && rSet.getInt("id_embreagem") != 99) {
					Embreagem embreagem = new Embreagem();
					embreagem.setId(rSet.getInt("id_embreagem"));
					embreagem = new EmbreagemDAO().pesquisarPorID(embreagem);
					embreagem.setQuantidade(rSet.getInt("qtde_embreagem"));
					lista.add(embreagem);

				}

				if (rSet.getInt("id_farol") > 0 && rSet.getInt("id_farol") != 99) {
					Farol farol = new Farol();
					farol.setId(rSet.getInt("id_farol"));
					farol = new FarolDAO().pesquisarPorID(farol);
					farol.setQuantidade(rSet.getInt("qtde_farol"));
					lista.add(farol);
				}

				if (rSet.getInt("id_filtro_de_ar") > 0 && rSet.getInt("id_filtro_de_ar") != 99) {
					FiltroDeAr filtroDeAr = new FiltroDeAr();
					filtroDeAr.setId(rSet.getInt("id_filtro_de_ar"));
					filtroDeAr = new FiltroDeArDAO().pesquisarPorID(filtroDeAr);
					filtroDeAr.setQuantidade(rSet.getInt("qtde_filtroDeAr"));
					lista.add(filtroDeAr);
				}

				if (rSet.getInt("id_filtro_de_oleo_motor") > 0 && rSet.getInt("id_filtro_de_oleo_motor") != 99) {
					FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
					filtroDeOleoMotor.setId(rSet.getInt("id_filtro_de_oleo_motor"));
					filtroDeOleoMotor = new FiltroDeOleoMotorDAO().pesquisarPorID(filtroDeOleoMotor);
					filtroDeOleoMotor.setQuantidade(rSet.getInt("qtde_filtro_de_oleo_motor"));
					lista.add(filtroDeOleoMotor);
				}

				if (rSet.getInt("id_fluido_de_freio") > 0 && rSet.getInt("id_fluido_de_freio") != 99) {
					FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
					fluidoDeFreio.setId(rSet.getInt("id_fluido_de_freio"));
					fluidoDeFreio = new FluidoDeFreioDAO().pesquisarPorID(fluidoDeFreio);
					fluidoDeFreio.setQuantidade(rSet.getInt("qtde_fluido_de_freio"));
					lista.add(fluidoDeFreio);
				}

				if (rSet.getInt("id_pastilhas_freios") > 0 && rSet.getInt("id_pastilhas_freios") != 99) {
					PastilhaFreio pastilhaFreio = new PastilhaFreio();
					pastilhaFreio.setId(rSet.getInt("id_pastilhas_freios"));
					pastilhaFreio = new PastilhaFreioDAO().pesquisarPorID(pastilhaFreio);
					pastilhaFreio.setQuantidade(rSet.getInt("qtde_pastilhas_freio"));
					lista.add(pastilhaFreio);
				}

				if (rSet.getInt("id_pneus") > 0 && rSet.getInt("id_pneus") != 99) {
					Pneu pneu = new Pneu();
					pneu.setId(rSet.getInt("id_pneus"));
					pneu = new PneuDAO().pesquisarPorID(pneu);
					pneu.setQuantidade(rSet.getInt("qtde_pneus"));
					lista.add(pneu);
				}

				if (rSet.getInt("id_velas_ignicao") > 0 && rSet.getInt("id_velas_ignicao") != 99) {
					VelasIgnicao velasIgnicao = new VelasIgnicao();
					velasIgnicao.setId(rSet.getInt("id_velas_ignicao"));
					velasIgnicao = new VelasIgnicaoDAO().pesquisarPorID(velasIgnicao);
					velasIgnicao.setQuantidade(rSet.getInt("qtde_velas_ignicao"));
					lista.add(velasIgnicao);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;

	}

	public List<ProdutoRevisao> pesquisarPorUltimoCadastrado() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from produto_revisao ");

		List<ProdutoRevisao> lista = new ArrayList<ProdutoRevisao>();

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				if (rSet.isLast()) {
					ProdutoRevisao produtoRevisaoRetorno = new ProdutoRevisao();

					produtoRevisaoRetorno.setId(rSet.getInt("id"));
					lista.add(produtoRevisaoRetorno);

					if (rSet.getInt("id_pneus") > 0 && rSet.getInt("id_pneus") != 99) {
						Amortecedor amortecedor = new Amortecedor();
						amortecedor.setId(rSet.getInt("id_pneus"));
						amortecedor = new AmortecedorDAO().pesquisarPorID(amortecedor);
						amortecedor.setQuantidade(rSet.getInt("qtde_pneus"));
						lista.add(amortecedor);

					}

					if (rSet.getInt("id_correia_dentada") > 0 && rSet.getInt("id_correia_dentada") != 99) {
						CorreiaDentada correiaDentada = new CorreiaDentada();
						correiaDentada.setId(rSet.getInt("id_correia_dentada"));
						correiaDentada = new CorreiaDentadaDAO().pesquisarPorID(correiaDentada);
						correiaDentada.setQuantidade(rSet.getInt("qtde_correia_dentada"));
						lista.add(correiaDentada);

					}

					if (rSet.getInt("id_embreagem") > 0 && rSet.getInt("id_embreagem") != 99) {
						Embreagem embreagem = new Embreagem();
						embreagem.setId(rSet.getInt("id_embreagem"));
						embreagem = new EmbreagemDAO().pesquisarPorID(embreagem);
						embreagem.setQuantidade(rSet.getInt("qtde_embreagem"));
						lista.add(embreagem);

					}

					if (rSet.getInt("id_farol") > 0 && rSet.getInt("id_farol") != 99) {
						Farol farol = new Farol();
						farol.setId(rSet.getInt("id_farol"));
						farol = new FarolDAO().pesquisarPorID(farol);
						farol.setQuantidade(rSet.getInt("qtde_farol"));
						lista.add(farol);
					}

					if (rSet.getInt("id_filtro_de_ar") > 0 && rSet.getInt("id_filtro_de_ar") != 99) {
						FiltroDeAr filtroDeAr = new FiltroDeAr();
						filtroDeAr.setId(rSet.getInt("id_filtro_de_ar"));
						filtroDeAr = new FiltroDeArDAO().pesquisarPorID(filtroDeAr);
						filtroDeAr.setQuantidade(rSet.getInt("qtde_filtroDeAr"));
						lista.add(filtroDeAr);
					}

					if (rSet.getInt("id_filtro_de_oleo_motor") > 0 && rSet.getInt("id_filtro_de_oleo_motor") != 99) {
						FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
						filtroDeOleoMotor.setId(rSet.getInt("id_filtro_de_oleo_motor"));
						filtroDeOleoMotor = new FiltroDeOleoMotorDAO().pesquisarPorID(filtroDeOleoMotor);
						filtroDeOleoMotor.setQuantidade(rSet.getInt("qtde_filtro_de_oleo_motor"));
						lista.add(filtroDeOleoMotor);
					}

					if (rSet.getInt("id_fluido_de_freio") > 0 && rSet.getInt("id_fluido_de_freio") != 99) {
						FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
						fluidoDeFreio.setId(rSet.getInt("id_fluido_de_freio"));
						fluidoDeFreio = new FluidoDeFreioDAO().pesquisarPorID(fluidoDeFreio);
						fluidoDeFreio.setQuantidade(rSet.getInt("qtde_fluido_de_freio"));
						lista.add(fluidoDeFreio);
					}

					if (rSet.getInt("id_pastilhas_freios") > 0 && rSet.getInt("id_pastilhas_freios") != 99) {
						PastilhaFreio pastilhaFreio = new PastilhaFreio();
						pastilhaFreio.setId(rSet.getInt("id_pastilhas_freios"));
						pastilhaFreio = new PastilhaFreioDAO().pesquisarPorID(pastilhaFreio);
						pastilhaFreio.setQuantidade(rSet.getInt("qtde_pastilhas_freio"));
						lista.add(pastilhaFreio);
					}

					if (rSet.getInt("id_pneus") > 0 && rSet.getInt("id_pneus") != 99) {
						Pneu pneu = new Pneu();
						pneu.setId(rSet.getInt("id_pneus"));
						pneu = new PneuDAO().pesquisarPorID(pneu);
						pneu.setQuantidade(rSet.getInt("qtde_pneus"));
						lista.add(pneu);
					}

					if (rSet.getInt("id_velas_ignicao") > 0 && rSet.getInt("id_velas_ignicao") != 99) {
						VelasIgnicao velasIgnicao = new VelasIgnicao();
						velasIgnicao.setId(rSet.getInt("id_velas_ignicao"));
						velasIgnicao = new VelasIgnicaoDAO().pesquisarPorID(velasIgnicao);
						velasIgnicao.setQuantidade(rSet.getInt("qtde_velas_ignicao"));
						lista.add(velasIgnicao);
					}
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;

	}
	
	

	@Override
	public void cadastrar(ProdutoRevisao produtoRevisao) {
		// TODO Auto-generated method stub

	}

	@Override
	public ProdutoRevisao pesquisarPorID(ProdutoRevisao produtoRevisao) {
		return null;
	}

}