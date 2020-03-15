package com.yundin.uienergymanagement

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat


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

        findViewById<Button>(R.id.button_3).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        val root = findViewById<ConstraintLayout>(R.id.root)
        var i = 0

        findViewById<Button>(R.id.button_4).setOnClickListener {
            val view = View(this)
            view.setBackgroundColor(Color.BLUE)

            val lp = ConstraintLayout.LayoutParams(100, 100)
            lp.setMargins(0, 110 * i++, 0, 0)
            view.layoutParams = lp
            view.id = ViewCompat.generateViewId()

            root.addView(view)

            val set = ConstraintSet()
            set.clone(root)
            set.connect(view.id, ConstraintSet.TOP, root.id, ConstraintSet.TOP)
            set.applyTo(root)
        }
    }
}
