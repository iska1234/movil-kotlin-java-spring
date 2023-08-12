package com.example.sispagosdef.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sispagosdef.Adaptadores.Clases.Entregas

import com.example.sispagosdef.R

class AdaptadorEntregas (context: Context?, private val listaentregas: List<Entregas>?) : BaseAdapter()  {

    private val layoutInflater: LayoutInflater
    init {
        layoutInflater= LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return listaentregas!!.size
    }

    override fun getItem(p0: Int): Any {
        return listaentregas!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista = p1
        if (vista == null) {
            //relacionamos la vista con el layout
            vista = layoutInflater.inflate(R.layout.item_entregas, p2, false)
            val objentregas = getItem(p0) as Entregas
            val lblid= vista!!.findViewById<TextView>(R.id.tvide)
            val lblnomprode= vista!!.findViewById<TextView>(R.id.tvnomentrega)
            val lbldir = vista!!.findViewById<TextView>(R.id.tvdireccionentrega)
            val lblfecha = vista!!.findViewById<TextView>(R.id.tvFechaent)
            //agregamos valores
            lblid.text=""+objentregas.entregasId
            lblnomprode.text=""+objentregas.prodnEntrega
            lbldir.text=""+objentregas.dirEntrega
            lblfecha.text=""+objentregas.fEntrega

        }
        return vista!!
    }
}