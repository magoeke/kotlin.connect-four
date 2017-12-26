package de.magoeke.kotlin.connectfour.models

import de.magoeke.kotlin.connectfour.util.ArrayUtils

/**
 * Created by max on 25.12.17.
 */

data class Board(val columns: List<Column>) {

    fun to2DArray(): Array<Array<String?>> =
            ArrayUtils.transpose(columns.map { column -> column.toArray() }.toTypedArray())

}