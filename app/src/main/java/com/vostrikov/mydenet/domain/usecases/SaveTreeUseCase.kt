package com.vostrikov.mydenet.domain.usecases

import com.vostrikov.mydenet.domain.Repository

class SaveTreeUseCase(private val repository: Repository) {

    operator fun invoke() {
        repository.saveTree()
    }
}