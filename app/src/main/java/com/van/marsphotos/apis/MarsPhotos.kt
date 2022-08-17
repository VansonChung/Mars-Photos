package com.van.marsphotos.apis
import com.google.gson.annotations.SerializedName

data class MarsPhotosItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("img_src")
    val imgSrc: String
)