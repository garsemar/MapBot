package logic

import cat.itb.martigarcia7e4.mapBot.logic.Logic
import cat.itb.martigarcia7e4.mapBot.model.googleGeo.Location
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class LogicTest {
    private val logic = Logic()
    init {
        logic.loadData()
    }
    @Test
    fun addPlace() {
        val response1 = logic.addPlace(1, listOf("/set", "name", "barcelona"))
        val response2 = logic.addPlace(1, listOf("/set", "name", "barcelona"))
        assertEquals(Location(41.3873974, 2.168568), response1)
        assertEquals(null, response2)
    }

    @Test
    fun deletePlace() {
        logic.addPlace(1, listOf("/set", "name", "barcelona"))
        val response1 = logic.deletePlace(listOf("/delete", "name"), 1)
        val response2 = logic.deletePlace(listOf("/delete", "abcd1"), 1)
        assertEquals(true, response1)
        assertEquals(false, response2)
    }
}