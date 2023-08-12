package com.example.sispagosdef

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MenuLlamadas : AppCompatActivity() {
    private lateinit var btnllamar: Button
    private lateinit var edtnum: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_llamadas)
        Enlazar()

        btnllamar.setOnClickListener {

            if(ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){
                val num=edtnum.text.toString().trim()
                val uri =Uri.parse("tel:$num")
                val i = Intent(Intent.ACTION_CALL)
                i.data=uri
                startActivity(i)
            }else{
                Toast.makeText(this,"No hay permisos de llamada",Toast.LENGTH_LONG).show()
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),123)
            }
        }
    }

   private fun Enlazar(){
       btnllamar=findViewById(R.id.btnLlamar)
       edtnum=findViewById(R.id.edtCelularLlamada)
   }

}