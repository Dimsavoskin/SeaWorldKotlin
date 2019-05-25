package com.example.seaworldkotlin.entities

import com.example.seaworldkotlin.SeaWorldApp
import com.example.seaworldkotlin.utils.*
import java.util.*
import javax.inject.Inject

class SeaWorld {

    init {
        SeaWorldApp.modelsComponent?.inject(this)
    }

    private var animalsNumber: MutableMap<Animal.Companion.Species, Int> = HashMap()
    private var animalsIdCounter = 0

    @Inject
    lateinit var waterSpace: Array<IntArray>

    @Inject
    lateinit var animalsMap: MutableMap<Int, Animal>

    fun reset() {
        animalsNumber[Animal.Companion.Species.ORCA] = numOfColumns * numOfRows * percentOfOrca / 100
        animalsNumber[Animal.Companion.Species.TUX] = numOfColumns * numOfRows * timeToReproductionTux / 100
        for (i in waterSpace.indices) {
            for (j in waterSpace[i].indices) {
                waterSpace[i][j] = freeWaterCode
            }
        }
        animalsIdCounter = 0
        animalsMap.clear()

        // create animals and put them on the field
        for (species in animalsNumber.keys) {
            for (i in 0 until animalsNumber[species]!!) {
                animalsMap[animalsIdCounter] = createAnimals(species, animalsIdCounter, occupyFreePosition(animalsIdCounter))
                animalsIdCounter++
            }
        }
    }

    fun nextStep() {
        for (animal in animalsMap.values) {
            if (animal.isAlive) {
                animal.lifeStep()
            }
        }
    }

    private fun occupyFreePosition(id: Int): Pair<Int, Int> {
        var randomPos: Int
        var posCandidate: Pair<Int, Int>

        do {
            randomPos = (Math.random() * numOfColumns * numOfRows).toInt()
            posCandidate = Pair(randomPos % numOfColumns, randomPos / numOfColumns)
        } while (waterSpace[posCandidate.second][posCandidate.first] != freeWaterCode)
        waterSpace[posCandidate.second][posCandidate.first] = id

        return posCandidate
    }

    companion object {

        private const val TAG = "SeaWorld"
    }
}