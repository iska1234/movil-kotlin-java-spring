package pe.idat.serv.cavero.ServicioProyecto.Config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.serv.cavero.ServicioProyecto.Entity.Proveedor;
import pe.idat.serv.cavero.ServicioProyecto.Service.ProveedorServiceImpl;


@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
	@Autowired
	private ProveedorServiceImpl proveedorService;
	
	public ProveedorController() {
		
	}
	

	@GetMapping("/listar")
	public ResponseEntity<?> listar_GET()
	{
		Collection<Proveedor> proveedorDb=proveedorService.findAll();

		
		if(proveedorDb.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(proveedorDb,HttpStatus.OK);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar_GET(@RequestBody Proveedor proveedor)
	{
		proveedorService.insert(proveedor);
		return new ResponseEntity<>("¡Proveedor registrado registrado!",HttpStatus.CREATED);
	}
	
	@PutMapping("/editar/{proveedorId}")
	public ResponseEntity<?> editar_PUT(@RequestBody Proveedor proveedor,@PathVariable Integer proveedorId)
	{
		Proveedor proveedorDb=proveedorService.findById(proveedorId);
		
		if(proveedorDb!=null)
		{
			proveedor.setNomProv(proveedor.getNomProv());
			proveedor.setDirProv(proveedor.getDirProv());
			proveedor.setTelfProv(proveedor.getTelfProv());
			proveedor.setEmaProv(proveedor.getEmaProv());		
			proveedorService.update(proveedorDb);
			return new ResponseEntity<>("¡Proveedor actualizado!",HttpStatus.OK);
		}
		
		return new ResponseEntity<>("¡Proveedor no encontrado!",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{proveedorId}")
	public ResponseEntity<?> borrar_DELETE(@PathVariable Integer proveedorId)
	{
		Proveedor proveedorDb=proveedorService.findById(proveedorId);
		
		if(proveedorDb!=null)
		{
			proveedorService.delete(proveedorId);
			return new ResponseEntity<>("¡Proveedor eliminado!",HttpStatus.OK);
		}
		
		return new ResponseEntity<>("¡Proveedor no encontrado!",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/buscar/{proveedorId}")
	public ResponseEntity<?> buscar_GET(@PathVariable Integer proveedorId)
	{
		Proveedor proveedorDb=proveedorService.findById(proveedorId);
		
		if(proveedorDb!=null) {
			return new ResponseEntity<>(proveedorDb,HttpStatus.FOUND);
		}
		
		return new ResponseEntity<>("¡Proveedor no encontrado!",HttpStatus.NOT_FOUND);
	}
}
