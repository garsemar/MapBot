package cat.itb.martigarcia7e4.mapBot.model

import kotlinx.serialization.Serializable

@Serializable
data class Place(
    val name: String,
    val cords: String
){
    companion object{
        var places = mutableMapOf<Long, MutableList<Place>>()
    }
}