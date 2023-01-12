package com.eou.gameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Author: Emmanuel O. Uduma
 * This class is the main class and controls the application
 * it also shows the content to be viewed
 * this is possible vis its layout xml which uses the navigation
 * host container which has the nav_graph which controls navigation between fragments
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}