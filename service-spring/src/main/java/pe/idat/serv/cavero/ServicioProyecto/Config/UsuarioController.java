package pe.idat.serv.cavero.ServicioProyecto.Config;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import pe.idat.serv.cavero.ServicioProyecto.Service.UsuarioServiceImpl;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl service;
	
	public UsuarioController() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	/*@PostMapping("/login")
	public GenericResponse<Usuario> login(HttpServletRequest request){
		String email=request.getParameter("correo");
		String contrasnia=request.getParameter("password");
		
		return this.service.login(email, contrasnia);
		
	}*/
}
