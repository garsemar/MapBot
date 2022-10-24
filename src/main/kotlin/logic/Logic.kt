package cat.itb.martigarcia7e4.mapBot.logic

import cat.itb.martigarcia7e4.mapBot.model.Place.Companion.places
import cat.itb.martigarcia7e4.mapBot.model.tmbRoute.Itineraries
import cat.itb.martigarcia7e4.mapBot.repositories.Api
import cat.itb.martigarcia7e4.mapBot.model.googleGeo.Location
import cat.itb.martigarcia7e4.mapBot.model.Place
import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlin.io.path.*

class Logic {
    private val api = Api()
    private val path = Path("src/main/kotlin/resources/places.json")

    @OptIn(DelicateCoroutinesApi::class)
    fun addPlace(id: Long, input: List<String>): Location? {
        var cords: Location? = null
        if (input.size == 3 && places[id]?.find { it.name == input[1] } == null) {
            val job = GlobalScope.launch {
                val geo = api.googleGeo(input[2])
                println(geo)
                if(geo.status == "ZERO_RESULTS"){
                    return@launch
                }
                else{
                    cords = geo.results.first().geometry.location
                }
                if (places[id] == null) {
                    places.put(id, mutableListOf(Place(input[1], "${cords?.lat},${cords?.lng}")))
                } else {
                    places[id]?.add(Place(input[1], "${cords?.lat},${cords?.lng}"))
                }
            }
            runBlocking {
                job.join()
            }
            saveData()
            return cords
        }
        return cords
    }
    fun deletePlace(input: List<String>, id: Long): Boolean {
        if(input.size == 2 && places[id]?.find { it.name == input[1] } != null){
            places[id]?.removeIf { it.name == input[1] }
            saveData()
            return true
        }
        return false
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun route(id: Long, input: MutableList<String>): List<Itineraries> {
        var route = listOf<Itineraries>()
        if(input.size != 3){
            return route
        }
        input.removeFirst()
        val words = mutableListOf<List<Place>?>()
        input.forEach { name ->
            words.add(places[id]?.filter { it.name == name })
        }
        val routeList = mutableListOf<String>()
        var cords: Location
        val job = GlobalScope.launch {
            for (i in input.indices) {
                if (words[i].isNullOrEmpty()) {
                    val geo = api.googleGeo(input[i])
                    if(geo.status == "ZERO_RESULTS"){
                        return@launch
                    }
                    else{
                        cords = geo.results.first().geometry.location
                    }
                    routeList.add("${cords.lat},${cords.lng}")
                } else {
                    routeList.add(words[i]!![0].cords)
                }
            }
            route = api.tmbRoute(routeList[0], routeList[1]).plan.itineraries
        }
        runBlocking {
            job.join()
        }
        return route
    }
    fun saveData() {
        val json = Json.encodeToString(places)
        path.writeText(json)
    }
    fun loadData() {
        if(path.notExists()){
            path.writeText("")
        }
        places = Json.decodeFromString(path.readText())
    }
}