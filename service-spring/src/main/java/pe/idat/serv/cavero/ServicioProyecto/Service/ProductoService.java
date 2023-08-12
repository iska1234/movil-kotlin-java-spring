package pe.idat.serv.cavero.ServicioProyecto.Service;

import java.util.Collection;

import pe.idat.serv.cavero.ServicioProyecto.Entity.Producto;



public interface ProductoService {
	public abstract void insert(Producto productoId);
	public abstract void update(Producto productoId);
	public abstract void delete(Integer productoId);
	public abstract Producto findById(Integer productoId);
	public abstract Collection<Producto> findAll();
}
