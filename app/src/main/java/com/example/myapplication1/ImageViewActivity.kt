package com.example.myapplication1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_imageview.*

class ImageViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imageview)
        button_new.setOnClickListener {
            var str = editText_new.text
            println(str)
            textView_new.text = str
            imageView_new.setImageResource(R.drawable.picture2)
        }
    }
}