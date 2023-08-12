package com.example.sispagos1.Servicio


import com.example.sispagos1.Clases.Proveedor
import retrofit2.Call
import retrofit2.http.*

interface ProveedorService {

    @GET("proveedores/listar")
    fun MostrarProveedor(): Call<List<Proveedor>?>?

    @GET("proveedores/buscar/{provId}")
    fun MostrarProveedorPersonalizada(): Call<List<Proveedor>?>?

    @POST("proveedores/registrar")
    fun RegistrarProveedor(@Body p: Proveedor?): Call<Proveedor>?

    @PUT("proveedores/editar/{provId}")
    fun ActualizarProveedor(@Path("provId") id:Long, @Body p: Proveedor?): Call<Proveedor?>?

    @DELETE("proveedores/borrar/{provId}")
    fun EliminarProveedor(@Path("provId") id:Long): Call<Proveedor?>?
}