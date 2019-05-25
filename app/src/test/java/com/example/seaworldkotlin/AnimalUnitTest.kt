package com.example.seaworldkotlin

import com.example.seaworldkotlin.entities.Orca
import com.example.seaworldkotlin.entities.Tux
import com.example.seaworldkotlin.utils.array2dOfInt
import com.example.seaworldkotlin.utils.freeWaterCode
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class AnimalUnitTest(val x: Int, val y: Int) {

    private val orca = Orca(1, Pair(x, y))

    init {
        orca.waterSpace = array2dOfInt(3, 3)
        orca.waterSpace[0] = intArrayOf(freeWaterCode, freeWaterCode, freeWaterCode)
        orca.waterSpace[1] = intArrayOf(freeWaterCode,          1           , freeWaterCode)
        orca.waterSpace[2] = intArrayOf(        2,            freeWaterCode,           3)

        orca.animalsMap = mutableMapOf()
        orca.animalsMap[1] = Tux(1, Pair(0, 1))
        orca.animalsMap[2] = Tux(2, Pair(2, 1))
        orca.animalsMap[3] = Orca(3, Pair(2, 2))
    }

    @Test
    fun testFindPlacesForMoving() {

        val size = orca.findPlacesForMoving().size
        assert(size > 0)
        println("x = $x, y = $y, founded positions = $size")
    }

    @Test
    fun testFindVictims() {

        val size = orca.findVictims().size
        assert(size > 0)
        println("x = $x, y = $y, founded victims = $size")
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: test 'Find free positions:")
        fun data(): Collection<Array<Int>> {
            return listOf(arrayOf(0, 0),
                arrayOf(1, 0),
                arrayOf(2, 0),
                arrayOf(0, 1),
                arrayOf(2, 1),
                arrayOf(1, 2))
        }
    }
}