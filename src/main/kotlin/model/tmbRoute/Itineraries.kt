package cat.itb.martigarcia7e4.mapBot.model.tmbRoute

import cat.itb.martigarcia7e4.mapBot.model.tmbRoute.Legs
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Itineraries(
    @SerializedName("duration") var duration: Int,
    @SerializedName("walkTime") var walkTime: Int,
    @SerializedName("transitTime") var transitTime: Int,
    @SerializedName("legs") var legs : List<Legs> = listOf()
)