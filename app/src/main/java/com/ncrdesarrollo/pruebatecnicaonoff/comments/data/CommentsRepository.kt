package com.ncrdesarrollo.pruebatecnicaonoff.comments.data

import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase.CommentsEntity
import com.ncrdesarrollo.pruebatecnicaonoff.comments.domain.ICommentsRepository
import javax.inject.Inject

class CommentsRepository @Inject constructor(private val dataSource: ICommentsDataSource) :
    ICommentsRepository {
    override suspend fun insertComment(comment: CommentsEntity): Boolean {
        return dataSource.insertComment(comment)
    }

    override suspend fun getCommentsDatabase(idPublication: Int): List<CommentsEntity> {
        return dataSource.getCommentsDatabase(idPublication)
    }
}