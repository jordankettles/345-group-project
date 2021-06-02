package com.poemgen.mockspire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.poemgen.mockspire.databinding.FragmentPoemLogListBinding
import com.poemgen.mockspire.poemgenerator.record.Garden

/**
 * Scrollable log display to place in other fragments.
 */
class PoemLogListFragment : Fragment() {

    // Data/View binding boilerplate
    private var binding: FragmentPoemLogListBinding? = null

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
            poemLogListFragment = this@PoemLogListFragment
        }

        // Bind Garden log to recycler view
        val rvPoemList = binding?.rvPoemList

        val adapter = PoemLogAdapter(Garden.seeds)
        rvPoemList?.adapter = adapter
        rvPoemList?.layoutManager = LinearLayoutManager(this.context)

    }


}