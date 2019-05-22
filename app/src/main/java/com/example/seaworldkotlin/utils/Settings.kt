package com.example.seaworldkotlin.utils

const val numOfColumns = 10
const val numOfRows = 15
const val percentOfTux = 50
const val percentOfOrca = 5
const val timeToReproductionTux = 3
const val timeToReproductionOrca = 8
const val timeWithoutFoodOrca = 3

fun array2dOfInt(sizeOuter: Int, sizeInner: Int): Array<IntArray>
        = Array(sizeOuter) { IntArray(sizeInner) }