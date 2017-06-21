package br.com.confeccao.model;


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
	@NamedQuery(name = "Item.listar", query = "SELECT item FROM Item item"),
	//será utilizado para buscar id e realizar exclusão e edição no banco
	@NamedQuery(name = "Item.buscarPorCodigo",query="SELECT item FROM Item item where item.codigo = :codigo")

})
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idItem")
	private long codigo;
	
	@Column(name = "item", length = 50, nullable = false)
	private String item;

	@Column(name = "referencia",nullable = false)
	private Integer referencia;
	

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

	public Integer getReferencia() {
		return referencia;
	}

	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}
	
	

	@Override
	public String toString() {
		return "Item [codigo=" + codigo + ", item=" + item + ", referencia="
				+ referencia + "]";
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
		Item other = (Item) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
}
