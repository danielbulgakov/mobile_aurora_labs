package com.example.lab4

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import androidx.core.app.NotificationCompat

class NotificationHandle : Service() {

    companion object {
        private var clickerValue = 0
        private var firstTime = true
        private const val NOTIFICATION_CHANNEL_ID = "01"

        const val ACTION_START = 0
        const val ACTION_UPDATE = 1
        const val ACTION_RESET = 2
    }
    private val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent!!.getIntExtra("ACTION", 0)
        when (action) {
            ACTION_START -> onStartService()
            ACTION_UPDATE -> onUpdateService()
            ACTION_RESET -> onResetService()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun onResetService() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        clickerValue = 0

        notificationBuilder.setContentText(clickerValue.toString())
        notificationManager.notify(1, notificationBuilder.build())
    }

    private fun onStartService() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val addIntent = Intent(this, NotificationHandle::class.java)
        addIntent.putExtra("ACTION", ACTION_UPDATE)
        val addPendingIntent = PendingIntent.getService(
            this, 1, addIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val resetIntent = Intent(this, NotificationHandle::class.java)
        resetIntent.putExtra("ACTION", ACTION_RESET)
        val resetPendingIntent = PendingIntent.getService(
            this, 2, resetIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        @SuppressLint("WrongConstant") val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "My Notifications",
            NotificationManager.IMPORTANCE_MAX
        )

        notificationChannel.description = "Sample Channel description"
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationManager.createNotificationChannel(notificationChannel)

        notificationBuilder.setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Уведомление кликер")
            .setContentInfo("Information")
            .addAction(
                androidx.appcompat.R.drawable.abc_action_bar_item_background_material,
                "+",
                addPendingIntent
            )
            .addAction(
                androidx.appcompat.R.drawable.abc_action_bar_item_background_material,
                "x",
                resetPendingIntent
            )
        notificationBuilder.setContentText(clickerValue.toString())
        notificationManager.notify(1, notificationBuilder.build())
    }

    private fun onUpdateService() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        clickerValue++

        notificationBuilder.setContentText(clickerValue.toString())
        notificationManager.notify(1, notificationBuilder.build())
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}