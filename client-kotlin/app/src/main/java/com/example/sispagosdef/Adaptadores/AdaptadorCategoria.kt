package com.example.sispagos1.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sispagos1.Clases.Categoria
import com.example.sispagosdef.R

class AdaptadorCategoria (context:Context?, private val listacategoria: List<Categoria>?) :BaseAdapter(){

    private val layoutInflater: LayoutInflater
    init {
        layoutInflater= LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return listacategoria!!.size
    }

    override fun getItem(p0: Int): Any {
        return listacategoria!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista = p1
        if (vista == null) {
            //relacionamos la vista con el layout
            vista = layoutInflater.inflate(R.layout.item_categoria, p2, false)
            val objcategoria = getItem(p0) as Categoria
            val lbldesccat = vista!!.findViewById<TextView>(R.id.tvNombrecat)
            val lblestcat = vista!!.findViewById<TextView>(R.id.tvEstadocat)

            //agregamos valores
            lbldesccat.text = "" + objcategoria.descCat
            if(objcategoria.estCat==true){
                lblestcat.text="Habilitado"
            }else{
                lblestcat.text="Deshabilitado"
            }

        }
        return vista!!
    }

}