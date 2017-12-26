package de.magoeke.kotlin.connectfour.controllers

import de.magoeke.kotlin.connectfour.models.Game
import de.magoeke.kotlin.connectfour.util.observer.IObservable

/**
 * Created by max on 25.12.17.
 */
interface IController : IObservable {

    fun turn(column: Int) : Boolean

    fun getGameInformation(): Game

}