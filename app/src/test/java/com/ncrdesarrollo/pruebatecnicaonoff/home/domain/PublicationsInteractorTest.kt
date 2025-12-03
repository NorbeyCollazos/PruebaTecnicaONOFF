package com.ncrdesarrollo.pruebatecnicaonoff.home.domain

import com.ncrdesarrollo.pruebatecnicaonoff.home.ui.model.Publication
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class PublicationsInteractorTest {

    @RelaxedMockK
    private lateinit var repository: IPublicationsRepository

    lateinit var interactor: PublicationsInteractor

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        interactor = PublicationsInteractor(repository)
    }

    @Test
    fun whenDbLocalPublicationsIsEmpty() = runBlocking {

        coEvery { interactor.getPublicationsDatabase() } returns emptyList()

        val response = interactor.getPublicationsDatabase()

        assert(response.isEmpty())

    }

    @Test
    fun whenDbLocalPublicationsIsNotEmpty() = runBlocking {

        val myList = listOf(Publication(1, 1, "Titulo prueba", "body prueba"))
        coEvery { interactor.getPublicationsDatabase() } returns myList

        val response = interactor.getPublicationsDatabase()

        assert(response == myList)

    }

    @Test
    fun whenFromApiPublicationsIsNotEmpty() = runBlocking {

        val myList = listOf(Publication(1, 1, "Titulo prueba", "body prueba"))
        coEvery { interactor.getPublications() } returns myList

        val response = interactor.getPublications()

        assert(response == myList)

    }

    @Test
    fun whenFromApiPublicationsIsEmpty() = runBlocking {

        coEvery { interactor.getPublications() } returns listOf()

        val response = interactor.getPublications()

        assert(response.isEmpty())

    }

}