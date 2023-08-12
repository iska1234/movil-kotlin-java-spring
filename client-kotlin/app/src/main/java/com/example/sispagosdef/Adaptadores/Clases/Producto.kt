package com.example.sispagos1.Clases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Producto {

    @SerializedName("prodId")
    @Expose
    var prodId:Long=0

    @SerializedName("nomProd")
    @Expose
    var nomProd:String?=null

    @SerializedName("precProd")
    @Expose
    var precProd:Double=0.0

    @SerializedName("categoria")
    @Expose
    var categoria:Categoria?=null

    constructor(){}

    constructor(prodId: Long, nomProd: String?, precProd: Double, categoria: Categoria?) {
        this.prodId = prodId
        this.nomProd = nomProd
        this.precProd = precProd
        this.categoria = categoria
    }


}