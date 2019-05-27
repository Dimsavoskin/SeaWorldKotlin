package com.example.seaworldkotlin.use_cases

import com.example.seaworldkotlin.use_cases.dto.CurrentStateDto
import com.example.seaworldkotlin.use_cases.dto.InitDataDto
import rx.Observable


class SeaWorldInteractor(val seaWorldRepository: ISeaWorldRepository) : ISeaWorldInteractor {

    override fun getInitData(): InitDataDto {
        return seaWorldRepository.getFieldParameters()
    }

    override fun getResetGameObservable(): Observable<CurrentStateDto> {

        return Observable.create { subscriber ->
            seaWorldRepository.resetGame()

            subscriber.onNext(seaWorldRepository.getCurrentState())
            subscriber.onCompleted()
        }
    }

    override fun getNextStepObservable(): Observable<CurrentStateDto> {
        return seaWorldRepository.getNextStepObservable()
    }
}