package com.poemgen.mockspire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.poemgen.mockspire.databinding.FragmentPoemMainBinding
import com.poemgen.mockspire.model.PoemMainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [PoemMainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PoemMainFragment : Fragment() {

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

//        return inflater.inflate(R.layout.fragment_poem_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            poemMainFragment = this@PoemMainFragment
        }
    }

    fun submitPrompt(){

        val routine = CoroutineScope(IO).launch{
            sharedViewModel.submitPrompt(binding?.promptField?.text.toString())

        }

        CoroutineScope(Main).launch {
            routine
        }

    }




//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment PoemMainFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            PoemMainFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}