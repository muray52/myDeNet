package com.vostrikov.mydenet.domain.usecases

import com.vostrikov.mydenet.data.model.NodeModel
import com.vostrikov.mydenet.domain.Repository

class GetRootNodeUseCase(private val repository: Repository) {

    operator fun invoke(): NodeModel {
        return repository.getRoot()
    }
}