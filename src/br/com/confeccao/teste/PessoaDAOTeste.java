package br.com.confeccao.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.confeccao.dao.PessoaDAO;
import br.com.confeccao.model.Pessoa;


public class PessoaDAOTeste {
	@Test
	@Ignore
	public void salvar(){
		PessoaDAO dao = new PessoaDAO();
		Pessoa p1 = new Pessoa();	
		p1.setEndereco("Riso");
		p1.setNome("AlanTeste");
		p1.setEmail("alanvictor@com.br");
		p1.setTelefone(55555);
		dao.salvar(p1);
	}
	@Test
	@Ignore
	public void listar(){
		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> pessoas = dao.ListaClientes();
		
		for(Pessoa pessoa : pessoas){
			System.out.println(pessoa);
		}
	}
	@Test
	@Ignore
	public void buscarPorCodigo(){
		PessoaDAO dao = new PessoaDAO();
		Pessoa p1 = dao.buscaPorCodigo(2l);
		System.out.println(p1);
	}
	@Test
	@Ignore
	public void excluir(){
		PessoaDAO dao = new PessoaDAO();
		Pessoa pessoa = dao.buscaPorCodigo(2l);
		dao.excluir(pessoa);	
		
	}
	@Test
	@Ignore
	public void editar(){
		PessoaDAO dao = new PessoaDAO();
		Pessoa funcionario = dao.buscaPorCodigo(1l);
		funcionario.setNome("Alanvictor");
		dao.editar(funcionario);
	}
}