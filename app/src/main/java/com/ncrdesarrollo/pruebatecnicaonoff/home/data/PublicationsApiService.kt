package com.ncrdesarrollo.pruebatecnicaonoff.home.data

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface PublicationsApiService {

    @GET("posts")
    suspend fun getPublications(): Response<ResponseBody>

}