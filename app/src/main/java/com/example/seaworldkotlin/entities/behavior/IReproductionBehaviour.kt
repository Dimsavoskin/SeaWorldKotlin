package com.example.seaworldkotlin.entities.behavior

import com.example.seaworldkotlin.entities.Animal


interface IReproductionBehaviour {

    fun reproduce(animal: Animal, foundPositionsInEnvirons: List<Pair<Int, Int>>): Boolean
}