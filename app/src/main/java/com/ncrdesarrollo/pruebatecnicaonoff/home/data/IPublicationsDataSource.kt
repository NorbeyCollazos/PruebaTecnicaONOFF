package com.ncrdesarrollo.pruebatecnicaonoff.home.data

import com.ncrdesarrollo.pruebatecnicaonoff.home.data.roomdatabase.PublicationsEntity
import okhttp3.ResponseBody
import retrofit2.Response

interface IPublicationsDataSource {

    suspend fun getPublications(): Response<ResponseBody>

    suspend fun insertPublications(publications: List<PublicationsEntity>)

    suspend fun getPublicationsDatabase(): List<PublicationsEntity>

}



