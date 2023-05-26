package com.vostrikov.mydenet.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.vostrikov.mydenet.data.model.NodeModel
import com.vostrikov.mydenet.databinding.ChildsItemBinding

class ChildAdapter() :
    ListAdapter<NodeModel, ChildsViewHolder>(ChildsDiffCallback()) {

    var onChildItemClickListener: ((NodeModel) -> Unit)? = null

    var onChildItemLongClickListener: ((NodeModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildsViewHolder {
        val itemBinding =
            ChildsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ChildsViewHolder, position: Int) {
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