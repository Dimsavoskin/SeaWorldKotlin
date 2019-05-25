package com.example.seaworldkotlin.entities.behavior


import android.util.Log
import com.example.seaworldkotlin.entities.Animal
import com.example.seaworldkotlin.utils.freeWaterCode


class EnvironsMoving: IMovingBehaviour {
    val TAG = "EnvironsMoving"

    override fun move(animal: Animal, foundPositionsInEnvirons: List<Pair<Int, Int>>): Boolean {
        val pos = animal.pos

        if (foundPositionsInEnvirons.isNotEmpty()) {
            // select random position
            val bufferRandomNum = (Math.random() * foundPositionsInEnvirons.size).toInt()
            val selectedFreePos = foundPositionsInEnvirons[bufferRandomNum]

            //move animal to new position
            animal.waterSpace[selectedFreePos.first][selectedFreePos.second] = animal.waterSpace[pos.first][pos.second]
            animal.waterSpace[pos.first][pos.second] = freeWaterCode
            animal.animalsMap[animal.id]?.pos = selectedFreePos
            Log.d(TAG, "${animal.animalsMap[animal.id]?.species?.name} (${animal.id}):" +
                    " [${pos.first}, ${pos.second}] -> [${selectedFreePos.first}, ${selectedFreePos.second}]")
            return true
        }
        return false
    }
}