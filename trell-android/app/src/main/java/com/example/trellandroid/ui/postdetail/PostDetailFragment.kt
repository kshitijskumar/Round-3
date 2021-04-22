package com.example.trellandroid.ui.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.trellandroid.R
import com.example.trellandroid.data.responses.VlogResponse
import com.example.trellandroid.databinding.FragmentPostDetailBinding

class PostDetailFragment : Fragment() {

    private var _binding : FragmentPostDetailBinding? = null
    private val binding get() = _binding!!
    private var vlogId : Long? = null
    private var userId : Long? = null

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
        binding.ivUser.setOnClickListener {
            //to be removed
            userId = 0L
            userId?.let {
                findNavController().navigate(
                    R.id.action_postDetailFragment_to_profileFragment,
                    bundleOf("userId" to userId)
                    )
            }
        }
    }

    private fun showVlogDetails(vlog: VlogResponse) {
        binding.apply {
            tvTitle.text = vlog.title
        }
        Glide.with(requireContext())
            .load(vlog.creatorImgUrl)
            .circleCrop()
            .placeholder(R.drawable.bg_circle_img)
            .into(binding.ivUser)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}