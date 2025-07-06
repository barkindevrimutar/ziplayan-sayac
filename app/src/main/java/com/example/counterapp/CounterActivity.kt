// CounterActivity.kt (Sayaç ekranı)
package com.example.counterapp

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CounterActivity : AppCompatActivity() {
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

       //Arayüz bileşenlerini tanımla
        val textViewCounter = findViewById<TextView>(R.id.textViewCounter)
        val buttonIncrease = findViewById<Button>(R.id.buttonIncrease)
        val buttonDecrease = findViewById<Button>(R.id.buttonDecrease)
        val buttonReset = findViewById<Button>(R.id.buttonReset)
        val mainLayout = findViewById<LinearLayout>(R.id.mainLayout)

        //SharedPreferences ile sayaç değerini sakla
        val prefs = getSharedPreferences("myPrefs", MODE_PRIVATE)
        counter = prefs.getInt("counterValue", 0)

       //Sayfa açıldığında mevcut değeri göster
        textViewCounter.text = counter.toString()
        updateTextColor(textViewCounter, counter)
        updateBackgroundColor(counter, mainLayout)

        //Animasyon nesnesini tanımlama
        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale)

        //Sayaç artırma işlemi
        buttonIncrease.setOnClickListener {
            counter++
            textViewCounter.text = counter.toString()
            textViewCounter.startAnimation(scaleAnimation)
            updateTextColor(textViewCounter, counter)
            updateBackgroundColor(counter, mainLayout)
            prefs.edit().putInt("counterValue", counter).apply()
        }

        //Sayaç azaltma işlemi
        buttonDecrease.setOnClickListener {
            counter--
            textViewCounter.text = counter.toString()
            textViewCounter.startAnimation(scaleAnimation)
            updateTextColor(textViewCounter, counter)
            updateBackgroundColor(counter, mainLayout)
            prefs.edit().putInt("counterValue", counter).apply()
        }

        //Sayaç sıfırlama işlemi
        buttonReset.setOnClickListener {
            counter = 0
            textViewCounter.text = counter.toString()
            textViewCounter.startAnimation(scaleAnimation)
            updateTextColor(textViewCounter, counter)
            updateBackgroundColor(counter, mainLayout)
            prefs.edit().putInt("counterValue", counter).apply()
        }
    }

   //Sayaç değerine göre yazı rengini ayarlayan fonksiyon

    private fun updateTextColor(textView: TextView, value: Int) {
        val color = when {
            value >= 20 -> android.R.color.holo_red_dark
            value >= 10 -> android.R.color.holo_blue_dark
            else -> android.R.color.black
        }
        textView.setTextColor(resources.getColor(color))
    }

    //Sayaç değerine göre arka plan rengini ayarlayan fonksiyon

    private fun updateBackgroundColor(value: Int, layout: LinearLayout) {
        val color = when {
            value >= 10 -> android.R.color.holo_red_light
            value >= 20 -> android.R.color.holo_purple
            value >= 30 -> android.R.color.holo_blue_light

            else -> android.R.color.white
        }
        layout.setBackgroundColor(resources.getColor(color))
    }
}
