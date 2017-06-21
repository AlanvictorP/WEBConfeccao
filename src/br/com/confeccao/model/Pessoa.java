package br.com.confeccao.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Pessoa")
@NamedQueries({
	@NamedQuery(name = "Pessoa.listar", query = "SELECT pessoa FROM Pessoa pessoa"),
	//será utilizado para buscar id e realizar exclusão e edição no banco
	@NamedQuery(name = "Pessoa.buscarPorCodigo",query="SELECT pessoa FROM Pessoa pessoa where pessoa.codigo = :codigo")

})
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idPessoa")
	private long codigo;
	
	@NotEmpty(message="O campo Nome é obrigatorio.")
	@Size(max=50,message="Tamanho do nome muito grande.")
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;
	
	@NotNull(message="O Campo Telefone é obrigatorio.")
	@Column(name = "telefone",nullable = false)
	private int telefone;
	
	@NotEmpty(message="O campo Endereco é obrigatorio.")
	@Size(max=100,message="Tamanho do endereço muito grande.")
	@Column(name = "endereco",length = 100, nullable = false)
	private String endereco;

	@NotEmpty(message="O campo E-mail é obrigatorio.")
	@Size(max=50,message="Tamanho do E-mail muito grande.")
	@Column(name = "email",length = 100, nullable = false)
	private String email;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Pessoa [codigo=" + codigo + ", nome=" + nome + ", telefone="
				+ telefone + ", endereco=" + endereco + ", email=" + email
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	
}
