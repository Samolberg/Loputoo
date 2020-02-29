package com.example.sights.api.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SightEntity {

    @Expose
    @SerializedName("id")
    var id: Int? = null

    @Expose
    @SerializedName("catId")
    var catId: Int? = null

    @Expose
    @SerializedName("name")
    var name: String? = null

    @Expose
    @SerializedName("Description")
    var description: String? = null

    @Expose
    @SerializedName("URL")
    var url: String? = null

    @Expose
    @SerializedName("latitude")
    var latitude: Double? = null

    @Expose
    @SerializedName("longitude")
    var longitude: Double? = null





}