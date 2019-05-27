package com.example.seaworldkotlin.entities.behavior

import com.example.seaworldkotlin.entities.Animal


class Vegan : IEatingBehaviour {
    override fun eat(animal: Animal, foundPositionsInEnvirons: List<Pair<Int, Int>>): Boolean {
        return false
    }
}