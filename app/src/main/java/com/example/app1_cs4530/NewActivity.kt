package com.example.app1_cs4530

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_activity)
        val First = intent.getStringExtra("First")
        val Middle = intent.getStringExtra("Middle")
        val Last = intent.getStringExtra("Last")

        val textView = findViewById<TextView>(R.id.textView)
        textView.setText(First + " " + Last + " has logged in!")
    }
}