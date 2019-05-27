package com.example.seaworldkotlin.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.seaworldkotlin.repositories.SeaWorldRepository
import com.example.seaworldkotlin.use_cases.ISeaWorldInteractor
import com.example.seaworldkotlin.use_cases.SeaWorldInteractor
import com.example.seaworldkotlin.use_cases.dto.CurrentStateDto
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription


@InjectViewState
class MainPresenter : MvpPresenter<IMainView>() {

    private var nextStepObservable: Observable<CurrentStateDto>? = null
    private var compositeSubscription: CompositeSubscription? = null

    private var seaWorldInteractor: ISeaWorldInteractor = SeaWorldInteractor(SeaWorldRepository())

    private var isLifeStepInProgress = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        registerSubscription(seaWorldInteractor.getResetGameObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { currentPosition ->
                val initData = seaWorldInteractor.getInitData()
                viewState.initField(initData.fieldSize, currentPosition.animalsList)
                nextStepObservable = seaWorldInteractor.getNextStepObservable()
            }
        )
    }

    fun onTouch() {
        if (!isLifeStepInProgress) {
            isLifeStepInProgress = true
            registerSubscription(
                nextStepObservable!!
                    .subscribeOn(Schedulers.computation())
                    .subscribe({ currentPosition -> viewState.drawWorld(currentPosition.animalsList) },
                        { },
                        {
                            //reset flag when life step was completed
                            isLifeStepInProgress = false
                        })
            )
        }
    }

    fun onReset() {
        registerSubscription(seaWorldInteractor.getResetGameObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { currentPosition ->
                viewState.drawWorld(currentPosition.animalsList)
                isLifeStepInProgress = false
            })
    }

    private fun registerSubscription(subscription: Subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = CompositeSubscription()
        }
        compositeSubscription!!.add(subscription)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        clearSubscription()
        super.onDestroy()
    }

    private fun clearSubscription() {
        if (compositeSubscription != null) {
            compositeSubscription!!.unsubscribe()
            compositeSubscription = null
        }
    }

    companion object {

        private const val TAG = "MainPresenter"
    }
}