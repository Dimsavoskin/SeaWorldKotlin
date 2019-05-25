package com.example.seaworldkotlin.entities

import android.util.Log

fun createAnimals(species: Animal.Companion.Species, id: Int, pos: Pair<Int, Int>) : Animal {
    val TAG = "AnimalFactory"

    val animal = when (species) {
        Animal.Companion.Species.ORCA -> Orca(id, pos)
        Animal.Companion.Species.TUX -> Tux(id, pos)
    }
    Log.d(TAG, "${species.name} (id = $id) was created and put in [${pos.first}, ${pos.second}]")
    return animal
}