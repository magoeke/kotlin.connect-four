package de.magoeke.kotlin.connectfour.util.observer

/**
 * Created by max on 26.12.17.
 */
interface IObservable {

    fun add(observer: IObserver): Boolean

    fun remove(observer: IObserver): Boolean

    fun notifyObservers(): Unit

}