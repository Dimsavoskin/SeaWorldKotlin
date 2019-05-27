package com.example.seaworldkotlin.entities

import com.example.seaworldkotlin.SeaWorldApp
import com.example.seaworldkotlin.utils.*
import java.util.*
import javax.inject.Inject

class SeaWorld {

    private var animalsNumber: MutableMap<Animal.Companion.Species, Int> = HashMap()

    @Inject
    lateinit var waterSpace: Array<IntArray>

    @Inject
    lateinit var animalsMap: MutableMap<Int, Animal>

    @Inject
    lateinit var animalsIdCounter: AnimalsIdCounter

    init {
        SeaWorldApp.modelsComponent?.inject(this)
    }

    fun reset() {
        animalsNumber[Animal.Companion.Species.ORCA] = WORLD_SIZE_X * WORLD_SIZE_Y * PERCENT_OF_ORCA / 100
        animalsNumber[Animal.Companion.Species.TUX] = WORLD_SIZE_X * WORLD_SIZE_Y * PERCENT_OF_TUX / 100
        for (i in waterSpace.indices) {
            for (j in waterSpace[i].indices) {
                waterSpace[i][j] = FREE_WATER_CODE
            }
        }
        animalsIdCounter.counter = 0
        animalsMap.clear()

        // create animals and put them on the field
        for (species in animalsNumber.keys) {
            for (i in 0 until animalsNumber[species]!!) {
                animalsMap[animalsIdCounter.counter] =
                    createAnimals(species, animalsIdCounter.counter, occupyFreePosition(animalsIdCounter.counter))
                animalsIdCounter.counter++
            }
        }
    }

    private fun occupyFreePosition(id: Int): Pair<Int, Int> {
        var randomPos: Int
        var posCandidate: Pair<Int, Int>

        do {
            randomPos = (Math.random() * WORLD_SIZE_X * WORLD_SIZE_Y).toInt()
            posCandidate = Pair(randomPos % WORLD_SIZE_X, randomPos / WORLD_SIZE_X)
        } while (waterSpace[posCandidate.second][posCandidate.first] != FREE_WATER_CODE)
        waterSpace[posCandidate.second][posCandidate.first] = id

        return posCandidate
    }

    companion object {

        private const val TAG = "SeaWorld"
    }
}