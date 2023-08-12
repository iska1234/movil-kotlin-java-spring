package com.example.sispagos1.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sispagos1.Clases.Distrito
import com.example.sispagosdef.R

class AdaptadorComboDistrito(context: Context?, private val listadistrito:List<Distrito>?): BaseAdapter() {

    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return listadistrito!!.size
    }

    override fun getItem(p0: Int): Any {
        return listadistrito!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista=p1
        if(vista==null){
            //relacionamos la vista con el layout correspondiente
            //en este caso elemento_lista_categoria
            vista=layoutInflater.inflate(R.layout.item_combo_distrito,p2,false)
            val objdistrito=getItem(p0) as Distrito
            //creamos los controles
            val lblnomdistrito= vista!!.findViewById<TextView>(R.id.lblnomdistrito)

            //agregamos los valores a la lista
            lblnomdistrito.text=""+objdistrito.nomDistrito
        }
        return vista!!
    }
}