package com.example.trellandroid.ui.allposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trellandroid.R
import com.example.trellandroid.databinding.FragmentAllPostsBinding
import com.example.trellandroid.utils.DummyResponses

class AllPostsFragment : Fragment() {

    private var _binding : FragmentAllPostsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AllPostsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAllPostsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = AllPostsAdapter {
            findNavController().navigate(
                R.id.action_allPostsFragment_to_postDetailFragment,
                bundleOf("vlogId" to it)
                )
        }

        binding.rvAllPosts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@AllPostsFragment.adapter
        }
        adapter.submitList(DummyResponses.dummyPosts)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}