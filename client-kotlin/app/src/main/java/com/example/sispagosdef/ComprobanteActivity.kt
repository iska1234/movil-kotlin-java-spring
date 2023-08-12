package com.example.sispagosdef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ComprobanteActivity : AppCompatActivity() {
    private lateinit var tvcad: TextView
    private lateinit var btnmenu: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprobante)
        Enlazar()
        val bundle=intent.extras
        val cad = bundle?.getString("cad")
        tvcad.setText(cad)
        btnmenu.setOnClickListener {
            val i = Intent(this,MenuPrincipal::class.java)
            startActivity(i)
        }
    }

    private fun Enlazar(){
        tvcad= findViewById(R.id.tvDatos)
        btnmenu=findViewById(R.id.btnmenu)

    }
}