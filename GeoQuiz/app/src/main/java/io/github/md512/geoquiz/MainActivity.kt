package io.github.md512.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

private const val TAG = "Лог MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var questionTextView: TextView
    private val questionBank = listOf(
            Question(R.string.question_australia, true),
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true))
    private var blockedQuestions = BooleanArray(questionBank.size)
    private var currentIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        backButton = findViewById(R.id.back_button)
        questionTextView = findViewById(R.id.question_text_view)

        nextButton.setOnClickListener { view: View ->
                currentIndex = (currentIndex + 1) % questionBank.size
                updateQuestion()
        }

        backButton.setOnClickListener { view: View ->
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
        }

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
            blockedQuestions[currentIndex] = true
            trueButton.setEnabled(false)
            falseButton.setEnabled(false)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            blockedQuestions[currentIndex] = true
            trueButton.setEnabled(false)
            falseButton.setEnabled(false)
        }

        questionTextView.setOnClickListener { view: View ->
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
        if(blockedQuestions[currentIndex]) {
            trueButton.setEnabled(false)
            falseButton.setEnabled(false)
        } else {
            trueButton.setEnabled(true)
            falseButton.setEnabled(true)
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId: Int
        if (userAnswer == correctAnswer) {
            messageResId  = R.string.correct_toast
            score++
        } else {
            messageResId = R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        if (currentIndex == questionBank.size - 1) {
            val gameResult = score * 100 / questionBank.size
            Toast.makeText(this, getResources().getString(R.string.show_result) + gameResult.toString(), Toast.LENGTH_LONG).show()
        }
    }

}