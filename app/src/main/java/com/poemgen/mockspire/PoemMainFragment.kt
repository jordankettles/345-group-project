package com.poemgen.mockspire

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.poemgen.mockspire.databinding.FragmentPoemMainBinding
import com.poemgen.mockspire.model.PoemMainViewModel
import kotlinx.coroutines.*

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


    val job = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + job)

    fun submitPrompt(){

        binding?.submitPromptButton?.isEnabled = false

        uiScope.launch(Dispatchers.IO) {
            // Update the poem text
            sharedViewModel.submitPrompt(binding?.promptField?.text.toString())

            //Generate spannable here
            val spannable = generateSpannable()

            withContext(Dispatchers.Main) {
                //Set text layout to spannable here.
                binding?.poemTextView?.text = spannable.toString()

                binding?.submitPromptButton?.isEnabled = true
            }
        }


    }

    fun generateSpannable(): SpannableString {
        val text = sharedViewModel.poemText.value.toString()

        val outputSpannable = SpannableString(text)

        val tempClickable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Log.d("Mock", "heyoo")
            }

        }
        outputSpannable.setSpan(tempClickable, 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        return outputSpannable
    }


    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
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