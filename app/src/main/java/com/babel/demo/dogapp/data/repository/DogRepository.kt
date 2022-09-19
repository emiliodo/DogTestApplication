package com.babel.demo.dogapp.data.repository

import com.babel.demo.dogapp.data.remote.DogApi
import com.babel.demo.dogapp.domain.model.Dog
import com.babel.demo.dogapp.domain.repository.DogRepository

const val LIMIT = 20
const val PAGES = 20

class DogRepositoryImpl(private val dogApi: DogApi) : DogRepository {

    override suspend fun getDogsList(): List<Dog> =
        dogApi.getDogsAsync(limit = LIMIT, page = PAGES).await().map { dogResponse ->
            dogResponse.toDomainModel()
        }

}
