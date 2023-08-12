package com.example.sispagosdef

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.sispagos1.Adaptadores.AdaptadorCategoria
import com.example.sispagos1.Adaptadores.AdaptadorComboCategoria
import com.example.sispagos1.Adaptadores.AdaptadorProveedor
import com.example.sispagos1.Clases.Categoria
import com.example.sispagos1.Clases.Producto
import com.example.sispagos1.Remoto.ApiUtil
import com.example.sispagos1.Servicio.CategoriaService
import com.example.sispagos1.Servicio.ProductoService
import com.example.sispagos1.Utilidad.util
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgregarProducto : AppCompatActivity() {
    private lateinit var btnRegistrar: Button
    private lateinit var edtNomprod: TextInputEditText
    private lateinit var edtPrecprod: TextInputEditText
    private lateinit var cboCat: Spinner
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
        setContentView(R.layout.activity_agregar_producto)
        Enlazar()

        //creamos el arraylist de Categoria
        registrocategoria=ArrayList()
        registroproducto=ArrayList()

        //implementamos el servicio
        categoriaService= ApiUtil.categoriasservice
        productoService= ApiUtil.productosservice
        MostrarCategoria()

        btnRegistrar.setOnClickListener {
            if(edtNomprod.getText().toString()==""){
                objutilidad.MensajeToast(this,"Ingrese el nombre")
                edtNomprod.requestFocus()
            }else if(edtPrecprod.getText().toString()==""){
                objutilidad.MensajeToast(this,"Ingrese el precio de compra")
                edtPrecprod.requestFocus()
            }else if(cboCat.selectedItemPosition==-1){
                objutilidad.MensajeToast(this,"Seleccione una categoria")
                cboCat.requestFocus()
            }else{
                //capturando valores
                nom=edtNomprod.getText().toString()
                prec=edtPrecprod.getText().toString().toDouble()
                pos=cboCat.selectedItemPosition
                codcat= (registrocategoria as ArrayList<Categoria>).get(pos).catId.toLong()
                nomcat= (registrocategoria as ArrayList<Categoria>).get(pos).descCat.toString()

                //enviamos los valores a la clase
                objproducto.nomProd=nom
                objproducto.precProd=prec

                objcategoria.catId=codcat
                objproducto.categoria=objcategoria

                RegistrarProducto(objproducto)
                DialogoCRUD(this,"Registro de Producto","Se registro el producto correctamente")

            }
        }
    }

    private fun Enlazar()
    {
        btnRegistrar=findViewById(R.id.btnAgregarprod)
        edtNomprod=findViewById(R.id.edtNomProd)
        edtPrecprod=findViewById(R.id.edtPrecProd)
        cboCat=findViewById(R.id.cboCat)
    }


    private fun MostrarCategoria() {
        val call= categoriaService!!.MostrarCategoria()
        call!!.enqueue(object: Callback<List<Categoria>?>{
            override fun onResponse(
                call: Call<List<Categoria>?>,
                response: Response<List<Categoria>?>
            ) {
                if(response.isSuccessful){
                    registrocategoria=response.body()
                    cboCat.adapter= AdaptadorComboCategoria(this@AgregarProducto,registrocategoria)
                }
            }
            override fun onFailure(call: Call<List<Categoria>?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }
        })
    }

    private fun RegistrarProducto(p: Producto?) {
        val call = productoService!!.RegistrarProducto(p)
        call!!.enqueue(object : Callback<Producto?> {
            override fun onResponse(call: Call<Producto?>, response: Response<Producto?>) {
                if (response.isSuccessful) {
                    Log.e("mensaje", "Se registro")
                }
            }

            override fun onFailure(call: Call<Producto?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }

        })
    }
    private fun DialogoCRUD(context: Context,titulo:String,mensaje:String){
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