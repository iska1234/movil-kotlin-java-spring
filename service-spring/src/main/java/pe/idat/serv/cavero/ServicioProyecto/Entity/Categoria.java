package pe.idat.serv.cavero.ServicioProyecto.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categoria")
public class Categoria implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer catId;
	
	@Column
	private String descCat;
	
	public Categoria() {
		
	}

	public Categoria(Integer catId, String descCat) {
		this.catId = catId;
		this.descCat = descCat;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getDescCat() {
		return descCat;
	}

	public void setDescCat(String descCat) {
		this.descCat = descCat;
	}
		
}
