package cat.itb.martigarcia7e4.mapBot.repositories

import cat.itb.martigarcia7e4.mapBot.model.googleGeo.GoogleGeo
import cat.itb.martigarcia7e4.mapBot.model.tmbRoute.TMBRoute
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class Api {
    suspend fun tmbRoute(fromCords: String, toCords: String): TMBRoute {
        val id = "8ce581bc"
        val key = "c970564ad34e733114ddceebb8fe38bf"
        val client = HttpClient(CIO) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                })
            }
        }
        return client.get("https://api.tmb.cat/v1/planner/plan?app_id=$id&app_key=$key&fromPlace=$fromCords&toPlace=$toCords")
    }
    suspend fun googleGeo(address: String): GoogleGeo {
        val key = "AIzaSyAmMqlyYvs8BPSV75PhrBuAUwH_7purVqs"
        var formatAddress = address.replace("/", "%20")
        formatAddress = formatAddress.replace(",", "%20")
        val client = HttpClient(CIO) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                })
            }
        }
        return client.get("https://maps.googleapis.com/maps/api/geocode/json?address=$formatAddress&key=$key")
    }
}