package com.vostrikov.mydenet.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NodeModel(
    @Expose(serialize = false)
    @SerializedName("parent") val parent: NodeModel?,
    @SerializedName("childList") val childList: MutableList<NodeModel?>,
    @SerializedName("hash") val hash: String,
)