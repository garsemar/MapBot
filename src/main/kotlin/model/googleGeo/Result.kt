package cat.itb.martigarcia7e4.mapBot.model.googleGeo

import cat.itb.martigarcia7e4.mapBot.model.googleGeo.Geometry
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerializedName("geometry") var geometry: Geometry
)