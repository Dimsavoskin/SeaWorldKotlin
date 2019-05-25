package com.example.seaworldkotlin.di

import com.example.seaworldkotlin.utils.array2dOfInt
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WorldModule(fieldSizeX: Int, fieldSizeY: Int) {

    private val field = array2dOfInt(fieldSizeX, fieldSizeY)

    @Provides
    @Singleton
    fun provideWaterSpace(): Array<IntArray> {
        return field
    }
}