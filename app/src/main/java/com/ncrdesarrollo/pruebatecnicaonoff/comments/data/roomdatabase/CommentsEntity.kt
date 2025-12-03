package com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Int,
    @ColumnInfo(name = "idPublication")val idPublication: Int,
    @ColumnInfo(name = "comment")val comment: String
)
