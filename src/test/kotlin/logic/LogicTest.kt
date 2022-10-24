package logic

import cat.itb.martigarcia7e4.mapBot.logic.Logic
import cat.itb.martigarcia7e4.mapBot.model.googleGeo.Location
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class LogicTest {
    private val logic = Logic()
    @Test
    fun addPlace() {
        val response1 = logic.addPlace(1631082699, listOf("/set", "name", "barcelona"))
        val response2 = logic.addPlace(1631082699, listOf("/set", "name", "barcelona"))
        assertEquals(Location(41.3873974, 2.168568), response1)
        assertEquals(null, response2)
    }

    @Test
    fun deletePlace() {
        logic.addPlace(1631082699, listOf("/set", "name", "barcelona"))
        val response1 = logic.deletePlace(listOf("/delete", "name"), 1631082699)
        val response2 = logic.deletePlace(listOf("/delete", "abcd1"), 1631082699)
        assertEquals(true, response1)
        assertEquals(false, response2)
    }

    @Test
    fun route() {
    }

    @Test
    fun loadData() {
    }

    @Test
    fun saveData() {
    }
}