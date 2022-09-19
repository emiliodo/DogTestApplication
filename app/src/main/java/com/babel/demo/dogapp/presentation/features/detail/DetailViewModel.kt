package com.babel.demo.dogapp.presentation.features.detail

import com.babel.demo.dogapp.domain.model.Dog
import com.babel.demo.dogapp.presentation.base.BaseViewModel
import com.babel.demo.dogapp.utils.SingleLiveEvent

class DetailViewModel : BaseViewModel() {

    val detailData = SingleLiveEvent<Dog>()

    fun loadData(dogResponse: Dog?) {
        detailData.value = dogResponse
    }

}
