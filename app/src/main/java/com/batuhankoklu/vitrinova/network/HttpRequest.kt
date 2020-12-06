package com.batuhankoklu.vitrinova.network

import android.os.AsyncTask
import android.util.Log
import com.appollob.teknik10.Network.NetworkHelpers
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

open class HttpRequest(baseUrl : Int = 0, endPoint : String, methodType : Int = 0, contentType : Int = 0, headers : ArrayList<Pair<String,String>>?, parameters : JSONObject) : AsyncTask<String , String , String>() {

    var nh = NetworkHelpers()

    private val base_urls : ArrayList<String> = arrayListOf("https://www.vitrinova.com/api/v2/")
    private val methodTypes : ArrayList<String> = arrayListOf("GET" , "POST")
    private val contentTypes : ArrayList<String> = arrayListOf("application/x-www-form-urlencoded" , "application/json; charset=utf-8" , "multipart/form-data; boundary=myFile")

    var baseUrl = baseUrl
    var endPoint = endPoint
    var methodType = methodType
    var contentType = contentType
    var headers = headers
    var parameters = parameters

    override fun doInBackground(vararg p0: String?): String {

        /*var url = URL(base_urls[baseUrl] + endPoint)

        var connection = url.openConnection() as HttpURLConnection //Web sayfasını okumaya yarar
*/
        val obj = URL(base_urls[baseUrl] + endPoint)
        val con = obj.openConnection() as HttpURLConnection
        con.requestMethod = methodTypes[methodType]
        val responseCode = con.responseCode
        println("Response Code :: $responseCode")
        return if (responseCode == HttpURLConnection.HTTP_OK) { // connection ok
            val `in` =
                BufferedReader(InputStreamReader(con.inputStream))
            var inputLine: String?
            val response = StringBuffer()
            while (`in`.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
            `in`.close()
            response.toString()
        } else {
            ""
        }

        //POST
        /*if(methodType == 0){

            connection.setRequestProperty("Content-Type", contentTypes[contentType])

            connection.requestMethod = methodTypes[methodType]
            connection.readTimeout = 15000
            connection.connectTimeout = 15000
            connection.doOutput = true
            connection.doInput = true

            try {
                var os : InputStream = connection.inputStream.bufferedReader().use { reader ->

                }
                var writer = BufferedWriter(OutputStreamWriter(os,"UTF-8"))
                writer.write(nh.encodeParams(parameters))
                writer.flush()
                writer.close()
                os.close()

                var responseCode = connection.responseCode
                if(responseCode == HttpsURLConnection.HTTP_OK){
                    val inputStream = BufferedReader(InputStreamReader(connection.inputStream)).use {
                        val response = StringBuffer()
                        var inputLine = it.readLine()

                        while(inputLine != null){
                            response.append(inputLine)
                            inputLine =  it.readLine()
                        }

                        return response.toString()
                        it.close()
                    }

                }

                return ""


            }catch (ex : Exception){
                Log.e("HATA" , "Internet bağlantınızı kontrol edin")
            }

        }*/

        return ""

    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

    }






}