package com.example.sispagos1.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sispagos1.Clases.Producto
import com.example.sispagosdef.R


class AdaptadorProductoAlmacen (context: Context?, private val listaproducto: List<Producto>?) : BaseAdapter()  {

    private val layoutInflater: LayoutInflater
    init {
        layoutInflater= LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return listaproducto!!.size
    }

    override fun getItem(p0: Int): Any {
        return listaproducto!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista = p1
        if (vista == null) {
            //relacionamos la vista con el layout
            vista = layoutInflater.inflate(R.layout.item_producto_almacen, p2, false)
            val objproducto = getItem(p0) as Producto
            val lblnomprov= vista!!.findViewById<TextView>(R.id.tvnomprodalmacen)
            val lblprecprod = vista!!.findViewById<TextView>(R.id.tvprecprodalmacen)
            val lblcatprod = vista!!.findViewById<TextView>(R.id.tvcatprodalmacen)
            //agregamos valores
            lblnomprov.text=""+objproducto.nomProd
            lblprecprod.text=""+objproducto.precProd
            lblcatprod.text=""+objproducto.categoria!!.descCat
        }
        return vista!!
    }
}