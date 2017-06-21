package br.com.confeccao.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.confeccao.model.Movimentacao;

public class MovimentacaoDAO {
	public void salvar(Movimentacao movimentacao) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(movimentacao);
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
	public List<Movimentacao> ListaMovimentacao(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Movimentacao> listaMovimentacao = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Movimentacao.listar");
			listaMovimentacao = consulta.list();
			
		}catch(RuntimeException ex){
			
			throw ex;
		}finally{
			sessao.close();
		}
		return listaMovimentacao;
	}
	
	public Movimentacao buscaPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Movimentacao movimentacao = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Movimentacao.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			
			movimentacao = (Movimentacao) consulta.uniqueResult();
			
		}catch(RuntimeException ex){
			
			throw ex;
		}finally{
			sessao.close();
		}
		return movimentacao;
	}
	public void excluir(Movimentacao movimentacao){//Recebe o funcionario que deseja excluir.
		Session session = HibernateUtil.getSessionFactory().openSession();//abrindo uma sessao de conex�o
		Transaction transacao = null;//criando uma transa��o nula
		
		try{
			transacao = session.beginTransaction();//inicia uma transa��o da sess�o
			session.delete(movimentacao);//deleta objeto funcionario na base
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
	

	public void editar(Movimentacao movimentacao){//Recebe o funcionario que deseja editar.
		Session session = HibernateUtil.getSessionFactory().openSession();//abrindo uma sessao de conex�o
		Transaction transacao = null;//criando uma transa��o nula
		
		try{
			transacao = session.beginTransaction();//inicia uma transa��o da sess�o			
			session.update(movimentacao);//atualiza o objeto funcionario na base
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
