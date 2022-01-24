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

            viewModel.getQuestions(questionNumber - 1)

             questionTv.text = viewModel.question.value.toString()
             submitBtn.text = "Next"
             submitBtn.setOnClickListener {

                 if(questionNumber <= 4){
                     viewModel.getAnswers(questionNumber - 1)
                     val trueOrFalse = viewModel.answer.value

                     if(trueOrFalse == true){
                         if(trueRb.isChecked){
                             numCorrect++
                         }
                     }else if(trueOrFalse == false){
                         if(falseRb.isChecked){
                             numCorrect++
                         }
                     }

                     questionNumber++

                     if(questionNumber == 5){
                         submitBtn.text = "Submit"
                     }

                     viewModel.getQuestions(questionNumber - 1)

                     questionTv.text = viewModel.question.value.toString()

                     answerRg.clearCheck()
                 }else{

                     viewModel.getAnswers(questionNumber - 1)
                     val trueOrFalse = viewModel.answer.value
                     if(trueOrFalse == true){
                            if(trueRb.isChecked){
                                numCorrect++
                            }
                        }else{
                            if(trueOrFalse == false){
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