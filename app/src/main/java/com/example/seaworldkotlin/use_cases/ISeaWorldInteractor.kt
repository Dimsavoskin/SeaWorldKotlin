package com.example.seaworldkotlin.use_cases

import com.example.seaworldkotlin.use_cases.dto.CurrentStateDto
import com.example.seaworldkotlin.use_cases.dto.InitDataDto
import rx.Observable


interface ISeaWorldInteractor {

    fun getInitData(): InitDataDto

    fun getResetGameObservable(): Observable<CurrentStateDto>

    fun getNextStepObservable(): Observable<CurrentStateDto>
}