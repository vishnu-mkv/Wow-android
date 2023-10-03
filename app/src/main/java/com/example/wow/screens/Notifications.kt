package com.example.wow.screens

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.wow.SecondaryActivity

val CHANNEL_ID = "notify"
private fun createNotificationChannel(context:Context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is not in the Support Library.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "New Notification"
        val descriptionText = "This channel gives you new notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system.
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NotificationWork() {

    val context = LocalContext.current
    createNotificationChannel(context)

    // Create an explicit intent for an Activity in your app.
    val intent = Intent(context, SecondaryActivity()::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent,
        PendingIntent.FLAG_IMMUTABLE)


    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(android.R.drawable.stat_notify_chat)
        .setContentTitle("My notification")
        .setContentText("Much longer text that cannot fit one line...")
        .setStyle(NotificationCompat.BigTextStyle()
            .bigText("Much longer text that cannot fit one line..."))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent)

    val requiredPermission = Manifest.permission.POST_NOTIFICATIONS

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if(!it) println("Permission denied for notification request")
    }

    val showNotification = {
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define.
            if (ActivityCompat.checkSelfPermission(
                    context, requiredPermission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                println("Permission not provided")
                launcher.launch(requiredPermission)
                return@with
            }
            notify(123, builder.build())
        }
    }
    
    Button(onClick = { showNotification() }) {
        Text(text = "Show Notification")
    }
}