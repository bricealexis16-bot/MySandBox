package com.example.mysandbox

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data: Intent? = result.data
        if (data != null) { // Check that we have data returned
            val sandquestion = data.getStringExtra("Question") // 'string1' needs to match the key we used when we put the string in the Intent
            val sandanswer = data.getStringExtra("Answer")

            // Log the value of the strings for easier debugging
            Log.i("MainActivity", "Question: $sandquestion")
            Log.i("MainActivity", "Answer: $sandanswer")

            findViewById<TextView>(R.id.SandBox_question).text=sandquestion
            findViewById<TextView>(R.id.SandBox_answer).text=sandanswer
        } else {
            Log.i("MainActivity", "Returned null data from MainActivity2")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val mysandquestion =findViewById<TextView>(R.id.SandBox_question)
        val mysandanswer =findViewById<TextView>(R.id.SandBox_answer)

        mysandquestion.setOnClickListener {
            mysandquestion.visibility = View.INVISIBLE
            mysandanswer.visibility = View.VISIBLE
        }

        findViewById<View>(R.id.firstbutton).setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            resultLauncher.launch(intent)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}