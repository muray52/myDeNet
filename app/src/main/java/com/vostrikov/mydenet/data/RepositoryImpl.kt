package com.vostrikov.mydenet.data

import android.app.Application
import com.vostrikov.mydenet.data.model.NodeModel
import com.vostrikov.mydenet.data.model.sharedpreferences.SharedPref
import com.vostrikov.mydenet.domain.Repository

class RepositoryImpl(application: Application) : Repository {

    private val sharedPref = SharedPref(application)

    private val root = NodeModel(
        null,
        mutableListOf(), //mutableListOf(NodeModel(null, mutableListOf(), "Example")),
        ROOT
    )

    private var count = 1

    override fun addChild(currentNode: NodeModel): NodeModel {
        currentNode.childList.add(
            NodeModel(null, mutableListOf(), count.toString())
        )
        count++
        return currentNode
    }

    override fun removeChild() {
        TODO("Not yet implemented")
    }

    override fun getRoot(): NodeModel = root

    override fun saveTree() {
        sharedPref.saveTree(root)
    }

    override fun getTree(): NodeModel {
        TODO("Not yet implemented")
    }

    companion object {
        private const val ROOT = "ROOT"
    }
}