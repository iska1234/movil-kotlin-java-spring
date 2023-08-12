package com.example.sispagosdef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MenuEntregas2 : AppCompatActivity() {
    private lateinit var viewAddEntrega: CardView
    private lateinit var viewListEntregas: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_entregas2)
        Enlazar()
        viewAddEntrega.setOnClickListener {
            val i = Intent(this,MenuEntregas::class.java)
            startActivity(i)
        }
        viewListEntregas.setOnClickListener {
            val i = Intent(this,VerEntregas::class.java)
            startActivity(i)
        }
    }

    private fun Enlazar()
    {
        viewAddEntrega=findViewById(R.id.btnAgregarDirEntrega)
        viewListEntregas=findViewById(R.id.btnVerEntregas)
    }
}