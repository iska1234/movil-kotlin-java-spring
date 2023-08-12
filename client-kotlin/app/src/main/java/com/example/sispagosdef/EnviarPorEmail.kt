package com.example.sispagosdef

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.google.android.material.textfield.TextInputEditText

class EnviarPorEmail : AppCompatActivity() {

    private lateinit var btnenviar: Button
    private lateinit var edtemail: TextInputEditText
    private lateinit var edtasunto: TextInputEditText
    private lateinit var tvcadena: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enviar_por_email)
        Enlazar()
        val bundle=intent.extras
        val cad = bundle?.getString("cad")
        tvcadena.setText(cad)

        btnenviar.setOnClickListener {
            val recipient = edtemail.text.toString().trim()
            val subject = edtasunto.text.toString().trim()
            val message = tvcadena.text.toString().trim()
            val intent = Intent(Intent.ACTION_SEND)
            intent.data = Uri.parse("mailto:")
            intent.type= "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(Intent.createChooser(intent,"Choose email"))

        }
    }
    private fun Enlazar(){
        btnenviar=findViewById(R.id.btnEnviarEmail)
        edtemail=findViewById(R.id.edtEmailAddress)
        edtasunto=findViewById(R.id.edtTemaEmail)
        tvcadena=findViewById(R.id.tvcademail)
    }

}