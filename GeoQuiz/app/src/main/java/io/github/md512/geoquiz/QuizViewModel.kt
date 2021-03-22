package io.github.md512.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "Лог QuizViewModel"

class QuizViewModel : ViewModel() {

    init {
        Log.d(TAG, "ViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }
}