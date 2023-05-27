package com.vostrikov.mydenet.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vostrikov.mydenet.data.RepositoryImpl
import com.vostrikov.mydenet.data.model.NodeModel
import com.vostrikov.mydenet.domain.usecases.AddChildUseCase
import com.vostrikov.mydenet.domain.usecases.GetRootNodeUseCase
import com.vostrikov.mydenet.domain.usecases.RemoveChildUseCase
import com.vostrikov.mydenet.domain.usecases.SaveTreeUseCase

class NodesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val saveTreeUseCase = SaveTreeUseCase(repository)
    private val getRootNodeUseCase = GetRootNodeUseCase(repository)
    private val removeChildUseCase = RemoveChildUseCase(repository)
    private val addChildUseCase = AddChildUseCase(repository)

    private val root = getRootNodeUseCase.invoke()
    private val _currentNode = MutableLiveData<NodeModel>()
    val currentNode: LiveData<NodeModel>
        get() = _currentNode


    init {
        _currentNode.value = root
    }

    fun addChild() {
        _currentNode.value?.let {
            addChildUseCase(it)
            _currentNode.value = it
        }
    }

    fun goToRoot() {
        _currentNode.value = root
    }

    fun goToChild(node: NodeModel) {
        _currentNode.value = node
    }

    fun goToParent(): Boolean {
        return if (currentNode.value?.parent == null) {
            false
        } else {
            _currentNode.value = currentNode.value?.parent ?: root
            true
        }
    }

    fun removeChild(child: NodeModel) {
        _currentNode.value?.let {
            removeChildUseCase.invoke(it, child)
            _currentNode.value = it
        }
    }

    fun saveTree() {
        saveTreeUseCase.invoke()
    }

    fun getLastChildPosition(): Int? {
        return currentNode.value?.childList?.lastIndex
    }

}