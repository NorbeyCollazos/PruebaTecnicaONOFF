package com.ncrdesarrollo.pruebatecnicaonoff.comments.data

import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase.CommentsEntity

interface ICommentsDataSource {

    suspend fun insertComment(comment: CommentsEntity): Boolean

    suspend fun getCommentsDatabase(idPublication: Int): List<CommentsEntity>

}


