package com.example.sispagosdef.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sispagosdef.Adaptadores.Clases.Usuario
import com.example.sispagosdef.R

class AdaptadorUsuarioCombo (context: Context?, private val listausuario:List<Usuario>?): BaseAdapter(){
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return listausuario!!.size
    }

    override fun getItem(p0: Int): Any {
        return listausuario!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista=p1
        if(vista==null){
            //relacionamos la vista con el layout correspondiente
            //en este caso elemento_lista_categoria
            vista=layoutInflater.inflate(R.layout.item_combo_usuario,p2,false)
            val objcategoria=getItem(p0) as Usuario
            //creamos los controles
            val lblnomcat= vista!!.findViewById<TextView>(R.id.lbluserema)

            //agregamos los valores a la lista
            lblnomcat.text=""+objcategoria.emaUsuario
        }
        return vista!!
    }
}