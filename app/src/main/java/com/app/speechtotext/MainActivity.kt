package com.app.speechtotext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startSpeechButton = findViewById<Button>(R.id.btnSpeech)
        val textResult = findViewById<TextView>(R.id.tvResult)

        val speechRecognitionLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data: Intent? = result.data
                    val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

                    val recognizedText = results?.get(0)

                    textResult.text=recognizedText
                }
            }


        startSpeechButton.setOnClickListener {
            val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            speechRecognizerIntent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

            speechRecognitionLauncher.launch(speechRecognizerIntent)

        }
    }
}