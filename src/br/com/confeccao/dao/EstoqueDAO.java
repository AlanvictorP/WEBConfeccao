package br.com.confeccao.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.confeccao.model.Estoque;

public class EstoqueDAO {

	public void salvar(Estoque estoque) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(estoque);
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
	public List<Estoque> ListaEstoque(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Estoque> listaEstoque = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Estoque.listar");
			listaEstoque = consulta.list();
			
		}catch(RuntimeException ex){
			
			throw ex;
		}finally{
			sessao.close();
		}
		return listaEstoque;
	}
	
	public Estoque buscaPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Estoque estoque = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Estoque.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			
			estoque = (Estoque) consulta.uniqueResult();
			
		}catch(RuntimeException ex){
			
			throw ex;
		}finally{
			sessao.close();
		}
		return estoque;
	}
	public void excluir(Estoque estoque){//Recebe o funcionario que deseja excluir.
		Session session = HibernateUtil.getSessionFactory().openSession();//abrindo uma sessao de conexão
		Transaction transacao = null;//criando uma transação nula
		
		try{
			transacao = session.beginTransaction();//inicia uma transação da sessão
			session.delete(estoque);//deleta objeto funcionario na base
			transacao.commit();//confirmando a transação
			
		}catch(RuntimeException ex){
			if(transacao!=null){//caso a trasação esteja nula ou seja ele não realizou a transação
				transacao.rollback();//repeti a transação
			}
			throw ex;//repropaga o erro
		}finally{
			session.close();//fecha a conexão
		}
	}
	

	public void editar(Estoque estoque){//Recebe o funcionario que deseja editar.
		Session session = HibernateUtil.getSessionFactory().openSession();//abrindo uma sessao de conexão
		Transaction transacao = null;//criando uma transação nula
		
		try{
			transacao = session.beginTransaction();//inicia uma transação da sessão			
			session.update(estoque);//atualiza o objeto funcionario na base
			transacao.commit();//confirmando a transação
			
		}catch(RuntimeException ex){
			if(transacao!=null){//caso a trasação esteja nula ou seja ele não realizou a transação
				transacao.rollback();//repeti a transação
			}
			throw ex;//repropaga o erro
		}finally{
			session.close();//fecha a conexão
		}
	}

}
