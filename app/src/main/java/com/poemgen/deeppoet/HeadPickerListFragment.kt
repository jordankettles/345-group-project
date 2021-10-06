package com.poemgen.deeppoet

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.poemgen.deeppoet.databinding.FragmentHeadPickerListBinding
import com.poemgen.deeppoet.util.HeadCollection

/**
 * A fragment representing a list of Items.
 */
class HeadPickerListFragment : Fragment() {

    private var binding: FragmentHeadPickerListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentHeadPickerListBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            headPickerListFragment = this@HeadPickerListFragment
        }

        val rvHeadPickerList = binding?.rvHeadPickerList
        val adapter = HeadPickerItemAdapter(HeadCollection.heads)
        rvHeadPickerList?.adapter = adapter
        rvHeadPickerList?.layoutManager = LinearLayoutManager(this.context)
    }

}