package com.example.seaworldkotlin

import android.app.Application
import com.example.seaworldkotlin.di.DaggerModelsComponent
import com.example.seaworldkotlin.di.ModelsComponent
import com.example.seaworldkotlin.di.WorldModule
import com.example.seaworldkotlin.utils.numOfColumns
import com.example.seaworldkotlin.utils.numOfRows

class SeaWorldApp : Application() {
    override fun onCreate() {
        super.onCreate()
        modelsComponent = buildModelsComponent()
    }

    private fun buildModelsComponent(): ModelsComponent {
        return DaggerModelsComponent
            .builder()
            .worldModule(WorldModule(numOfColumns, numOfRows))
            .build()
    }

    companion object {

        var modelsComponent: ModelsComponent? = null
    }
}