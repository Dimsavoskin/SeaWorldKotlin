package com.example.seaworldkotlin.use_cases

import com.example.seaworldkotlin.use_cases.dto.CurrentStateDto
import com.example.seaworldkotlin.use_cases.dto.InitDataDto


interface ISeaWorldRepository {

    fun getFieldData(): InitDataDto

    fun nextStep()

    fun getCurrentState(): CurrentStateDto

    fun resetGame()

}