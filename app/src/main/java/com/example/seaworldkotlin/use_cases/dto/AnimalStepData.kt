package com.example.seaworldkotlin.use_cases.dto

import com.example.seaworldkotlin.entities.Animal


data class AnimalStepData(
    val species: Animal.Companion.Species,
    val pos: Pair<Int, Int>,
    val age: Int
)