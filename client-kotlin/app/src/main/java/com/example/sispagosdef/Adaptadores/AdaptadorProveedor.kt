package com.example.sispagos1.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sispagos1.Clases.Proveedor
import com.example.sispagosdef.R


class AdaptadorProveedor (context: Context?, private val listaproveedor: List<Proveedor>?) :
    BaseAdapter() {

    private val layoutInflater: LayoutInflater
    init {
        layoutInflater= LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return listaproveedor!!.size
    }

    override fun getItem(p0: Int): Any {
        return listaproveedor!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista = p1
        if (vista == null) {
            //relacionamos la vista con el layout
            vista = layoutInflater.inflate(R.layout.item_proveedor, p2, false)
            val objproveedor = getItem(p0) as Proveedor
            val lblnomprov= vista!!.findViewById<TextView>(R.id.tvNombreprov)
            val lblemaprov = vista!!.findViewById<TextView>(R.id.tvEmailProv)
            val lbltelfprov = vista!!.findViewById<TextView>(R.id.tvTelefonoProv)
            //agregamos valores
            lblnomprov.text=""+objproveedor.nomProv
            lblemaprov.text=""+objproveedor.emaProv
            lbltelfprov.text=""+objproveedor.telfProv

        }
        return vista!!
    }
}