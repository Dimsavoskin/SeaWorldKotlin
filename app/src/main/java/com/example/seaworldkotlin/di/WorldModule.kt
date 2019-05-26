package com.example.seaworldkotlin.di

import com.example.seaworldkotlin.entities.Animal
import com.example.seaworldkotlin.utils.array2dOfInt
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class WorldModule(fieldSizeX: Int, fieldSizeY: Int) {

    private val waterSpace = array2dOfInt(fieldSizeY, fieldSizeX)
    private val animalsMap: MutableMap<Int, Animal> = TreeMap()
    private var animalsIdCounter: Int = 0

    @Provides
    @Singleton
    fun provideWaterSpace(): Array<IntArray> {
        return waterSpace
    }

    @Provides
    @Singleton
    fun provideAnimalsMap(): MutableMap<Int, Animal> {
        return animalsMap
    }

    @Provides
    @Singleton
    fun provideAnimalsIdCounter(): Int {
        return animalsIdCounter
    }
}