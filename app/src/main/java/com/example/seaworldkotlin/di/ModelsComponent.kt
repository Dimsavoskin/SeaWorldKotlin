package com.example.seaworldkotlin.di

import com.example.seaworldkotlin.entities.Animal
import com.example.seaworldkotlin.entities.SeaWorld
import dagger.Component
import javax.inject.Singleton

@Component(modules = [WorldModule::class])
@Singleton
interface ModelsComponent {

    fun inject(world: SeaWorld)

    fun inject(animal: Animal)
}