package com.example.sispagosdef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.ListView
import com.example.sispagos1.Clases.Categoria
import com.example.sispagos1.Clases.Producto
import com.example.sispagos1.Remoto.ApiUtil

import com.example.sispagos1.Utilidad.util
import com.example.sispagosdef.Adaptadores.AdaptadorProductoVentas
import com.example.sispagosdef.Adaptadores.AdaptadorVentas
import com.example.sispagosdef.Adaptadores.Clases.Ventas
import com.example.sispagosdef.Adaptadores.Servicio.VentasService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MantenimientoVentas : AppCompatActivity() {
    private lateinit var lstVenta: ListView
    private lateinit var adapter: AdaptadorVentas
    private var dialogo: android.app.AlertDialog.Builder?=null
    val objventas= Ventas()
    //
    private var ventasService: VentasService?=null
    //creamos un arraylist de Categoria
    private var registroventas:List<Ventas>?=null
    var cod=0L
    var nom=""
    var prec=0.0
    var cant=0
    var tipop=""
    var tipoe=""
    var tipoc=""
    var total=0.0
    var cad=""

    var fila=-1
    var indice=-1
    var codcat=0L
    var pos=-1
    var nomcat=""
    //creamos un obejto de la clase utilidad
    var objutilidad= util()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mantenimiento_ventas)
        Enlazar()
        adapter=AdaptadorVentas(this, mutableListOf())
        lstVenta.adapter=adapter
        registroventas=ArrayList()
        //implementamos el servicio
        ventasService= ApiUtil.ventasservice
        MostrarVentas()

        lstVenta.setOnItemClickListener { parent, view, position, id ->
            fila=position
            //asignamos los valores a los controles
            cod= (registroventas as ArrayList<Ventas>).get(fila).ventasId.toString().toLong()
            nom= (registroventas as ArrayList<Ventas>).get(fila).prodnVenta.toString()
            prec=(registroventas as ArrayList<Ventas>).get(fila).precioVenta
            cant=(registroventas as ArrayList<Ventas>).get(fila).cantVenta
            tipop=(registroventas as ArrayList<Ventas>).get(fila).tipopago.toString()
            tipoe=(registroventas as ArrayList<Ventas>).get(fila).tipoentrega.toString()
            tipoc=(registroventas as ArrayList<Ventas>).get(fila).tipocomprobante.toString()
            total=(registroventas as ArrayList<Ventas>).get(fila).totalVenta

            cad= "Producto: "+nom+"\n"+
                    "Tipo Comprobante: " + tipoc + "\n" +
                    "Tipo de Entrega: " + tipoe + "\n" +
                    "Tipo de pago: "+tipop+"\n"+
                    "Precio: S/." + prec + "\n" +
                    "Cantidad: " + cant +"\n" +
                    "Total a Pagar: S/." + total;

            val i = Intent(this,EnviarPorEmail::class.java)
            i.putExtra("cad",cad)
            startActivity(i)
        }
    }

    fun Enlazar(){
        lstVenta=findViewById(R.id.lstventa)

    }

     fun MostrarVentas(){
        val call= ventasService!!.MostrarVentas()
        call.enqueue(object : Callback<List<Ventas>> {
            override fun onResponse(
                call: Call<List<Ventas>>,
                response: Response<List<Ventas>>
            ) {
                if(response.isSuccessful){
                    registroventas=response.body()
                    lstVenta.adapter=AdaptadorVentas(this@MantenimientoVentas, registroventas)
                }
            }
            override fun onFailure(call: Call<List<Ventas>>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }
        })
    }
}