package com.ncrdesarrollo.pruebatecnicaonoff.home.data

import okhttp3.ResponseBody
import retrofit2.Response

interface IPublicationsDataSource {

    suspend fun getPublications(): Response<ResponseBody>
}



