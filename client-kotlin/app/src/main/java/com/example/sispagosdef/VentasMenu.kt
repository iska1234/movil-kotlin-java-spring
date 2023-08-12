package com.example.sispagosdef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class VentasMenu : AppCompatActivity() {
    private lateinit var vmantenimiento: CardView

    private lateinit var vnuevo: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventas_menu)
        Enlazar()
        vnuevo.setOnClickListener {
            val i = Intent(this,MenuVentas::class.java)
            startActivity(i)
        }
        vmantenimiento.setOnClickListener {
            val i = Intent(this,MantenimientoVentas::class.java)
            startActivity(i)
        }

    }
    private fun Enlazar(){
        vmantenimiento=findViewById(R.id.vmantenimiento)
        vnuevo=findViewById(R.id.vnueva)
    }
}