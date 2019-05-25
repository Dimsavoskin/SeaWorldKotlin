package com.example.seaworldkotlin.di

import com.example.seaworldkotlin.entities.Animal
import com.example.seaworldkotlin.utils.array2dOfInt
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class WorldModule(fieldSizeX: Int, fieldSizeY: Int) {

    private val field = array2dOfInt(fieldSizeX, fieldSizeY)
    private var animalsMap: MutableMap<Int, Animal> = TreeMap()

    @Provides
    @Singleton
    fun provideWaterSpace(): Array<IntArray> {
        return field
    }

    @Provides
    @Singleton
    fun provideAnimalsMap(): MutableMap<Int, Animal> {
        return animalsMap
    }
}