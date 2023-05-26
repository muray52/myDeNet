package com.vostrikov.mydenet.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.vostrikov.mydenet.data.model.NodeModel

class ChildsDiffCallback : DiffUtil.ItemCallback<NodeModel>() {
    override fun areItemsTheSame(oldItem: NodeModel, newItem: NodeModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NodeModel, newItem: NodeModel): Boolean {
        return oldItem == newItem &&
                oldItem.childList == newItem.childList
    }

}