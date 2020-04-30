package com.example.seaworldkotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.seaworldkotlin.R
import com.example.seaworldkotlin.presenters.IMainView
import com.example.seaworldkotlin.presenters.MainPresenter
import com.example.seaworldkotlin.use_cases.dto.AnimalStepData
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MvpActivity(), IMainView {

    private lateinit var playingWorldView: PlayingWorldView

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reset_game_button.setOnClickListener { presenter.onReset() }
        
        test(5)
    }

    private fun test(param : Int){
        test(param)
    }

    override fun initField(fieldSize: Pair<Int, Int>, animalsList: List<AnimalStepData>) {
        Log.d(TAG, "initField")
        playingWorldView = PlayingWorldView(this)
        playingWorldView.fieldSizeX = fieldSize.first
        playingWorldView.fieldSizeY = fieldSize.second
        playingWorldView.animalsList = animalsList
        playingWorldView.setOnTouchListener { view, motionEvent ->
            val action = motionEvent.action
            if (action == MotionEvent.ACTION_DOWN) {
                presenter.onTouch()
            }
            true
        }
        playing_world_view_frame.addView(playingWorldView)
    }

    override fun drawWorld(animalsList: List<AnimalStepData>) {
        playingWorldView.animalsList = animalsList
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    companion object {

        private const val TAG = "MainActivity"
    }
}
