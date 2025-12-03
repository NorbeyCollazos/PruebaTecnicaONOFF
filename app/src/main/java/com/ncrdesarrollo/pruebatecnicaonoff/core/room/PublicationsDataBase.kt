package com.ncrdesarrollo.pruebatecnicaonoff.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase.CommentsDao
import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase.CommentsEntity
import com.ncrdesarrollo.pruebatecnicaonoff.home.data.roomdatabase.PublicationsDao
import com.ncrdesarrollo.pruebatecnicaonoff.home.data.roomdatabase.PublicationsEntity


@Database(
    entities = [PublicationsEntity::class, CommentsEntity::class],
    version = 1
)
abstract class PublicationsDatabase : RoomDatabase() {
    abstract fun publicationsDao(): PublicationsDao
    abstract fun commentsDao(): CommentsDao
}