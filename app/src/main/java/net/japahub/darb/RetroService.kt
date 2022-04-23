package net.japahub.darb

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetroService {
    @POST("predict")
    @Headers("Accept:application/json",
                    "content-type:application/json",
                    "Authorisation: Bearer 85d15a51-25fc-48d4-b68b-3b0033ca078d")
    fun prediction(@Body params: U): Call<UServerResponse>
}

