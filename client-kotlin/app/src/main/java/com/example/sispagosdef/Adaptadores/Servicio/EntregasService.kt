package com.example.sispagosdef.Adaptadores.Servicio


import com.example.sispagosdef.Adaptadores.Clases.Entregas
import retrofit2.Call
import retrofit2.http.*

interface EntregasService {

    @GET("entregas/listar")
    fun MostrarEntregas(): Call<List<Entregas>?>?

    @GET("entregas/buscar/{entregasId}")
    fun MostrarEntregasPersonalizadas(): Call<List<Entregas>?>?

    @POST("entregas/registrar")
    fun RegistrarEntregas(@Body e: Entregas?): Call<Entregas>?

    @PUT("entregas/editar/{entregasId}")
    fun ActualizarEntregas(@Path("entregasId") id:Long, @Body e: Entregas?): Call<Entregas?>?

    @DELETE("entregas/borrar/{entregasId}")
    fun EliminarEntregas(@Path("catId") id:Long): Call<Entregas?>?
}