package net.japahub.darb

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST

class PredictViewModel: ViewModel() {

    lateinit var serverResponse: MutableLiveData<UServerResponse?>

    init {
        serverResponse = MutableLiveData()
    }

    fun getPredictObservable(): MutableLiveData<UServerResponse?>{
        return serverResponse
    }
    fun makePredict(u: U){
        val retroInstance = RetroInstance.getRetroInstance()
                                         .create(RetroService::class.java)
        val call = retroInstance.prediction(u)
        call.enqueue(object: Callback<UServerResponse?>{

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected exception
             * occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<UServerResponse?>, t: Throwable) {
                serverResponse.postValue(null)

            }

            override fun onResponse(call: Call<UServerResponse?>, response: Response<UServerResponse?>) {
                getPredictObservable()
                if (response.isSuccessful){
                    serverResponse.postValue(response.body())
                    Log.i("TESTING_1","Working")
                    Log.i("Testing_2",""+ response.message())
                    Log.i("Testing_3",""+ response.body().toString())
                    Log.i("Testing_4",""+ response.raw())

                    Log.i("Testing_5",""+ (response.body()?.getPrediction()?: response))

                 } else{
                    serverResponse.postValue(null)
                }
            }

        })
    }
}