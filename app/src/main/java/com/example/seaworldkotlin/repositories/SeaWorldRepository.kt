package com.example.seaworldkotlin.repositories

import com.example.seaworldkotlin.entities.Animal
import com.example.seaworldkotlin.entities.Orca
import com.example.seaworldkotlin.entities.SeaWorld
import com.example.seaworldkotlin.use_cases.ISeaWorldRepository
import com.example.seaworldkotlin.use_cases.dto.AnimalStepData
import com.example.seaworldkotlin.use_cases.dto.CurrentStateDto
import com.example.seaworldkotlin.use_cases.dto.InitDataDto
import com.example.seaworldkotlin.utils.WORLD_SIZE_X
import com.example.seaworldkotlin.utils.WORLD_SIZE_Y
import com.example.seaworldkotlin.utils.TIME_WITHOUT_FOOD_ORCA


class SeaWorldRepository : ISeaWorldRepository {
    val world = SeaWorld()

    override fun getFieldData(): InitDataDto {
        return InitDataDto(WORLD_SIZE_X, WORLD_SIZE_Y)
    }

    override fun nextStep() {
        world.nextStep()
    }

    override fun getCurrentState(): CurrentStateDto {
        val animalsList = mutableListOf<AnimalStepData>()
        for (animal in world.animalsMap.values) {

            var lifeTime = -1
            if (animal.species.equals(Animal.Companion.Species.TUX) || animal.species.equals(Animal.Companion.Species.ORCA)) {
                lifeTime = animal.lifeTime
            }

            var isStarvingDeathSoon = false
            val isChildbirthSoon = animal.lifeTime % animal.reproductionPeriod >= animal.reproductionPeriod - 1

            //orca has additional parameters for displaying
            if (animal.species.equals(Orca)) {
                animal as Orca

                isStarvingDeathSoon = animal.timeFromEating >= TIME_WITHOUT_FOOD_ORCA - 1
            }

            animalsList.add(
                AnimalStepData(
                    animal.species,
                    animal.pos,
                    lifeTime,
                    isStarvingDeathSoon,
                    isChildbirthSoon
                )
            )
        }
        return CurrentStateDto(animalsList)
    }

    override fun resetGame() {
        world.reset()
    }
}