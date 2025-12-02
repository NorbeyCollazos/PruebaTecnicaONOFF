package com.ncrdesarrollo.pruebatecnicaonoff.home.domain

import com.ncrdesarrollo.pruebatecnicaonoff.home.ui.model.Publication
import javax.inject.Inject

class PublicationsInteractor @Inject constructor(private val repository: IPublicationsRepository) :
    IPublicationsInteractor {
    override suspend fun getPublications(): List<Publication> {
        return repository.getPublications()
    }
}