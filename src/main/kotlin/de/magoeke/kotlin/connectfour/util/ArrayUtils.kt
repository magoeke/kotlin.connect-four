package de.magoeke.kotlin.connectfour.util

/**
 * Created by max on 26.12.17.
 */
object ArrayUtils {

    // Unfortunately there is no library function that transposes 2d arrays
    inline fun <reified T> transpose(array: Array<Array<T?>>): Array<Array<T?>> {
        val size = array.size
        val transposed = Array<Array<T?>>(size, {i -> arrayOfNulls(size)})

        for (i in array.indices) {
            for (j in array[i].indices) {
                transposed[j][i] = array[i][j]
            }
        }

        return transposed
    }

}