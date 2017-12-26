package de.magoeke.kotlin.connectfour.util.observer

/**
 * Created by max on 26.12.17.
 */
open class Observable : IObservable{

    val observers = mutableListOf<IObserver>()

    override fun add(observer: IObserver): Boolean = observers.add(observer)

    override fun remove(observer: IObserver): Boolean = observers.remove(observer)

    override fun notifyObservers() = observers.forEach{ observer -> observer.update() }

}