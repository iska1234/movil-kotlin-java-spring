package com.example.sispagosdef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.sispagos1.Remoto.ApiUtil
import com.example.sispagos1.Utilidad.util
import com.example.sispagosdef.Adaptadores.AdaptadorUsuarioCombo
import com.example.sispagosdef.Adaptadores.Clases.Usuario
import com.example.sispagosdef.Adaptadores.Servicio.UsuarioService
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var tvregistrar :TextView
    private lateinit var btnIngresar : Button
    private lateinit var cbouser: Spinner
    private lateinit var edtPassw: TextInputEditText
    private lateinit var tilPassw: TextInputLayout

    private var dialogo: android.app.AlertDialog.Builder?=null

    val objusuario= Usuario()

    private var usuarioService: UsuarioService?=null

    //    private  var reservacionesService: ReservacionService?=null
    //creamos un arraylist de Categoria
    private var registrousuario:List<Usuario>?=null

    var objutilidad= util()

    var id=0L
    var user=""
    var passw=""
    var edtus=""
    var edtpass=""
    var fila=-1
    var indice=-1
    var codcat=0L
    var pos1=-1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Enlazar()
        registrousuario = ArrayList()
        usuarioService = ApiUtil.usuariosservice

        MostrarUsuario()

        cbouser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                pos1 = cbouser.selectedItemPosition
                user = (registrousuario as ArrayList<Usuario>).get(pos1).emaUsuario.toString()
                passw = (registrousuario as ArrayList<Usuario>).get(pos1).passwUsuario.toString()

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle nothing selected here

            }
        }

        tvregistrar.setOnClickListener {
            val i = Intent(this,RegistrarActivity::class.java)
            startActivity(i)
        }
        btnIngresar.setOnClickListener {
            edtpass=edtPassw.getText().toString()
            if (edtpass.isEmpty()) {
                tilPassw.setError("Este campo es requerido");
            } else {
                tilPassw.setError(null);
            }
            if(edtpass==passw)
            {
                Toast.makeText(this@LoginActivity, "Bienvenido", Toast.LENGTH_SHORT).show()
                val i = Intent(this,MenuPrincipal::class.java)
                startActivity(i)
            }else{
                Toast.makeText(this@LoginActivity, "No coinciden", Toast.LENGTH_SHORT).show()
            }

        }

    }
    fun Enlazar(){
        tilPassw=findViewById(R.id.tilPassEmail)
        tvregistrar = findViewById(R.id.tvRegistrarse)
        btnIngresar = findViewById(R.id.btnIngresarUsuario)
        cbouser=findViewById(R.id.cbouser)
        edtPassw = findViewById(R.id.edtContraseniaUsuario)
    }

    private fun MostrarUsuario() {
        val call= usuarioService!!.MostrarUsuarios()
        call!!.enqueue(object: Callback<List<Usuario>?> {
            override fun onResponse(
                call: Call<List<Usuario>?>,
                response: Response<List<Usuario>?>
            ) {
                if(response.isSuccessful){
                    registrousuario=response.body()
                    cbouser.adapter= AdaptadorUsuarioCombo(this@LoginActivity,registrousuario)
                }
            }
            override fun onFailure(call: Call<List<Usuario>?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }
        })
    }

}
