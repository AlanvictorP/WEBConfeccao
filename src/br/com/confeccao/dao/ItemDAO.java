package br.com.confeccao.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.confeccao.model.Item;



public class ItemDAO {

	public void salvar(Item item) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {			
			transacao = sessao.beginTransaction();
			sessao.save(item);
			transacao.commit();
		} catch (RuntimeException ex) {

			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Item> ListaCortes() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Item> itens = null;

		try {
			Query consulta = sessao.getNamedQuery("Item.listar");
			itens = consulta.list();

		} catch (RuntimeException ex) {

			throw ex;
		} finally {
			sessao.close();
		}
		return itens;
	}

	public Item buscaPorCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Item itens = null;

		try {
			Query consulta = sessao.getNamedQuery("Item.buscarPorCodigo");
			consulta.setLong("codigo", codigo);

			itens = (Item) consulta.uniqueResult();

		} catch (RuntimeException ex) {

			throw ex;
		} finally {
			sessao.close();
		}
		return itens;
	}

	public void excluir(Item item) {// Recebe o corte que deseja excluir.
		Session session = HibernateUtil.getSessionFactory().openSession();// abrindo
																			// uma
																			// sessao
																			// de
																			// conex�o
		Transaction transacao = null;// criando uma transa��o nula

		try {
			transacao = session.beginTransaction();// inicia uma transa��o da
													// sess�o
			session.delete(item);// deleta objeto corte na base
			transacao.commit();// confirmando a transa��o

		} catch (RuntimeException ex) {
			if (transacao != null) {// caso a trasa��o esteja nula ou seja ele
									// n�o realizou a transa��o
				transacao.rollback();// repeti a transa��o
			}
			throw ex;// repropaga o erro
		} finally {
			session.close();// fecha a conex�o
		}
	}

	public void editar(Item item) {// Recebe o corte que deseja editar.
		Session session = HibernateUtil.getSessionFactory().openSession();// abrindo
																			// uma
																			// sessao
																			// de
																			// conex�o
		Transaction transacao = null;// criando uma transa��o nula

		try {
			transacao = session.beginTransaction();// inicia uma transa��o da
													// sess�o
			session.update(item);// atualiza o objeto corte na base
			transacao.commit();// confirmando a transa��o

		} catch (RuntimeException ex) {
			if (transacao != null) {// caso a trasa��o esteja nula ou seja ele
									// n�o realizou a transa��o
				transacao.rollback();// repeti a transa��o
			}
			throw ex;// repropaga o erro
		} finally {
			session.close();// fecha a conex�o
		}
	}

}
