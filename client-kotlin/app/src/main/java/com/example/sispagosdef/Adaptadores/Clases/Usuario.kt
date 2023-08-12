package com.example.sispagosdef.Adaptadores.Clases

import com.example.sispagos1.Clases.Distrito
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Usuario {

    @SerializedName("usuarioId")
    @Expose
    var usuarioId:Long=0;
    @SerializedName("nomUsuario")
    @Expose
    var nomUsuario:String?=null
    @SerializedName("apeUsuario")
    @Expose
    var apeUsuario:String?=null
    @SerializedName("docUsuario")
    @Expose
    var docUsuario:String?=null
    @SerializedName("tlfUsuario")
    @Expose
    var tlfUsuario:String?=null
    @SerializedName("emaUsuario")
    @Expose
    var emaUsuario:String?=null
    @SerializedName("passwUsuario")
    @Expose
    var passwUsuario:String?=null
    @SerializedName("distritouser")
    @Expose
    var distritouser:Distrito?=null

    constructor(){}
    constructor(
        usuarioId: Long,
        nomUsuario: String?,
        apeUsuario: String?,
        docUsuario: String?,
        tlfUsuario: String?,
        emaUsuario: String?,
        passwUsuario: String?,
        distritouser: Distrito?
    ) {
        this.usuarioId = usuarioId
        this.nomUsuario = nomUsuario
        this.apeUsuario = apeUsuario
        this.docUsuario = docUsuario
        this.tlfUsuario = tlfUsuario
        this.emaUsuario = emaUsuario
        this.passwUsuario = passwUsuario
        this.distritouser = distritouser
    }

}