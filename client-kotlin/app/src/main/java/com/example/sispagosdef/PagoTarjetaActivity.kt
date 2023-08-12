package com.example.sispagosdef

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import com.google.android.material.textfield.TextInputEditText

class PagoTarjetaActivity : AppCompatActivity() {

    private lateinit var edtnum: TextInputEditText
    private lateinit var edtfv: TextInputEditText
    private lateinit var edtcvv: TextInputEditText
    private lateinit var edttitular: TextInputEditText
    private lateinit var btnregistrar: Button
    private var dialogo: AlertDialog.Builder?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago_tarjeta)
        Enlazar()
        val bundle=intent.extras
        val cad = bundle?.getString("cad")
        btnregistrar.setOnClickListener {
            DialogoCRUD(this,"Registro de Venta","Se la venta correctamente")
            val i = Intent(this,ComprobanteActivity::class.java)
            i.putExtra("cad",cad)
            startActivity(i)
        }
    }

    private fun Enlazar() {
        edtnum=findViewById(R.id.edtNumtarjeta)
        edtfv=findViewById(R.id.edtFvtarjeta)
        edtcvv=findViewById(R.id.edtCVVTarjeta)
        edttitular=findViewById(R.id.edtTitularTarjeta)
        btnregistrar=findViewById(R.id.btnRegistrartarjeta)

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