package com.example.seaworldkotlin.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.seaworldkotlin.repositories.SeaWorldRepository
import com.example.seaworldkotlin.use_cases.ISeaWorldInteractor
import com.example.seaworldkotlin.use_cases.SeaWorldInteractor


@InjectViewState
class MainPresenter : MvpPresenter<IMainView>() {

    private var seaWorldInteractor : ISeaWorldInteractor = SeaWorldInteractor(SeaWorldRepository())

    override fun attachView(view: IMainView?) {
        super.attachView(view)

        val initData = seaWorldInteractor.fieldInitialization()
        val currentPosition = seaWorldInteractor.getCurrentPosition()
        viewState.initField(initData.sizeX, initData.sizeY)
        viewState.drawWorld(currentPosition.animalsList)
    }

    fun onTouch() {
        Log.d(TAG, "onTouch")
    }

    fun onReset() {
        seaWorldInteractor.resetGame()
        val currentPosition = seaWorldInteractor.getCurrentPosition()
        viewState.drawWorld(currentPosition.animalsList)
    }

    companion object {

        val TAG = "MainPresenter"
    }
}