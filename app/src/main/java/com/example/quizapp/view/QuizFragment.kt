package com.example.quizapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentQuizBinding
import com.example.quizapp.viewmodel.QuizViewModel

class QuizFragment: Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding: FragmentQuizBinding get() = _binding!!

    private lateinit var viewModel: QuizViewModel

    private fun getQuestions(index: Int): String{

        val quizQuestions = mutableListOf(
            "Roses are red.",
            "Violets are purple.",
            "The sky is green.",
            "Kotlin is fun.",
            "Programmers are wizards.")

        return quizQuestions[index]
    }

    private fun getAnswers(index: Int): Boolean{

        val quizAnswers = mutableListOf(true, false, false, true, true)

        return quizAnswers[index]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[QuizViewModel::class.java]
        with(binding) {
             var questionNumber = 1
             var numCorrect = 0
             questionTv.text = getQuestions(questionNumber - 1)
             submitBtn.text = "Next"
             submitBtn.setOnClickListener {

                 if(questionNumber <= 4){
                     val trueOrFalse = getAnswers(questionNumber - 1)

                     if(trueOrFalse){
                         if(trueRb.isChecked){
                             numCorrect++
                         }
                     }else if(!trueOrFalse){
                         if(falseRb.isChecked){
                             numCorrect++
                         }
                     }

                     questionNumber++

                     if(questionNumber == 5){
                         submitBtn.text = "Submit"
                     }

                     questionTv.text = getQuestions(questionNumber - 1)

                     answerRg.clearCheck()
                 }else{
                     val trueOrFalse = getAnswers(questionNumber - 1)
                     if(trueOrFalse){
                            if(trueRb.isChecked){
                                numCorrect++
                            }
                        }else{
                            if(!trueOrFalse){
                                numCorrect++
                            }
                        }

                     viewModel.addCorrect(numCorrect)
                     val bundle = Bundle()
                     parentFragmentManager.beginTransaction()
                         .replace(R.id.countFragment, ResultsFragment::class.java, bundle)
                         .addToBackStack(null)
                         .commit()
                 }
             }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}