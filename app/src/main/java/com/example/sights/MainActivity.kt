package com.example.sights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sights.api.endpoints.SightApi
import com.example.sights.api.getRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = getRetrofit().create(SightApi::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val Sights = api.getSight().await()
                Log.d("sights", Sights.size.toString())
                Log.d("sights", Sights.get(0).description)
            } catch (e: Exception) {
                Log.e("testerror", "asdasd", e)
            }

        }


    }
}
