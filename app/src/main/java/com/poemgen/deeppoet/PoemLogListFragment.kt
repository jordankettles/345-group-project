package com.poemgen.deeppoet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.poemgen.deeppoet.databinding.FragmentPoemLogListBinding
import com.poemgen.deeppoet.poemgenerator.record.Garden

/**
 * Container for recyclerview. Insert into activity or other fragments to display contents of Garden.
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