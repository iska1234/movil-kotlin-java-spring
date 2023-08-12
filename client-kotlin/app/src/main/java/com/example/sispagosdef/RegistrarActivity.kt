package com.example.sispagosdef

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.example.sispagos1.Adaptadores.AdaptadorComboDistrito
import com.example.sispagos1.Clases.Distrito
import com.example.sispagos1.Remoto.ApiUtil
import com.example.sispagos1.Servicio.DistritoService
import com.example.sispagos1.Utilidad.util
import com.example.sispagosdef.Adaptadores.Clases.Usuario
import com.example.sispagosdef.Adaptadores.Servicio.UsuarioService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrarActivity : AppCompatActivity() {
    private lateinit var edtNombre:TextInputEditText
    private lateinit var edtApellido:TextInputEditText
    private lateinit var edtDni:TextInputEditText
    private lateinit var edtTelefono:TextInputEditText
    private lateinit var edtCorreo:TextInputEditText
    private lateinit var edtPassword:TextInputEditText
    private lateinit var cbouser: Spinner
    private lateinit var btnRegisrar: Button

    private var dialogo: android.app.AlertDialog.Builder?=null
    val objusuario= Usuario()
    val objdistrito= Distrito()
//    val objreservaciones = Reservaciones()
    //val objproducto= Producto()

    //
    private var usuarioService: UsuarioService?=null
    private  var distritoService: DistritoService?=null
    //    private  var reservacionesService: ReservacionService?=null
    //creamos un arraylist de Categoria
    private var registrousuario:List<Usuario>?=null
    private var registrodistrito:List<Distrito>?=null
    //    private var registroreservaciones:List<Reservaciones>?=null
    //creamos un obejto de la clase utilidad
    var objutilidad= util()

    var id=0L
    var nom=""
    var ape=""
    var dni=""
    var telf=""
    var user=""
    var passw=""
    var dis=""
    var disid=0L

    var pago=0.0
    var crgid=0L
    var pagoid=0L
    var prec=0.0

    var fila=-1
    var indice=-1
    var codcat=0L
    var pos1=-1
    var pos2=-1
    var nomcat=""
    var est=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        Enlazar()
        registrodistrito=ArrayList()
        registrousuario=ArrayList()

        //implementamos el servicio
        distritoService= ApiUtil.distritosservice
        usuarioService= ApiUtil.usuariosservice

        MostrarDistrito()
        btnRegisrar.setOnClickListener {
            nom=edtNombre.getText().toString()
            ape=edtApellido.getText().toString()
            dni=edtDni.getText().toString()
            telf=edtTelefono.getText().toString()
            user=edtCorreo.getText().toString()
            passw=edtPassword.getText().toString()

            pos2=cbouser.selectedItemPosition
            disid= (registrodistrito as ArrayList<Distrito>).get(pos2).distritoId.toLong()
            dis= (registrodistrito as ArrayList<Distrito>).get(pos2).nomDistrito.toString()

            objusuario.nomUsuario=nom
            objusuario.apeUsuario=ape
            objusuario.docUsuario=dni
            objusuario.tlfUsuario=telf
            objusuario.emaUsuario=user
            objusuario.passwUsuario=passw

            objdistrito.distritoId=disid
            objusuario.distritouser=objdistrito

            RegistrarUsuario(objusuario)
            DialogoCRUD(this,"Registro de usuario exitoso","Se registro al usuario correctamente")
        }
    }

    fun Enlazar(){
        edtNombre=findViewById(R.id.edtNombreuser)
        edtApellido=findViewById(R.id.edtApellidouser)
        edtDni=findViewById(R.id.edtDocumentouser)
        edtTelefono=findViewById(R.id.edtTelefono)
        edtCorreo=findViewById(R.id.edtCorreoRegistraruser)
        edtPassword=findViewById(R.id.edtContraseniaRegistraruser)
        cbouser=findViewById(R.id.cbodistritouser)
        btnRegisrar=findViewById(R.id.btnRegistrarCuenta)
    }


    private fun MostrarDistrito() {
        val call= distritoService!!.MostrarDistrito()
        call!!.enqueue(object: Callback<List<Distrito>?> {
            override fun onResponse(
                call: Call<List<Distrito>?>,
                response: Response<List<Distrito>?>
            ) {
                if(response.isSuccessful){
                    registrodistrito=response.body()
                    cbouser.adapter= AdaptadorComboDistrito(this@RegistrarActivity,registrodistrito)
                }
            }
            override fun onFailure(call: Call<List<Distrito>?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }
        })
    }
    private fun RegistrarUsuario(u: Usuario?) {
        val call = usuarioService!!.RegistrarUsuario(u)
        call!!.enqueue(object : Callback<Usuario?> {
            override fun onResponse(call: Call<Usuario?>, response: Response<Usuario?>) {
                if (response.isSuccessful) {
                    Log.e("mensaje", "Se registro")
                }
            }

            override fun onFailure(call: Call<Usuario?>, t: Throwable) {
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

        }
        dialogo!!.show()
    }
}