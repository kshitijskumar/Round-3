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
import com.example.trellandroid.utils.Result
import com.example.trellandroid.utils.UtilsFunction.showToast
import com.example.trellandroid.viewmodel.MainViewModel

class AllPostsFragment : Fragment() {

    private var _binding : FragmentAllPostsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AllPostsAdapter

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAllPostsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupViews()
        observeValues()
    }

    private fun setupViewModel() {
        viewModel = MainViewModel.provideMainViewModel(this)
        viewModel.getAllVlogs()
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
    }

    private fun setupViews() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getAllVlogs()
        }
    }

    private fun observeValues() {
        viewModel.allPosts.observe(viewLifecycleOwner) {
            when(it) {
                is Result.Success -> {
                    stopRefresh()
                    adapter.submitList(it.data)
                }

                is Result.Error -> {
                    stopRefresh()
                    requireContext().showToast(it.errorMsg)
                }

                is Result.Loading -> {
                    showRefresh()
                }
            }
        }
    }

    private fun showRefresh() {
        binding.swipeRefresh.isRefreshing = true
    }

    private fun stopRefresh() {
        binding.swipeRefresh.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}