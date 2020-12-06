package com.appollob.teknik10.Network

import org.json.JSONObject
import java.net.URLEncoder

class NetworkHelpers {

    @Throws(Exception::class)
    fun encodeParams(params : JSONObject) : String{

        var result : StringBuilder = StringBuilder()
        var first = true
        var itr : Iterator<String> = params.keys()
        while(itr.hasNext()){
            var key = itr.next()
            var value = params.get(key)
            if(first)
                first = false
            else
                result.append("&")

            result.append(URLEncoder.encode(key, "UTF-8"))
            result.append("=")
            result.append(URLEncoder.encode(value.toString(), "UTF-8"))
        }
        return result.toString()
    }



}