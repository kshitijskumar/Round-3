package com.example.trellandroid.ui.comments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trellandroid.databinding.BottomSheetFragmentCommentBinding
import com.example.trellandroid.utils.Result
import com.example.trellandroid.utils.UtilsFunction.showToast
import com.example.trellandroid.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CommentsBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding : BottomSheetFragmentCommentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : MainViewModel

    private var vlogId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vlogId = arguments?.getLong("vlogId")
        setupViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = BottomSheetFragmentCommentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeValues()
    }

    private fun setupViewModel() {
        viewModel = MainViewModel.provideMainViewModel(this)
        vlogId?.let {
            viewModel.getAllComments(it)
        }
    }

    private fun setupViews() {
        binding.btnSend.setOnClickListener {
            val commentText = binding.etComment.text.toString()
            if (commentText.isNotEmpty()) {
                vlogId?.let {
                    viewModel.postComment(it, commentText)
                    binding.etComment.setText("")
                }
                //in real app this part of the code would not be here.
                //this is only for prototype
                binding.tvNoCommentMsg.visibility = View.GONE
                binding.llSingleComment.visibility = View.VISIBLE
                binding.tvComment.text = commentText

            }
        }
    }

    private fun observeValues() {
        viewModel.comments.observe(viewLifecycleOwner) {
            when(it) {
                is Result.Success -> {
                    if (it.data.isNullOrEmpty()) {
                        binding.tvNoCommentMsg.visibility = View.VISIBLE
                    }else {
                        //we'll post this list of comments to the recyclerview.
                        //for simplicity we are not having any recycler view and only getting dummy values.
                        // ie. the comments list is empty.
                        Log.d("CommentBottomSheet", "Post this list to recyclerview")
                    }
                }
                is Result.Error -> {
                    requireContext().showToast(it.errorMsg)
                }
                is Result.Loading -> {
                    Log.d("CommentBottomSheet", "loading")
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}