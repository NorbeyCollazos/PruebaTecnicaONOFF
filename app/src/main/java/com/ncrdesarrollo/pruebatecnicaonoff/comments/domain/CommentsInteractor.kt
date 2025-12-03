package com.ncrdesarrollo.pruebatecnicaonoff.comments.domain

import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase.CommentsEntity
import javax.inject.Inject

class CommentsInteractor @Inject constructor(private val repository: ICommentsRepository) : ICommentsInteractor {
    override suspend fun insertComment(comment: CommentsEntity): Boolean {
        return repository.insertComment(comment)
    }

    override suspend fun getCommentsDatabase(idPublication: Int): List<CommentsEntity> {
        return repository.getCommentsDatabase(idPublication)
    }
}