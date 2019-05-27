package com.example.seaworldkotlin.entities.behavior

import android.util.Log
import com.example.seaworldkotlin.entities.Animal
import com.example.seaworldkotlin.utils.FREE_WATER_CODE


class Hunting : IEatingBehaviour {

    override fun eat(animal: Animal, foundPositionsInEnvirons: List<Pair<Int, Int>>): Boolean {
        var result = false
        val pos = animal.pos

        if (foundPositionsInEnvirons.isNotEmpty()) {
            // select random position
            val bufferRandomNum = (Math.random() * foundPositionsInEnvirons.size).toInt()
            val selectedFreePos = foundPositionsInEnvirons[bufferRandomNum]

            //kill victim and move animal to new position
            val victimId = animal.waterSpace[selectedFreePos.second][selectedFreePos.first]

            Log.d(
                TAG, "${animal.animalsMap[animal.id]?.species?.name} (${animal.id})" +
                        " [${pos.first}, ${pos.second}]: killed ${animal.animalsMap[victimId]?.species?.name} ($victimId)" +
                        " [${selectedFreePos.first}, ${selectedFreePos.second}]"
            )

            (animal.animalsMap[victimId] as Animal).isAlive = false

            animal.waterSpace[selectedFreePos.second][selectedFreePos.first] = animal.waterSpace[pos.second][pos.first]
            animal.waterSpace[pos.second][pos.first] = FREE_WATER_CODE
            animal.animalsMap[animal.id]?.pos = selectedFreePos

            result = true
        }
        return result
    }

    companion object {

        private const val TAG = "Hunting"
    }
}