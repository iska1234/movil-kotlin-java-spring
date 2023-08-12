package pe.idat.serv.cavero.ServicioProyecto.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipodepago")
public class TipoPago implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tipocId;
	
	@Column
	private String nombTipoPago;
	
	public TipoPago() {
		// TODO Auto-generated constructor stub
	}

	public TipoPago(Integer tipocId, String nombTipoPago) {
		this.tipocId = tipocId;
		this.nombTipoPago = nombTipoPago;
	}

	public Integer getTipocId() {
		return tipocId;
	}

	public void setTipocId(Integer tipocId) {
		this.tipocId = tipocId;
	}

	public String getNombTipoPago() {
		return nombTipoPago;
	}

	public void setNombTipoPago(String nombTipoPago) {
		this.nombTipoPago = nombTipoPago;
	}

}
