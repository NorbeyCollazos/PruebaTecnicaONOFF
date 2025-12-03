package com.ncrdesarrollo.pruebatecnicaonoff.comments.domain

import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase.CommentsEntity

interface ICommentsInteractor {

    suspend fun insertComment(comment: CommentsEntity): Boolean

    suspend fun getCommentsDatabase(idPublication: Int): List<CommentsEntity>
}