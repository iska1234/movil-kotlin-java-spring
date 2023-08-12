package pe.idat.serv.cavero.ServicioProyecto.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer usuarioId;
	
	@Column
	private String nomUsuario;

	@Column
	private String apeUsuario;
	
	@Column
	private String distUsuario;
	
	@Column
	private Integer docUsuario;
	
	@Column
	private Integer tlfUsuario;
	
	@Column
	private String emaUsuario;
	
	@Column
	private String passwUsuario;
	
	public Usuario() {
		
	}

	public Usuario(Integer usuarioId, String nomUsuario, String apeUsuario, String distUsuario, Integer docUsuario,
			Integer tlfUsuario, String emaUsuario, String passwUsuario) {
		this.usuarioId = usuarioId;
		this.nomUsuario = nomUsuario;
		this.apeUsuario = apeUsuario;
		this.distUsuario = distUsuario;
		this.docUsuario = docUsuario;
		this.tlfUsuario = tlfUsuario;
		this.emaUsuario = emaUsuario;
		this.passwUsuario = passwUsuario;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNomUsuario() {
		return nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public String getApeUsuario() {
		return apeUsuario;
	}

	public void setApeUsuario(String apeUsuario) {
		this.apeUsuario = apeUsuario;
	}

	public String getDistUsuario() {
		return distUsuario;
	}

	public void setDistUsuario(String distUsuario) {
		this.distUsuario = distUsuario;
	}

	public Integer getDocUsuario() {
		return docUsuario;
	}

	public void setDocUsuario(Integer docUsuario) {
		this.docUsuario = docUsuario;
	}

	public Integer getTlfUsuario() {
		return tlfUsuario;
	}

	public void setTlfUsuario(Integer tlfUsuario) {
		this.tlfUsuario = tlfUsuario;
	}

	public String getEmaUsuario() {
		return emaUsuario;
	}

	public void setEmaUsuario(String emaUsuario) {
		this.emaUsuario = emaUsuario;
	}

	public String getPasswUsuario() {
		return passwUsuario;
	}

	public void setPasswUsuario(String passwUsuario) {
		this.passwUsuario = passwUsuario;
	}
	
	
	
	
	
}
