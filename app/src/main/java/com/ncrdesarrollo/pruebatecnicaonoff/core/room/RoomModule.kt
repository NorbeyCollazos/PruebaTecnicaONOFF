package com.ncrdesarrollo.pruebatecnicaonoff.core.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val PUBLICATIONS_APP = "publications_app"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PublicationsDatabase::class.java, PUBLICATIONS_APP).build()

    @Singleton
    @Provides
    fun provideLocalitiesDao(db: PublicationsDatabase) = db.publicationsDao()


    @Provides
    @Named("dispatcherIO")
    fun provideDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named("dispatcherMain")
    fun provideDispatcherMain(): CoroutineDispatcher = Dispatchers.Main
}