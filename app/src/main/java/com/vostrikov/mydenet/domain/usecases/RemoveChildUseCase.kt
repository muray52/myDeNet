package com.vostrikov.mydenet.domain.usecases

import com.vostrikov.mydenet.data.model.NodeModel
import com.vostrikov.mydenet.domain.Repository

class RemoveChildUseCase(private val repository: Repository) {

    operator fun invoke(currentNode: NodeModel, child: NodeModel) {
        repository.removeChild(currentNode,child)
    }
}