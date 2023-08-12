package com.example.sispagosdef.Adaptadores.Servicio


import com.example.sispagosdef.Adaptadores.Clases.Usuario
import retrofit2.Call
import retrofit2.http.*

interface UsuarioService {
    @GET("usuario/listar")
    fun MostrarUsuarios(): Call<List<Usuario>>

    @GET("usuario/buscar/{usuarioId}")
    fun MostrarUsuariosPersonalizado(): Call<List<Usuario>?>?

    @POST("usuario/registrar")
    fun RegistrarUsuario(@Body v: Usuario?): Call<Usuario>?

    @PUT("usuario/editar/{usuarioId}")
    fun ActualizarUsuario(@Path("usuarioId") id:Long, @Body v: Usuario?): Call<Usuario?>?

    @DELETE("usuario/borrar/{usuarioId}")
    fun EliminarUsuario(@Path("usuarioId") id:Long): Call<Usuario?>?
}
