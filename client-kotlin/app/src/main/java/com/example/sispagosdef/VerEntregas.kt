package com.example.sispagosdef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.example.sispagos1.Clases.Producto
import com.example.sispagos1.Remoto.ApiUtil
import com.example.sispagos1.Servicio.ProductoService
import com.example.sispagos1.Utilidad.util
import com.example.sispagosdef.Adaptadores.AdaptadorEntregas
import com.example.sispagosdef.Adaptadores.AdaptadorProductoVentas
import com.example.sispagosdef.Adaptadores.Clases.Entregas
import com.example.sispagosdef.Adaptadores.Clases.Ventas
import com.example.sispagosdef.Adaptadores.Servicio.EntregasService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerEntregas : AppCompatActivity() {
    private lateinit var lstEnt: ListView
    private var dialogo: android.app.AlertDialog.Builder?=null
    val objentregas= Entregas()

    private var entregasService: EntregasService?=null
    //creamos un arraylist de Categoria
    private var registroentregas:List<Entregas>?=null
    var cod=0L
    var nom=""
    var dir=""
    var lat=0.0
    var lng=0.0

    var fila=-1
    var indice=-1
    var codcat=0L
    var pos=-1
    var nomcat=""
    //creamos un obejto de la clase utilidad
    var objutilidad= util()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_entregas)
        Enlazar()
        registroentregas=ArrayList()
        //implementamos el servicio
        entregasService= ApiUtil.entregasservice
        MostrarEntregas()

        lstEnt.setOnItemClickListener { parent, view, position, id ->
            fila=position
            //asignamos los valores a los controles
            cod= (registroentregas as ArrayList<Entregas>).get(fila).entregasId.toString().toLong()
            nom= (registroentregas as ArrayList<Entregas>).get(fila).prodnEntrega.toString()
            dir=(registroentregas as ArrayList<Entregas>).get(fila).dirEntrega.toString()
            lat=(registroentregas as ArrayList<Entregas>).get(fila).latEnt
            lng=(registroentregas as ArrayList<Entregas>).get(fila).lngEnt
            val i = Intent(this,MantenimientoEntregas::class.java)
            i.putExtra("id",cod)
            i.putExtra("nomb",nom)
            i.putExtra("dir",dir)
            i.putExtra("lat",lat)
            i.putExtra("lng",lng)
            startActivity(i)
        }
    }

    private fun Enlazar()
    {
        lstEnt=findViewById(R.id.lstentregas)
    }
    private fun MostrarEntregas(){
        val call= entregasService!!.MostrarEntregas()
        call!!.enqueue(object : Callback<List<Entregas>?> {
            override fun onResponse(
                call: Call<List<Entregas>?>,
                response: Response<List<Entregas>?>
            ) {
                if(response.isSuccessful){
                    registroentregas=response.body()
                    lstEnt.adapter= AdaptadorEntregas(this@VerEntregas,registroentregas)
                }
            }
            override fun onFailure(call: Call<List<Entregas>?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }
        })
    }
}