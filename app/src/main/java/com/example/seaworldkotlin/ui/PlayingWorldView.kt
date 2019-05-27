package com.example.seaworldkotlin.ui

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.seaworldkotlin.entities.Animal
import com.example.seaworldkotlin.use_cases.dto.AnimalStepData
import com.example.seaworldkotlin.utils.WORLD_SIZE_X
import com.example.seaworldkotlin.utils.WORLD_SIZE_Y
import rx.Completable
import rx.Subscription
import rx.schedulers.Schedulers


class PlayingWorldView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    var fieldSizeX = 0
    var fieldSizeY = 0

    private var screenWidth = 0
    private var screenHeight = 0
    private var squareWidth = 0F
    private var squareHeight = 0F

    private val orcaBmp = BitmapFactory.decodeResource(resources, Animal.Companion.Species.ORCA.pngId)
    private val penguinBmp = BitmapFactory.decodeResource(resources, Animal.Companion.Species.TUX.pngId)

    private val backgroundPaint = Paint()
    private val linePaint = Paint()

    private var textSize = 0

    lateinit var animalsList: List<AnimalStepData>

    var drawWorldFlag = false

    private var drawWorldSubscription: Subscription? = null

    init {
        holder.addCallback(this)
        backgroundPaint.color = Color.WHITE
        linePaint.color = Color.BLACK
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        screenWidth = w
        screenHeight = h
        squareWidth = (screenWidth / WORLD_SIZE_X).toFloat()
        squareHeight = ((screenHeight - textSize) / WORLD_SIZE_Y).toFloat()
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
        Log.d(TAG, "surfaceCreated")
        drawWorld(p0!!)
    }

    private fun drawWorld(holder: SurfaceHolder) {
        var canvas: Canvas? = null

        drawWorldSubscription = Completable.fromAction {
            drawWorldFlag = true
            while (drawWorldFlag) {
                try {
                    canvas = holder.lockCanvas(null)

                    synchronized(holder) {
                        drawLines(canvas!!)
                        drawAnimals(canvas!!)
                    }
                } finally {
                    if (canvas != null) {
                        holder.unlockCanvasAndPost(canvas)
                    }
                }
            }
        }
            .subscribeOn(Schedulers.newThread())
            .subscribe()
    }

    private fun drawLines(canvas: Canvas) {
        canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), backgroundPaint)

        //draw lines
        for (i in 0..fieldSizeX) {
            canvas.drawLine(i * squareWidth, 0f, i * squareWidth, (screenHeight - textSize).toFloat(), linePaint)
        }
        for (i in 0..fieldSizeY) {
            canvas.drawLine(0f, i * squareHeight, screenWidth.toFloat(), i * squareHeight, linePaint)
        }
    }

    private fun drawAnimals(canvas: Canvas) {
        for (animal in animalsList) {
            drawAnimal(canvas, animal)
        }
    }

    private fun drawAnimal(canvas: Canvas, animalStepData: AnimalStepData) {
        var scaleFactor = 1f
        if (animalStepData.age < BMP_SCALE) {
            scaleFactor = (1f + animalStepData.age % BMP_SCALE) / BMP_SCALE
        }

        val bmpWidth = (scaleFactor * squareWidth).toInt()
        val bmpHeight = (scaleFactor * squareHeight).toInt()

        //draw animals
        val bmp = when (animalStepData.species) {
            Animal.Companion.Species.ORCA -> orcaBmp
            Animal.Companion.Species.TUX -> penguinBmp
        }
        canvas.drawBitmap(
            Bitmap.createScaledBitmap(bmp, bmpWidth, bmpHeight, false),
            squareWidth * animalStepData.pos.first + (0.5 * (squareWidth - bmpWidth)).toInt(),
            squareHeight * animalStepData.pos.second + (0.5 * (squareHeight - bmpHeight)).toInt(), null
        )
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
        Log.d(TAG, "surfaceChanged")
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        Log.d(TAG, "surfaceDestroyed")
        stopGame()
    }

    private fun stopGame() {
        drawWorldFlag = false
        if (drawWorldSubscription != null) {
            drawWorldSubscription!!.unsubscribe()
        }
    }

    companion object {
        private val TAG = "PlayingWorldView"

        private val BMP_SCALE = 3
    }
}