package com.example.seaworldkotlin

import android.app.Application
import com.example.seaworldkotlin.di.DaggerModelsComponent
import com.example.seaworldkotlin.di.ModelsComponent
import com.example.seaworldkotlin.di.WorldModule
import com.example.seaworldkotlin.utils.WORLD_SIZE_X
import com.example.seaworldkotlin.utils.WORLD_SIZE_Y

class SeaWorldApp : Application() {
    override fun onCreate() {
        super.onCreate()
        modelsComponent = buildModelsComponent()
    }

    private fun buildModelsComponent(): ModelsComponent {
        return DaggerModelsComponent
            .builder()
            .worldModule(WorldModule(WORLD_SIZE_X, WORLD_SIZE_Y))
            .build()
    }

    companion object {

        var modelsComponent: ModelsComponent? = null
    }
}