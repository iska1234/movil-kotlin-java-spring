package com.example.sispagosdef

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sispagos1.Clases.Categoria
import com.example.sispagos1.Clases.Producto
import com.example.sispagos1.Remoto.ApiUtil
import com.example.sispagos1.Utilidad.util

import com.example.sispagosdef.Adaptadores.Clases.Entregas
import com.example.sispagosdef.Adaptadores.Servicio.EntregasService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

class DatosEntregas : AppCompatActivity() {
    private lateinit var tvId: TextView
    private lateinit var tvNomb: TextView
    private lateinit var edtDireccion: TextInputEditText
    private lateinit var btnagregar: Button
    private var dialogo: android.app.AlertDialog.Builder?=null
    var TAG = "DatosEntregas"
    var addressLat=0.0
    var addressLong=0.0
    //
    val objentregas= Entregas()
    //
    private var entregasService: EntregasService?=null
    //creamos un arraylist de Categoria
    private var registroentregas:List<Entregas>?=null
    //
    var objutilidad= util()
    //
    var cod=0L
    var nom=""
    var dir=""

    //
    var fila=-1
    var indice=-1
    var codcat=0L
    var pos=-1
    var nomcat=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_entregas)
        Enlazar()
        //creamos el arraylist de Categoria
        registroentregas=ArrayList()
        entregasService= ApiUtil.entregasservice

        val bundle=intent.extras
        val id = bundle?.getLong("id")
        val nomb = bundle?.getString("nomb")
        tvId.setText(id.toString())
        tvNomb.setText(nomb)
        edtDireccion.setOnClickListener {
            gotoAddressMap()
        }

        btnagregar.setOnClickListener {
            if(edtDireccion.getText().toString()==""){
                objutilidad.MensajeToast(this,"Ingrese el la direccion")
                edtDireccion.requestFocus()
            }else{
                //capturando valores
                nom=tvNomb.getText().toString()
                dir=edtDireccion.getText().toString()
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                var date =dateFormat.format(Date())

                //enviamos los valores a la clase
                objentregas.prodnEntrega=nom
                objentregas.dirEntrega=dir
                objentregas.fEntrega=date
                objentregas.latEnt=addressLat
                objentregas.lngEnt=addressLong
                RegistrarEntregas(objentregas)
                DialogoCRUD(this,"Registro de entrega","Se registro la entrega correctamente")

            }
        }
    }

    var resultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
        if(result.resultCode== Activity.RESULT_OK){
            val data =result.data
            val city=data?.getStringExtra("city")
            val address=data?.getStringExtra("address")
            val country=data?.getStringExtra("country")
            addressLat=data?.getDoubleExtra("lat",0.0)!!
            addressLong=data?.getDoubleExtra("lng",0.0)!!

            edtDireccion?.setText("$address $city")

            Log.d(TAG,"City: $city")
            Log.d(TAG,"Address: $address")
            Log.d(TAG,"Country: $country")
            Log.d(TAG,"Lat: $addressLat")
            Log.d(TAG,"Lng: $addressLong")


        }
    }
    private fun gotoAddressMap(){
        val i = Intent(this,EntregasActivity::class.java)
        resultLauncher.launch(i)
    }
    fun Enlazar(){
        tvId=findViewById(R.id.tvIdventa)
        tvNomb=findViewById(R.id.tvNombentrega)
        edtDireccion=findViewById(R.id.edtDireccionentrega)
        btnagregar=findViewById(R.id.btnAñadirEntrega)
    }

    private fun RegistrarEntregas(e: Entregas?) {
        val call = entregasService!!.RegistrarEntregas(e)
        call!!.enqueue(object : Callback<Entregas?> {
            override fun onResponse(call: Call<Entregas?>, response: Response<Entregas?>) {
                if (response.isSuccessful) {
                    Log.e("mensaje", "Se registro")
                }
            }
            override fun onFailure(call: Call<Entregas?>, t: Throwable) {
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
                val i = Intent(this,MenuEntregas2::class.java)
                startActivity(i)
        }
        dialogo!!.show()
    }
}