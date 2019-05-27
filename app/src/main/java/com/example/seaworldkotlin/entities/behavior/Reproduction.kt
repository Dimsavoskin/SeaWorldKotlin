package com.example.seaworldkotlin.entities.behavior

import android.util.Log
import com.example.seaworldkotlin.SeaWorldApp
import com.example.seaworldkotlin.entities.Animal
import com.example.seaworldkotlin.entities.AnimalsIdCounter
import javax.inject.Inject


class Reproduction : IReproductionBehaviour {

    @Inject
    lateinit var animalsIdCounter: AnimalsIdCounter

    init {
        SeaWorldApp.modelsComponent?.inject(this)
    }

    override fun reproduce(animal: Animal, foundPositionsInEnvirons: List<Pair<Int, Int>>): Boolean {
        var result = false
        val pos = animal.pos

        if (foundPositionsInEnvirons.isNotEmpty()) {
            // select random position
            val bufferRandomNum = (Math.random() * foundPositionsInEnvirons.size).toInt()
            val selectedFreePos = foundPositionsInEnvirons[bufferRandomNum]

            //create baby at the new position
            val baby = animal.createBaby(animalsIdCounter.counter, selectedFreePos)
            animal.animalsMap[animalsIdCounter.counter] = baby
            animal.waterSpace[selectedFreePos.second][selectedFreePos.first] = animalsIdCounter.counter

            Log.d(
                TAG, "${animal.animalsMap[animal.id]?.species?.name} (${animal.id})" +
                        " [${pos.first}, ${pos.second}]: produced ${animal.animalsMap[animalsIdCounter.counter]?.species?.name}" +
                        "(${baby.id}) [${baby.pos.first}, ${baby.pos.second}]"
            )

            animalsIdCounter.counter++
            result = true
        }
        return result
    }

    companion object {

        private const val TAG = "Reproduction"
    }
}