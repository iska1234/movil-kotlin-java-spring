package com.example.sispagosdef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf

class InicioVentaActivity : AppCompatActivity() {
    private lateinit var btnBoleta: Button
    private lateinit var btnFactura: Button
    private lateinit var tvNombre: TextView
    private lateinit var tvPrecio: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_venta)
        Enlazar()
        val bundle=intent.extras
        val nom = bundle?.getString("nom")
        val prec=bundle?.getDouble("prec")

        tvNombre.setText(nom)
        tvPrecio.setText("S/."+prec)

        btnBoleta.setOnClickListener {
            val i = Intent(this,ExtrasVentasActivity::class.java)
            i.putExtra("nom",nom)
            i.putExtra("prec",prec)
            i.putExtra("tipoc","Boleta")
            startActivity(i)
        }

        btnFactura.setOnClickListener {
            val i = Intent(this,ExtrasVentasActivity::class.java)
            i.putExtra("nom",nom)
            i.putExtra("prec",prec)
            i.putExtra("tipoc","Factura")
            startActivity(i)

        }
    }

    private fun Enlazar(){
        tvNombre=findViewById(R.id.tvnomprodventa1)
        tvPrecio=findViewById(R.id.tvprecrodventa1)
        btnBoleta=findViewById(R.id.btnBoleta)
        btnFactura=findViewById(R.id.btnFactura)

    }
}


