package com.example.sispagosdef.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sispagosdef.Adaptadores.Clases.Ventas
import com.example.sispagosdef.R

class AdaptadorVentas  (context: Context?, private val listaventas: List<Ventas>?) : BaseAdapter()  {

    private val layoutInflater: LayoutInflater
    init {
        layoutInflater= LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return listaventas!!.size
    }

    override fun getItem(p0: Int): Any {
        return listaventas!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista = p1
        if (vista == null) {
            //relacionamos la vista con el layout
            vista = layoutInflater.inflate(R.layout.item_ventas, p2, false)
            val objventas = getItem(p0) as Ventas
            val lblid= vista!!.findViewById<TextView>(R.id.tvidv)
            val lblnomprov= vista!!.findViewById<TextView>(R.id.tvNombreprodv)
            val lbltipoc = vista!!.findViewById<TextView>(R.id.tvTComprobantev)
            val lbltipoe = vista!!.findViewById<TextView>(R.id.tvTEntregav)
            val lbltipop = vista!!.findViewById<TextView>(R.id.tvTPagov)
            val lblprec = vista!!.findViewById<TextView>(R.id.tvPrecioprov)
            val lblcant = vista!!.findViewById<TextView>(R.id.tvCantidadv)
            val lbltotal = vista!!.findViewById<TextView>(R.id.tvTotalv)

            //agregamos valores
            lblid.text=""+objventas.ventasId
            lblnomprov.text=""+objventas.prodnVenta
            lbltipoc.text=""+objventas.tipocomprobante
            lbltipoe.text=""+objventas.tipoentrega
            lbltipop.text=""+objventas.tipopago
            lblprec.text=""+objventas.precioVenta
            lblcant.text=""+objventas.cantVenta
            lbltotal.text=""+objventas.totalVenta
        }
        return vista!!
    }
}