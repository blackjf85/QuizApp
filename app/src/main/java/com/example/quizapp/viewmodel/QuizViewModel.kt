package com.example.quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {

    private var _numCorrect: MutableLiveData<Int> = MutableLiveData(0)
    val numCorrect: LiveData<Int> get() = _numCorrect

    fun addCorrect(num: Int){
        val numCorrect = numCorrect.value
    }

}