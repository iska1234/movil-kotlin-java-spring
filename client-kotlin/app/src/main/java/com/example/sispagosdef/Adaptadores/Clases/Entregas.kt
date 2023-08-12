package com.example.sispagosdef.Adaptadores.Clases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.util.Date

class Entregas {
    @SerializedName("entregasId")
    @Expose
    var entregasId:Long=0
    @SerializedName("prodnEntrega")
    @Expose
    var prodnEntrega:String?=null
    @SerializedName("dirEntrega")
    @Expose
    var dirEntrega:String?=null
    @SerializedName("fEntrega")
    @Expose
    var fEntrega:String?=null
    @SerializedName("latEnt")
    @Expose
    var latEnt:Double=0.0
    @SerializedName("lngEnt")
    @Expose
    var lngEnt:Double=0.0

    constructor(){}
    constructor(
        entregasId: Long,
        prodnEntrega: String?,
        dirEntrega: String?,
        fEntrega: String?,
        latEnt: Double,
        lngEnt: Double
    ) {
        this.entregasId = entregasId
        this.prodnEntrega = prodnEntrega
        this.dirEntrega = dirEntrega
        this.fEntrega = fEntrega
        this.latEnt = latEnt
        this.lngEnt = lngEnt
    }


}