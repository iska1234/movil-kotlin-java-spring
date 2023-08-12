package pe.idat.serv.cavero.ServicioProyecto.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="proveedor")
public class Proveedor implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer prodId;
	
	@Column
	private String nomProv;

	@Column
	private String dirProv;
	
	@Column
	private String telfProv;
	
	@Column
	private String emaProv;
	
	public Proveedor() {
		
	}

	public Proveedor(Integer prodId, String nomProv, String dirProv, String telfProv, String emaProv) {
		this.prodId = prodId;
		this.nomProv = nomProv;
		this.dirProv = dirProv;
		this.telfProv = telfProv;
		this.emaProv = emaProv;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getNomProv() {
		return nomProv;
	}

	public void setNomProv(String nomProv) {
		this.nomProv = nomProv;
	}

	public String getDirProv() {
		return dirProv;
	}

	public void setDirProv(String dirProv) {
		this.dirProv = dirProv;
	}

	public String getTelfProv() {
		return telfProv;
	}

	public void setTelfProv(String telfProv) {
		this.telfProv = telfProv;
	}

	public String getEmaProv() {
		return emaProv;
	}

	public void setEmaProv(String emaProv) {
		this.emaProv = emaProv;
	}
	
	
	
}
