package com.vostrikov.mydenet.data.sharedpreferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vostrikov.mydenet.data.model.NodeModel

class SharedPref(
    application: Application
) {

    private val sharedPref: SharedPreferences = application.getSharedPreferences(
        application.packageName,
        Context.MODE_PRIVATE
    )

    fun saveTree(root: NodeModel) {
        deleteTree()
        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        val resString = gson.toJson(root)
        with(sharedPref.edit()) {
            putString(SP_TREE, resString).apply()
        }
        loadTree()
    }

    private fun deleteTree() {
        sharedPref.edit().remove(SP_TREE).apply()
    }

    fun loadTree(): NodeModel? {
        val json = sharedPref.getString(SP_TREE, null)
        return if (json == null) {
            null
        } else {
            val rootNode = Gson().fromJson(json, NodeModel::class.java)
            updateParent(rootNode)
            return rootNode
        }
    }

    private fun updateParent(node: NodeModel) {
//        if (node == null) {
//            return
//        }
        for (child in node.childList.filterNotNull()) {
            child.parent = node
            child.also { updateParent(it) }
        }
    }


    companion object {
        private const val SP_TREE = "KEY_TREE"
    }
}