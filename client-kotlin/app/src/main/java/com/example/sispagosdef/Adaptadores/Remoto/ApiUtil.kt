package com.example.sispagos1.Remoto

import com.example.sispagos1.Servicio.CategoriaService
import com.example.sispagos1.Servicio.DistritoService
import com.example.sispagos1.Servicio.ProductoService
import com.example.sispagos1.Servicio.ProveedorService
import com.example.sispagosdef.Adaptadores.Servicio.EntregasService
import com.example.sispagosdef.Adaptadores.Servicio.UsuarioService
import com.example.sispagosdef.Adaptadores.Servicio.VentasService

object ApiUtil {

    //configuramos una constante con la direccion del servicio
    val API_URL="http://192.168.1.40:8090/idat/"

    val categoriasservice:CategoriaService?
        get()=RetrofitGeneral.getClient(API_URL)?.create(CategoriaService::class.java)
    val productosservice:ProductoService?
        get()=RetrofitGeneral.getClient(API_URL)?.create(ProductoService::class.java)
    val proveedorsservice:ProveedorService?
        get()=RetrofitGeneral.getClient(API_URL)?.create(ProveedorService::class.java)
    val distritosservice:DistritoService?
        get()=RetrofitGeneral.getClient(API_URL)?.create(DistritoService::class.java)
    val ventasservice:VentasService?
        get()=RetrofitGeneral.getClient(API_URL)?.create(VentasService::class.java)
    val entregasservice: EntregasService?
        get()=RetrofitGeneral.getClient(API_URL)?.create(EntregasService::class.java)
    val usuariosservice: UsuarioService?
        get()=RetrofitGeneral.getClient(API_URL)?.create(UsuarioService::class.java)
}