package com.example.seaworldkotlin.utils

const val WORLD_SIZE_X = 10
const val WORLD_SIZE_Y = 15
const val PERCENT_OF_TUX = 50
const val PERCENT_OF_ORCA = 5
const val TIME_TO_REPRODUCTION_TUX = 3
const val TIME_TO_REPRODUCTION_ORCA = 8
const val TIME_WITHOUT_FOOD_ORCA = 3
const val FREE_WATER_CODE = -1

fun array2dOfInt(sizeOuter: Int, sizeInner: Int): Array<IntArray>
        = Array(sizeOuter) { IntArray(sizeInner) }