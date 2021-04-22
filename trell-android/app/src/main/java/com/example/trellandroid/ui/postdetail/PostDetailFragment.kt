package com.example.trellandroid.ui.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trellandroid.data.responses.VlogResponse
import com.example.trellandroid.databinding.FragmentPostDetailBinding

class PostDetailFragment : Fragment() {

    private var _binding : FragmentPostDetailBinding? = null
    private val binding get() = _binding!!
    private var vlogId : Long? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPostDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vlogId = arguments?.getLong("vlogId")

        setupViews()
    }

    private fun setupViews() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun showVlogDetails(vlog: VlogResponse) {
        binding.apply {
            tvTitle.text = vlog.title
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}