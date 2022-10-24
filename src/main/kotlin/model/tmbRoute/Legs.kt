package cat.itb.martigarcia7e4.mapBot.model.tmbRoute

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Legs(
    @SerializedName("mode") var mode: String,
    @SerializedName("route") var route: String,
    @SerializedName("from") var from: From = From(),
    @SerializedName("to") var to: To = To(),
    @SerializedName("steps") var steps: List<Steps> = listOf()
)