package com.poemgen.mockspire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.poemgen.mockspire.databinding.FragmentPoemLogListBinding
import com.poemgen.mockspire.model.PoemMainViewModel
import com.poemgen.mockspire.poemgenerator.record.Garden

/**
 * A simple [Fragment] subclass.
 * Use the [PoemLogListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PoemLogListFragment : Fragment() {

    private var binding: FragmentPoemLogListBinding? = null
    private val sharedViewModel: PoemMainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentPoemLogListBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            poemLogListFragment = this@PoemLogListFragment
        }

        val rvPoemList = binding?.rvPoemList

//        sharedViewModel.addDummyPoems()
//        val list = sharedViewModel.poemList.value

//        Garden.addDummyPoems()

        val adapter = PoemLogAdapter(Garden.seeds)
        rvPoemList?.adapter = adapter
        rvPoemList?.layoutManager = LinearLayoutManager(this.context)

        // record this value before making any changes to the existing list

    }


}