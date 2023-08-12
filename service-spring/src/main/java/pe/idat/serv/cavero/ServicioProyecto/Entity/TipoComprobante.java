package pe.idat.serv.cavero.ServicioProyecto.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipodecomprobante")
public class TipoComprobante implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tipocId;
	
	@Column
	private String nombComprobante;
	
	public TipoComprobante() {
		
	}

	public TipoComprobante(Integer tipocId, String nombComprobante) {
		this.tipocId = tipocId;
		this.nombComprobante = nombComprobante;
	}

	public Integer getTipocId() {
		return tipocId;
	}

	public void setTipocId(Integer tipocId) {
		this.tipocId = tipocId;
	}

	public String getNombComprobante() {
		return nombComprobante;
	}

	public void setNombComprobante(String nombComprobante) {
		this.nombComprobante = nombComprobante;
	}
	
	
	
	
	
}
