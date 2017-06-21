package br.com.confeccao.controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.confeccao.dao.FacesUtil;
import br.com.confeccao.dao.FuncionarioDAO;
import br.com.confeccao.model.Funcionario;

@ManagedBean
@SessionScoped
public class LogarBean {
	private Funcionario funLogin;
	
	public String logar(){
		
		try {
			FuncionarioDAO funDAO = new FuncionarioDAO();
			funLogin = funDAO.logar(funLogin.getNome(),
					DigestUtils.md5Hex(funLogin.getSenha()));// envia os dados
																// para
																// verificar na
																// dao o md5hex antes da senha converte a senha recebida para a senha no formato md5
			if(funLogin == null){
				FacesUtil.msgError("Login ou senha inválido.");
				
				return null;//se usuário ou senha invalida continua na mesma tela
			}else{
			FacesUtil.msgInfor("Funcionario logado.");
			return "/paginas/principal.xhtml?faces-redirect=true";//se usuário existir redireciona para tela principal do sistema
			}
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao tentar logar no sistema:"+ex.getMessage());
			return null;//se usuário ou senha invalida continua na mesma tela
		}
	}
	
	public String sair(){
		funLogin = null;//limpa o funcionario logado para sair do sistema.
		return "/paginas/login.xhtml?faces-redirect=true";//redireciona para tela de login.
	}
	
	public void limpar(){
		funLogin = new Funcionario();
	}
	
	public Funcionario getFunLogin() {
		if(funLogin == null){
			funLogin = new Funcionario();//no caso de ouver alguma busca e o funLogin estiver nulo ele instacia novamente.
		}
		return funLogin;
	}
	
	public void setFunLogin(Funcionario funLogin) {
		this.funLogin = funLogin;
	}
	

}
