package com.example.sispagosdef.Adaptadores.Clases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Ventas {

    @SerializedName("ventasId")
    @Expose
    var ventasId:Long=0

    @SerializedName("prodnVenta")
    @Expose
    var prodnVenta:String?=null;

    @SerializedName("precioVenta")
    @Expose
    var precioVenta:Double=0.0

    @SerializedName("cantVenta")
    @Expose
    var cantVenta:Int=0

    @SerializedName("totalVenta")
    @Expose
    var totalVenta:Double=0.0

    @SerializedName("tipocomprobante")
    @Expose
    var tipocomprobante:String?=null;

    @SerializedName("tipopago")
    @Expose
    var tipopago:String?=null;

    @SerializedName("tipoentrega")
    @Expose
    var tipoentrega:String?=null
    constructor(){}

    constructor(
        ventasId: Long,
        prodnVenta: String?,
        precioVenta: Double,
        cantVenta: Int,
        totalVenta: Double,
        tipocomprobante: String?,
        tipopago: String?,
        tipoentrega: String?
    ) {
        this.ventasId = ventasId
        this.prodnVenta = prodnVenta
        this.precioVenta = precioVenta
        this.cantVenta = cantVenta
        this.totalVenta = totalVenta
        this.tipocomprobante = tipocomprobante
        this.tipopago = tipopago
        this.tipoentrega=tipoentrega
    }


}