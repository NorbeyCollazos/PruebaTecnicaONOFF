package com.ncrdesarrollo.pruebatecnicaonoff.home.data.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publications")
data class PublicationsEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")val id: Int,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "body")val body: String
)