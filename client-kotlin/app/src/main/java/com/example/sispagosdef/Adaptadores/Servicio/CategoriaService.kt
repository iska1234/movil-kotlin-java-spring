package com.example.sispagos1.Servicio

import com.example.sispagos1.Clases.Categoria
import retrofit2.Call
import retrofit2.http.*

interface CategoriaService {

    //creamos los metodos para acceder al servicio web
    @GET("categoria/listar")
    fun MostrarCategoria(): Call<List<Categoria>?>?

    @GET("categoria/buscar/{catId}")
    fun MostrarCategoriaPersonalizada(): Call<List<Categoria>?>?

    @POST("categoria/registrar")
    fun RegistrarCategoria(@Body c:Categoria?): Call<Categoria>?

    @PUT("categoria/editar/{catId}")
    fun ActualizarCategoria(@Path("catId") id:Long, @Body c:Categoria?): Call<Categoria?>?

    @DELETE("categoria/borrar/{catId}")
    fun EliminarCategoria(@Path("catId") id:Long): Call<Categoria?>?
}