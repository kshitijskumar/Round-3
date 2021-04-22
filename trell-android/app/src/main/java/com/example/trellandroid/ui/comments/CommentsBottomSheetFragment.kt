package com.example.trellandroid.ui.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trellandroid.databinding.BottomSheetFragmentCommentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CommentsBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding : BottomSheetFragmentCommentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = BottomSheetFragmentCommentBinding.inflate(inflater)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}