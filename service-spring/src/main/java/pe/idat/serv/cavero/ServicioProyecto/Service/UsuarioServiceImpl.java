package pe.idat.serv.cavero.ServicioProyecto.Service;

import java.util.Collection;


import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import pe.idat.serv.cavero.ServicioProyecto.Entity.Usuario;
import pe.idat.serv.cavero.ServicioProyecto.Repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository repository;

	@Override
	@Transactional
	public void insert(Usuario producto) {	
		repository.save(producto);
	}

	@Override
	@Transactional
	public void update(Usuario usuario) {
		repository.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Integer usuarioId) {
		repository.deleteById(usuarioId);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findById(Integer usuarioId) {
		return repository.findById(usuarioId).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Usuario> findAll() {
		return repository.findAll();
	}

	
	
	/*public GenericResponse<Usuario> login(String correo, String password){
		Optional<Usuario> optU=this.repository.login(correo, password);
		if(optU.isPresent()) {
			
			return new GenericResponse<Usuario>(TIPO_AUTH,RPTA_OK,"Haz iniciado sesion correctamente",optU.get());
		}else {
			return new GenericResponse<Usuario>(TIPO_AUTH,RPTA_WARNING,"El usuario no existe",new Usuario());
			
		}
		
		
	}*/
	
}
