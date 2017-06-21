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
		Session session = HibernateUtil.getSessionFactory().openSession();//abrindo uma sessao de conexão
		Transaction transacao = null;//criando uma transação nula
		
		try{
			transacao = session.beginTransaction();//inicia uma transação da sessão
			session.delete(movimentacao);//deleta objeto funcionario na base
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
	

	public void editar(Movimentacao movimentacao){//Recebe o funcionario que deseja editar.
		Session session = HibernateUtil.getSessionFactory().openSession();//abrindo uma sessao de conexão
		Transaction transacao = null;//criando uma transação nula
		
		try{
			transacao = session.beginTransaction();//inicia uma transação da sessão			
			session.update(movimentacao);//atualiza o objeto funcionario na base
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
