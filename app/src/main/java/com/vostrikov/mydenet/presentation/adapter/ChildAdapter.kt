package com.vostrikov.mydenet.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.vostrikov.mydenet.data.model.NodeModel
import com.vostrikov.mydenet.databinding.ChildsItemBinding

class ChildAdapter() :
    ListAdapter<NodeModel, ChildViewHolder>(ChildDiffCallback()) {

    var onChildItemClickListener: ((NodeModel) -> Unit)? = null

    var onChildItemLongClickListener: ((NodeModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val itemBinding =
            ChildsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val binding = holder.binding
        val childItem = getItem(position)

        binding.tvChildName.text = childItem.hash

        holder.itemView.setOnClickListener {
            onChildItemClickListener?.invoke(childItem)
        }
        holder.itemView.setOnLongClickListener {
            onChildItemLongClickListener?.invoke(childItem)
            true
        }
    }
}