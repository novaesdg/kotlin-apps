package com.example.bluromatic.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.bluromatic.KEY_BLUR_LEVEL
import com.example.bluromatic.KEY_IMAGE_URI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageProcessingWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    private val TAG = "ImageProcessingWorker"

    override suspend fun doWork(): Result {
        makeStatusNotification("Aplicando desfoque na imagem...", applicationContext)

        return withContext(Dispatchers.IO) {
            return@withContext try {
                val resourceUri = inputData.getString(KEY_IMAGE_URI)
                val blurLevel = inputData.getInt(KEY_BLUR_LEVEL, 1)

                if (resourceUri.isNullOrEmpty()) {
                    Log.e(TAG, "URI da imagem inválida")
                    throw IllegalArgumentException("URI da imagem inválida")
                }

                val resolver = applicationContext.contentResolver

                val picture = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri))
                )

                val outputBitmap = blurBitmap(picture, blurLevel)

                val outputUri = writeBitmapToFile(applicationContext, outputBitmap)

                val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())

                Log.d(TAG, "Trabalho de desfoque finalizado com sucesso.")
                Result.success(outputData)

            } catch (throwable: Throwable) {
                Log.e(TAG, "Erro ao aplicar o desfoque", throwable)
                Result.failure()
            }
        }
    }
}