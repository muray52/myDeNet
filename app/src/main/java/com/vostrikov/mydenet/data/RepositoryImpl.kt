package com.vostrikov.mydenet.data

import com.vostrikov.mydenet.data.model.NodeModel
import com.vostrikov.mydenet.domain.Repository

class RepositoryImpl : Repository {

    private val root = NodeModel(
        null,
        mutableListOf(NodeModel(null, mutableListOf(),"Example")),
        "ROOT")

    private var count = 1

    override fun addChild(currentNode: NodeModel): NodeModel {
        currentNode.childList.add(
            NodeModel(currentNode, mutableListOf(), count.toString())
        )
        count++
        return currentNode
    }

    override fun removeChild() {
        TODO("Not yet implemented")
    }

    override fun getRoot(): NodeModel = root
}