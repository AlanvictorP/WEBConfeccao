package br.com.confeccao.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.confeccao.model.Pessoa;

public class PessoaDAO {
	public void salvar(Pessoa pessoa) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(pessoa);
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
	public List<Pessoa> ListaClientes(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Pessoa> pessoas = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Pessoa.listar");
			pessoas = consulta.list();
			
		}catch(RuntimeException ex){
			
			throw ex;
		}finally{
			sessao.close();
		}
		return pessoas;
	}
	
	public Pessoa buscaPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Pessoa pessoas = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Pessoa.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			
			pessoas = (Pessoa) consulta.uniqueResult();
			
		}catch(RuntimeException ex){
			
			throw ex;
		}finally{
			sessao.close();
		}
		return pessoas;
	}
	public void excluir(Pessoa pessoa){//Recebe o pessoa que deseja excluir.
		Session session = HibernateUtil.getSessionFactory().openSession();//abrindo uma sessao de conexão
		Transaction transacao = null;//criando uma transação nula
		
		try{
			transacao = session.beginTransaction();//inicia uma transação da sessão
			session.delete(pessoa);//deleta objeto pessoa na base
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
	
	public void editar(Pessoa pessoa){//Recebe o pessoa que deseja editar.
		Session session = HibernateUtil.getSessionFactory().openSession();//abrindo uma sessao de conexão
		Transaction transacao = null;//criando uma transação nula
		
		try{
			transacao = session.beginTransaction();//inicia uma transação da sessão			
			session.update(pessoa);//atualiza o objeto pessoa na base
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