package com.example.seaworldkotlin.di.components

import com.example.seaworldkotlin.di.modules.WorldModule
import com.example.seaworldkotlin.entities.Animal
import com.example.seaworldkotlin.entities.SeaWorld
import com.example.seaworldkotlin.entities.behavior.Reproduction
import dagger.Component
import javax.inject.Singleton

@Component(modules = [WorldModule::class])
@Singleton
interface ModelsComponent {

    fun inject(world: SeaWorld)

    fun inject(animal: Animal)

    fun inject(reproduction: Reproduction)
}