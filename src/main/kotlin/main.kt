package cat.itb.martigarcia7e4.mapBot

import cat.itb.martigarcia7e4.mapBot.logic.Logic
import cat.itb.martigarcia7e4.mapBot.ui.UI

fun main() {
    val ui = UI()
    val logic = Logic()

    logic.loadData()
    ui.runBot()
    println("Running!")
}