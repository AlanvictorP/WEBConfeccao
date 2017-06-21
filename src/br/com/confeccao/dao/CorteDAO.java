package br.com.confeccao.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.confeccao.model.Corte;

public class CorteDAO {

	public void salvar(Corte corte) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(corte);
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
	public List<Corte> ListaCortes(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Corte> cortes = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Corte.listar");
			cortes = consulta.list();
			
		}catch(RuntimeException ex){
			
			throw ex;
		}finally{
			sessao.close();
		}
		return cortes;
	}
	
	public Corte buscaPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Corte cortes = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Corte.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			
			cortes = (Corte) consulta.uniqueResult();
			
		}catch(RuntimeException ex){
			
			throw ex;
		}finally{
			sessao.close();
		}
		return cortes;
	}
	public void excluir(Corte corte){//Recebe o corte que deseja excluir.
		Session session = HibernateUtil.getSessionFactory().openSession();//abrindo uma sessao de conex�o
		Transaction transacao = null;//criando uma transa��o nula
		
		try{
			transacao = session.beginTransaction();//inicia uma transa��o da sess�o
			session.delete(corte);//deleta objeto corte na base
			transacao.commit();//confirmando a transa��o
			
		}catch(RuntimeException ex){
			if(transacao!=null){//caso a trasa��o esteja nula ou seja ele n�o realizou a transa��o
				transacao.rollback();//repeti a transa��o
			}
			throw ex;//repropaga o erro
		}finally{
			session.close();//fecha a conex�o
		}
	}
	
//	public void ExcluirPoCodigo(Long codigo) {
//		Session sessao = HibernateUtil.getSessionFactory().openSession();
//		Transaction transacao = null;
//
//		try {
//			transacao = sessao.beginTransaction();
//			Corte corte = buscaPorCodigo(codigo);
//			sessao.delete(corte);
//			transacao.commit();
//		} catch (RuntimeException ex) {
//
//			if (transacao != null) {
//				transacao.rollback();
//			}
//			throw ex;
//		} finally {
//			sessao.close();
//		}
//
//	}
	public void editar(Corte corte){//Recebe o corte que deseja editar.
		Session session = HibernateUtil.getSessionFactory().openSession();//abrindo uma sessao de conex�o
		Transaction transacao = null;//criando uma transa��o nula
		
		try{
			transacao = session.beginTransaction();//inicia uma transa��o da sess�o			
			session.update(corte);//atualiza o objeto corte na base
			transacao.commit();//confirmando a transa��o
			
		}catch(RuntimeException ex){
			if(transacao!=null){//caso a trasa��o esteja nula ou seja ele n�o realizou a transa��o
				transacao.rollback();//repeti a transa��o
			}
			throw ex;//repropaga o erro
		}finally{
			session.close();//fecha a conex�o
		}
	}

}
