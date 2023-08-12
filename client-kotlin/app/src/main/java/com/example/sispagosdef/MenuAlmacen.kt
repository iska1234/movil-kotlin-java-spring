package com.example.sispagosdef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MenuAlmacen : AppCompatActivity() {
    private lateinit var viewMantenimientoprod: CardView
    private lateinit var viewReportealmacen: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_almacen)
        Enlazar()

        viewMantenimientoprod.setOnClickListener {
            val i = Intent(this,MantenimientoProducto::class.java)
            startActivity(i)
        }
        viewReportealmacen.setOnClickListener {
            val i = Intent(this,ReporteAlmacen::class.java)
            startActivity(i)
        }
    }

    private fun Enlazar()
    {
        viewMantenimientoprod=findViewById(R.id.btnlistarprod)
        viewReportealmacen=findViewById(R.id.btnreportealmacen)
    }

}