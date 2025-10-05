package com.example.waterme.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.waterme.R

class WaterReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    companion object {
        const val KEY_PLANT_NAME = "plant_name"
    }

    override suspend fun doWork(): Result {
        val plantName = inputData.getString(KEY_PLANT_NAME)

        makePlantReminderNotification(
            applicationContext.resources.getString(R.string.notification_text, plantName),
            applicationContext
        )

        return Result.success()
    }
}