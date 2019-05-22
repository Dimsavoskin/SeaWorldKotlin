package com.example.seaworldkotlin.entities

import com.example.seaworldkotlin.R

abstract class Animal(id: Int, pos: Pair<Int, Int>) {

    var lifeTime = 0
    var timeToReprodution = 0
    var isAlive = true

    abstract val species: Species

    abstract fun createBaby(id: Int, pos: Pair<Int, Int>): Animal

    companion object {

        enum class Species(val pngId: Int) {
            TUX(R.drawable.tux),
            ORCA(R.drawable.orca)
        }

        private val TAG = "Animal"
    }
}