package br.com.confeccao.controle;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.confeccao.dao.CorteDAO;
import br.com.confeccao.dao.FacesUtil;
import br.com.confeccao.dao.PessoaDAO;
import br.com.confeccao.model.Corte;
import br.com.confeccao.model.Pessoa;

@ManagedBean
@ViewScoped
public class CorteBean {
	private Corte corteCadastro = new Corte();
	private List<Corte> listaCortes;
	private List<Corte> listaCortesFilter;
	private List<Pessoa> listaClientes;
	private Long codigo;
	private String acao;

	public void total() {

		this.corteCadastro.setValorTotal(this.corteCadastro.getValorUni()
				.multiply(new BigDecimal(corteCadastro.getQuantidade())));
	}

	public void salvar() {
		CorteDAO dao = new CorteDAO();

		try {
			this.corteCadastro.setValorTotal(this.corteCadastro.getValorUni()
					.multiply(new BigDecimal(corteCadastro.getQuantidade())));
			dao.salvar(corteCadastro);
			corteCadastro = new Corte();
			FacesUtil.msgInfor("Corte salvo com sucesso!");

		} catch (RuntimeException ex) {
			FacesUtil.msgInfor("Erro:" + ex.toString());
		}
	}

	public void pesquisarCorte() {
		try {
			CorteDAO dao = new CorteDAO();
			listaCortes = dao.ListaCortes();
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ou pesquisar cortes: " + ex.getMessage());
		}

	}

	public void carregarPesquisa() {
		try {

			if (codigo != null) {// verifica se o codigo estiver nulo então ele
									// ira instanciar para salvar um novo
				CorteDAO dao = new CorteDAO();
				corteCadastro = dao.buscaPorCodigo(codigo);
			} else {
				corteCadastro = new Corte();
			}
			PessoaDAO daoPessoa = new PessoaDAO();
			listaClientes = daoPessoa.ListaClientes();

		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao obter cliente" + ex.getMessage());
		}
	}

	public void excluir() {
		try {
			CorteDAO dao = new CorteDAO();
			dao.excluir(corteCadastro);
			FacesUtil.msgInfor("Cliente removido com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao tentar remover o cliente: "
					+ ex.getMessage());
		}

	}

	public void editar() {
		try {
			this.corteCadastro.setValorTotal(this.corteCadastro.getValorUni()
					.multiply(new BigDecimal(corteCadastro.getQuantidade())));
			CorteDAO dao = new CorteDAO();
			dao.editar(corteCadastro);
			FacesUtil.msgInfor("Cliente editado com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.msgError("Erro ao tentar editar o cliente: "
					+ ex.getMessage());
		}
	}
		public void finalizar() {
			try {
				this.corteCadastro.setValorTotal(this.corteCadastro.getValorUni()
						.multiply(new BigDecimal(corteCadastro.getQuantidade())));
				CorteDAO dao = new CorteDAO();
				corteCadastro.setSituacao(1);
				dao.editar(corteCadastro);
				FacesUtil.msgInfor("Cliente editado com sucesso!");
			} catch (RuntimeException ex) {
				FacesUtil.msgError("Erro ao tentar editar o cliente: "
						+ ex.getMessage());
			}

	}

	public void limpar() {
		corteCadastro = new Corte();
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getAcao() {
		return acao;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCorteCadastro(Corte corteCadastro) {
		this.corteCadastro = corteCadastro;
	}

	public Corte getCorteCadastro() {
		if (corteCadastro == null) {
			corteCadastro = new Corte();
			corteCadastro.setValorTotal(new BigDecimal("0.00"));
		}
		return corteCadastro;
	}

	public void setListaCortes(List<Corte> listaCortes) {
		this.listaCortes = listaCortes;
	}

	public List<Corte> getListaCortes() {
		return listaCortes;
	}

	public void setListaCortesFilter(List<Corte> listaCortesFilter) {
		this.listaCortesFilter = listaCortesFilter;
	}

	public List<Corte> getListaCortesFilter() {
		return listaCortesFilter;
	}

	public void setListaClientes(List<Pessoa> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Pessoa> getListaClientes() {
		return listaClientes;
	}

}
