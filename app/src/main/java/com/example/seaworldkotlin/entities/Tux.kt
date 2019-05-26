package com.example.seaworldkotlin.entities

import com.example.seaworldkotlin.entities.behavior.Reproduction
import com.example.seaworldkotlin.entities.behavior.Vegan
import com.example.seaworldkotlin.utils.TIME_TO_REPRODUCTION_TUX

class Tux(id: Int, pos: Pair<Int, Int>) : Animal(id, pos) {

    override val species = Animal.Companion.Species.ORCA
    override val eatingBehaviour = Vegan()
    override val reproductionBehaviour = Reproduction()

    init {
        reproductionPeriod = TIME_TO_REPRODUCTION_TUX
    }

    override fun createBaby(id: Int, pos: Pair<Int, Int>): Animal {
        return Tux(id, pos)
    }

    companion object {

        private const val TAG = "Tux"
    }
}