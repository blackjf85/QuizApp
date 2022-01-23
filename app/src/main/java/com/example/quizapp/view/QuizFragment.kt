package com.example.quizapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[QuizViewModel::class.java]
        with(binding) {

            submitBtn.setOnClickListener {
                var correctCount = 0
                if(trueCb1.isChecked){
                    correctCount += 1
                }
                if(!trueCb2.isChecked){
                    correctCount += 1
                }
                if(!trueCb3.isChecked){
                    correctCount += 1
                }
                if(trueCb4.isChecked){
                    correctCount += 1
                }
                if(trueCb5.isChecked){
                    correctCount += 1
                }
                viewModel.addCorrect(correctCount)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}