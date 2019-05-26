package com.example.seaworldkotlin.use_cases

import com.example.seaworldkotlin.use_cases.dto.CurrentStateDto
import com.example.seaworldkotlin.use_cases.dto.InitDataDto


class SeaWorldInteractor(val seaWorldRepository: ISeaWorldRepository): ISeaWorldInteractor {


    override fun fieldInitialization(): InitDataDto {
        resetGame()
        return seaWorldRepository.getFieldData()
    }

    override fun doNextStep() {
        seaWorldRepository.nextStep()
    }

    override fun getCurrentPosition(): CurrentStateDto {
        return seaWorldRepository.getCurrentState()
    }

    override fun resetGame() {
        seaWorldRepository.resetGame()
    }
}