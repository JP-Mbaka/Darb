package net.japahub.darb

import retrofit2.http.Body
import retrofit2.http.POST


//data class U(val data: List<Values>)

data class U(val preg: String?,
                  val plas: String?,
                  val pres: String?,
                  val skin: String?,
                  val insu: String?,
                  val mass: String?,
                  val pedi: String?,
                  val age: String?   )

data class UResponse(val data: List<U>) //U

 class UServerResponse(){
     private lateinit var prediction: String

     public fun getPrediction(): String{
         return prediction
     }
     public fun setPrediction(prediction: String){
         this.prediction = prediction
     }
}
