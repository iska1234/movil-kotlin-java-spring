package com.example.sispagosdef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MenuPrincipal : AppCompatActivity() {
    private lateinit var viewAlmacen:CardView
    private lateinit var viewCompras:CardView
    private lateinit var viewVentas:CardView
    private lateinit var viewEntregas:CardView
    private lateinit var viewLLamadas:CardView
    private lateinit var viewMensajes:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_principal)
        Enlazar()
        viewAlmacen.setOnClickListener {
            val i = Intent(this,MenuAlmacen::class.java)
            startActivity(i)
        }
        viewCompras.setOnClickListener {
            val i = Intent(this,MenuCompras::class.java)
            startActivity(i)
        }
        viewVentas.setOnClickListener {
            val i = Intent(this,VentasMenu::class.java)
            startActivity(i)
        }
        viewEntregas.setOnClickListener {
            val i = Intent(this,MenuEntregas2::class.java)
            startActivity(i)
        }
        viewLLamadas.setOnClickListener {
            val i = Intent(this,MenuLlamadas::class.java)
            startActivity(i)
        }
        viewMensajes.setOnClickListener {
            val i = Intent(this,MenuMensajes::class.java)
            startActivity(i)
        }
    }
    private fun Enlazar(){

        viewAlmacen=findViewById(R.id.viewM1)
        viewCompras=findViewById(R.id.viewM2)
        viewVentas=findViewById(R.id.viewM4)
        viewEntregas=findViewById(R.id.viewM5)
        viewLLamadas=findViewById(R.id.viewM6)
        viewMensajes=findViewById(R.id.viewM7)

    }

}