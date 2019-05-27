package com.example.seaworldkotlin.presenters

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.seaworldkotlin.use_cases.dto.AnimalStepData

@StateStrategyType(SkipStrategy::class)
interface IMainView : MvpView {

    fun initField(fieldSize: Pair<Int, Int>, animalsList: List<AnimalStepData>)

    fun drawWorld(animalsList: List<AnimalStepData>)
}