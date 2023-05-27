package com.vostrikov.mydenet.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vostrikov.mydenet.data.RepositoryImpl
import com.vostrikov.mydenet.data.model.NodeModel
import com.vostrikov.mydenet.domain.usecases.SaveTreeUseCase

class NodesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val saveTreeUseCase = SaveTreeUseCase(repository)

    private val root = repository.getRoot()
    private val _currentNode = MutableLiveData<NodeModel>()
    val currentNode: LiveData<NodeModel>
        get() = _currentNode


    init {
        _currentNode.value = root
    }

    fun addChild() {
        _currentNode.value?.apply {
            repository.addChild(this)
            _currentNode.value = this
        }
        Log.d("NODE_STATE", "childList size = ${currentNode.value?.childList?.size}")
    }

    fun goToRoot() {
//        _currentNode.value = root
        saveTreeUseCase.invoke()
    }

    fun goToChild(node: NodeModel) {
        _currentNode.value = node
    }

    fun goToParent() {
        _currentNode.value?.parent?.let {
            _currentNode.value = it
        }
//        TODO("toast if null")
    }

    fun removeChild(node: NodeModel) {
        _currentNode.value?.apply {
            this.childList.remove(node)
            _currentNode.value = this
        }
    }

}