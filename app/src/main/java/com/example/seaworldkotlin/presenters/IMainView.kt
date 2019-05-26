package com.example.seaworldkotlin.presenters

import com.arellomobile.mvp.MvpView
import com.example.seaworldkotlin.use_cases.dto.AnimalStepData


interface IMainView: MvpView {

    fun initField(fieldSizeX: Int, fieldSizeY: Int)

    fun drawWorld(animalsList: List<AnimalStepData>)
}