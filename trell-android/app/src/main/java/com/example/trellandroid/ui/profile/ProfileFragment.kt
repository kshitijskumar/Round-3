package com.example.trellandroid.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.trellandroid.R
import com.example.trellandroid.data.responses.UserResponse
import com.example.trellandroid.databinding.FragmentProfileBinding
import com.example.trellandroid.ui.allposts.AllPostsAdapter
import com.example.trellandroid.utils.DummyResponses

class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var userId: Long? = null

    private lateinit var adapter: AllPostsAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userId = arguments?.getLong("userId")

        //to be change to observing
        showProfile(DummyResponses.dummyUserResponse)
    }

    private fun showProfile(user: UserResponse) {
        binding.apply {
            tvName.text = user.name
            tvUsername.text = user.username
            tvTotalPosts.text = user.totalPosts.toString()
            tvFollowers.text = user.followers.toString()
            tvFollowing.text = user.following.toString()
        }

        Glide.with(requireContext())
            .load(user.userImg)
            .placeholder(R.drawable.bg_circle_img)
            .circleCrop()
            .into(binding.ivProfileImg)

        adapter = AllPostsAdapter {
            //click listener of the posts from user
        }

        binding.rvUserPost.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@ProfileFragment.adapter
        }

        //this is the dummy response of the user's post
        adapter.submitList(DummyResponses.dummyUserPosts)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}