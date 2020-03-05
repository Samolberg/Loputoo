package com.example.sights

import android.Manifest
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sights.api.endpoints.SightApi
import com.example.sights.api.entities.SightEntity
import com.example.sights.api.getRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.location.Location
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {


    var api = getRetrofit().create(SightApi::class.java)
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    var sightIdValue: Int = 0
    var sights: List<SightEntity> = emptyList()
    var sortedSightsList: List<SightEntity> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    1
                )

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.

                loadSights(location?.latitude, location?.longitude)
                Log.d("locationtest", location?.latitude.toString())
                Log.d("locationtest", location?.longitude.toString())

            }


        nextSight.setOnClickListener {
            sightIdValue++
            if (sightIdValue >= sortedSightsList.size) {
                sightIdValue = 0
            }
            loadSight(sightIdValue)

        }

        sightSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }


    }

    fun loadSights(latitude: Double?, longitude: Double?) {

        CoroutineScope(Dispatchers.IO).launch {


            sights = api.getSight(latitude, longitude).await()

            val pref = getSharedPreferences("sights.app.settings", Context.MODE_PRIVATE)
           val settings: List<String> = Gson().fromJson(pref.getString("settings", "[]"), Array<String>::class.java).toList()
            Log.d("arraytest", settings.toString())

          //  sortedSightsList = sights.filter { it.catList.contains(settings.toString()) }
            sortedSightsList = sights.filter { it.catList.contains("Modern") }

            Log.d("filter", Gson().toJson(sortedSightsList))



            Log.d("currentloc", Gson().toJson(sights))

            /*      try {
                      val sights = api.getSight().await()
                      Log.d("sights", sights.size.toString())
                      Log.d("sights", sights.get(0).url)
                  } catch (e: Exception) {
                      Log.e("testerror", "asdasd", e)

                  }
      */



            CoroutineScope(Dispatchers.Main).launch {

                loadSight(sightIdValue)
            }
        }
    }

    fun loadSight(index: Int) {

        val currentSight: SightEntity = sortedSightsList.get(index)

      //  val editor = pref.edit()

        //   pref.getBoolean("historicalSettings", false)
        //  pref.getBoolean("modernSettings", false)


        //Sight Picture
        Picasso.get().load(currentSight.url).into(landingImage)
        //Sight Description
        sightDescription.setText(currentSight.description)
        //Sight Distance
        sightDistance.setText(currentSight.distance.toString())


        //Google Maps Button
        sightMaps.setOnClickListener {

            startMaps(currentSight.latitude, currentSight.longitude)
        }


    }

    fun startMaps(latitude: Double?, longitude: Double?) {

        // val gmmIntentUri = Uri.parse("geo:37.7749,-122.4194")

        val gmmIntentUri =
            Uri.parse("google.navigation:q=" + latitude + "," + longitude + "&mode=w")
        Log.d("coordinatesTest", gmmIntentUri.toString())
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }
    }

}
