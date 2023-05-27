package com.vostrikov.mydenet.data.model.sharedpreferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
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
        val gson = Gson()
        val resString = gson.toJson(root)
        with(sharedPref.edit()) {
            putString(SP_TREE, resString).apply()
        }
    }

    private fun deleteTree() {
        sharedPref.edit().remove(SP_TREE).apply()
    }

    fun getTree(): NodeModel {
        val json = sharedPref.getString(SP_TREE, "") ?: ""
        return Gson().fromJson(json, NodeModel::class.java)
    }

    companion object {
        private const val SP_TREE = "KEY_TREE"
    }
}