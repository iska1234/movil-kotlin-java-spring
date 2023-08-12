package com.example.sispagosdef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import com.example.sispagos1.Adaptadores.AdaptadorProductoAlmacen
import com.example.sispagos1.Clases.Categoria
import com.example.sispagos1.Clases.Producto
import com.example.sispagos1.Remoto.ApiUtil
import com.example.sispagos1.Servicio.CategoriaService
import com.example.sispagos1.Servicio.ProductoService
import com.example.sispagos1.Utilidad.util
import com.example.sispagosdef.Adaptadores.AdaptadorVentas
import com.example.sispagosdef.Adaptadores.Clases.Ventas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReporteAlmacen : AppCompatActivity() {
    private lateinit var lstProd: ListView

    private var dialogo: android.app.AlertDialog.Builder?=null

    val objcategoria= Categoria()
    val objproducto= Producto()
    private var categoriaService: CategoriaService?=null
    private var productoService: ProductoService?=null

    //creamos un arraylist de Categoria
    private var registrocategoria:List<Categoria>?=null
    private var registroproducto:List<Producto>?=null

    //creamos un obejto de la clase utilidad
    var objutilidad= util()

    var cod=0L
    var nom=""
    var prec=0.0
    var cat = ""
    var cad =""
    var fila=-1
    var indice=-1
    var codcat=0L
    var pos=-1
    var nomcat=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporte_almacen)
        Enlazar()
        registrocategoria=ArrayList()
        registroproducto=ArrayList()

        //implementamos el servicio
        categoriaService= ApiUtil.categoriasservice
        productoService= ApiUtil.productosservice

        MostrarProducto()

        lstProd.setOnItemClickListener { parent, view, position, id ->
            fila=position
            //asignamos los valores a los controles
            cod= (registroproducto as ArrayList<Producto>).get(fila).prodId.toString().toLong()
            nom= (registroproducto as ArrayList<Producto>).get(fila).nomProd.toString()
            prec=(registroproducto as ArrayList<Producto>).get(fila).precProd
            cat= (registroproducto as ArrayList<Producto>).get(fila).categoria?.descCat.toString()

            cad=    "Id Producto: "+cod+"\n"+
                    "Nombre del producto: " + nom + "\n" +
                    "Precio del producto: " + prec + "\n" +
                    "Categoria de producto: "+cat

            val i = Intent(this,EnviarPorEmail::class.java)
            i.putExtra("cad",cad)
            startActivity(i)
        }
    }

    private fun Enlazar(){
        lstProd=findViewById(R.id.lstreportealmacen)
    }

    private fun MostrarProducto(){
        val call= productoService!!.MostrarProducto()
        call!!.enqueue(object : Callback<List<Producto>?> {
            override fun onResponse(
                call: Call<List<Producto>?>,
                response: Response<List<Producto>?>
            ) {
                if(response.isSuccessful){
                    registroproducto=response.body()
                    lstProd.adapter= AdaptadorProductoAlmacen(this@ReporteAlmacen,registroproducto)
                }
            }
            override fun onFailure(call: Call<List<Producto>?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }
        })
    }
}