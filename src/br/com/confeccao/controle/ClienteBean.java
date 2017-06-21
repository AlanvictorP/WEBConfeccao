package br.com.confeccao.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.confeccao.dao.FacesUtil;
import br.com.confeccao.dao.PessoaDAO;
import br.com.confeccao.model.Pessoa;

@ManagedBean
@ViewScoped
public class ClienteBean {
	private Pessoa clienteCadastro;
	private List<Pessoa> listaClientes;//lista utilizada para fazer as consultas
	private List<Pessoa> listaClientesFilter;//lista utilizada para fazer consultas especificas
	private String acao;
	private int quantidade;
	private Long codigo;

	public void salvar() {
		clienteCadastro.setTelefone(clienteCadastro.getTelefone());
		
		try {
			PessoaDAO dao = new PessoaDAO();
			dao.salvar(clienteCadastro);
			clienteCadastro = new Pessoa();
			FacesUtil.msgInfor("Cliente salvo com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao tentar salvar cliente: "
					+ ex.getMessage());
		}

	}

	public void pesquisarClientes() {
		try {
			PessoaDAO dao = new PessoaDAO();
			listaClientes = dao.ListaClientes();
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao listar clientes" + ex.getMessage());
		}

	}

	public void carregarPesquisa() {
		
		try {
			if (codigo != null) {//verifica se o codigo estiver nulo então ele ira instanciar para salvar um novo
				PessoaDAO dao = new PessoaDAO();
				clienteCadastro = dao.buscaPorCodigo(codigo);
			}else{
				clienteCadastro = new Pessoa();
			}
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao obter cliente" + ex.getMessage());
		}
	}

	public void excluir() {
		try {
			PessoaDAO dao = new PessoaDAO();
			dao.excluir(clienteCadastro);
			FacesUtil.msgInfor("Cliente removido com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao tentar remover o cliente: "
					+ ex.getMessage());
		}

	}
	public void editar() {
		try {
			PessoaDAO dao = new PessoaDAO();
			dao.editar(clienteCadastro);
			FacesUtil.msgInfor("Cliente editado com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao tentar editar o cliente: "
					+ ex.getMessage());
		}

	}

	public void setListaClientes(List<Pessoa> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Pessoa> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientesFilter(List<Pessoa> listaClientesFilter) {
		this.listaClientesFilter = listaClientesFilter;
	}

	public List<Pessoa> getListaClientesFilter() {
		return listaClientesFilter;
	}

	public void setClienteCadastro(Pessoa clienteCadastro) {
		this.clienteCadastro = clienteCadastro;
	}

	public Pessoa getClienteCadastro() {
		return clienteCadastro;
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
		clienteCadastro = new Pessoa();
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getQuantidade() {
		return quantidade;
	}

}
