package com.example.sights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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

    var api = getRetrofit().create(SightApi::class.java)

    var sightIdValue: Int = 0
    var sights: List<SightEntity> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        final nextSightButton button = findViewById(R.id.nextSight);
        button.setOnClickListener(new View.OnClickListenrer(){
            public void onClick(View v){
                sightIdValue++
            }
        });

        */

       nextSight.setOnClickListener {
           sightIdValue++
           if(sightIdValue >= sights.size){
               sightIdValue = 0
           }
           loadSight(sightIdValue)

       }






        CoroutineScope(Dispatchers.IO).launch {
            sights = api.getSight().await()
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

    fun loadSight(index: Int){

        val currentSight: SightEntity = sights.get(index)

        Picasso.get().load(currentSight.url).into(landingImage)

    }
}
