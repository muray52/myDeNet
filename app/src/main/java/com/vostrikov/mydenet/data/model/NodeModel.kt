package com.vostrikov.mydenet.data.model

import com.google.gson.annotations.Expose

data class NodeModel(
    @Expose(serialize = true, deserialize = true) val id: Int,
    @Expose(serialize = false, deserialize = false) var parent: NodeModel?,
    @Expose(serialize = true, deserialize = true) val childList: MutableList<NodeModel?>,
    @Expose(serialize = true, deserialize = true) val hash: String,
)