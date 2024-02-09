package com.example.broadcastrs

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat

class Notification(val context:Context) {
    private val notification_channel="Notification"
    private val notification_id="1000"
    private val notificationManager=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private var requast=0
    fun initNOtification(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            val channel=NotificationChannel(notification_id,notification_channel,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        val customview=RemoteViews(context.packageName,R.layout.notilay)
        customview.setOnClickPendingIntent(R.id.btn1,getbroadcast("btn1 call"))
        customview.setOnClickPendingIntent(R.id.btn2,getbroadcast("btn2 call"))
        customview.setOnClickPendingIntent(R.id.btn3,getbroadcast("btn3 call"))
        customview.setTextViewText(R.id.text2,"body")
        customview.setTextViewText(R.id.text1,"titale")
        val Buillder=NotificationCompat.Builder(context,notification_id)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContent(customview)
        val notification=Buillder.build()
    notificationManager.notify(notification_id.toInt(),notification)
    }
    private fun getbroadcast(msg:String):PendingIntent
    {
        val intent=Intent(context,Notificationrecevier::class.java).apply {
            action="BTN_ACTION"
            putExtra("msg",msg)
        }
        requast++
        return PendingIntent.getBroadcast(context,requast,intent,PendingIntent.FLAG_UPDATE_CURRENT)
    }
}