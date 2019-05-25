package com.example.seaworldkotlin.entities.behavior

import com.example.seaworldkotlin.entities.Animal


interface IEatingBehaviour {

    fun eat(animal: Animal, foundPositionsInEnvirons: List<Pair<Int, Int>>): Boolean
}