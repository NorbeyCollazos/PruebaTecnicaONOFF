package com.ncrdesarrollo.pruebatecnicaonoff.home.data.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PublicationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPublications(publications: List<PublicationsEntity>)

    @Query("SELECT * FROM publications")
    suspend fun getPublicationsDatabase(): List<PublicationsEntity>


}