package br.com.confeccao.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.confeccao.dao.MovimentacaoDAO;
import br.com.confeccao.model.Movimentacao;


public class MovimentacaoDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		MovimentacaoDAO dao = new MovimentacaoDAO();
		Movimentacao m1 = new Movimentacao();
		m1.setMotivo("TesteUni2");
		m1.setValorEntrada(new BigDecimal(20.0));
		
		dao.salvar(m1);
	}
	@Test
	@Ignore
	public void listar(){
		MovimentacaoDAO dao = new MovimentacaoDAO();
		List<Movimentacao> movimentacoes = dao.ListaMovimentacao();
		
		for(Movimentacao movimentacao : movimentacoes){
			System.out.println(movimentacoes);
		}
	}
	@Test
	@Ignore
	public void buscarPorCodigo(){
		MovimentacaoDAO dao = new MovimentacaoDAO();
		Movimentacao m1 = dao.buscaPorCodigo(1l);
		System.out.println(m1);
	}
	@Test
	@Ignore
	public void excluir(){
		MovimentacaoDAO dao = new MovimentacaoDAO();
		Movimentacao  movimentacao = dao.buscaPorCodigo(2l);
		dao.excluir(movimentacao);	
		
	}
	@Test
	public void editar(){
		MovimentacaoDAO dao = new MovimentacaoDAO();
		Movimentacao movimentacao = dao.buscaPorCodigo(1l);
		
		movimentacao.setValorSaida(new BigDecimal(50.00));

		dao.editar(movimentacao);
	}
	

}

