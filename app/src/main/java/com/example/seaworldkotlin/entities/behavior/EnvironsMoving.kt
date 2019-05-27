package com.example.seaworldkotlin.entities.behavior


import android.util.Log
import com.example.seaworldkotlin.entities.Animal
import com.example.seaworldkotlin.utils.FREE_WATER_CODE


class EnvironsMoving : IMovingBehaviour {

    override fun move(animal: Animal, foundPositionsInEnvirons: List<Pair<Int, Int>>): Boolean {
        var result = false
        val pos = animal.pos

        if (foundPositionsInEnvirons.isNotEmpty()) {
            // select random position
            val bufferRandomNum = (Math.random() * foundPositionsInEnvirons.size).toInt()
            val selectedFreePos = foundPositionsInEnvirons[bufferRandomNum]

            //move animal to new position
            animal.waterSpace[selectedFreePos.second][selectedFreePos.first] = animal.waterSpace[pos.second][pos.first]
            animal.waterSpace[pos.second][pos.first] = FREE_WATER_CODE
            animal.animalsMap[animal.id]?.pos = selectedFreePos

            Log.d(
                TAG, "${animal.animalsMap[animal.id]?.species?.name} (${animal.id}):" +
                        " [${pos.first}, ${pos.second}] -> [${selectedFreePos.first}, ${selectedFreePos.second}]"
            )

            result = true
        }
        return result
    }

    companion object {

        private val TAG = "EnvironsMoving"
    }
}