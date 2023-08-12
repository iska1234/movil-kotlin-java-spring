package pe.idat.serv.cavero.ServicioProyecto.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="distrito")
public class Distrito implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer distritoId;
	
	@Column
	private String nomDistrito;
	
	public Distrito() {
		// TODO Auto-generated constructor stub
	}

	public Distrito(Integer distritoId, String nomDistrito) {
		this.distritoId = distritoId;
		this.nomDistrito = nomDistrito;
	}

	public Integer getDistritoId() {
		return distritoId;
	}

	public void setDistritoId(Integer distritoId) {
		this.distritoId = distritoId;
	}

	public String getNomDistrito() {
		return nomDistrito;
	}

	public void setNomDistrito(String nomDistrito) {
		this.nomDistrito = nomDistrito;
	}
	
	
	
}
