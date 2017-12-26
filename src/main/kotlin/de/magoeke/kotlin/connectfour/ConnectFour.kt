package de.magoeke.kotlin.connectfour

import de.magoeke.kotlin.connectfour.controllers.IController
import de.magoeke.kotlin.connectfour.controllers.impl.MainController
import de.magoeke.kotlin.connectfour.models.Player
import de.magoeke.kotlin.connectfour.views.tui.TUI

fun main(args: Array<String>) {

    // initialize variables
    val player1 = Player("One", "X")
    val player2 = Player("Two", "O")

    val controller: IController = MainController(listOf(player1, player2))

    val tui = TUI(controller)

    var finished = false

    while(!finished) { finished = tui.iterate() }

}
