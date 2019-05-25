package com.example.seaworldkotlin.entities.behavior

import com.example.seaworldkotlin.entities.Animal


interface IMovingBehaviour {

    fun move(animal: Animal, foundPositionsInEnvirons: List<Pair<Int, Int>>): Boolean
}