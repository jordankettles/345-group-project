package com.poemgen.mockspire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.poemgen.mockspire.databinding.FragmentLogDisplayBinding
import com.poemgen.mockspire.model.PoemMainViewModel

// Log display screen
class LogDisplayFragment : Fragment() {
    // Data/View Boilerplate
    private var binding: FragmentLogDisplayBinding? = null
    private val sharedViewModel: PoemMainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val fragmentBinding = FragmentLogDisplayBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            logDisplayFragment = this@LogDisplayFragment
        }
    }

    // Navigate back to main screen
    fun showMain() {
        findNavController().navigate(R.id.action_logDisplayFragment_to_poemMainFragment)
    }

}
