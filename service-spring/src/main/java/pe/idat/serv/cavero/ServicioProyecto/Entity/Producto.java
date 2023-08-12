package pe.idat.serv.cavero.ServicioProyecto.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer prodId;
	
	@Column
	private String nomProd;
	
	@Column
	private Integer precProd;
	
	
	public Producto() {
		
	}


	public Producto(Integer prodId, String nomProd, Integer precProd) {
		this.prodId = prodId;
		this.nomProd = nomProd;
		this.precProd = precProd;
	}


	public Integer getProdId() {
		return prodId;
	}


	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}


	public String getNomProd() {
		return nomProd;
	}


	public void setNomProd(String nomProd) {
		this.nomProd = nomProd;
	}


	public Integer getPrecProd() {
		return precProd;
	}


	public void setPrecProd(Integer precProd) {
		this.precProd = precProd;
	}
	
	
	
	
}
