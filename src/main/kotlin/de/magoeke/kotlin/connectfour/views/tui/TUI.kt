package de.magoeke.kotlin.connectfour.views.tui

import de.magoeke.kotlin.connectfour.controllers.IController
import de.magoeke.kotlin.connectfour.models.Board
import de.magoeke.kotlin.connectfour.models.GameState
import de.magoeke.kotlin.connectfour.models.Player
import de.magoeke.kotlin.connectfour.util.observer.IObserver

/**
 * Created by max on 25.12.17.
 */
class TUI(val controller: IController) : IObserver{

    private var finished = false

    init {
        controller.add(this)
        update()
    }

    fun iterate() : Boolean {
        controller.turn(readLine()!!.toInt())
        return finished
    }


    override fun update() {
        val gameInfo = controller.getGameInformation()

        printBoard(gameInfo.board)

        when (gameInfo.gameState) {
            GameState.WON, GameState.DRAW -> finished = true
            else -> Unit
        }

        when (gameInfo.gameState) {
            GameState.WON -> won(gameInfo.winner)
            GameState.DRAW -> draw()
            GameState.RUNNING -> running(gameInfo.currentPlayer, gameInfo.board.columns.size)
            else -> println("Unexpected GameState.")
        }
    }

    private fun won(winner: Player?) =
            println("Game is over. Player \"${winner?.name}\" with symbol \"${winner?.symbol}\" has won.")

    private fun draw() = println("Game is over. Nobody wins. It's a draw.")

    private fun running(currentPlayer: Player, boardSize: Int) {
        println("It's Player \"${currentPlayer.name}\" with symbol \"${currentPlayer.symbol}\" turn.")
        println("Choose a column(0-$boardSize): ")

    }

    private fun printBoard(board: Board) =
            board.to2DArray().forEach { row ->
                row.forEach { cell -> print(if(cell == null) "  " else cell + " ") }
                println()
            }

}