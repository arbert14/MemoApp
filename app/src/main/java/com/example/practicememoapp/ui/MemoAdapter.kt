package com.example.practicememoapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.practicememoapp.data.entites.Memo
import com.example.practicememoapp.databinding.ItemMemoBinding

class MemoAdapter: ListAdapter<Memo, MemoAdapter.MemoViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Memo>() {
            override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem.idx == newItem.idx
            }

            override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MemoViewHolder(private val binding: ItemMemoBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(memo: Memo) {
            binding.titleTv.text = memo.memoTitle
            binding.contentTv.text = memo.memoContent
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        return MemoViewHolder(
            ItemMemoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}