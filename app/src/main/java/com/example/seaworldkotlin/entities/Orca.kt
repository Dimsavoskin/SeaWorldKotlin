package com.example.seaworldkotlin.entities

import com.example.seaworldkotlin.entities.behavior.Hunting
import com.example.seaworldkotlin.entities.behavior.Reproduction
import com.example.seaworldkotlin.utils.timeToReproductionOrca

class Orca(id: Int, pos: Pair<Int, Int>) : Animal(id, pos) {

    override val species = Animal.Companion.Species.ORCA
    override val eatingBehaviour = Hunting()
    override val reproductionBehaviour = Reproduction()

    init {
        reproductionPeriod = timeToReproductionOrca
    }

    override fun lifeStep() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createBaby(id: Int, pos: Pair<Int, Int>): Animal {
        return Orca(id, pos)
    }

    companion object {

        private const val TAG = "Orca"
    }
}