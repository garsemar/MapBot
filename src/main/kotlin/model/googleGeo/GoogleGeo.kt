package cat.itb.martigarcia7e4.mapBot.model.googleGeo

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GoogleGeo(
    @SerializedName("results") var results: List<Result>,
    @SerializedName("status") var status: String
)