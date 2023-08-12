package pe.idat.serv.cavero.ServicioProyecto.Entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="entrega")
public class Entregas implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer entregasId;
	
	@Column
	private String dirEntrega;
	
	@Column
	private LocalDate fEntrega;
	
	
	public Entregas() {
		
	}


	public Entregas(Integer entregasId, String dirEntrega, LocalDate fEntrega) {
		this.entregasId = entregasId;
		this.dirEntrega = dirEntrega;
		this.fEntrega = fEntrega;
	}


	public Integer getEntregasId() {
		return entregasId;
	}


	public void setEntregasId(Integer entregasId) {
		this.entregasId = entregasId;
	}


	public String getDirEntrega() {
		return dirEntrega;
	}


	public void setDirEntrega(String dirEntrega) {
		this.dirEntrega = dirEntrega;
	}


	public LocalDate getfEntrega() {
		return fEntrega;
	}


	public void setfEntrega(LocalDate fEntrega) {
		this.fEntrega = fEntrega;
	}
	
	
	
	
	
}
