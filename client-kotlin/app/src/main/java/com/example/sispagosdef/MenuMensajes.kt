package com.example.sispagosdef

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat

class MenuMensajes : AppCompatActivity() {
    private lateinit var edtnum: EditText
    private lateinit var message:EditText
    private lateinit var btnenviar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_mensajes)
        Enlazar()
        btnenviar.setOnClickListener {
            val num=edtnum.text.toString()
            val mess=message.text.toString()
            val sms =SmsManager.getDefault()
            if(num.isNotEmpty()){
                val smsIntent=Intent(Intent.ACTION_VIEW)
                smsIntent.data= Uri.parse("sms:$num")
                sms.sendTextMessage(num,null,mess,null,null)
                startActivity(smsIntent)
            }
        }
    }

    private fun Enlazar(){
        edtnum=findViewById(R.id.edtNumeroDestino)
        message=findViewById(R.id.edtMensaje)
        btnenviar=findViewById(R.id.btnEnviarSMS)
    }

    private fun checkPermissions(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),101)
        }
    }
}