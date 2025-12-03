package com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentsEntity): Long

    @Query("SELECT * FROM comments WHERE idPublication = :idPublication")
    suspend fun getPublicationsDatabase(idPublication: Int): List<CommentsEntity>

}