package com.example.seaworldkotlin.use_cases.dto

import com.example.seaworldkotlin.entities.Animal


class AnimalStepData(val species: Animal.Companion.Species, val pos: Pair<Int, Int>, val age:Int,
                     val isStarvingDeathSoon: Boolean, val isChildbirthSoon: Boolean) {
}