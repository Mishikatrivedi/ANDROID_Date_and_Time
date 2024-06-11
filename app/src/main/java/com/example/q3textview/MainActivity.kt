package com.example.q3textview

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.activity.ComponentActivity
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Timer
import java.util.TimerTask

class MainActivity : ComponentActivity() {
    private lateinit var tt: TextView // Declare the TextView variable
    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        // tv -> id of textView in layout
        tt = findViewById(R.id.tv); // Initialize the TextView

        //Schedule a task to update time every second
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val c = Calendar.getInstance().time;
                println("Current time => $c ");
                val dfDate = SimpleDateFormat("dd-MMM-yyyy");
                val dfTime = SimpleDateFormat("HH:mm:ss")
                val formattedDate = dfDate.format(c);
                val formattedTime = dfTime.format(c);
                val fullDateTime = "$formattedDate $formattedTime" //combine date and time
                //update TextView on UI thread
                runOnUiThread {
                    tt.setText(fullDateTime)
                    tt.textSize = 24f // Set text size (default unit is sp)
                    tt.setTextColor(resources.getColor(R.color.purple_700))
                    // Alternatively, use ContextCompat.getColor(this, R.color.colorPrimaryDark)
                }
            }
        }, 0, 1000)//update every 1000 milliseconds(1 second)
    }

    override fun onDestroy() {
        super.onDestroy()
        //clean up the timer when activity is destroyed
        timer.cancel()
    }
}

