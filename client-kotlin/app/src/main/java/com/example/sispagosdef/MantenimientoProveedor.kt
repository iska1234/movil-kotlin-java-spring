package com.example.sispagosdef

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.sispagos1.Adaptadores.AdaptadorComboDistrito
import com.example.sispagos1.Adaptadores.AdaptadorProveedor
import com.example.sispagos1.Clases.Distrito
import com.example.sispagos1.Clases.Proveedor
import com.example.sispagos1.Remoto.ApiUtil
import com.example.sispagos1.Servicio.DistritoService
import com.example.sispagos1.Servicio.ProveedorService
import com.example.sispagos1.Utilidad.util
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MantenimientoProveedor : AppCompatActivity() {

    //declaramos los controles
    private lateinit var txtnom: TextInputEditText
    private lateinit var txtdir: TextInputEditText
    private lateinit var txtema: TextInputEditText
    private lateinit var txttelf: TextInputEditText
    private lateinit var lblCodProv: TextView
    private lateinit var btnRegistrar: Button
    private lateinit var btnActualizar: Button
    private lateinit var btnEliminar: Button
    private lateinit var lstProv: ListView
    private var dialogo: AlertDialog.Builder?=null

    //creamos un objeto de la clase Categoria
    val objproveedor= Proveedor()

    //declaramos el servicio
    private var proveedorService: ProveedorService?=null

    //creamos un arraylist de Categoria
    private var registroproveedor:List<Proveedor>?=null

    //creamos un obejto de la clase utilidad
    var objutilidad= util()

    //declaramos variables
    var cod=0L
    var nom=""
    var dir=""
    var ema=""
    var telf=""
    var fila=-1
    var indice=-1
    var coddist=0L
    var pos=-1
    var nomdist=""

    private var ft: FragmentTransaction?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mantenimiento_proveedor)
        Enlazar()

        //creamos el arraylist de Categoria

        registroproveedor=ArrayList()

        //implementamos el servicio

        proveedorService= ApiUtil.proveedorsservice


        //llamamos ala funcion para mostrar los datos del combo


        //llamamos al metodo para mostrar los producto
        MostrarProveedor()

        //generamos los eventos
        btnRegistrar.setOnClickListener {
            if(txtnom.getText().toString()==""){
                objutilidad.MensajeToast(this,"Ingrese el nombre")
                txtnom.requestFocus()
            }else if(txtdir.getText().toString()==""){
                objutilidad.MensajeToast(this,"Ingrese la direccion")
                txtdir.requestFocus()
            }else if(txttelf.getText().toString()==""){
                objutilidad.MensajeToast(this,"Ingrese el telefono")
                txttelf.requestFocus()
            }else if(txtema.getText().toString()==""){
                objutilidad.MensajeToast(this,"Ingrese el email")
                txtema.requestFocus()
            } else{
                //capturando valores
                nom=txtema.getText().toString()
                dir=txtdir.getText().toString()
                telf=txttelf.getText().toString()
                ema=txtema.getText().toString()

                //enviamos los valores a la clase
                objproveedor.nomProv=nom
                objproveedor.dirProv=dir
                objproveedor.telfProv=telf
                objproveedor.emaProv=ema
                RegistrarProveedor(objproveedor)
                DialogoCRUD(this,"Registro de Proveedor","Se registro el proveedor correctamente")

            }
        }

        lstProv.setOnItemClickListener { parent, view, position, id ->
            fila=position
            //asignamos los valores a los controles
            lblCodProv.setText(""+ (registroproveedor as ArrayList<Proveedor>).get(fila).provId)
            txtnom.setText(""+ (registroproveedor as ArrayList<Proveedor>).get(fila).nomProv)
            txtdir.setText(""+ (registroproveedor as ArrayList<Proveedor>).get(fila).dirProv)
            txtema.setText(""+ (registroproveedor as ArrayList<Proveedor>).get(fila).emaProv)
            txttelf.setText(""+ (registroproveedor as ArrayList<Proveedor>).get(fila).telfProv)


        }

        btnActualizar.setOnClickListener {
            if(fila>=0){
                cod=lblCodProv.getText().toString().toLong()
                nom=txtnom.getText().toString()
                dir=txtdir.getText().toString()
                ema=txtema.getText().toString()
                telf=txttelf.getText().toString()

                //enviamos los valores a la clase
                objproveedor.nomProv=nom
                objproveedor.dirProv=dir
                objproveedor.telfProv=telf
                objproveedor.emaProv=ema

                ActualizarProveedor(objproveedor,cod)
                DialogoCRUD(this,"Actualizacion","Se actualizo el proveedor correctamente")

            }else{
                objutilidad.MensajeToast(this,"Seleccione un elemento de la lista")
                lstProv.requestFocus()
            }
        }

        btnEliminar.setOnClickListener {
            if(fila>=0){
                cod=lblCodProv.getText().toString().toLong()
                //enviamos los valores a la clase
                objproveedor.provId=cod
                EliminarProveedor(cod)
                DialogoCRUD(this,"Elimiar","Se elimino el proveedorcorrectamente")

            }else{
                objutilidad.MensajeToast(this,"Seleccione un elemento de la lista")
                lstProv.requestFocus()
            }
        }

    }


    private fun Enlazar(){
        txtnom=findViewById(R.id.txtNomprov)
        txtdir=findViewById(R.id.txtDirprov)
        txtema=findViewById(R.id.txtEmaprov)
        txttelf=findViewById(R.id.txtTelfprov)
        lblCodProv=findViewById(R.id.lblIdprov)
        lstProv=findViewById(R.id.lstProv)
        btnRegistrar=findViewById(R.id.btnRegistrarProv)
        btnActualizar=findViewById(R.id.btnActualizarProv)
        btnEliminar=findViewById(R.id.btnEliminarProv)
    }
    //creamos la funcion para mostrar productos
    fun MostrarProveedor(){
        val call= proveedorService!!.MostrarProveedor()
        call!!.enqueue(object : Callback<List<Proveedor>?> {
            override fun onResponse(
                call: Call<List<Proveedor>?>,
                response: Response<List<Proveedor>?>
            ) {
                if(response.isSuccessful){
                    registroproveedor=response.body()
                    lstProv.adapter= AdaptadorProveedor(this@MantenimientoProveedor,registroproveedor)
                }
            }

            override fun onFailure(call: Call<List<Proveedor>?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }
        })
    }

    //creamos una funcion para registrar producto
    fun RegistrarProveedor(p: Proveedor?){
        val call= proveedorService!!.RegistrarProveedor(p)
        call!!.enqueue(object : Callback<Proveedor?> {
            override fun onResponse(call: Call<Proveedor?>, response: Response<Proveedor?>) {
                if(response.isSuccessful){
                    Log.e("mensaje","Se registro")
                }
            }
            override fun onFailure(call: Call<Proveedor?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }

        })
    }

    //creamos una funcion para actualizar
    fun ActualizarProveedor(p: Proveedor?, id:Long){
        val call= proveedorService!!.ActualizarProveedor(id,p)
        call!!.enqueue(object : Callback<Proveedor?> {
            override fun onResponse(call: Call<Proveedor?>, response: Response<Proveedor?>) {
                if(response.isSuccessful){
                    Log.e("mensaje","Se actualizo")
                }
            }

            override fun onFailure(call: Call<Proveedor?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }


        })
    }

    //creamos una funcion para eliminar
    fun EliminarProveedor(id:Long){
        val call= proveedorService!!.EliminarProveedor(id)
        call!!.enqueue(object : Callback<Proveedor?> {
            override fun onResponse(call: Call<Proveedor?>, response: Response<Proveedor?>) {
                if(response.isSuccessful){
                    Log.e("mensaje","Se elimino")
                }
            }

            override fun onFailure(call: Call<Proveedor?>, t: Throwable) {
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
            val i = Intent(this,MantenimientoProveedor::class.java)
            startActivity(i)
        }
        dialogo!!.show()
    }

}