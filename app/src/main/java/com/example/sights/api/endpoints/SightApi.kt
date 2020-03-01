package com.example.sights.api.endpoints


import com.example.sights.api.entities.SightEntity
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import retrofit2.http.*

interface SightApi {


    @GET("#")
    fun getSight(@Query("latitude") latitude: Double?,
                 @Query("longitude") longitude: Double?):Deferred<List<SightEntity>>





}