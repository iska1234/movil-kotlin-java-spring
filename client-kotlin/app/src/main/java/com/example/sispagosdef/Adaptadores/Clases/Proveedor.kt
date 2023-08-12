package com.example.sispagos1.Clases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Proveedor {

    @SerializedName("provId")
    @Expose
    var provId:Long=0;
    @SerializedName("nomProv")
    @Expose
    var nomProv:String?=null
    @SerializedName("dirProv")
    @Expose
    var dirProv:String?=null
    @SerializedName("telfProv")
    @Expose
    var telfProv:String?=null
    @SerializedName("emaProv")
    @Expose
    var emaProv:String?=null


    constructor(){}
    constructor(
        provId: Long,
        nomProv: String?,
        dirProv: String?,
        telfProv: String?,
        emaProv: String?
    ) {
        this.provId = provId
        this.nomProv = nomProv
        this.dirProv = dirProv
        this.telfProv = telfProv
        this.emaProv = emaProv
    }


}