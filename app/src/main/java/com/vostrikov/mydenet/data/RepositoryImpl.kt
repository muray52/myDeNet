package com.vostrikov.mydenet.data

import android.app.Application
import android.util.Log
import com.vostrikov.mydenet.data.model.NodeModel
import com.vostrikov.mydenet.data.sharedpreferences.SharedPref
import com.vostrikov.mydenet.domain.Repository
import java.lang.Integer.max

class RepositoryImpl(application: Application) : Repository {

    private val sharedPref = SharedPref(application)
    private val root = loadTree()
    private var counter = getMaxId(root)

    override fun addChild(currentNode: NodeModel): NodeModel {
        currentNode.childList.add(
            NodeModel(counter, currentNode, mutableListOf(), counter.toString())
        )
        Log.d(TREE_STATUS, "added new child = $counter")
        counter++
        return currentNode
    }

    override fun removeChild(currentNode: NodeModel, child: NodeModel) {
        currentNode.childList.remove(child)
        Log.d(TREE_STATUS, "removed child ${child.id}")
    }

    override fun getRoot(): NodeModel = root

    override fun saveTree() {
        sharedPref.saveTree(root)
        Log.d(TREE_STATUS, "tree was saved in SharedPrefs")

    }

    override fun loadTree(): NodeModel =
        sharedPref.loadTree()
            ?: NodeModel(
                0,
                null,
                mutableListOf(),
                ROOT
            )


    private fun getMaxId(node: NodeModel): Int {
        var maxId = 1
        for (child in node.childList.filterNotNull()) {
            maxId = max(max(child.id, getMaxId(child)), maxId)
            maxId++
        }

        return maxId
    }

    companion object {
        private const val ROOT = "ROOT"
        private const val TREE_STATUS = "TREE_STATUS"
    }
}