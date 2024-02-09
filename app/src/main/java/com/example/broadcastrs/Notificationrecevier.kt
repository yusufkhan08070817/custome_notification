package com.example.broadcastrs

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Notificationrecevier:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action=="BTN_ACTION")
        {
            val msg=intent.getStringExtra("msg")
            Toast.makeText(context, "$msg", Toast.LENGTH_SHORT).show()
        }
    }
}