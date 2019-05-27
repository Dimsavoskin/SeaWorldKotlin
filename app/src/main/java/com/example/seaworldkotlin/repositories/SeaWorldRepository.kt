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
import rx.Observable


class SeaWorldRepository : ISeaWorldRepository {
    private val world = SeaWorld()

    /**
     * Flag for interrupt caused by reset.
     */
    var nextStepFlag = false

    override fun getFieldParameters(): InitDataDto {
        return InitDataDto(Pair(WORLD_SIZE_X, WORLD_SIZE_Y))
    }

    override fun resetGame() {
        nextStepFlag = false
        world.reset()
    }

    override fun getNextStepObservable(): Observable<CurrentStateDto> {
        return Observable.create { subscriber ->
            nextStepFlag = true
            try {
                for (animal in world.animalsMap.values.sortedWith(Comparator { t1, t2 -> t1.compareTo(t2) })) {
                    if (!nextStepFlag) {
                        break
                    }
                    if (animal.isAlive) {
                        animal.lifeStep()
                        subscriber.onNext(getCurrentState())
                    }
                }
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            }
            subscriber.onCompleted()
        }
    }

    override fun getCurrentState(): CurrentStateDto {
        val animalsList = mutableListOf<AnimalStepData>()
        for (animal in world.animalsMap.values) {

            var lifeTime = -1

            //add alive animal in list only
            var isAddInList = true

            if (animal.species.equals(Animal.Companion.Species.TUX) || animal.species.equals(Animal.Companion.Species.ORCA)) {

                isAddInList = animal.isAlive

                lifeTime = animal.lifeTime

                if (animal.species.equals(Orca)) {
                    animal as Orca
                }
            }

            if (isAddInList) {
                animalsList.add(
                    AnimalStepData(
                        animal.species,
                        animal.pos,
                        lifeTime
                    )
                )
            }
        }
        return CurrentStateDto(animalsList)
    }

    companion object {

        private val TAG = "SeaWorldRepository"
    }
}