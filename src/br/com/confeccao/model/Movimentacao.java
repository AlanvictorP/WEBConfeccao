package br.com.confeccao.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Movimentacao")
@NamedQueries({
	@NamedQuery(name="Movimentacao.listar",query="SELECT movimentacao FROM Movimentacao movimentacao"),
	@NamedQuery(name = "Movimentacao.buscarPorCodigo",query="SELECT movimentacao FROM Movimentacao movimentacao where movimentacao.codigo = :codigo")
})
public class Movimentacao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idMovimentacao")
	private long codigo;

	@Column(name = "valorentrada",precision = 7, scale = 2)
	private BigDecimal valorEntrada;
	
	@Column(name = "valorsaida",precision = 7, scale = 2)
	private BigDecimal valorSaida;
	
	@Column(name = "motivo",length = 50, nullable = false)
	private String motivo;
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorSaida() {
		return valorSaida;
	}

	public void setValorSaida(BigDecimal valorSaida) {
		this.valorSaida = valorSaida;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Override
	public String toString() {
		return "Movimentacao [codigo=" + codigo + ", valorEntrada="
				+ valorEntrada + ", valorSaida=" + valorSaida + ", motivo="
				+ motivo + "]";
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
		Movimentacao other = (Movimentacao) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
}
