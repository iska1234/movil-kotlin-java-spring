package com.example.sispagosdef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MenuCompras : AppCompatActivity() {
    private lateinit var viewAddProducto: CardView
    private lateinit var viewMantenimientoProveedor: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_compras)
        Enlazar()
        viewAddProducto.setOnClickListener {
            val i = Intent(this,AgregarProducto::class.java)
            startActivity(i)
        }
        viewMantenimientoProveedor.setOnClickListener {
            val i = Intent(this,MantenimientoProveedor::class.java)
            startActivity(i)
        }
    }

    private fun Enlazar()
    {
        viewAddProducto=findViewById(R.id.btnAgregarprodcompras)
        viewMantenimientoProveedor=findViewById(R.id.btnMantenimientoProveedor)
    }
}