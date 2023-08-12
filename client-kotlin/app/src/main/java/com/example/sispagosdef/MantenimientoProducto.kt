package com.example.sispagosdef

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import com.example.sispagos1.Adaptadores.AdaptadorComboCategoria
import com.example.sispagos1.Adaptadores.AdaptadorProductoAlmacen
import com.example.sispagos1.Adaptadores.AdaptadorProveedor
import com.example.sispagos1.Clases.Categoria
import com.example.sispagos1.Clases.Distrito
import com.example.sispagos1.Clases.Producto
import com.example.sispagos1.Clases.Proveedor
import com.example.sispagos1.Remoto.ApiUtil
import com.example.sispagos1.Servicio.CategoriaService
import com.example.sispagos1.Servicio.ProductoService
import com.example.sispagos1.Utilidad.util
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MantenimientoProducto : AppCompatActivity() {
    private lateinit var btnActualizar: Button
    private lateinit var btnEliminar: Button
    private lateinit var edtNomprod: TextInputEditText
    private lateinit var edtPrecprod: TextInputEditText
    private lateinit var cboCat: Spinner
    private lateinit var lstProd: ListView
    private lateinit var lblcod: TextView

    private var dialogo: android.app.AlertDialog.Builder?=null

    val objcategoria= Categoria()
    val objproducto= Producto()

    //
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

    var fila=-1
    var indice=-1
    var codcat=0L
    var pos=-1
    var nomcat=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mantenimiento_producto)
        Enlazar()

        registrocategoria=ArrayList()
        registroproducto=ArrayList()

        //implementamos el servicio
        categoriaService= ApiUtil.categoriasservice
        productoService= ApiUtil.productosservice

        MostrarCategoria()
        MostrarProducto()

        lstProd.setOnItemClickListener { parent, view, position, id ->
            fila=position
            //asignamos los valores a los controles
            lblcod.setText(""+ (registroproducto as ArrayList<Producto>).get(fila).prodId)
            edtNomprod.setText(""+ (registroproducto as ArrayList<Producto>).get(fila).nomProd)
            edtPrecprod.setText(""+ (registroproducto as ArrayList<Producto>).get(fila).precProd)
            for(i in (registrocategoria as ArrayList<Categoria>).indices){
                if((registrocategoria as ArrayList<Categoria>).get(i).descCat== (registroproducto as ArrayList<Producto>).get(fila).categoria?.descCat){
                    indice=i
                }
            }
            cboCat.setSelection(indice)
        }

        btnActualizar.setOnClickListener {
            if(fila>=0){
                cod=lblcod.getText().toString().toLong()
                nom=edtNomprod.getText().toString()
                prec=edtPrecprod.getText().toString().toDouble()
                pos=cboCat.selectedItemPosition
                codcat= (registrocategoria as ArrayList<Categoria>).get(pos).catId.toLong()
                nomcat= (registrocategoria as ArrayList<Categoria>).get(pos).descCat.toString()

                //enviamos los valores a la clase
                objproducto.prodId=cod
                objproducto.nomProd=nom
                objproducto.precProd=prec

                objcategoria.catId=codcat
                objproducto.categoria=objcategoria

                ActualizarProducto(objproducto,cod)
                DialogoCRUD(this,"Actualizacion","Se actualizo el producto correctamente")

            }else{
                objutilidad.MensajeToast(this,"Seleccione un elemento de la lista")
                lstProd.requestFocus()
            }
        }

        btnEliminar.setOnClickListener {
            if(fila>=0){
                cod=lblcod.getText().toString().toLong()

                //enviamos los valores a la clase
                objproducto.prodId=cod
                EliminarProducto(cod)
                DialogoCRUD(this,"Elimiar","Se elimino el producto correctamente")

            }else{
                objutilidad.MensajeToast(this,"Seleccione un elemento de la lista")
                lstProd.requestFocus()
            }
        }
    }



    private fun Enlazar()
    {
        lblcod=findViewById(R.id.lblcodprod)
        btnActualizar=findViewById(R.id.btnActualizarprodal)
        btnEliminar=findViewById(R.id.btnEliminarprodal)
        edtNomprod=findViewById(R.id.edtNomProdal)
        edtPrecprod=findViewById(R.id.edtPrecProdal)
        cboCat=findViewById(R.id.cboCatal)
        lstProd=findViewById(R.id.lstProdalmacen)


    }

    private fun MostrarCategoria() {
        val call= categoriaService!!.MostrarCategoria()
        call!!.enqueue(object: Callback<List<Categoria>?> {
            override fun onResponse(
                call: Call<List<Categoria>?>,
                response: Response<List<Categoria>?>
            ) {
                if(response.isSuccessful){
                    registrocategoria=response.body()
                    cboCat.adapter= AdaptadorComboCategoria(this@MantenimientoProducto,registrocategoria)
                }
            }
            override fun onFailure(call: Call<List<Categoria>?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }
        })
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
                    lstProd.adapter= AdaptadorProductoAlmacen(this@MantenimientoProducto,registroproducto)
                }
            }
            override fun onFailure(call: Call<List<Producto>?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }
        })
    }

    private fun ActualizarProducto(p: Producto?,id:Long){
        val call= productoService!!.ActualizarProducto(id,p)
        call!!.enqueue(object :Callback<Producto?>{
            override fun onResponse(call: Call<Producto?>, response: Response<Producto?>) {
                if(response.isSuccessful){
                    Log.e("mensaje","Se actualizo")
                }
            }

            override fun onFailure(call: Call<Producto?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }

        })
    }

    private fun EliminarProducto(id:Long){
        val call= productoService!!.EliminarProducto(id)
        call!!.enqueue(object :Callback<Producto?>{
            override fun onResponse(call: Call<Producto?>, response: Response<Producto?>) {
                if(response.isSuccessful){
                    Log.e("mensaje","Se elimino")
                }
            }
            override fun onFailure(call: Call<Producto?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }
        })
    }
    private fun DialogoCRUD(context: Context, titulo:String, mensaje:String){
        dialogo= android.app.AlertDialog.Builder(context)
        dialogo!!.setTitle(titulo)
        dialogo!!.setMessage(mensaje)
        dialogo!!.setCancelable(false)
        dialogo!!.setPositiveButton("Ok"){
                dialog,which->
            val i = Intent(this,MantenimientoProducto::class.java)
            startActivity(i)
        }
        dialogo!!.show()
    }
}