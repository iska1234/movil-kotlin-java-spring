package com.example.sispagos1.Servicio


import com.example.sispagos1.Clases.Distrito
import retrofit2.Call
import retrofit2.http.GET

interface DistritoService {

    //creamos los metodos para acceder al servicio web
    @GET("distrito/listar")
    fun MostrarDistrito(): Call<List<Distrito>?>?
}