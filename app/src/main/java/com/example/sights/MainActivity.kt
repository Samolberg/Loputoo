package com.example.sights

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


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var api = getRetrofit().create(SightApi::class.java)

        var sightIdValue = 0
        var sightList = null
        var currentSight = null




        CoroutineScope(Dispatchers.IO).launch {
            val sights = api.getSight().await()
      /*      try {
                val sights = api.getSight().await()
                Log.d("sights", sights.size.toString())
                Log.d("sights", sights.get(0).url)
            } catch (e: Exception) {
                Log.e("testerror", "asdasd", e)

            }
*/
            var sightList = sights.size.toString()
            var currentSight : SightEntity = sights.get(sightIdValue)

            CoroutineScope(Dispatchers.Main).launch {



                Picasso.get().load(currentSight.url).into(landingImage)

            }

        }


    }
}
