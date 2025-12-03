package com.ncrdesarrollo.pruebatecnicaonoff.comments.data

import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase.CommentsDao
import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase.CommentsEntity
import javax.inject.Inject

class CommentsDataSource @Inject constructor(private val commentsDao: CommentsDao) : ICommentsDataSource {
    override suspend fun insertComment(comment: CommentsEntity): Boolean {
        val idRow = commentsDao.insertComment(comment)
        return idRow > 0
    }

    override suspend fun getCommentsDatabase(idPublication: Int): List<CommentsEntity> {
        return commentsDao.getPublicationsDatabase(idPublication)
    }
}