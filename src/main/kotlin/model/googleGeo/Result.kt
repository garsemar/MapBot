package cat.itb.martigarcia7e4.dam2.m06.uf1.mapBot.model.googleGeo

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerializedName("geometry") var geometry: Geometry
)