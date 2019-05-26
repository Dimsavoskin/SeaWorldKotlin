package com.example.seaworldkotlin.use_cases

import com.example.seaworldkotlin.use_cases.dto.CurrentStateDto
import com.example.seaworldkotlin.use_cases.dto.InitDataDto


interface ISeaWorldInteractor {

    fun fieldInitialization(): InitDataDto

    fun getCurrentPosition(): CurrentStateDto

    fun doNextStep()

    fun resetGame()
}