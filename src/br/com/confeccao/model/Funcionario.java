package br.com.confeccao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "Funcionario")
@NamedQueries({
	@NamedQuery(name = "Funcionario.listar", query = "SELECT funcionario FROM Funcionario funcionario"),
	//será utilizado para buscar id e realizar exclusão e edição no banco
	@NamedQuery(name = "Funcionario.buscarPorCodigo",query="SELECT funcionario FROM Funcionario funcionario where funcionario.codigo = :codigo"),
	@NamedQuery(name="Funcionario.Logar",query="SELECT funcionario FROM Funcionario funcionario WHERE funcionario.nome = :nome AND funcionario.senha = :senha")
})
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idFuncionario")
	private long codigo;
	
	@NotEmpty(message="O campo nome é obrigatorio.")
	@Size(min=3,max=50,message="Tamanho do nome invalido deve ser entre 3 e 50")
	@Column(name = "fun_nome", length = 50, nullable = false)
	private String nome;
	
	@CPF(message="CPF invalido")
	@Size(min=3,max=50,message="Tamanho do CPF invalido.")
	@Column(name = "cpf",length=14,nullable = false, unique= true)
	private String cpf;
	
	@NotEmpty(message="O campo senha é obrigatorio.")
	@Size(min=3,max=50,message="Senha deve ter no minimo 3 caracteres")
	@Column(name = "senha", length = 35, nullable = false)
	private String senha;
	
	@NotEmpty(message="O campo função é obritagorio.")
	@Column(name = "funcao",length=50,nullable = false)
	private String funcao;

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String fun_Nome) {
		this.nome = fun_Nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Funcionario [codigo=" + codigo + ", nome=" + nome + ", cpf="
				+ cpf + ", senha=" + senha + ", funcao=" + funcao + "]";
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
		Funcionario other = (Funcionario) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	

}
