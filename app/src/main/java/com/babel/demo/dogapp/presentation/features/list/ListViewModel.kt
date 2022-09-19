package com.babel.demo.dogapp.presentation.features.list

import androidx.lifecycle.MutableLiveData
import com.babel.demo.dogapp.domain.model.Dog
import com.babel.demo.dogapp.domain.usecases.GetDogsBaseUseCase
import com.babel.demo.dogapp.presentation.base.BaseViewModel
import com.babel.demo.dogapp.utils.SingleLiveEvent
import com.babel.demo.dogapp.utils.UseCaseResult
import kotlinx.coroutines.launch

class ListViewModel(private val getDogsUseCase: GetDogsBaseUseCase) : BaseViewModel() {

    val showLoading = SingleLiveEvent<Boolean>()
    val dogList = SingleLiveEvent<List<Dog>>()
    val showError = SingleLiveEvent<String>()
    val navigateToDetail = SingleLiveEvent<Dog>()

    init {
        loadDogs()
    }

    internal fun loadDogs() {
        showLoading.value = true
        launch {
            getDogsUseCase.execute(Unit).also { result ->
                showLoading.value = false
                when (result) {
                    is UseCaseResult.Success -> dogList.value = result.data
                    is UseCaseResult.Error -> showError.value = result.exception.message
                }
            }
        }
    }

    fun itemClicked(dogResponse: Dog) {
        navigateToDetail.value = dogResponse
    }
}
