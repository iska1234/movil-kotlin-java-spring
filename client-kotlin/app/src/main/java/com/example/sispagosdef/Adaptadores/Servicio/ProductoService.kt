package com.example.sispagos1.Servicio


import com.example.sispagos1.Clases.Producto
import retrofit2.Call
import retrofit2.http.*

interface ProductoService {
    //creamos los metodos para acceder al servicio web
    @GET("productos/listar")
    fun MostrarProducto(): Call<List<Producto>?>?

    @GET("producto/buscar/{prodId}")
    fun MostrarProductoPersonalizado(): Call<List<Producto>?>?

    @POST("productos/registrar")
    fun RegistrarProducto(@Body p: Producto?): Call<Producto?>?

    @PUT("productos/editar/{prodId}")
    fun ActualizarProducto(@Path("prodId") id:Long, @Body p: Producto?): Call<Producto?>?

    @DELETE("productos/borrar/{prodId}")
    fun EliminarProducto(@Path("prodId") id:Long): Call<Producto?>?
}