package com.example.seaworldkotlin.entities

import android.util.Log
import com.example.seaworldkotlin.entities.behavior.Hunting
import com.example.seaworldkotlin.entities.behavior.Reproduction
import com.example.seaworldkotlin.utils.FREE_WATER_CODE
import com.example.seaworldkotlin.utils.TIME_TO_REPRODUCTION_ORCA
import com.example.seaworldkotlin.utils.TIME_WITHOUT_FOOD_ORCA

class Orca(id: Int, pos: Pair<Int, Int>) : Animal(id, pos) {

    override val species = Animal.Companion.Species.ORCA
    override val eatingBehaviour = Hunting()
    override val reproductionBehaviour = Reproduction()

    init {
        reproductionPeriod = TIME_TO_REPRODUCTION_ORCA
    }

    override fun lifeStep() {
        //try hunting, if unsuccessful - try move
        if (eatingBehaviour.eat(this, findVictims())) {
            timeFromEating = 0
            Log.d(TAG, "id = $id, success hunting")
        } else {
            //TODO: think about dublicate code removing
            Log.d(TAG, "id = $id, success hunting")
            val isMove = movingBehaviour.move(this, findFreePlaces())
            if (isMove) {
                Log.d(TAG, "id = $id, success moving")
            }
            timeFromEating++
        }

        //check on starving death
        if (timeFromEating >= TIME_WITHOUT_FOOD_ORCA) {
            waterSpace[pos.second][pos.first] = FREE_WATER_CODE
            animalsMap.remove(this.id)
            //TODO: decrease orcas numbers
            Log.d(TAG, "${animalsMap[id]?.species?.name} (${id}):" +
                    " [${pos.first}, ${pos.second}]: died if hungry!")
        } else {
            //TODO: think about dublicate code removing
            //reproduction
            lifeTime++;
            if (lifeTime != 0 && 0 == lifeTime % reproductionPeriod) {
                reproductionBehaviour.reproduce(this, findFreePlaces())
            }
        }
    }

    override fun createBaby(id: Int, pos: Pair<Int, Int>): Animal {
        return Orca(id, pos)
    }

    companion object {

        private const val TAG = "Orca"
    }
}