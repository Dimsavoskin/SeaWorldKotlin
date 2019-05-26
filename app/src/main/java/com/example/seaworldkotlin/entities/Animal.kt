package com.example.seaworldkotlin.entities

import com.example.seaworldkotlin.R
import com.example.seaworldkotlin.SeaWorldApp
import com.example.seaworldkotlin.entities.behavior.EnvironsMoving
import com.example.seaworldkotlin.entities.behavior.IEatingBehaviour
import com.example.seaworldkotlin.entities.behavior.IMovingBehaviour
import com.example.seaworldkotlin.entities.behavior.IReproductionBehaviour
import com.example.seaworldkotlin.utils.FREE_WATER_CODE
import java.util.function.Function
import javax.inject.Inject

abstract class Animal(val id: Int, var pos: Pair<Int, Int>) {

    init {
        SeaWorldApp.modelsComponent?.inject(this)
    }

    @Inject
    lateinit var waterSpace: Array<IntArray>

    @Inject
    lateinit var animalsMap: MutableMap<Int, Animal>

    var lifeTime = 0
    var timeFromEating = 0
    var timeToReprodution = 0
    var isAlive = true

    var reproductionPeriod = 0

    abstract val eatingBehaviour: IEatingBehaviour
    abstract val reproductionBehaviour: IReproductionBehaviour
    val movingBehaviour: IMovingBehaviour = EnvironsMoving()

    abstract val species: Species
    private val environs = 1

    abstract fun createBaby(id: Int, pos: Pair<Int, Int>): Animal

    open fun lifeStep() {
        movingBehaviour.move(this, findFreePlaces())
        lifeTime++
        if (lifeTime != 0 && 0 == lifeTime % reproductionPeriod) {
            reproductionBehaviour.reproduce(this, findFreePlaces())
        }
    }

    fun findFreePlaces(): List<Pair<Int, Int>> {
        return findInEnvirons(Function { potentialPosition -> FREE_WATER_CODE == potentialPosition })
    }

    fun findVictims(): List<Pair<Int, Int>> {
        return findInEnvirons(Function { potentialTargetId ->
            potentialTargetId != FREE_WATER_CODE
                    && species == Companion.Species.ORCA
        })
    }

    private fun findInEnvirons(conditionForAddPosition: Function<Int, Boolean>): List<Pair<Int, Int>> {
        //define environs border by X
        var beginRangeBypassX = pos.first - environs
        if (beginRangeBypassX < 0) {
            beginRangeBypassX = 0
        }

        var endRangeBypassX = pos.first + environs
        if (endRangeBypassX > waterSpace[0].lastIndex) {
            endRangeBypassX = waterSpace[0].lastIndex
        }

        //define environs border by Y
        var beginRangeBypassY = pos.second - environs
        if (beginRangeBypassY < 0) {
            beginRangeBypassY = 0
        }

        var endRangeBypassY = pos.second + environs
        if (endRangeBypassY > waterSpace.lastIndex) {
            endRangeBypassY = waterSpace.lastIndex
        }

        //search suitable positions
        val foundPositions = mutableListOf<Pair<Int, Int>>()
        for (i in beginRangeBypassY..endRangeBypassY) {
            for (j in beginRangeBypassX..endRangeBypassX) {
                if (i == pos.second && j == pos.first) {
                    continue
                } else {
                    if (conditionForAddPosition.apply(waterSpace[i][j])) {
                        foundPositions.add(Pair(j, i))
                    }
                }
            }
        }

        return foundPositions
    }

    companion object {

        enum class Species(val pngId: Int) {
            TUX(R.drawable.tux),
            ORCA(R.drawable.orca)
        }

        private const val TAG = "Animal"
    }
}