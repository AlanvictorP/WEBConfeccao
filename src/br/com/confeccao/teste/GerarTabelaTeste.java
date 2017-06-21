package br.com.confeccao.teste;

import org.junit.Test;

import br.com.confeccao.dao.HibernateUtil;

public class GerarTabelaTeste {
	@Test
	public void gerar(){
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}

}
