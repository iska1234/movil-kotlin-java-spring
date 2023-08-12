package pe.idat.serv.cavero.ServicioProyecto.Service;

import java.util.Collection;


import pe.idat.serv.cavero.ServicioProyecto.Entity.Usuario;



public interface UsuarioService {
	
	public abstract void insert(Usuario usuario);
	public abstract void update(Usuario usuario);
	public abstract void delete(Integer usuarioId);
	public abstract Usuario findById(Integer usuarioId);
	public abstract Collection<Usuario> findAll();
}
