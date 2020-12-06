package com.batuhankoklu.vitrinova.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.batuhankoklu.vitrinova.model.BaseResponse
import com.batuhankoklu.vitrinova.model.Featured
import com.batuhankoklu.vitrinova.model.FeaturedResponse
import com.batuhankoklu.vitrinova.repository.Repository

class MainActivityViewModel : ViewModel(){

    var isLoading = MutableLiveData<Boolean>()
    var baseResponse : LiveData<BaseResponse>? = null

    fun getDiscover() : LiveData<BaseResponse>? {
        baseResponse = Repository().getDiscover()
        return baseResponse

    }

}