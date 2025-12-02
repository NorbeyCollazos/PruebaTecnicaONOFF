package com.ncrdesarrollo.pruebatecnicaonoff.home.data

import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class PublicationsDataSource @Inject constructor(private val apiService: PublicationsApiService) :
    IPublicationsDataSource {

    override suspend fun getPublications(): Response<ResponseBody> = apiService.getPublications()
}