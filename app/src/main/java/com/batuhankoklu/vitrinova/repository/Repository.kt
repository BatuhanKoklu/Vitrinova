package com.batuhankoklu.vitrinova.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.batuhankoklu.vitrinova.model.BaseResponse
import com.batuhankoklu.vitrinova.network.AssignModels
import com.batuhankoklu.vitrinova.network.HttpRequest
import org.json.JSONArray
import org.json.JSONObject

class Repository : AssignModels() {

    fun getDiscover() : LiveData<BaseResponse>?{

        var headers : ArrayList<Pair<String , String>> = arrayListOf(Pair("", ""))

        var parameters = JSONObject()
        parameters.put("emptyField" , "emptyField")

        var dataString  = HttpRequest(0,"discover" , 0 , 0 ,headers , parameters).execute().get()

        if(dataString != ""){

            var jsonArray = JSONArray(dataString)
            var baseResponse = getDiscoverModel(jsonArray)

            var data = MutableLiveData<BaseResponse>()
            data.value = baseResponse

            return data
        }

        return null

    }

}