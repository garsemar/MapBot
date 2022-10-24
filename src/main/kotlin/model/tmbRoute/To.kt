package cat.itb.martigarcia7e4.mapBot.model.tmbRoute

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class To(
    @SerializedName("name") var name: String? = null
)