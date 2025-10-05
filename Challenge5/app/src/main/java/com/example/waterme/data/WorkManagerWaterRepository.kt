/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.waterme.data

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.waterme.model.Plant
import com.example.waterme.worker.WaterReminderWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf // <-- ADICIONE ESTE IMPORT
import java.util.concurrent.TimeUnit

class WorkManagerWaterRepository(context: Context) : WaterRepository {

    private val workManager = WorkManager.getInstance(context)

    override val plants: Flow<List<Plant>> = flowOf(DataSource.plants)

    override fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
        plantName: String
    ) {
        // Cria os dados de entrada para o Worker, passando o nome da planta.
        val data = Data.Builder()
            .putString(WaterReminderWorker.KEY_PLANT_NAME, plantName)
            .build()

        // Cria a requisição de trabalho PERIÓDICA.
        val waterRequest =
            PeriodicWorkRequestBuilder<WaterReminderWorker>(duration, unit)
                .setInputData(data)
                .build()

        // Enfileira o trabalho como um trabalho periódico único.
        workManager.enqueueUniquePeriodicWork(
            plantName,
            ExistingPeriodicWorkPolicy.KEEP,
            waterRequest
        )
    }
}