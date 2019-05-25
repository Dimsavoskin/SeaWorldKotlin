package com.example.seaworldkotlin.entities

import com.example.seaworldkotlin.R
import com.example.seaworldkotlin.SeaWorldApp
import javax.inject.Inject

abstract class Animal(id: Int, pos: Pair<Int, Int>) {

    init {
        SeaWorldApp.modelsComponent.inject(this)
    }

    @Inject
    lateinit var waterSpace: Array<IntArray>

    var lifeTime = 0
    var timeToReprodution = 0
    var isAlive = true

    abstract val species: Species

    abstract fun lifeStep()

    abstract fun createBaby(id: Int, pos: Pair<Int, Int>): Animal

    companion object {

        enum class Species(val pngId: Int) {
            TUX(R.drawable.tux),
            ORCA(R.drawable.orca)
        }

        private const val TAG = "Animal"
    }
}