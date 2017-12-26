package de.magoeke.kotlin.connectfour.controllers.impl

import de.magoeke.kotlin.connectfour.controllers.IController
import de.magoeke.kotlin.connectfour.models.GameState
import de.magoeke.kotlin.connectfour.models.Player
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Created by max on 25.12.17.
 */
class MainControllerTest {

    var boardSize = 4
    lateinit var controller: IController

    @Before
    fun setUp() {
        val player1 = Player("Player 1", "X")
        val player2 = Player("Player 2", "0")
        val players = arrayListOf(player1, player2)
        controller = MainController(players, boardSize)
    }

    @Test
    fun fillOneColumn() {
        assertTrue { controller.turn(0) }
        assertTrue { controller.turn(0) }
        assertTrue { controller.turn(0) }
        assertTrue { controller.turn(0) }
        assertFalse { controller.turn(0) }
    }

    @Test
    fun turnWithColumnThatDoesNotExist() {
        assertFalse { controller.turn(boardSize + 1) }
    }

    @Test
    fun draw() {
        assertEquals(GameState.RUNNING, controller.getGameInformation().gameState)

        for(i in 0..3 step 2) {
            for (j in 1..2) {
                assertTrue { controller.turn(i) }
                assertTrue { controller.turn(i+1) }
            }

            for(j in 1..2) {
                assertTrue { controller.turn(i+1) }
                assertTrue { controller.turn(i) }
            }
        }

        assertEquals(GameState.DRAW, controller.getGameInformation().gameState)
    }

    @Test
    fun verticalWin() {
        assertEquals(GameState.RUNNING, controller.getGameInformation().gameState)

        for(i in 1..4) {
            assertTrue { controller.turn(0) }
            assertTrue { controller.turn(1) }
        }

        assertEquals(GameState.WON, controller.getGameInformation().gameState)
    }

    @Test
    fun horizontalWin() {
        assertEquals(GameState.RUNNING, controller.getGameInformation().gameState)

        for(i in 0..3) {
            assertTrue { controller.turn(i) }
            assertTrue { controller.turn(i) }
        }

        assertEquals(GameState.WON, controller.getGameInformation().gameState)
    }

    @Test // "/"
    fun diagonalWin1() {
        assertEquals(GameState.RUNNING, controller.getGameInformation().gameState)

        assertTrue { controller.turn(0) }
        assertTrue { controller.turn(1) }
        assertTrue { controller.turn(1) }
        assertTrue { controller.turn(2) }
        assertTrue { controller.turn(2) }
        assertTrue { controller.turn(3) }
        assertTrue { controller.turn(2) }
        assertTrue { controller.turn(3) }
        assertTrue { controller.turn(1) }
        assertTrue { controller.turn(3) }
        assertTrue { controller.turn(3) }

        assertEquals(GameState.WON, controller.getGameInformation().gameState)
    }

    @Test // "\"
    fun diagonalWin2() {
        assertEquals(GameState.RUNNING, controller.getGameInformation().gameState)

        assertTrue { controller.turn(3) }
        assertTrue { controller.turn(2) }
        assertTrue { controller.turn(2) }
        assertTrue { controller.turn(1) }
        assertTrue { controller.turn(1) }
        assertTrue { controller.turn(0) }
        assertTrue { controller.turn(1) }
        assertTrue { controller.turn(0) }
        assertTrue { controller.turn(2) }
        assertTrue { controller.turn(0) }
        assertTrue { controller.turn(0) }

        assertEquals(GameState.WON, controller.getGameInformation().gameState)
    }


}