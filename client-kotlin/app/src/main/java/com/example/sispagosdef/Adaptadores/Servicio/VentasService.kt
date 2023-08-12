package com.example.sispagosdef.Adaptadores.Servicio


import com.example.sispagosdef.Adaptadores.Clases.Ventas
import retrofit2.Call
import retrofit2.http.*

interface VentasService {

    @GET("ventas/listar")
    fun MostrarVentas(): Call<List<Ventas>>

    @GET("ventas/buscar/{ventasId}")
    fun MostrarVentasPersonalizada(): Call<List<Ventas>?>?

    @POST("ventas/registrar")
    fun RegistrarVentas(@Body v: Ventas?): Call<Ventas>?

    @PUT("ventas/editar/{ventasId}")
    fun ActualizarVentas(@Path("ventasId") id:Long, @Body v: Ventas?): Call<Ventas?>?

    @DELETE("ventas/borrar/{ventasId}")
    fun EliminarVentas(@Path("ventasId") id:Long): Call<Ventas?>?
}