package com.ncrdesarrollo.pruebatecnicaonoff.comments.di

import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.CommentsDataSource
import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.CommentsRepository
import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.ICommentsDataSource
import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase.CommentsDao
import com.ncrdesarrollo.pruebatecnicaonoff.comments.domain.CommentsInteractor
import com.ncrdesarrollo.pruebatecnicaonoff.comments.domain.ICommentsInteractor
import com.ncrdesarrollo.pruebatecnicaonoff.comments.domain.ICommentsRepository
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
object CommentsModule {

    @Singleton
    @Provides
    fun provideCommentsDao(db: PublicationsDatabase) = db.commentsDao()

    @Provides
    @Singleton
    fun providesDatasource(commentsDao: CommentsDao): ICommentsDataSource =
        CommentsDataSource(commentsDao)


    @Provides
    @Singleton
    fun providesRepository(dataSource: ICommentsDataSource): ICommentsRepository =
        CommentsRepository(dataSource)

    @Provides
    @Singleton
    fun providesInteractor(repository: ICommentsRepository): ICommentsInteractor =
        CommentsInteractor(repository)

}