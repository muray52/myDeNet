package com.vostrikov.mydenet.domain

import com.vostrikov.mydenet.data.model.NodeModel

interface Repository {
    fun addChild(currentNode: NodeModel): NodeModel

    fun removeChild(currentNode: NodeModel, child: NodeModel)

    fun getRoot(): NodeModel

    fun saveTree()

    fun loadTree(): NodeModel
}