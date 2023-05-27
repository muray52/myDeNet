package com.vostrikov.mydenet.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.vostrikov.mydenet.data.model.NodeModel

class ChildDiffCallback : DiffUtil.ItemCallback<NodeModel>() {
    override fun areItemsTheSame(oldItem: NodeModel, newItem: NodeModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NodeModel, newItem: NodeModel): Boolean {
        return oldItem == newItem
    }
}