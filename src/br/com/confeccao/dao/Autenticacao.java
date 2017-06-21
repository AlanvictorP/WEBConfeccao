package br.com.confeccao.dao;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.confeccao.controle.LogarBean;
import br.com.confeccao.model.Funcionario;

@SuppressWarnings("serial")
public class Autenticacao implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent pagina) {
		FacesContext facesContext = pagina.getFacesContext();
		UIViewRoot root = facesContext.getViewRoot();//recebe o viewroot do contexto atual
		String atual = root.getViewId();//recebe o nome da pagina atual
		
		boolean paginaPublica = atual.contains("login.xhtml");//verificar se a pagina do contexto é public ou não.
		if(!paginaPublica){
			ExternalContext externalContext = facesContext.getExternalContext();
			Map<String, Object> mapa = externalContext.getSessionMap();
			LogarBean login = (LogarBean) mapa.get("logarBean");
			
			Funcionario funcionario = login.getFunLogin();
			
			if(funcionario.getFuncao() == null){
				Application app = facesContext.getApplication();
				NavigationHandler navigationHandler = app.getNavigationHandler();
				navigationHandler.handleNavigation(facesContext, null, "/paginas/login.xhtml?faces-redirect=true");
				
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
