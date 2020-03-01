package com.example.sights.api.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.Query


class SightEntity {

    @Expose
    @SerializedName("ID")
    var id: Int? = null

    @Expose
    @SerializedName("CategoryID")
    var catId: Int? = null

    @Expose
    @SerializedName("Name")
    var name: String? = null

    @Expose
    @SerializedName("Description")
    var description: String? = null

    @Expose
    @SerializedName("URL")
    var url: String? = null

    @Expose
    @SerializedName("Latitude")
    var latitude: Double? = null

    @Expose
    @SerializedName("Longitude")
    var longitude: Double? = null








}