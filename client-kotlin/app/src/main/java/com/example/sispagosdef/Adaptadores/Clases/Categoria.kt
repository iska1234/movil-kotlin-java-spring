package com.example.sispagos1.Clases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Categoria {
    @SerializedName("catId")
    @Expose
    var catId:Long=0

    @SerializedName("descCat")
    @Expose
    var descCat:String?=null

    @SerializedName("estCat")
    @Expose
    var estCat:Boolean=false

    constructor(){}

    constructor(catId: Long, descCat: String?, estCat: Boolean) {
        this.catId = catId
        this.descCat = descCat
        this.estCat = estCat
    }


}