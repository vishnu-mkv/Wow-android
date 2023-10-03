package com.example.wow.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.wow.workers.MyWorker
import com.example.wow.workers.MyWorker.Companion.Progress

@Composable
fun WorkerDemo() {
    val myWorkRequest: WorkRequest =
        OneTimeWorkRequestBuilder<MyWorker>()
            .build()

    val context = LocalContext.current

    var progressValue by remember {
        mutableStateOf(0f)
    }

    var showLoading by remember {
        mutableStateOf(false)
    }

    val assignWork = {
        WorkManager
            .getInstance(context)
            .enqueue(myWorkRequest)
        progressValue = 0f
        showLoading = true
    }

    WorkManager.getInstance(context)
        // requestId is the WorkRequest id
        .getWorkInfoByIdLiveData(myWorkRequest.id)
        .observeForever { workInfo: WorkInfo? ->
            if (workInfo != null) {
                val progress = workInfo.progress
                val value = progress.getInt(Progress, 0).toFloat() / 100
                // Do something with progress information
                progressValue = value
                if (value >= 1) {
                    showLoading = false
                }
            }
        }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = { assignWork() }) {
            Text(text = "Do the Work")
        }

        if (showLoading) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    modifier = Modifier.weight(1F),
                    progress = progressValue
                )
                Text(text = "${String.format("%.1f", progressValue*100)} %")
            }

        }
    }
}