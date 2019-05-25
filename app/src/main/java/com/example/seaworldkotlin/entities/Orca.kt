package com.example.seaworldkotlin.entities

class Orca(id: Int, pos: Pair<Int, Int>) : Animal(id, pos) {

    override val species = Animal.Companion.Species.ORCA

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