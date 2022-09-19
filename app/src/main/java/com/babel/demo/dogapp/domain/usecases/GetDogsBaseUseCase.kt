package com.babel.demo.dogapp.domain.usecases

import com.babel.demo.dogapp.domain.model.Dog
import com.babel.demo.dogapp.domain.repository.DogRepository
import com.babel.demo.dogapp.utils.UseCaseResult

class GetDogsBaseUseCase(private val dogRepository: DogRepository) :
    BaseUseCase<Unit, UseCaseResult<List<Dog>>>() {

    override suspend fun useCaseFunction(input: Unit): UseCaseResult<List<Dog>> {
        return runCatching {
            val result = dogRepository.getDogsList().filter {
                it.name.isNotEmpty()
            }
            UseCaseResult.Success(result)
        }.getOrElse { error ->
            UseCaseResult.Error(error)
        }
    }
}




