package com.example.trellandroid.ui.allposts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trellandroid.data.responses.VlogResponse
import com.example.trellandroid.databinding.HolderSinglePostBinding

class AllPostsAdapter(
    private val itemClick : (vlogId: Long) -> Unit
) : ListAdapter<VlogResponse, AllPostsAdapter.VlogViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<VlogResponse>() {
            override fun areItemsTheSame(oldItem: VlogResponse, newItem: VlogResponse): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: VlogResponse, newItem: VlogResponse): Boolean {
                return oldItem.vlogId == newItem.vlogId
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VlogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HolderSinglePostBinding.inflate(inflater, parent, false)
        return VlogViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: VlogViewHolder, position: Int) {
        val item = getItem(position)
        holder.setDataToHolder(item)
    }

    class VlogViewHolder(private val binding : HolderSinglePostBinding, private val itemClick: (vlogId: Long) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun setDataToHolder(item: VlogResponse) {
            binding.apply {
                tvName.text = item.creatorName
            }
            binding.root.setOnClickListener {
                item.vlogId?.let { id -> itemClick(id) }
            }
        }
    }
}