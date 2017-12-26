package de.magoeke.kotlin.connectfour.controllers.impl

import de.magoeke.kotlin.connectfour.controllers.IController
import de.magoeke.kotlin.connectfour.models.*
import de.magoeke.kotlin.connectfour.util.ArrayUtils
import de.magoeke.kotlin.connectfour.util.observer.Observable
import kotlinx.collections.immutable.toImmutableList

class MainController(val players: List<Player>, val boardSize: Int = 9) : Observable(), IController {

    private val connect = 4

    // initialize Board
    private var board = Board((1..boardSize).map { Column(boardSize) }.toImmutableList())

    override fun turn(column: Int): Boolean {
        val result = validateColumn(column) && updateBoard(column)
        this.notifyObservers()
        return result
    }

    private fun validateColumn(column: Int): Boolean = column in 0..(boardSize - 1)

    private fun updateBoard(column: Int): Boolean = board.columns[column].add(currentPlayer().symbol)

    private fun currentPlayer(): Player = getPlayer(calculateNumberOfTurns())
    private fun lastPlayer(): Player = getPlayer(calculateNumberOfTurns() + players.size - 1)
    private fun getPlayer(turnCount: Int) = players[turnCount.rem(players.size)]

    private fun calculateNumberOfTurns(): Int = board.columns.map { it.usedCells() }.sum()

    override fun getGameInformation(): Game =
            Game(board, isFinished(), currentPlayer(), if (isFinished() == GameState.WON) lastPlayer() else null)

    private fun isFinished(): GameState {
        if(isWon()) { return GameState.WON }
        if (board.columns.all { it.usedCells() == it.maxCells }) { return GameState.DRAW }
        return GameState.RUNNING
    }

    private fun isWon(): Boolean {
        val grid = board.to2DArray()

        return isNotDiagonalWin(grid) // horizontal
            || isNotDiagonalWin(ArrayUtils.transpose(grid)) // vertical
            || isDiagonalWin(grid) {_, i, j -> i-j } // diagonal "/"
            || isDiagonalWin(grid) {size, i, j -> (size -1) - (i-j) } // diagonal "\"
    }

    private fun isNotDiagonalWin(grid: Array<Array<String?>>): Boolean {
        val player = lastPlayer().symbol
        var connected = 0

        grid.forEach { level ->
            level.forEach { cell ->
                if (cell == player) connected += 1 else connected = 0
                if (connected == this.connect) return true
            }
            connected = 0
        }

        return false
    }

    private fun isDiagonalWin(grid: Array<Array<String?>>, lambda: (size: Int, i: Int, j: Int) -> Int): Boolean {
        val player = lastPlayer().symbol
        val size = grid.size
        var connected = 0

        for (i in 0..size * 2 - 1) {
            var start = if (i < size) 0 else i - size + 1
            for (j in start..i-start) {
                if (grid[j][lambda(size, i, j)] == player) connected += 1 else connected = 0
                if (connected == this.connect) return true
            }
            connected = 0
        }

        return false
    }

}