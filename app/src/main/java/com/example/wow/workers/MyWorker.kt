package com.example.wow.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class MyWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {
        companion object {
            const val Progress = "Progress"
            private const val delayDuration = 20L
        }

        override suspend fun doWork(): Result {
            for(i in 1..100) run {
                val update = workDataOf(Progress to i)
                setProgress(update)
                delay(delayDuration)
            }
            return Result.success()
        }
}