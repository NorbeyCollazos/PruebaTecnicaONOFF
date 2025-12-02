package com.ncrdesarrollo.pruebatecnicaonoff.home.data

import com.ncrdesarrollo.pruebatecnicaonoff.home.data.roomdatabase.PublicationsDao
import com.ncrdesarrollo.pruebatecnicaonoff.home.data.roomdatabase.PublicationsEntity
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class PublicationsDataSource @Inject constructor(
    private val apiService: PublicationsApiService,
    private val publicationsDao: PublicationsDao
) :
    IPublicationsDataSource {

    override suspend fun getPublications(): Response<ResponseBody> = apiService.getPublications()

    override suspend fun insertPublications(publications: List<PublicationsEntity>) =
        publicationsDao.insertPublications(publications)

    override suspend fun getPublicationsDatabase(): List<PublicationsEntity> =
        publicationsDao.getPublicationsDatabase()
}