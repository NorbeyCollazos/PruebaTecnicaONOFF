package com.ncrdesarrollo.pruebatecnicaonoff.home.di

import com.ncrdesarrollo.pruebatecnicaonoff.core.room.PublicationsDatabase
import com.ncrdesarrollo.pruebatecnicaonoff.home.data.IPublicationsDataSource
import com.ncrdesarrollo.pruebatecnicaonoff.home.data.PublicationsApiService
import com.ncrdesarrollo.pruebatecnicaonoff.home.data.PublicationsDataSource
import com.ncrdesarrollo.pruebatecnicaonoff.home.data.PublicationsRepository
import com.ncrdesarrollo.pruebatecnicaonoff.home.data.roomdatabase.PublicationsDao
import com.ncrdesarrollo.pruebatecnicaonoff.home.domain.IPublicationsInteractor
import com.ncrdesarrollo.pruebatecnicaonoff.home.domain.IPublicationsRepository
import com.ncrdesarrollo.pruebatecnicaonoff.home.domain.PublicationsInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PublicationsModule {

    @Provides
    @Singleton
    fun providesApiPublications(retrofit: Retrofit): PublicationsApiService {
        return retrofit.create(PublicationsApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePublicationsDao(db: PublicationsDatabase) = db.publicationsDao()

    @Provides
    @Singleton
    fun providesDatasource(apiPublications: PublicationsApiService, publicationsDao: PublicationsDao): IPublicationsDataSource =
        PublicationsDataSource(apiPublications, publicationsDao)


    @Provides
    @Singleton
    fun providesRepository(dataSource: IPublicationsDataSource): IPublicationsRepository =
        PublicationsRepository(dataSource)

    @Provides
    @Singleton
    fun providesInteractor(repository: IPublicationsRepository): IPublicationsInteractor =
        PublicationsInteractor(repository)

}