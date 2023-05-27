package com.vostrikov.mydenet.domain.usecases

import com.vostrikov.mydenet.data.model.NodeModel
import com.vostrikov.mydenet.domain.Repository

class AddChildUseCase(private val repository: Repository) {

    operator fun invoke(currentNode: NodeModel) {
        repository.addChild(currentNode)
    }
}