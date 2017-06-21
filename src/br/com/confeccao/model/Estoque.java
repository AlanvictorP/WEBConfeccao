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
@Table (name ="Estoque" )
@NamedQueries({
	@NamedQuery(name = "Estoque.listar", query = "SELECT estoque FROM Estoque estoque"),
	//será utilizado para buscar id e realizar exclusão e edição no banco
	@NamedQuery(name = "Estoque.buscarPorCodigo",query="SELECT estoque FROM Estoque estoque where estoque.codigo = :codigo")

})
public class Estoque {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idEstoque")
	private long codigo;
	
	@Column(name = "item", length = 50, nullable = false)
	private String item;

	@Column(name = "est_valoruni",precision = 7, scale = 2,nullable = false)
	private BigDecimal est_valorUni;
	
	@Column(name = "est_motivo", length = 50, nullable = false)
	private String est_motivo;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public BigDecimal getEst_valorUni() {
		return est_valorUni;
	}

	public void setEst_valorUni(BigDecimal est_valorUni) {
		this.est_valorUni = est_valorUni;
	}

	public String getEst_motivo() {
		return est_motivo;
	}

	public void setEst_motivo(String est_motivo) {
		this.est_motivo = est_motivo;
	}

	@Override
	public String toString() {
		return "Estoque [codigo=" + codigo + ", item=" + item
				+ ", est_valorUni=" + est_valorUni + ", est_motivo="
				+ est_motivo + "]";
	}
	
	

}
