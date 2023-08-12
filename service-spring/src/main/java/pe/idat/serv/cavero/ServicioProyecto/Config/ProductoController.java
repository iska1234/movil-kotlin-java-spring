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

import pe.idat.serv.cavero.ServicioProyecto.Entity.Producto;
import pe.idat.serv.cavero.ServicioProyecto.Service.ProductoServiceImpl;



@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoServiceImpl productoService;
	
	public ProductoController() {
		
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar_GET()
	{
		Collection<Producto> productosDb=productoService.findAll();

		
		if(productosDb.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(productosDb,HttpStatus.OK);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar_GET(@RequestBody Producto producto)
	{
		productoService.insert(producto);
		return new ResponseEntity<>("¡Producto registrado!",HttpStatus.CREATED);
	}
	
	@PutMapping("/editar/{productoId}")
	public ResponseEntity<?> editar_PUT(@RequestBody Producto producto,@PathVariable Integer productoId)
	{
		Producto productoDb=productoService.findById(productoId);
		
		if(productoDb!=null)
		{
			productoDb.setNomProd(producto.getNomProd());
			productoDb.setPrecProd(producto.getPrecProd());
			
			
			productoService.update(productoDb);
			return new ResponseEntity<>("¡Producto actualizado!",HttpStatus.OK);
		}
		
		return new ResponseEntity<>("¡Producto no encontrado!",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/borrar/{productoId}")
	public ResponseEntity<?> borrar_DELETE(@PathVariable Integer productoId)
	{
		Producto productoDb=productoService.findById(productoId);
		
		if(productoDb!=null)
		{
			productoService.delete(productoId);
			return new ResponseEntity<>("¡Producto eliminado!",HttpStatus.OK);
		}
		
		return new ResponseEntity<>("¡Producto no encontrado!",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/buscar/{productoId}")
	public ResponseEntity<?> buscar_GET(@PathVariable Integer productoId)
	{
		Producto productoDb=productoService.findById(productoId);
		
		if(productoDb!=null) {
			return new ResponseEntity<>(productoDb,HttpStatus.FOUND);
		}
		
		return new ResponseEntity<>("¡Producto no encontrado!",HttpStatus.NOT_FOUND);
	}

}
