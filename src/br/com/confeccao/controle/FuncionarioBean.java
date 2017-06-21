package br.com.confeccao.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.confeccao.dao.FacesUtil;
import br.com.confeccao.dao.FuncionarioDAO;
import br.com.confeccao.model.Funcionario;

@ManagedBean
@ViewScoped
public class FuncionarioBean {
	
	private Funcionario funcionarioCadastro;
	private List<Funcionario> listaFuncionarios;//lista utilizada para fazer as consultas
	private List<Funcionario> listaFuncionariosFilter;//lista utilizada para fazer consultas especificas
	private String acao;
	private Long codigo;

	public void salvar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			dao.salvar(funcionarioCadastro);
			funcionarioCadastro = new Funcionario();
			FacesUtil.msgInfor("Cliente salvo com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao tentar salvar cliente: "
					+ ex.getMessage());
		}

	}

	public void pesquisarClientes() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			listaFuncionarios = dao.ListaFuncionarios();
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao listar clientes" + ex.getMessage());
		}

	}

	public void carregarPesquisa() {
		try {

			if (codigo != null) {//verifica se o codigo estiver nulo então ele ira instanciar para salvar um novo
				FuncionarioDAO dao = new FuncionarioDAO();
				funcionarioCadastro = dao.buscaPorCodigo(codigo);
			}else{
				funcionarioCadastro = new Funcionario();
			}
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao obter funcionario" + ex.getMessage());
		}
	}

	public void excluir() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.excluir(funcionarioCadastro);
			FacesUtil.msgInfor("Funcionario editado com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao tentar editar o funcionario: "
					+ ex.getMessage());
		}

	}
	public void editar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			dao.editar(funcionarioCadastro);
			FacesUtil.msgInfor("Funcionario editado com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao tentar editar o funcionario: "
					+ ex.getMessage());
		}

	}	
	
	public Funcionario getFuncionarioCadastro() {
		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Funcionario> getListaFuncionariosFilter() {
		return listaFuncionariosFilter;
	}

	public void setListaFuncionariosFilter(List<Funcionario> listaFuncionariosFilter) {
		this.listaFuncionariosFilter = listaFuncionariosFilter;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}
	public String getAcao() {
		return acao;
	}
		

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void limpar() {
		funcionarioCadastro = new Funcionario();
	}

}
