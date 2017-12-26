package de.magoeke.kotlin.connectfour.models

import kotlinx.collections.immutable.toImmutableList

/**
 * Created by max on 25.12.17.
 */
data class Column(val maxCells: Int) {

    private val cells: MutableList<String> = mutableListOf()

    fun add(cell: String): Boolean {
        if(cells.size >= maxCells) { return false }

        cells.add(cell)
        return true
    }

    fun usedCells(): Int = cells.size

    fun toArray(): Array<String?> {
        // Snapshot of cells
        val cells = this.cells.toImmutableList()
        return (0..maxCells-1).map { i -> if(i >= cells.size) null else cells[i] }.reversed().toTypedArray()
    }
}