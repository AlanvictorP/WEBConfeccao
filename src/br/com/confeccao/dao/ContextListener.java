package br.com.confeccao.dao;
import javax.servlet.*;

public class ContextListener implements ServletContextListener{
	@Override
	public void contextDestroyed(ServletContextEvent sce){
		HibernateUtil.getSessionFactory().close();
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce){
		HibernateUtil.getSessionFactory().openSession();
	}

}
