package cat.itb.martigarcia7e4.mapBot.ui

import cat.itb.martigarcia7e4.mapBot.logic.Logic
import cat.itb.martigarcia7e4.mapBot.model.Place.Companion.places
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.entities.*

class UI {
    private val logic = Logic()
    fun runBot() {
        val validCommands = listOf("/start", "/help", "/set", "/list", "/delete", "/route")
        val bot = bot {
            token = "5665193864:AAFref9rhKJpibgr3azJbgiWoaueXI8gqio"
            dispatch {
                command("start") {
                    bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Try /help")
                }
                command("help") {
                    bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id), text = """
                        /start - Start the bot
                        /set Place name Address without spaces(c/street,number,city) - Set a place
                        /list - List all places
                        /delete place name - Delete a place
                        /route [origin place name or address], [destination place name or address] - Get the route between two places
                        /help - Show this message
                    """.trimIndent()
                    )
                }
                command("set") {
                    val input = message.text?.split(" ")!!
                    if (logic.addPlace(message.chat.id, input) != null) {
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Place saved correctly")
                    } else {
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Invalid command")
                    }
                }
                command("list") {
                    if (places[message.chat.id]?.isNotEmpty() == true) {
                        places[message.chat.id]?.forEach {
                            bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "${it.name}: ${it.cords}")
                        }
                    } else {
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "No places saved")
                    }
                }
                command("delete") {
                    val input = message.text?.split(" ")!!.toMutableList()
                    if (logic.deletePlace(input, message.chat.id)) {
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Place deleted correctly")
                    } else {
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Invalid command")
                    }
                }
                command("route") {
                    val route = logic.route(message.chat.id, message.text!!.split(" ").toMutableList())
                    if (route.isNotEmpty()) {
                        route.first().legs.forEach {
                            if (it.mode == "WALK") {
                                it.steps.forEach { steps ->
                                    bot.sendMessage(
                                        chatId = ChatId.fromId(message.chat.id),
                                        text = "Go ${steps.streetName} ${steps.relativeDirection} ${steps.absoluteDirection}"
                                    )
                                }
                            } else {
                                bot.sendMessage(
                                    chatId = ChatId.fromId(message.chat.id),
                                    text = "Go ${it.from.name} ${it.route} to ${it.to.name}"
                                )
                            }
                        }
                    }
                    else {
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Invalid command")
                    }
                }
                text {
                    if (text.split(" ").first() !in validCommands) {
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Error")
                    }
                }
            }
        }
        logic.loadData()
        bot.startPolling()
        println("Running!")
    }
}