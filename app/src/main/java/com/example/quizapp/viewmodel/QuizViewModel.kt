package com.example.quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {

    private var _numCorrect: MutableLiveData<Int> = MutableLiveData(0)
    val numCorrect: LiveData<Int> get() = _numCorrect

    private var _question: MutableLiveData<String> = MutableLiveData("")
    val question: LiveData<String> get() = _question

    private var _answer: MutableLiveData<Boolean> = MutableLiveData(true)
    val answer: LiveData<Boolean> get() = _answer

    fun addCorrect(num: Int){
        _numCorrect.value = num
    }

    fun getQuestions(index: Int){

        val quizQuestions = mutableListOf(
            "Roses are red.",
            "Violets are purple.",
            "The sky is green.",
            "Kotlin is fun.",
            "Programmers are wizards.")

        _question.value = quizQuestions[index]
    }

    fun getAnswers(index: Int){

        val quizAnswers = mutableListOf(true, false, false, true, true)

        _answer.value = quizAnswers[index]
    }

}