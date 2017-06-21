package br.com.confeccao.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "corte")
@NamedQueries({
		@NamedQuery(name = "Corte.listar", query = "SELECT corte FROM Corte corte"),
		// será utilizado para buscar id e realizar exclusão e edição no banco
		@NamedQuery(name = "Corte.buscarPorCodigo", query = "SELECT corte FROM Corte corte where corte.codigo = :codigo") })
public class Corte {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idCorte")
	private long codigo;

	@NotNull(message = "Lote obrigatorio.")
	@Min(value = 0, message = "Informe um valor maior que zero para o lote")
	@Max(value = 9999, message = "Informe um valor menor que 10000 para o lote.")
	@Column(name = "lote", nullable = false)
	private int lote;

	@NotNull(message = "Quantidade obrigatorio.")
	@Min(value = 0, message = "Informe um valor maior ou igual a zero para quantidade.")
	@Max(value = 9999, message = "Informe um valor menor que 10000 para quantidade.")
	@Column(name = "quantidade", nullable = false)
	private int quantidade;

	@NotNull(message = "Campo valor unidade obrigatorio.")
	@DecimalMin(value = "0.0", message = "Informe um valor maior ou igual a zero.")
	@DecimalMax(value = "99999.99", message = "Valor muito alto.")
	@Column(name = "valoruni", precision = 7, scale = 2, nullable = false)
	private BigDecimal valorUni;

	@Column(name = "valortotal", precision = 7, scale = 2, nullable = false)
	private BigDecimal valorTotal;

	@NotNull(message="Data de entrada obrigatorio.")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "dataentrada", nullable = false)
	private Date dataE;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "datasaida", nullable = false)
	private Date dataS;

	@NotNull(message="Campo Cliente obrigatorio.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoa_idpessoa", referencedColumnName = "idPessoa", nullable = false)
	private Pessoa pessoa;
	
	@Column(name="flag_situacao", nullable=false)
	private int situacao;

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public int getLote() {
		return lote;
	}

	public void setLote(int lote) {
		this.lote = lote;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUni() {
		return valorUni;
	}

	public void setValorUni(BigDecimal valorUni) {
		this.valorUni = valorUni;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataE() {
		return dataE;
	}

	public void setDataE(Date dataE) {
		this.dataE = dataE;
	}

	public Date getDataS() {
		return dataS;
	}

	public void setDataS(Date dataS) {
		this.dataS = dataS;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Corte [codigo=" + codigo + ", lote=" + lote + ", quantidade="
				+ quantidade + ", valorUni=" + valorUni + ", valorTotal="
				+ valorTotal + ", dataE=" + dataE + ", dataS=" + dataS
				+ ", pessoa=" + pessoa + "]";
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
		Corte other = (Corte) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	

}
