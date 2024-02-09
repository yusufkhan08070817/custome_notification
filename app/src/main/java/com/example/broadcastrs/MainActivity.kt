package com.example.broadcastrs


import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
lateinit var Notification:Notification
    val permision_code=11
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val send: Button =findViewById(R.id.send)
        Notification= Notification(this)
        send.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),permision_code)
            }else{
Notification.initNOtification()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode)
        {
            permision_code ->{
                if ((grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED)){
                    Toast.makeText(this, "PERMISSION_GRANTED", Toast.LENGTH_SHORT).show()
                    Notification.initNOtification()
                }
            }
        }
    }
}