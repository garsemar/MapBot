package cat.itb.martigarcia7e4.mapBot.model.googleGeo

import cat.itb.martigarcia7e4.mapBot.model.googleGeo.Location
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Geometry(
    @SerializedName("location") var location: Location
)