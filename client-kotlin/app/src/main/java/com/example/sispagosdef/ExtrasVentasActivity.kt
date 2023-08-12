package com.example.sispagosdef

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView.FindListener
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import com.example.sispagos1.Clases.Categoria
import com.example.sispagos1.Clases.Producto
import com.example.sispagos1.Clases.Proveedor
import com.example.sispagos1.Remoto.ApiUtil
import com.example.sispagos1.Servicio.CategoriaService
import com.example.sispagos1.Servicio.ProductoService
import com.example.sispagosdef.Adaptadores.Clases.Ventas
import com.example.sispagosdef.Adaptadores.Servicio.VentasService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExtrasVentasActivity : AppCompatActivity() {
    private lateinit var btnPagoEfectivo: Button
    private lateinit var btnPagoTarjeta: Button
    private lateinit var btnCalcularTotal: Button
    private lateinit var edtCant: EditText
    private lateinit var tvNombre: TextView
    private lateinit var tvPrecio: TextView
    private lateinit var tvTipoC: TextView
    private lateinit var tvTotal: TextView
    private lateinit var rbdDelivery: RadioButton
    private lateinit var rbdRecojo: RadioButton
    private var dialogo: AlertDialog.Builder?=null
    var cant=0
    var total=0.0
    var tipoe=""
    var tipop=""
    var cad = ""
    val objventas= Ventas()

    private var ventasService: VentasService?=null

    private var registroventas:List<Ventas>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extras_ventas)
        Enlazar()
        registroventas=ArrayList()
        ventasService= ApiUtil.ventasservice
        val bundle=intent.extras
        val nom = bundle?.getString("nom")
        val prec=bundle?.getDouble("prec")
        val tipoc = bundle?.getString("tipoc")
        //
        tvNombre.setText(nom)
        tvPrecio.setText("S/."+prec)
        tvTipoC.setText(tipoc)

        btnCalcularTotal.setOnClickListener {
            cant=Integer.parseInt(edtCant.getText().toString())
            total=cant*prec!!
            //
            if (rbdRecojo.isChecked()==true){
                total+=0;
                tipoe="Recojo en tienda";
            }
            // si el checkbox de movilidad està marcado
            if (rbdDelivery.isChecked()==true){
                total+=20;
                tipoe="Delivery";
            }
            tvTotal.setText("S/."+total)
        }

        btnPagoEfectivo.setOnClickListener {
            tipop= "Pago en efectivo";
            cad= "Producto: "+nom+"\n"+
                    "Tipo Comprobante: " + tipoc + "\n" +
                    "Tipo de Entrega: " + tipoe + "\n" +
                    "Tipo de pago: "+tipop+"\n"+
                    "Precio: S/." + prec + "\n" +
                    "Cantidad: " + cant +"\n" +
                    "Total a Pagar: S/." + total;

            objventas.prodnVenta=nom
            objventas.tipocomprobante=tipoc
            objventas.precioVenta= prec!!
            objventas.tipoentrega=tipoe
            objventas.cantVenta=cant
            objventas.tipopago=tipop
            objventas.totalVenta=total

            RegistrarVentas(objventas)
            DialogoCRUD(this,"Registro de Ventas","Se registro la venta correctamente")
            val i = Intent(this,ComprobanteActivity::class.java)
            i.putExtra("cad",cad)
            startActivity(i)
        }

        btnPagoTarjeta.setOnClickListener {
            tipop= "Pago con tarjeta";
            cad= "Producto: "+nom+"\n"+
                    "Tipo Comprobante: " + tipoc + "\n" +
                    "Tipo de Entrega: " + tipoe + "\n" +
                    "Tipo de pago: "+tipop+"\n"+
                    "Precio: S/." + prec + "\n" +
                    "Cantidad: " + cant +"\n" +
                    "Total a Pagar: S/." + total;

            objventas.prodnVenta=nom
            objventas.tipocomprobante=tipoc
            objventas.precioVenta= prec!!
            objventas.tipoentrega=tipoe
            objventas.cantVenta=cant
            objventas.tipopago=tipop
            objventas.totalVenta=total

            RegistrarVentas(objventas)

            val i = Intent(this,PagoTarjetaActivity::class.java)
            i.putExtra("cad",cad)
            startActivity(i)
        }


    }

    private fun Enlazar(){
        tvNombre=findViewById(R.id.tvNombreProductoex)
        tvPrecio=findViewById(R.id.tvPrecioProductoex)
        tvTipoC=findViewById(R.id.tvTipoComprobanteex)
        tvTotal=findViewById(R.id.tvTotalProductoex)
        edtCant=findViewById(R.id.edtCantidadex)
        //
        btnCalcularTotal=findViewById(R.id.btncalculartotalex)
        btnPagoEfectivo=findViewById(R.id.btnpagoefectivo)
        btnPagoTarjeta=findViewById(R.id.btnpagotarjeta)
        //
        rbdRecojo=findViewById(R.id.rbRecojotiendaex)
        rbdDelivery=findViewById(R.id.rbDeliveryex)
    }

    fun RegistrarVentas(v: Ventas?){
        val call= ventasService!!.RegistrarVentas(v)
        call!!.enqueue(object : Callback<Ventas?> {
            override fun onResponse(call: Call<Ventas?>, response: Response<Ventas?>) {
                if(response.isSuccessful){
                    Log.e("mensaje","Se registro")
                }
            }
            override fun onFailure(call: Call<Ventas?>, t: Throwable) {
                Log.e("Error: ", t.message!!)
            }

        })
    }

    private fun DialogoCRUD(context: Context, titulo:String, mensaje:String){
        dialogo= android.app.AlertDialog.Builder(context)
        dialogo!!.setTitle(titulo)
        dialogo!!.setMessage(mensaje)
        dialogo!!.setCancelable(false)
        dialogo!!.setPositiveButton("Ok"){
                dialog,which->

        }
        dialogo!!.show()
    }

}