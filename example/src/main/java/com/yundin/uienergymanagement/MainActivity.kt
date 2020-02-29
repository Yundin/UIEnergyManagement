package com.yundin.uienergymanagement

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            @Suppress("DEPRECATION")
            fragmentManager.beginTransaction()
                .replace(R.id.container, FirstFragment())
                .commit()
        }

        findViewById<Button>(R.id.button_2).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SecondFragment())
                .commit()
        }
    }
}
