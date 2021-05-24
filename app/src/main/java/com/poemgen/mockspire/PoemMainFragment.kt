package com.poemgen.mockspire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.poemgen.mockspire.databinding.FragmentPoemMainBinding
import com.poemgen.mockspire.model.PoemMainViewModel
import kotlinx.coroutines.*

/**
 * Main screen
 */
class PoemMainFragment : Fragment() {

    // Data/View boilerplate
    private var binding: FragmentPoemMainBinding? = null
    private val sharedViewModel: PoemMainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentPoemMainBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            poemMainFragment = this@PoemMainFragment
        }
    }


    // Coroutine to prevent UI freezing while waiting for poemgenerator
    val job = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + job)

    fun submitPrompt(){
        sharedViewModel.setReady(false)

        uiScope.launch(Dispatchers.IO) {
            sharedViewModel.submitPrompt(binding?.promptField?.text.toString())

            withContext(Dispatchers.Main) {
                sharedViewModel.setReady(true)
            }
        }
    }

    //Navigate to show log
    fun showLog() {
        findNavController().navigate(R.id.action_poemMainFragment_to_logDisplayFragment)
    }

    // Navigate to show help
    fun showHelp() {
        findNavController().navigate(R.id.action_poemMainFragment_to_helpFragment)
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

}