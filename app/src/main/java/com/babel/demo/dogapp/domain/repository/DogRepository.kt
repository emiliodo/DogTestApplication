package com.babel.demo.dogapp.domain.repository

import com.babel.demo.dogapp.domain.model.Dog

interface DogRepository {
    suspend fun getDogsList(): List<Dog>
}