package com.example.seaworldkotlin.entities

class Tux(id: Int, pos: Pair<Int, Int>) : Animal(id, pos) {

    override val species = Animal.Companion.Species.ORCA

    override fun lifeStep() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createBaby(id: Int, pos: Pair<Int, Int>): Animal {
        return Tux(id, pos)
    }

    companion object {

        private val TAG = "Tux"
    }
}