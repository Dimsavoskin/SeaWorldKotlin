package com.example.seaworldkotlin.use_cases

import com.example.seaworldkotlin.use_cases.dto.CurrentStateDto
import com.example.seaworldkotlin.use_cases.dto.InitDataDto
import rx.Observable


interface ISeaWorldRepository {

    fun getFieldParameters(): InitDataDto

    fun resetGame()

    fun getCurrentState(): CurrentStateDto

    fun getNextStepObservable(): Observable<CurrentStateDto>
}