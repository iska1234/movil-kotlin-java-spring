package pe.idat.serv.cavero.ServicioProyecto.Service;

import java.util.Collection;

import pe.idat.serv.cavero.ServicioProyecto.Entity.Proveedor;



public interface ProveedorService {
	public abstract void insert(Proveedor proveedorId);
	public abstract void update(Proveedor proveedorId);
	public abstract void delete(Integer proveedorId);
	public abstract Proveedor findById(Integer proveedorId);
	public abstract Collection<Proveedor> findAll();
}
