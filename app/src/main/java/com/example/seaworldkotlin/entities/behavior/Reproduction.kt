package com.example.seaworldkotlin.entities.behavior

import android.util.Log
import com.example.seaworldkotlin.SeaWorldApp
import com.example.seaworldkotlin.entities.Animal
import javax.inject.Inject
import javax.inject.Named


class Reproduction: IReproductionBehaviour {
    val TAG = "Reproduction"

    @set:[Inject Named("animalsIdCounter")]
    var animalsIdCounter: Int = 0

    init {
        SeaWorldApp.modelsComponent?.inject(this)
    }

    override fun reproduce(animal: Animal, foundPositionsInEnvirons: List<Pair<Int, Int>>): Boolean {
        val pos = animal.pos

        if (foundPositionsInEnvirons.isNotEmpty()) {
            // select random position
            val bufferRandomNum = (Math.random() * foundPositionsInEnvirons.size).toInt()
            val selectedFreePos = foundPositionsInEnvirons[bufferRandomNum]

            //create baby at the new position
            val baby = animal.createBaby(animalsIdCounter, selectedFreePos)
            animal.animalsMap.put(animalsIdCounter, baby)
            animal.waterSpace[pos.second][pos.first] = animalsIdCounter

            Log.d(TAG, "${animal.animalsMap[animal.id]?.species?.name} (${animal.id})" +
                    " [${pos.first}, ${pos.second}]: produced ${animal.animalsMap[animalsIdCounter]?.species?.name}" +
                    "($animalsIdCounter) [${selectedFreePos.first}, ${selectedFreePos.second}]")

            animalsIdCounter++
            return true
        }
        return false
    }
}