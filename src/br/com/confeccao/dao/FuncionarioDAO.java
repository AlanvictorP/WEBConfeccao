package br.com.confeccao.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.confeccao.model.Funcionario;



public class FuncionarioDAO {

	public void salvar(Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(funcionario);
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
	public List<Funcionario> ListaFuncionarios(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Funcionario> funcionarios = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Funcionario.listar");
			funcionarios = consulta.list();
			
		}catch(RuntimeException ex){
			
			throw ex;
		}finally{
			sessao.close();
		}
		return funcionarios;
	}
	
	public Funcionario buscaPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionarios = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Funcionario.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			
			funcionarios = (Funcionario) consulta.uniqueResult();
			
		}catch(RuntimeException ex){
			
			throw ex;
		}finally{
			sessao.close();
		}
		return funcionarios;
	}
	public void excluir(Funcionario funcionario){//Recebe o funcionario que deseja excluir.
		Session session = HibernateUtil.getSessionFactory().openSession();//abrindo uma sessao de conexão
		Transaction transacao = null;//criando uma transação nula
		
		try{
			transacao = session.beginTransaction();//inicia uma transação da sessão
			session.delete(funcionario);//deleta objeto funcionario na base
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
	

	public void editar(Funcionario funcionario){//Recebe o funcionario que deseja editar.
		Session session = HibernateUtil.getSessionFactory().openSession();//abrindo uma sessao de conexão
		Transaction transacao = null;//criando uma transação nula
		
		try{
			transacao = session.beginTransaction();//inicia uma transação da sessão			
			session.update(funcionario);//atualiza o objeto funcionario na base
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
	
	public Funcionario logar(String nome, String senha){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Funcionario login = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Funcionario.Logar");
			consulta.setString("nome", nome);
			consulta.setString("senha", senha);
			
			login = (Funcionario) consulta.uniqueResult();
			
		}catch(RuntimeException ex){
			
			throw ex;
		}finally{
			sessao.close();
		}
		return login;
		
	}

}
