package com.example.seaworldkotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.seaworldkotlin.R
import com.example.seaworldkotlin.presenters.IMainView
import com.example.seaworldkotlin.presenters.MainPresenter
import com.example.seaworldkotlin.use_cases.dto.AnimalStepData
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MvpActivity(), IMainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reset_game_button.setOnClickListener({ presenter.onReset() })

        playing_world_view.setOnTouchListener { _, motionEvent ->
            val action = motionEvent.action
            if (action == MotionEvent.ACTION_DOWN) {
                presenter.onTouch()
            }
            true
        }
    }

    override fun onPause() {
        super.onPause()
        playing_world_view.stopGame()
    }

    override fun initField(fieldSizeX: Int, fieldSizeY: Int) {
        playing_world_view.fieldSizeX = fieldSizeX
        playing_world_view.fieldSizeY = fieldSizeY
    }

    override fun drawWorld(animalsList: List<AnimalStepData>) {
        playing_world_view.animalsList = animalsList
    }

    companion object {

        private val TAG = "MainActivity"
    }
}
