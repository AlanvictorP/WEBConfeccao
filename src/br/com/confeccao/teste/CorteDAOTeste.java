package br.com.confeccao.teste;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.confeccao.dao.CorteDAO;
import br.com.confeccao.dao.PessoaDAO;
import br.com.confeccao.model.Corte;
import br.com.confeccao.model.Pessoa;

public class CorteDAOTeste {
	@Test
	public void salvar(){
		CorteDAO dao = new CorteDAO();
		Corte c1 = new Corte();
		PessoaDAO pesDAO = new PessoaDAO();
		Pessoa pessoa = pesDAO.buscaPorCodigo(1l);
		
		c1.setDataE(new Date());
		c1.setDataS(new Date());
		c1.setLote(123);
		c1.setPessoa(pessoa);
		c1.setQuantidade(50);
		c1.setValorTotal(new BigDecimal(2000));
		c1.setValorUni(new BigDecimal(5000));
		
		dao.salvar(c1);
	}
	@Test
	public void listar(){
		CorteDAO dao = new CorteDAO();
		List<Corte> cortes = dao.ListaCortes();
		
		for(Corte corte : cortes){
			System.out.println(corte);
		}
	}
	@Test
	@Ignore
	public void buscarPorCodigo(){
		CorteDAO dao = new CorteDAO();
		Corte c1 = dao.buscaPorCodigo(1l);
		System.out.println(c1);
	}
	@Test
	@Ignore
	public void excluir(){
		CorteDAO dao = new CorteDAO();
		Corte  corte = dao.buscaPorCodigo(1l);
		dao.excluir(corte);	
		
	}
	@Test
	@Ignore
	public void editar(){
		CorteDAO dao = new CorteDAO();
		Corte corte = dao.buscaPorCodigo(2l);
		dao.editar(corte);
	}
	

}
