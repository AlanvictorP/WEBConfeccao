package br.com.confeccao.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.confeccao.dao.FuncionarioDAO;
import br.com.confeccao.model.Funcionario;

public class FuncionarioDAOTeste {
	@Test
	@Ignore
	public void salvar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f1 = new Funcionario();	
		f1.setCpf("055.171.653-35");
		f1.setNome("ADMIN");
		f1.setFuncao("ADMINISTRADOR");
		f1.setSenha("015693");
		dao.salvar(f1);
	}
	@Test
	@Ignore
	public void listar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funcionarios = dao.ListaFuncionarios();
		
		for(Funcionario funcionario : funcionarios){
			System.out.println(funcionario);
		}
	}
	@Test
	@Ignore
	public void buscarPorCodigo(){
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f1 = dao.buscaPorCodigo(1l);
		System.out.println(f1);
	}
	@Test
	@Ignore
	public void excluir(){
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscaPorCodigo(1l);
		dao.excluir(funcionario);	
		
	}
	@Test
	@Ignore
	public void editar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscaPorCodigo(1l);
		funcionario.setNome("Alanvictor");
		dao.editar(funcionario);
	}
	@Test
	@Ignore
	public void autenticar(){
		FuncionarioDAO funDAO = new FuncionarioDAO();
		
		Funcionario funcionario = funDAO.logar("Alanvictor", "654");
		
		System.out.println(funcionario);
	}
	

}
