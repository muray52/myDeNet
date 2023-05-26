package com.vostrikov.mydenet.data.model

data class NodeModel(
    val parent: NodeModel?,
    val childList: MutableList<NodeModel?>,
    val hash: String,
)