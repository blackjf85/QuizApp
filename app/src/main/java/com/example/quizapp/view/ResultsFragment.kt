package com.example.quizapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentResultsBinding
import com.example.quizapp.viewmodel.QuizViewModel

class ResultsFragment: Fragment() {
    private var _binding: FragmentResultsBinding? = null
    private val binding: FragmentResultsBinding get() = _binding!!

    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[QuizViewModel::class.java]
        with(binding) {
            viewModel.numCorrect.observe(viewLifecycleOwner) { count ->

                val score: String = when(count){
                    5 -> "100%"
                    4 -> "80%"
                    3 -> "60%"
                    2 -> "40%"
                    1 -> "20%"
                    else -> "0%"
                }

                if(count >= 4){
                    resultsHeadingTv.text = "Passed"
                    resultTv.text = "Congratulations"
                    againBtn.text = "Again"
                }else{
                    resultsHeadingTv.text = "Failed"
                    resultTv.text = "Better Luck Next Time"
                    againBtn.text = "Try Again"
                }
                scoreTv.text = "You got $count out of 5 correct and got a score of $score."
            }

            againBtn.setOnClickListener {
                viewModel.addCorrect(0)
                val bundle = Bundle()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.countFragment, StartFragment::class.java, bundle)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}