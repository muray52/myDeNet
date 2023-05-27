package com.vostrikov.mydenet.domain

import android.app.Application
import com.vostrikov.mydenet.data.model.NodeModel

interface Repository {
    fun addChild(currentNode: NodeModel): NodeModel

    fun removeChild()

    fun getRoot(): NodeModel

    fun saveTree()

    fun getTree(): NodeModel
}