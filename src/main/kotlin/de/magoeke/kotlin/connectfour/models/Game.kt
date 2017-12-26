package de.magoeke.kotlin.connectfour.models

/**
 * Created by max on 26.12.17.
 */
data class Game(val board: Board, val gameState: GameState, val currentPlayer: Player, val winner: Player?)