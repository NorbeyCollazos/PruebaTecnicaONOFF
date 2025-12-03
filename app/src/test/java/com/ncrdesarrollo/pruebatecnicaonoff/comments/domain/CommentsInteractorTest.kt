package com.ncrdesarrollo.pruebatecnicaonoff.comments.domain

import com.ncrdesarrollo.pruebatecnicaonoff.comments.data.roomdatabase.CommentsEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CommentsInteractorTest {

    @RelaxedMockK
    private lateinit var repository: ICommentsRepository

    private lateinit var interactor: CommentsInteractor

    private val idPublication = 1

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        interactor = CommentsInteractor(repository)
    }

    @Test
    fun whenDbLocalListCommentsIsEmpty() = runBlocking {

        coEvery { interactor.getCommentsDatabase(idPublication) } returns emptyList()

        val response = interactor.getCommentsDatabase(idPublication)

        assert(response.isEmpty())

    }

    @Test
    fun whenDbLocalListCommentsIsNotEmpty() = runBlocking {

        val myList = listOf(CommentsEntity(1, idPublication, "Esto es un comentario de prueba"))
        coEvery { interactor.getCommentsDatabase(idPublication) } returns myList

        val response = interactor.getCommentsDatabase(idPublication)

        assert(response == myList)

    }
}