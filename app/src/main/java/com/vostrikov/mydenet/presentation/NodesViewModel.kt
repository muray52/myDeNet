package com.vostrikov.mydenet.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vostrikov.mydenet.data.RepositoryImpl
import com.vostrikov.mydenet.data.model.NodeModel

class NodesViewModel : ViewModel() {
    private val repository = RepositoryImpl()

    private val root = repository.getRoot()

    private val _currentNode = MutableLiveData<NodeModel>()
    val currentNode: LiveData<NodeModel>
        get() = _currentNode

    init{
        _currentNode.value = root
    }

    fun addChild(){
        val test = currentNode.value
        test?.let{
            _currentNode.value = repository.addChild(it)
        }
        Log.d("NODE_STATE", "childList size = ${currentNode.value?.childList?.size}")
    }

    fun goToRoot(){

    }

}