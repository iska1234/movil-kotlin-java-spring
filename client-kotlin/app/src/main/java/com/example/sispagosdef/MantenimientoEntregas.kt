package com.example.sispagosdef

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.LocationManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.location.*
import com.google.android.gms.maps.*


import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions


class MantenimientoEntregas : AppCompatActivity(), OnMapReadyCallback {

    val TAG ="MantenimientoEntregas"
    var googleMap:GoogleMap?=null
    val PERMISSON_ID=42
    var fusedLocationClient:FusedLocationProviderClient?=null
    var buttonAccept: Button?=null
    var city=""
    var country=""
    var address=""
    var addressLatLng: LatLng?=null

    var markerDelivery: Marker? =null
    var markerAddress: Marker? =null
    var myLocationLatLng: LatLng?=null

    private val locationCallback= object : LocationCallback()  {
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation=locationResult.lastLocation
            myLocationLatLng = LatLng(lastLocation?.latitude!!,lastLocation?.longitude!!)

//            googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(
//                CameraPosition.builder().target(
//                    LatLng(myLocationLatLng?.latitude!!,myLocationLatLng?.longitude!!)
//                ).zoom(15f).build()
//            ))
            removeDeliveryMarker()
            addDeliveryMarker()

            Log.d("LOCALIZACION","Callback: $lastLocation")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mantenimiento_entregas)
        val mapFragment=supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        val bundle=intent.extras
        val cod = bundle?.getLong("id")
        val nomb = bundle?.getString("nomb")
        val dir = bundle?.getString("dir")

        mapFragment?.getMapAsync(this)
        fusedLocationClient= LocationServices.getFusedLocationProviderClient(this)

        buttonAccept=findViewById(R.id.btnAceptardir1)
        getLastLocation()

        buttonAccept?.setOnClickListener {
            goToCreateAddress()
        }
    }


    private fun removeDeliveryMarker(){
        markerDelivery?.remove()
    }
    private fun addDeliveryMarker(){
        markerDelivery=googleMap?.addMarker(
            MarkerOptions()
                .position(myLocationLatLng)
                .title("Mi posicion")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.courierdev))
        )
    }
    private fun addAddressMarker(){
        val bundle=intent.extras
        val lat=bundle?.getDouble("lat")
        val lng=bundle?.getDouble("lng")
        val addressLocation = LatLng(lat!!,lng!!)
        markerAddress=googleMap?.addMarker(
            MarkerOptions()
                .position(addressLocation)
                .title("Entregar Aqui")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation))
        )
    }

    private fun goToCreateAddress(){
        val i = Intent()
        i.putExtra("city",city)
        i.putExtra("address",address)
        i.putExtra("country",country)
        i.putExtra("lat",addressLatLng?.latitude)
        i.putExtra("lng",addressLatLng?.longitude)
        setResult(RESULT_OK,i)
        finish()//volver hacia atras
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap=map
        googleMap?.uiSettings?.isZoomControlsEnabled=true

    }

    private fun getLastLocation(){
        if(checkPermissions()){
            if (isLocationEnabled()){
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                requestNewLocationData()//iniciamos la posicion en tiempo real
                fusedLocationClient?.lastLocation?.addOnCompleteListener {task->
                    var location=task.result
                    myLocationLatLng= LatLng(location.latitude,location.longitude)
                    removeDeliveryMarker()
                    addDeliveryMarker()
                    addAddressMarker()

                    googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(
                        CameraPosition.builder().target(
                            LatLng(location.latitude,location.longitude)
                        ).zoom(15f).build()
                    ))
                }
            }
            else{
                Toast.makeText(this,"Habilita la localizacion",Toast.LENGTH_LONG).show()
                val i = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(i)
            }
        }
        else{
            requestPermissions()
        }
    }
    private fun requestNewLocationData(){
        val locationRequest= LocationRequest.create().apply {
            interval=1000
            fastestInterval=1000
            priority=LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient?.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper())//Inicializa la posicion
    }

    private fun isLocationEnabled():Boolean{
        var locationManager: LocationManager =getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun checkPermissions():Boolean{
        if(ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ){
            return true
        }
        return false
    }
    private fun requestPermissions(){
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSON_ID)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==PERMISSON_ID){
            if (grantResults.isNotEmpty() && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                getLastLocation()
            }
        }
    }

}




