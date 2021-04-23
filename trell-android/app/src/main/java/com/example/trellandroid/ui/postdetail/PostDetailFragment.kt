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
import com.example.trellandroid.utils.Result
import com.example.trellandroid.utils.UtilsFunction.showToast
import com.example.trellandroid.viewmodel.MainViewModel

class PostDetailFragment : Fragment() {

    private var _binding : FragmentPostDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewmodel: MainViewModel

    private var vlogId : Long? = null
    private var userId : Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vlogId = arguments?.getLong("vlogId")
        setupViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPostDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeValues()
    }

    private fun setupViewModel() {
        viewmodel = MainViewModel.provideMainViewModel(this)
        vlogId?.let {
            viewmodel.getVlogDetails(it)
        }
    }

    private fun setupViews() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.ivUser.setOnClickListener {
            userId?.let {
                findNavController().navigate(
                    R.id.action_postDetailFragment_to_profileFragment,
                    bundleOf(
                            "userId" to userId,
                            "isInterest" to true,
                            "vlogId" to vlogId
                            )
                    )
            }
        }

        binding.cbLike.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                vlogId?.let {
                    viewmodel.likeVlog(it)
                }
            }
        }

        binding.btnComment.setOnClickListener {
            vlogId?.let {
                findNavController().navigate(
                        R.id.action_postDetailFragment_to_commentsBottomSheetFragment,
                        bundleOf(
                                "vlogId" to vlogId
                            )
                        )
            }
        }

        binding.btnSave.setOnClickListener {
            vlogId?.let {
                viewmodel.saveVlog(it)
                requireContext().showToast("Saved")
            }
        }
    }

    private fun observeValues() {
        viewmodel.postDetails.observe(viewLifecycleOwner) {
            when(it) {
                is Result.Success -> {
                    hideLoading()
                    showVlogDetails(it.data)
                }
                is Result.Error -> {
                    hideLoading()
                    requireContext().showToast(it.errorMsg)
                }
                is Result.Loading -> {
                    showLoading()
                }
            }
        }
    }

    private fun showVlogDetails(vlog: VlogResponse) {
        userId = vlog.creatorId
        binding.apply {
            tvTitle.text = vlog.title
        }
        Glide.with(requireContext())
            .load(vlog.creatorImgUrl)
            .circleCrop()
            .placeholder(R.drawable.bg_circle_img)
            .into(binding.ivUser)

        Glide.with(requireContext())
                .load(vlog.vlogUrl)
                .placeholder(R.drawable.ic_image_holder)
                .into(binding.ivPost)
    }

    private fun showLoading() {
        binding.apply {
            cbPlayPause.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun hideLoading() {
        binding.apply {
            cbPlayPause.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}