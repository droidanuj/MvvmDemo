package com.fortytwo.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.fortytwo.R
import com.fortytwo.ui.home.Home

private const val DELAY: Long = 1000

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }

    private fun init() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToPermissions()
        }, DELAY)
    }

    private fun navigateToPermissions() {
        startActivity(Intent(this, Home::class.java))
        finish()
    }
}