package com.example.livedata

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var textv1 : TextView
    private lateinit var textv2 : TextView

    private lateinit var myViewModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textv1 = findViewById(R.id.testText1)
        textv2 = findViewById(R.id.testText2)
        myViewModel = ViewModelProvider(this, MainFactory(application, "FINISH"))[MainViewModel::class.java]

    }

    override fun onStart() {
        super.onStart()
        myViewModel.liveData.observe(this) {
            textv2.text = it
        }
        object : CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                textv1.text = (p0/1000).toString()
            }

            override fun onFinish() {
                textv1.text = "finish"
            }

        }.start()
    }
}